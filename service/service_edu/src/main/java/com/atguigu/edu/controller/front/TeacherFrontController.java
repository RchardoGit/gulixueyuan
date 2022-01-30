package com.atguigu.edu.controller.front;

import com.atguigu.commonutils.CommonResult;
import com.atguigu.edu.enties.EduCourse;
import com.atguigu.edu.enties.EduTeacher;
import com.atguigu.edu.service.EduCourseService;
import com.atguigu.edu.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author konglingyang
 * @date 2022/1/26 19:56
 */
@RequestMapping(value = "/eduservice/front/teacher")
@RestController
public class TeacherFrontController {

    @Resource
    private EduTeacherService eduTeacherService;
    @Resource
    private EduCourseService eduCourseService;

    @GetMapping(value = "getList/{current}/{size}")
    public CommonResult pageFrontTeacherList(@PathVariable Long current, @PathVariable Long size) {
        Page<EduTeacher> eduTeacherPage = new Page<>(current, size);
        Map<String,Object> map = eduTeacherService.getTeacherListWeb(eduTeacherPage);
        return CommonResult.ok().data(map);
    }

    @GetMapping(value = "getTeacherAndCourse/{teacherId}")
    public CommonResult getTeacherAndCourse(@PathVariable String teacherId) {
        // 根据讲师id获取讲师信息
        EduTeacher teacher = eduTeacherService.getById(teacherId);
        List<EduCourse> courseList = eduCourseService.getCouresListByTeacherId(teacherId);
        // 根据讲师id获取course列表
        return CommonResult.ok().data("teacher",teacher).data("courseList",courseList);
    }
}
