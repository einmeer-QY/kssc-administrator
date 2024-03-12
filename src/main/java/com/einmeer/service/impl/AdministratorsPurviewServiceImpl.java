package com.einmeer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.einmeer.entity.AdministratorsPurview;
import com.einmeer.mapper.AdministratorsPurviewMapper;
import com.einmeer.service.AdministratorsPurviewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色权限 服务实现类
 * </p>
 *
 * @author 芊嵛
 * @since 2024-03-12
 */
@Service
public class AdministratorsPurviewServiceImpl extends ServiceImpl<AdministratorsPurviewMapper, AdministratorsPurview> implements AdministratorsPurviewService {

    @Resource
    AdministratorsPurviewMapper administratorsPurviewMapper;
    @Override
    public List<AdministratorsPurview> queryPurview() {
        QueryWrapper<AdministratorsPurview> wrapper = new QueryWrapper<>();
        wrapper.select("purview_id","purview_name");
        return administratorsPurviewMapper.selectList(wrapper);
    }
}
