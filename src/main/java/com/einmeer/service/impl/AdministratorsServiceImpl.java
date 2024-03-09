package com.einmeer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.einmeer.config.MyException;
import com.einmeer.entity.Administrators;
import com.einmeer.mapper.AdministratorsMapper;
import com.einmeer.service.AdministratorsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.einmeer.service.SysFileService;
import io.minio.errors.*;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author 芊嵛
 * @since 2024-03-06
 */
@Service
public class AdministratorsServiceImpl extends ServiceImpl<AdministratorsMapper, Administrators> implements AdministratorsService {
    // 自己的mapper
    @Resource
    AdministratorsMapper administratorsMapper;
    // 加密
    @Resource
    BCryptPasswordEncoder passwordEncoder;
    // SysFile的service
    @Resource
    SysFileService sysFileService;

    /**
     * 用户登录
     *
     * @param administratorsUsername
     * @param administratorsPassword
     * @return
     */
    @Override
    public Administrators login(String administratorsUsername, String administratorsPassword) {
        QueryWrapper<Administrators> wrapper = new QueryWrapper<>();
        wrapper.eq("administrators_username", administratorsUsername);
        Administrators administrators = this.getOne(wrapper);
        if (null == administrators) {
            throw new MyException("用户名错误");
        }
        if (!passwordEncoder.matches(administratorsPassword, administrators.getAdministratorsPassword())) {
            throw new MyException("密码错误");
        }
        return administrators;
    }

    /**
     * 查询全部管理员信息并分页
     *
     * @param page
     * @return
     */
    @Override
    public IPage<Administrators> queryAllByAAP(Page<?> page) {
        return administratorsMapper.selectAllByAAP(page);
    }

    /**
     * 根据id查询一条管理员信息
     *
     * @param administratorsId
     * @return
     */
    @Override
    public Administrators queryOneInfo(Integer administratorsId) {
        return administratorsMapper.selectById(administratorsId);
    }


    /**
     * 创建管理员
     *
     * @param administrators
     * @return
     */
    @Override
    public boolean createAdministrators(Administrators administrators) {
        // 默认密码
        administrators.setAdministratorsPassword(passwordEncoder.encode("123456"));
        // 默认头像
        administrators.setAdministratorsPicture("http://192.168.21.130:9000/image/default.jfif");
        return this.save(administrators);
    }

    /**
     * 修改管理员头像
     *
     * @return
     */
    @Override
    public int changePicture(Administrators administrators, MultipartFile file) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String path = sysFileService.upload(file, "image");
        administrators.setAdministratorsPicture(path);
        return administratorsMapper.updateById(administrators);
    }


}
