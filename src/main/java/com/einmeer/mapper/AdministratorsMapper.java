package com.einmeer.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.einmeer.entity.Administrators;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 管理员表 Mapper 接口
 * </p>
 *
 * @author 芊嵛
 * @since 2024-03-06
 */
public interface AdministratorsMapper extends BaseMapper<Administrators> {
    // 管理查询管理员与权限表
    IPage<Administrators> selectAllByAAP(Page<?> page);

}
