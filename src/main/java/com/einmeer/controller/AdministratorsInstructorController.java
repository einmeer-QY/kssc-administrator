package com.einmeer.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.einmeer.service.AdministratorsInstructorService;
import com.einmeer.vo.ResultJson;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 指导教师表 前端控制器
 * </p>
 *
 * @author 芊嵛
 * @since 2024-03-05
 */
@RestController
@RequestMapping("/instructor")
public class AdministratorsInstructorController {
    @Resource
    AdministratorsInstructorService administratorsInstructorService;

    @GetMapping("/index")
    ResultJson list(@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize) throws InterruptedException {
        // 模拟慢
//        TimeUnit.SECONDS.sleep(2);
       return ResultJson.success(administratorsInstructorService.page(new Page<>(pageNo,pageSize)));
    }
}
