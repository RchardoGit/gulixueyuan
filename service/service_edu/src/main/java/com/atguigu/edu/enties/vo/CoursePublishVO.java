package com.atguigu.edu.enties.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author konglingyang
 * @date 2022/1/21 20:58
 */
@ApiModel(value = "课程发布消息")
@Data
public class CoursePublishVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
