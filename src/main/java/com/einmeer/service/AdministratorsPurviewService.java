package com.einmeer.service;

import com.einmeer.entity.AdministratorsPurview;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色权限 服务类
 * </p>
 *
 * @author 芊嵛
 * @since 2024-03-12
 */
public interface AdministratorsPurviewService extends IService<AdministratorsPurview> {
    // 查询权限id和名称用来创建修改管理员
    List<AdministratorsPurview> queryPurview();
}
