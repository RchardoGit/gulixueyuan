package com.atguigu.vod;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author konglingyang
 * @date 2022/1/21 23:04
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.atguigu"})
public class VodMain8003 {

    public static void main(String[] args) {
        SpringApplication.run(VodMain8003.class, args);
    }
}
