package com.einmeer.service;

import com.einmeer.entity.SysFile;
import com.baomidou.mybatisplus.extension.service.IService;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * 文件表 服务类
 * </p>
 *
 * @author 芊嵛
 * @since 2024-03-09
 */
public interface SysFileService extends IService<SysFile> {
    // 查询文件是否存在，根据MD5,size,类型
    SysFile get(String md5,long size,String contentType);
    // 文件上传服务器，并返回上传后的路径
    String upload(MultipartFile file, String bucket) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;
}
