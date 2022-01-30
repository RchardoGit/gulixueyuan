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
 * 课程科目
 * </p>
 *
 * @author kly
 * @since 2022-01-18
 */
@Getter
@Setter
@TableName("edu_subject")
@ApiModel(value = "EduSubject对象", description = "课程科目")
public class EduSubject extends Model<EduSubject> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("课程类别ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("类别名称")
    @TableField("title")
    private String title;

    @ApiModelProperty("父ID")
    @TableField("parent_id")
    private String parentId;

    @ApiModelProperty("排序字段")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty("创建时间")
    @TableField(value = "gmt_create" ,fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty("更新时间")
    @TableField(value = "gmt_modified",fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
