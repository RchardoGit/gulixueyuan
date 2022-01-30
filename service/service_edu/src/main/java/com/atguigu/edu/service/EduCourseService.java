package com.atguigu.edu.service;

import com.atguigu.edu.enties.EduCourse;
import com.atguigu.edu.enties.vo.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author kly
 * @since 2022-01-19
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourse(CourseInfoVO courseInfoVO);

    void updateCourse(CourseInfoVO courseInfoVO);

    CourseInfoVO getCourseInfoById(String courseId);

    CoursePublishVO getCoursePublish(String id);

    Boolean publishCourseById(String id);

    Page<EduCourse> pageQuery(Page<EduCourse> page, EduCourseQuery eduCourseQuery);

    Boolean removeCourseById(String courseId);

    List<EduCourse> getCourseListByLimit();

    List<EduCourse> getCouresListByTeacherId(String teacherId);

    Map<String, Object> coursePageWeb(Page<EduCourse> coursePage, CourseFrontQueryVO courseFrontQueryVO);

    CourseWebVO getCourseWebInfoById(String courseId);

    void updateCourseViewCount(String courseId);
}
