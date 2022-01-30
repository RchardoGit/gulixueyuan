package com.atguigu.cms.controller;

import com.atguigu.cms.enties.Banner;
import com.atguigu.cms.service.BannerService;
import com.atguigu.commonutils.CommonResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author konglingyang
 * @date 2022/1/24 0:04
 */

@RestController
@RequestMapping("/cmsservice/banner")
@CrossOrigin
public class BannerFrontController {

    @Resource
    private BannerService bannerService;

    @GetMapping(value = "getAllBanner")
    public CommonResult getAllBanner() {
        List<Banner> bannerList = bannerService.getBannerlist();
        return CommonResult.ok().data("bannerList",bannerList);
    }
}
