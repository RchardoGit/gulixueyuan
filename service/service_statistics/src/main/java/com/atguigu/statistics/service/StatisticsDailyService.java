package com.atguigu.statistics.service;

import com.atguigu.statistics.enties.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author kly
 * @since 2022-01-29
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void createStatisticsByDate(String day);

    Map<String, Object> getChartData(String begin, String end, String type);
}
