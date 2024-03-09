package com.einmeer.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.einmeer.entity.Administrators;
import com.einmeer.service.AdministratorsService;
import com.einmeer.vo.ResultJson;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Wrapper;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author 芊嵛
 * @since 2024-03-06
 */
@RestController
@RequestMapping("/administrators")
public class AdministratorsController {
    @Resource
    AdministratorsService administratorsService;

    @GetMapping("/login")
    ResultJson login(@RequestParam("administratorsUsername") String administratorsUsername,@RequestParam("administratorsPassword") String administratorsPassword){
        return ResultJson.success(administratorsService.login(administratorsUsername, administratorsPassword),"登录成功");
    }

    /**
     * 查询全部
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    ResultJson list(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {
        return ResultJson.success(administratorsService.queryAllByAAP(new Page<>(pageNo, pageSize)));
    }

    /**
     * 根据id查询一条
     * @param administratorsId
     * @return
     */
    @GetMapping("/oneInfo")
    ResultJson OneInfo(@RequestParam("administratorsId") Integer administratorsId) {
        return ResultJson.success(administratorsService.queryOneInfo(administratorsId), "根据id查询一条信息");
    }

    /**
     * 创建管理员
     * @param administrators
     * @return
     */
    @PostMapping("/create")
    ResultJson create(@RequestBody Administrators administrators) {
        System.out.println("输出的信息" + administrators);
        return ResultJson.success(administratorsService.createAdministrators(administrators), "添加成功");
    }

    /**
     * 根基id修改管理员头像
     * @param administrators
     * @param file
     * @return
     * @throws ServerException
     * @throws InsufficientDataException
     * @throws ErrorResponseException
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws InvalidResponseException
     * @throws XmlParserException
     * @throws InternalException
     */
    @PutMapping("/Picture")
    ResultJson updatePicture(Administrators administrators, @RequestParam("file") MultipartFile file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        administrators.setAdministratorsId(9);

        // 存本地
//        file.transferTo(new File("g:/aaa.jpg"));

        int n = administratorsService.changePicture(administrators,file);
        if (0 == n) {
            return ResultJson.failed(n, "修改失败，无此用户id");
        } else if (1 == n) {
            return ResultJson.success(n, "更新成功");
        }
        return ResultJson.failed("更新失败");
    }


}
