package com.atguigu.statistics.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author konglingyang
 * @date 2022/1/29 21:59
 */
@Configuration
@ComponentScan("com.atguigu")
@MapperScan("com.atguigu.statistics.dao")
public class StatisticsConfig {
}
