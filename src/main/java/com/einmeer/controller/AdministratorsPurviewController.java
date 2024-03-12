package com.einmeer.controller;

import com.einmeer.entity.AdministratorsPurview;
import com.einmeer.service.AdministratorsPurviewService;
import com.einmeer.vo.ResultJson;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 角色权限 前端控制器
 * </p>
 *
 * @author 芊嵛
 * @since 2024-03-12
 */
@RestController
@RequestMapping("/administratorsPurview")
public class AdministratorsPurviewController {
    @Resource
    AdministratorsPurviewService administratorsPurviewService;

    @GetMapping("/queryIAN")
    public ResultJson queryIAN(){
        List<AdministratorsPurview> administratorsPurviews = administratorsPurviewService.queryPurview();
        if (administratorsPurviews.isEmpty()){
            return ResultJson.failed("未查询到");
        }
        return  ResultJson.success(administratorsPurviews,"查询成功");
    }

}
