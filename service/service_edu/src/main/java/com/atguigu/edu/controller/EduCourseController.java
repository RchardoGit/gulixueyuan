package com.atguigu.edu.controller;


import com.atguigu.commonutils.CommonResult;
import com.atguigu.commonutils.JwtUtils;
import com.atguigu.edu.client.OrderClient;
import com.atguigu.edu.enties.EduCourse;
import com.atguigu.edu.enties.vo.*;
import com.atguigu.edu.service.EduChapterService;
import com.atguigu.edu.service.EduCourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author kly
 * @since 2022-01-19
 */
@Api(value = "课程")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/course")
public class EduCourseController {

    @Resource
    private EduCourseService eduCourseService;
    @Resource
    private EduChapterService eduChapterService;
    @Resource
    private OrderClient orderClient;


    @PostMapping(value = "saveCourse")
    public CommonResult addCourse(@RequestBody CourseInfoVO courseInfoVO) {

        String courseId =eduCourseService.saveCourse(courseInfoVO);
        return CommonResult.ok().data("courseId",courseId);
    }

    @PostMapping(value = "update")
    public CommonResult updateCourse(@RequestBody CourseInfoVO courseInfoVO) {
        eduCourseService.updateCourse(courseInfoVO);
        return CommonResult.ok();
    }

    @GetMapping(value = "getCourse/{id}")
    public CommonResult getCourseById(@PathVariable String id) {
       CourseInfoVO courseInfoVO = eduCourseService.getCourseInfoById(id);
        return CommonResult.ok().data("courseInfoVO",courseInfoVO);
    }

    @GetMapping(value = "getCoursePublish/{id}")
    public CommonResult getCoursePublish(@PathVariable String id) {
        CoursePublishVO coursePublishVO = eduCourseService.getCoursePublish(id);
        return CommonResult.ok().data("coursePublish",coursePublishVO);
    }

    @ApiOperation(value = "根据id发布课程")
    @PutMapping("publishCourse/{id}")
    public CommonResult publishCourseById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){

        eduCourseService.publishCourseById(id);
        return CommonResult.ok();
    }

    @PostMapping(value = "/get/page/condition/{current}/{size}")
    public CommonResult getCoursePageCondition(@PathVariable Long current,
                                               @PathVariable Long size,
                                               @RequestBody(required = false)EduCourseQuery eduCourseQuery) {
        // 创建page
        Page<EduCourse> page = new Page<>(current, size);
        // 条件查询
        Page<EduCourse> coursePage = eduCourseService.pageQuery(page, eduCourseQuery);
        long total = coursePage.getTotal();
        System.out.println(total);
        List<EduCourse> courseList = coursePage.getRecords();

        return CommonResult.ok().data("total",total).data("items",courseList);
    }
    @DeleteMapping(value = "delete/{courseId}")
    public CommonResult removeCourseById(@PathVariable String courseId) {
        Boolean result = eduCourseService.removeCourseById(courseId);
        if(result) {
            return CommonResult.ok();
        } else {
            return CommonResult.error().message("删除课程失败");
        }
    }
    @PostMapping(value = "/get/page/condition/front/{current}/{size}")
    public CommonResult getPageFrontCourseList(@PathVariable Long current,
                                               @PathVariable Long size,
                                               @RequestBody(required = false) CourseFrontQueryVO courseFrontQueryVO) {

        Page<EduCourse> coursePage = new Page<>(current,size);
        Map<String,Object> map = eduCourseService.coursePageWeb(coursePage,courseFrontQueryVO);
        return CommonResult.ok().data(map);
    }

    @GetMapping(value = "{courseId}")
    public CommonResult getCourseChapterWebById(@PathVariable String courseId, HttpServletRequest request) {
        // 查询课程信息
        CourseWebVO courseWebVO = eduCourseService.getCourseWebInfoById(courseId);
        // 根据课程编号查询章节
        List<ChapterVO> chapterVideoList = eduChapterService.getChapterVideoById(courseId);
        // 获取订单情况
        Boolean isBuy = orderClient.getOrderByCourseAndUserId(courseId, JwtUtils.getMemberIdByJwtToken(request));

        return CommonResult.ok().data("course",courseWebVO).data("chapterVideoList",chapterVideoList).data("isBuy",isBuy);
    }

    @GetMapping(value = "getDto/{courseId}")
    public CourseWebVO getCourseInfoById(@PathVariable String courseId) {
        CourseWebVO courseWebVO = eduCourseService.getCourseWebInfoById(courseId);
        return courseWebVO;
    }





}

