package com.atguigu.edu.enties;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 课程简介
 * </p>
 *
 * @author kly
 * @since 2022-01-19
 */
@Getter
@Setter
@TableName("edu_course_description")
@ApiModel(value = "EduCourseDescription对象", description = "课程简介")
public class EduCourseDescription extends Model<EduCourseDescription> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("课程ID")
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    @ApiModelProperty("课程简介")
    @TableField("description")
    private String description;

    @ApiModelProperty("创建时间")
    @TableField(value = "gmt_create",fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty("更新时间")
    @TableField(value = "gmt_modified",fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
