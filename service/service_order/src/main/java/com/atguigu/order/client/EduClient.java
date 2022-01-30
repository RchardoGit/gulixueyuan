package com.atguigu.order.client;


import com.atguigu.commonutils.CourseWebVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author konglingyang
 * @date 2022/1/27 13:25
 */
@FeignClient(value = "service-edu")
public interface EduClient {

    @GetMapping(value = "/eduservice/course/getDto/{courseId}")
    CourseWebVO getCourseInfoById(@PathVariable("courseId") String courseId);

}
