package com.atguigu.ucenter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author konglingyang
 * @date 2022/1/25 17:28
 */
@Data
@Component
@ConfigurationProperties(prefix = "wx.open")
public class WxOpenConfigProperties {


    private String appId;
    
    private String appSecret;

    private String redirectUrl;
}
