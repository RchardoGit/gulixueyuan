package com.atguigu.edu.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author konglingyang
 * @date 2022/1/21 23:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class VodConfigProperties {

    private String accessKeyId;
    private String accessKeySecret;

}
