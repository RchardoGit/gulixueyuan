package com.atguigu.statistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author konglingyang
 * @date 2022/1/29 21:53
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class StatisticsMain8008 {

    public static void main(String[] args) {
        SpringApplication.run(StatisticsMain8008.class, args);
    }
}
