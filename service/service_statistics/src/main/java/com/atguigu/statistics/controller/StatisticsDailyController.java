package com.atguigu.statistics.controller;


import com.atguigu.commonutils.CommonResult;
import com.atguigu.statistics.service.StatisticsDailyService;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author kly
 * @since 2022-01-29
 */
@RestController
@CrossOrigin
@RequestMapping("/statistics/statisticsDaily")
public class StatisticsDailyController {

    @Resource
    private StatisticsDailyService statisticsDailyService;

    @GetMapping(value = "{day}")
    public CommonResult createStatisticsByDate(@PathVariable String day) {
        statisticsDailyService.createStatisticsByDate(day);
        return CommonResult.ok();
    }

    @GetMapping("show-chart/{begin}/{end}/{type}")
    public CommonResult showChart(@PathVariable String begin,@PathVariable String end,@PathVariable String type){
        Map<String, Object> map = statisticsDailyService.getChartData(begin, end, type);
        return CommonResult.ok().data(map);
    }

}

