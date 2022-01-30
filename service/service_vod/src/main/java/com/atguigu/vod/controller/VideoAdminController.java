package com.atguigu.vod.controller;

import com.atguigu.commonutils.CommonResult;
import com.atguigu.vod.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author konglingyang
 * @date 2022/1/21 23:12
 */
@Api(value="阿里云视频点播微服务")
@CrossOrigin //跨域
@RestController
@RequestMapping("/admin/vod/video")
public class VideoAdminController {

    @Resource
    private VideoService videoService;

    @PostMapping("upload")
    public CommonResult uploadVideo(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) {

        String videoId = videoService.uploadVideo(file);
        return CommonResult.ok().message("视频上传成功").data("videoId", videoId);
    }

    @DeleteMapping("remove/{videoId}")
    public CommonResult removeVideo(@ApiParam(name = "videoId", value = "云端视频id", required = true)
                         @PathVariable String videoId){

        videoService.removeVideo(videoId);
        return CommonResult.ok().message("视频删除成功");
    }

    @DeleteMapping("remove/fetch")
    public CommonResult removeVideoList(@ApiParam(name = "videoIdList", value = "云端视频id集合", required = true)
                                    @RequestParam("videoIdList") List<String> videoIdList){

        videoService.removeVideoList(videoIdList);
        return CommonResult.ok().message("视频删除成功");
    }
}
