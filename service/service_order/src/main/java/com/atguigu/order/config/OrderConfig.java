package com.atguigu.order.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author konglingyang
 * @date 2022/1/27 12:24
 */
@Configuration
@ComponentScan("com.atguigu")
@MapperScan("com.atguigu.order.dao")
public class OrderConfig {

}
