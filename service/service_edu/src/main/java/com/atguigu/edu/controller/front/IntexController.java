package com.atguigu.edu.controller.front;

import com.atguigu.commonutils.CommonResult;
import com.atguigu.edu.enties.EduCourse;
import com.atguigu.edu.enties.EduTeacher;
import com.atguigu.edu.service.EduCourseService;
import com.atguigu.edu.service.EduTeacherService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author konglingyang
 * @date 2022/1/24 0:09
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice")
public class IntexController {

    @Resource
    private EduTeacherService eduTeacherService;
    @Resource
    private EduCourseService eduCourseService;

    @GetMapping(value ="index" )
    public CommonResult index() {

        // 查询八名热门讲师
        List<EduTeacher> teacherList = eduTeacherService.getTeacherListByLimit();
        // 查询八名热门课程
        List<EduCourse> courseList = eduCourseService.getCourseListByLimit();

        return CommonResult.ok().data("courseList",courseList).data("teacherList",teacherList);
    }
}
