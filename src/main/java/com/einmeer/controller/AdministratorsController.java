package com.einmeer.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.einmeer.entity.Administrators;
import com.einmeer.service.AdministratorsService;
import com.einmeer.util.MyUtil;
import com.einmeer.vo.ResultJson;
import io.minio.errors.*;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

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

    /**
     * 登录
     *
     * @param administratorsUsername
     * @param administratorsPassword
     * @return
     */
    @GetMapping("/login")
    ResultJson login(@RequestParam("administratorsUsername") String administratorsUsername, @RequestParam("administratorsPassword") String administratorsPassword) {
        Administrators administrators = administratorsService.login(administratorsUsername, administratorsPassword);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name", administrators.getAdministratorsName());
        hashMap.put("token", MyUtil.getJWT(administrators));
        return ResultJson.success(hashMap, "登录成功");
    }

    /**
     * 查询全部
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    ResultJson list(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {
        return ResultJson.success(administratorsService.queryAllByAAP(new Page<>(pageNo, pageSize)));
    }

    /**
     * 根据id查询一条关联
     *
     * @param overallId
     * @return
     */
    @GetMapping("/oneInfo")
    ResultJson OneInfo(@RequestParam("overallId") Integer overallId) {
        return ResultJson.success(administratorsService.queryOneInfo(overallId), "根据id查询一条信息");
    }

    /**
     * 根据id查询一条不关联
     *
     * @param administratorsId
     * @return
     */
    @GetMapping("/oneInfoNo")
    ResultJson OneInfoNo(@RequestParam("administratorsId") Integer administratorsId) {
        Administrators administrators = administratorsService.queryOneInfoNo(administratorsId);
        if (null == administrators) {
            return ResultJson.failed("查询为空");
        }
        return ResultJson.success(administrators, "查询成功");
    }

    /**
     * 创建管理员
     *
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
     *
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
    ResultJson updatePicture(Administrators administrators, @RequestParam("file") MultipartFile file, @RequestParam("overallId") Integer overallId) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        administrators.setAdministratorsId(overallId);

        // 存本地
//        file.transferTo(new File("g:/aaa.jpg"));

        int n = administratorsService.changePicture(administrators, file);
        if (0 == n) {
            return ResultJson.failed(n, "修改失败，无此用户id");
        } else if (1 == n) {
            return ResultJson.success(n, "更新成功");
        }
        return ResultJson.failed("更新失败");
    }

    // 修改个人信息
    @PutMapping("/changeMyInfo")
    ResultJson changeMyInfo(@RequestBody Administrators administrators) {
        int n = administratorsService.changeMyInfo(administrators);
        if (n == 1) {
            return ResultJson.success("修改成功");
        }
        return ResultJson.failed("修改失败");

    }

    /**
     * 修改一个管理员信息
     *
     * @param administrators
     * @return
     */
    @PutMapping("/changeAdministrator")
    ResultJson changeAdministrator(@RequestBody Administrators administrators) {
        int n = administratorsService.changeAdministrator(administrators);
        if (n == 1) {
            return ResultJson.success("修改成功");
        }
        return ResultJson.failed("修改失败");
    }

    /**
     * 修改状态
     *
     * @param administrators
     * @return
     */
    @PutMapping("/changeAdministratorsState")
    ResultJson changeAdministratorsState(@RequestBody Administrators administrators) {
        int n = administratorsService.changeAdministratorsState(administrators);
        if (0 == n) {
            return ResultJson.failed("更新失败");
        } else {
            return ResultJson.success("更新成功");
        }
    }

}
