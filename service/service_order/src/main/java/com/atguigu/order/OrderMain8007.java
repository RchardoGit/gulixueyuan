package com.atguigu.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author konglingyang
 * @date 2022/1/27 12:23
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class OrderMain8007 {

    public static void main(String[] args) {
        SpringApplication.run(OrderMain8007.class, args);
    }
}
