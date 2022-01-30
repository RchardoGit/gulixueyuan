package com.atguigu.canal;

import com.atguigu.canal.client.CannalClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * @author konglingyang
 * @date 2022/1/30 10:35
 */
@SpringBootApplication
public class CannalMain10000 implements CommandLineRunner {

    @Resource
    private CannalClient cannalClient;

    public static void main(String[] args) {

        SpringApplication.run(CannalMain10000.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        //项目启动，执行canal客户端监听
        cannalClient.run();
    }
}
