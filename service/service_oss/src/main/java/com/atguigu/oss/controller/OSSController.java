package com.atguigu.oss.controller;

import com.atguigu.commonutils.CommonResult;
import com.atguigu.oss.service.OSSService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author konglingyang
 * @date 2022/1/18 10:37
 */
@RestController
@RequestMapping(value = "/eduoss/fileoss")
@CrossOrigin
public class OSSController {

    @Resource
    private OSSService ossService;


    @PostMapping
    public CommonResult uploadAvatarFile(@ApiParam(name = "file", value = "文件", required = true)
                                             @RequestParam("file") MultipartFile file) {

        try {
            String url = ossService.uploadAvatarFile(file);
            if(url != null) {
                return CommonResult.ok().data("url",url);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CommonResult.error();
    }

}
