package com.atguigu.cms.controller;


import com.atguigu.cms.enties.Banner;
import com.atguigu.cms.service.BannerService;
import com.atguigu.commonutils.CommonResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * <p>
 * 首页banner表 前端控制器
 * 后台添加，修改，删除，查询幻灯片
 * </p>
 *
 * @author kly
 * @since 2022-01-23
 */

@RestController
@RequestMapping("/cmsservice/banner")
@CrossOrigin
public class BannerAdminController {

    @Resource
    private BannerService bannerService;

    // 获取分页列表
    @GetMapping(value = "get/{current}/{size}")
    public CommonResult getPageIndex(@PathVariable("current") Long current,
                                     @PathVariable("size") Long size) {
        Page<Banner> page = bannerService.getPage(current, size);

        return CommonResult.ok().data("total",page.getTotal()).data("items",page.getRecords());

    }

    // 增加banner
    @PostMapping(value = "save")
    public CommonResult saveBanner(@RequestBody Banner banner) {
        boolean saveResult = bannerService.saveBanner(banner);
        if (saveResult) {
            return CommonResult.ok().message("幻灯片保存成功");
        } else {
            return CommonResult.error().message("幻灯片保存失败");
        }
    }

    @PostMapping(value = "update")
    public CommonResult updateBanner(@RequestBody Banner banner) {
        boolean updateResult = bannerService.updateBannerById(banner);
        if(updateResult) {
            return CommonResult.ok().message("幻灯片修改成功");
        } else {
            return CommonResult.error().message("幻灯片修改失败");
        }
    }

    @GetMapping(value = "getById/{id}")
    public CommonResult getBannerById(@PathVariable String id) {
        Banner banner = bannerService.getBannerById(id);
        return CommonResult.ok().data("item",banner);
    }

    @DeleteMapping(value = "delete/{id}")
    public CommonResult removeById(@PathVariable String id) {
        boolean removeResult = bannerService.removeBannerById(id);
        if(removeResult) {
            return CommonResult.ok().message("幻灯片删除成功");
        } else {
            return CommonResult.error().message("幻灯片删除失败");
        }
    }


}

