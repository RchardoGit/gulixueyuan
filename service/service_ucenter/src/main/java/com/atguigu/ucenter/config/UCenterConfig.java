package com.atguigu.ucenter.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author konglingyang
 * @date 2022/1/24 21:02
 */

@ComponentScan("com.atguigu")
@MapperScan("com.atguigu.ucenter.dao")
@Configuration
public class UCenterConfig {
}
