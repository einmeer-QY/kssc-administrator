package com.einmeer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.*;

/**
 * <p>
 * 角色权限
 * </p>
 *
 * @author 芊嵛
 * @since 2024-03-12
 */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
@TableName("administrators_purview")
public class AdministratorsPurview implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限id
     */
    @TableId(value = "purview_id", type = IdType.AUTO)
    private Integer purviewId;

    /**
     * 权限名称
     */
    private String purviewName;

    /**
     * 创建人
     */
    private String purviewAdministrators1;

    /**
     * 创建时间
     */
    private LocalDateTime purviewTime1;

    /**
     * 修改人
     */
    private String purviewAdministrators2;

    /**
     * 最后修改时间
     */
    private LocalDateTime purviewTime2;

    /**
     * 状态0开启1关闭
     */
    private Integer purviewState;
}
