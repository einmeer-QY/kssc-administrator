package com.einmeer.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.einmeer.entity.Administrators;
import com.baomidou.mybatisplus.extension.service.IService;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author 芊嵛
 * @since 2024-03-06
 */
public interface AdministratorsService extends IService<Administrators> {
    // 用户登录
    Administrators login(String administratorsUsername,String administratorsPassword);
    // 管理员和权限表联合查询
    IPage<Administrators> queryAllByAAP(Page<?> page);
    // 根据id查询整个管理员信息
    Administrators queryOneInfo(Integer administratorsId);
    // 创建管理员
    boolean createAdministrators(Administrators administrators);
    // 根据id修改用户头像
    int changePicture(Administrators administrators, MultipartFile file) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;

}
