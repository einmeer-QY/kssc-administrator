package com.einmeer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.*;

/**
 * <p>
 * 指导教师表
 * </p>
 *
 * @author 芊嵛
 * @since 2024-03-05
 */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
@TableName("administrators_instructor")
public class AdministratorsInstructor implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * instructor的id
     */
    @TableId(value = "instructor_id", type = IdType.AUTO)
    private Long instructorId;

    /**
     * 指导教师姓名
     */
    private String instructorName;

    /**
     * 工号
     */
    private String instructorJobNumber;

    /**
     * 学校名
     */
    private String instructorSchool;

    /**
     * 教师职务
     */
    private String instructorPosition;

    /**
     * 教师电话
     */
    private String instructorPhone;

    /**
     * 教师指导部落数量
     */
    private Integer instructorTribes;

    /**
     * 教师指导时长（分钟）
     */
    private Long instructorActivityTimes;

    /**
     * 教师指导活动数量
     */
    private Integer instructorActivityNumbers;

    /**
     * 教师指导活动总人数
     */
    private Integer instructorNumberOfParticipants;

    /**
     * 删除状态0正常1停用
     */
    private Integer instructorState;
}
