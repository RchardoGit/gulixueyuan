package com.atguigu.edu.dao;

import com.atguigu.edu.enties.EduCourse;
import com.atguigu.edu.enties.vo.CoursePublishVO;
import com.atguigu.edu.enties.vo.CourseWebVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author kly
 * @since 2022-01-19
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    CoursePublishVO selectCoursePublish(String courseId);

    CourseWebVO selectWebInfo(String courseId);
}
