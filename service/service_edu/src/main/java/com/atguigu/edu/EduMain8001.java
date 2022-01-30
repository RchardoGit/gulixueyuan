package com.atguigu.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author konglingyang
 * @date 2022/1/13 22:33
 */
@EnableFeignClients
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.atguigu"})
@SpringBootApplication
public class EduMain8001 {

    public static void main(String[] args) {
        SpringApplication.run(EduMain8001.class, args);
    }
}
