package com.atguigu.edu.controller;


import com.atguigu.commonutils.CommonResult;
import com.atguigu.edu.enties.vo.OneSubjectVO;
import com.atguigu.edu.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author kly
 * @since 2022-01-18
 */
@Api(value = "课程")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/subject")
public class EduSubjectController {

    @Resource
    private EduSubjectService eduSubjectService;

    @ApiOperation(value = "添加课程")
    @PostMapping(value = "/save")
    public CommonResult saveSubject(@ApiParam(name = "file", value = "文件", required = true)
                                        @RequestParam("file") MultipartFile file) {

        Boolean flag = eduSubjectService.saveSubject(file, eduSubjectService);
        if(flag) {
            return CommonResult.ok();
        } else {
            return CommonResult.error();
        }

    }
    @ApiOperation(value = "课程列表")
    @GetMapping(value = "getAll")
    public CommonResult getAllSubjectList() {
        try {
            List<OneSubjectVO> oneSubjectVOList = eduSubjectService.getAllSubjectList();

            return CommonResult.ok().data("items",oneSubjectVOList);
        } catch (Exception exception) {
            exception.printStackTrace();
            return CommonResult.error();
        }
    }

}

