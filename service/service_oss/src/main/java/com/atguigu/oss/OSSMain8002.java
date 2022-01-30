package com.atguigu.oss;



import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author konglingyang
 * @date 2022/1/18 10:17
 */

@EnableDiscoveryClient
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class})
public class OSSMain8002 {

    public static void main(String[] args) {
        SpringApplication.run(OSSMain8002.class, args);
    }
}
