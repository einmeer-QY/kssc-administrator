package com.einmeer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.einmeer.entity.SysFile;
import com.einmeer.mapper.SysFileMapper;
import com.einmeer.service.SysFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * 文件表 服务实现类
 * </p>
 *
 * @author 芊嵛
 * @since 2024-03-09
 */
@Service
public class SysFileServiceImpl extends ServiceImpl<SysFileMapper, SysFile> implements SysFileService {
    // 获取配置文件中的内容,前提这类要在IOC容器里
    @Value("${minio.username}")
    String username;
    @Value("${minio.password}")
    String password;
    @Value("${minio.endpoint}")
    String endpoint;


    /**
     * 查询文件是否存在，根据 md5 size contentType
     *
     * @param md5
     * @param size
     * @param contentType
     * @return
     */
    @Override
    public SysFile get(String md5, long size, String contentType) {
        System.out.println("打印"+contentType);
        QueryWrapper<SysFile> wrapper = new QueryWrapper<>();
        wrapper.eq("md5", md5)
                .eq("size", size)
                .eq("content_type", contentType);

        return this.getOne(wrapper);
    }

    /**
     * 文件上传服务器，并返回上传后的路径
     *
     * @param file
     * @param bucket
     * @return
     */
    @Override
    public String upload(MultipartFile file, String bucket) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        // 获取md5 大小 类型
        String md5 = DigestUtils.md5Hex(file.getInputStream());
        long size = file.getSize();
        String contentType = file.getContentType();
        // 判断是否上传过
        SysFile sysFile = this.get(md5, size, contentType);
        if (null != sysFile) {
            // 如果不是空指针说明上传过，直接把路径返回
            return sysFile.getPath();
        }
        // 如果没有上传过，需要上传
        // 解决图片重名，采用时间戳，精确到毫秒
        StringBuilder name = new StringBuilder();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        // 拼接：时间戳+随机6个数字+名字.类型
        name
                .append(timestamp)
                // 万一同一毫秒有多个人同时上传，也可能重名，再生成6个随机数
                .append(RandomStringUtils.random(6, false, true))
                // 拼名字
                .append(file.getOriginalFilename());
        /**
         *          // 如果不想要名字就是用时间戳+随机6个数字+.+类型，可以把上面的拼名字换成下面的拼后缀名
         *          //获取后缀名
         *          String extension = FilenameUtils.getExtension(file.getOriginalFilename());
         *          name.append(".")
         *                  .append(extension);
         */


        // 创建客户端
        MinioClient client = MinioClient.builder()
                .credentials(username, password)
                .endpoint(endpoint)
                .build();

        // 创建上传参数对象
        PutObjectArgs args = PutObjectArgs.builder()
                // 设置上传文件的桶
                .bucket(bucket)
                // 设置上传文件的类型，应该和前端给后端的类型一致
                .contentType(file.getContentType())
                // 设置上传文件的名称
                .object(name.toString())
                // 上传文件的字节流
                .stream(file.getInputStream(), file.getSize(), 0)
                .build();

        // 通过客户端上传
        client.putObject(args);
        // 定义返回路径
        StringBuilder path = new StringBuilder();
        path
                .append(endpoint)
                .append("/")
                .append(bucket)
                .append("/")
                .append(name);
        // 存入数据库
        sysFile = new SysFile(null,md5,size,contentType,path.toString(),bucket);
        this.save(sysFile);
        return path.toString();
    }
}
