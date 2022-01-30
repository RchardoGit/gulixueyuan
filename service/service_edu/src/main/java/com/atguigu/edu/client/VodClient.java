package com.atguigu.edu.client;

import com.atguigu.commonutils.CommonResult;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author konglingyang
 * @date 2022/1/22 15:42
 */
@Component
@FeignClient(name = "service-vod", fallback =VodFileDegradeFeignClient.class )
public interface VodClient {

    @DeleteMapping("/admin/vod/video/remove/{videoId}")
    CommonResult removeVideo(@ApiParam(name = "videoId", value = "云端视频id", required = true)
                                    @PathVariable("videoId") String videoId);

    @DeleteMapping("/admin/vod/video/remove/fetch")
    CommonResult removeVideoList(@ApiParam(name = "videoIdList", value = "云端视频id集合", required = true)
                                        @RequestParam("videoIdList") List<String> videoIdList);

}
