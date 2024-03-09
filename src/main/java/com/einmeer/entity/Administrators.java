package com.einmeer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.*;

/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author 芊嵛
 * @since 2024-03-06
 */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
public class Administrators implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "administrators_id", type = IdType.AUTO)
    private Integer administratorsId;

    /**
     * 姓名
     */
    private String administratorsName;

    /**
     * 电话
     */
    private String administratorsPhone;

    /**
     * 微信
     */
    @TableField(value = "administrators_weChat")
    private String administratorsWechat;

    /**
     * 邮箱
     */
    private String administratorsEmail;

    /**
     * 权限
     */
    @TableField(value = "administrators_purviewId")
    private Integer administratorsPurviewId;

    /**
     * 账号
     */
    private String administratorsUsername;

    /**
     * 密码
     */
    private String administratorsPassword;

    /**
     * 头像
     */
    private String administratorsPicture;

    /**
     * 状态
     */
    private Integer administratorsState;

    /**
     * 职务
     */
    private String administratorsDuties;

    /**
     * 权限名称
     */
    @TableField(exist = false)
    private String administratorsPurviewNameNoSql;
}
