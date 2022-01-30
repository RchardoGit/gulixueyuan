package com.atguigu.edu.controller;


import com.atguigu.commonutils.CommonResult;
import com.atguigu.edu.config.VodConfigProperties;
import com.atguigu.edu.enties.vo.VideoInfoVO;
import com.atguigu.edu.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author kly
 * @since 2022-01-19
 */
@Api(value = "视频")
@CrossOrigin
@RestController
@RequestMapping("/eduservice/video")
public class EduVideoController {

    @Resource
    private EduVideoService eduVideoService;

    @Resource
    private VodConfigProperties vodConfigProperties;

    @PostMapping(value = "save")
    public CommonResult saveVideo(@ApiParam(name ="videoInfoVO",value = "小节",required = true)
                                  @RequestBody VideoInfoVO videoInfoVO) {
        eduVideoService.saveEduVideoInfo(videoInfoVO);
        return CommonResult.ok();
    }


    @GetMapping(value = "get/{videoId}")
    public CommonResult getVideoById(@PathVariable String videoId) {
        VideoInfoVO videoInfoVO = eduVideoService.getVideoInfoById(videoId);

        return CommonResult.ok().data("videoInfo",videoInfoVO);



    }

    @PostMapping(value = "update")
    public CommonResult updateVideo(@RequestBody VideoInfoVO videoInfoVO) {
        Boolean result = eduVideoService.updateVideoInfo(videoInfoVO);
        if(result) {
            return CommonResult.ok();
        } else {
            return CommonResult.error().message("小节修改失败");
        }
    }

    @DeleteMapping(value = "delete/{videoId}")
    public CommonResult removeVideoById(@PathVariable String videoId) {
        Boolean removeResult = eduVideoService.removeVideoById(videoId);
        if(removeResult) {
            return CommonResult.ok();
        } else {
            return CommonResult.error().message("小节删除失败");
        }
    }
    @GetMapping("get-play-auth/{videoId}")
    public CommonResult getVideoPlayAuth(@PathVariable("videoId") String videoId) throws Exception {

        // 获取阿里云存储相关常量
        String accessKeyId = vodConfigProperties.getAccessKeyId();
        String accessKeySecret = vodConfigProperties.getAccessKeySecret();
        // 初始化

        return CommonResult.ok();
    }



}

