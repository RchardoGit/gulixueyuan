package com.atguigu.edu.client;

import com.atguigu.commonutils.CommonResult;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author konglingyang
 * @date 2022/1/22 16:13
 */
@Component
public class VodFileDegradeFeignClient implements VodClient {
    @Override
    public CommonResult removeVideo(String videoId) {
        return CommonResult.error().message("调用删除阿里云视频失败");
    }

    @Override
    public CommonResult removeVideoList(List<String> videoIdList) {
        return CommonResult.error().message("调用批量删除阿里云视频失败");
    }
}
