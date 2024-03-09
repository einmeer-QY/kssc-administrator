package com.einmeer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.*;

/**
 * <p>
 * 文件表
 * </p>
 *
 * @author 芊嵛
 * @since 2024-03-09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_file")
public class SysFile implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 文件md5
     */
    private String md5;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 文件类型
     */
    private String contentType;

    /**
     * 路径
     */
    private String path;

    /**
     * 桶
     */
    private String buckets;
}
