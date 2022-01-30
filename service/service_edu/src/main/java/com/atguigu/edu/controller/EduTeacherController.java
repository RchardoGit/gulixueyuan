package com.atguigu.edu.controller;


import com.atguigu.commonutils.CommonResult;
import com.atguigu.edu.enties.EduTeacher;
import com.atguigu.edu.enties.vo.EduTeacherQuery;
import com.atguigu.edu.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author kly
 * @since 2022-01-14
 */
@Api(value = "讲师管理")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Resource
    private EduTeacherService teacherService;

    /**
     * 获取所有讲师
     * @return
     */
    @ApiOperation(value = "所有讲师列表")
    @GetMapping(value = "/getAll")
    public CommonResult getAllTeacher() {
        List<EduTeacher> teacherList = teacherService.list(null);
        return CommonResult.ok().data("items", teacherList);
    }

    /**
     * 根据id逻辑化删除讲师
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id删除讲师")
    @DeleteMapping(value = "/delete/{id}")
    public CommonResult removeTeacherById(@ApiParam(name = "id",value = "讲师id",required = true) @PathVariable("id") String id) {
        boolean flag = teacherService.removeById(id);
        if(flag) {
            return CommonResult.ok();
        } else {
            return CommonResult.error();
        }
    }

    /**
     * 分页查询讲师
     * @param current
     * @param size
     * @return
     */
    @GetMapping(value = "/getAll/page/{current}/{size}")
    public CommonResult getAllTeacherPage(@PathVariable Long current,
                                          @PathVariable Long size){
        Page<EduTeacher> page = new Page<>(current, size);
        Page<EduTeacher> teacherPage = teacherService.page(page);
        long total = teacherPage.getTotal();
        List<EduTeacher> teacherList = teacherPage.getRecords();
        return CommonResult.ok().data("total",total).data("items",teacherList);
    }


    /**
     * 根据条件分页查询
     * @param current
     * @param size
     * @param eduTeacherQuery
     * @return
     */
    @PostMapping(value = "/get/page/condition/{current}/{size}")
    public CommonResult getTeacherPageCondition(@PathVariable Long current,
                                                @PathVariable Long size,
                                                @RequestBody(required = false) EduTeacherQuery eduTeacherQuery) {
        // 创建page
        Page<EduTeacher> page = new Page<>(current, size);

        // 条件查询
        Page<EduTeacher> teacherPage = teacherService.pageQuery(page, eduTeacherQuery);
        long total = teacherPage.getTotal();
        System.out.println(total);
        List<EduTeacher> teacherList = teacherPage.getRecords();
        System.out.println(teacherList);
        return CommonResult.ok().data("total",total).data("items",teacherList);
    }

    /**
     * 根据id获取对象
     * @param id
     * @return
     */
    @GetMapping(value = "/get/byId/{id}")
    public CommonResult getTeacherById(@PathVariable String id) {
        EduTeacher eduTeacher = teacherService.getById(id);
        return CommonResult.ok().data("items",eduTeacher);
    }

    /**
     * 根据id修改
     * @param eduTeacher
     * @return
     */
    @PostMapping(value = "/update")
    public CommonResult updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean flag = teacherService.updateById(eduTeacher);
        if(flag) {
            return CommonResult.ok();
        } else {
            return CommonResult.error();
        }
    }

    /**
     * 保存讲师
     * @param eduTeacher
     * @return
     */
    @PostMapping(value = "/save")
    public CommonResult saveTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean flag = teacherService.save(eduTeacher);
        if(flag) {
            return CommonResult.ok();
        } else {
            return CommonResult.error();
        }
    }





}

