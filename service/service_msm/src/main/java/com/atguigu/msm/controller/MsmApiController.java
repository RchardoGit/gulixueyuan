package com.atguigu.msm.controller;

import com.atguigu.commonutils.CommonResult;
import com.atguigu.commonutils.RandomUtil;
import com.atguigu.msm.service.MsmService;
import io.micrometer.core.instrument.util.TimeUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author konglingyang
 * @date 2022/1/24 20:28
 */

@RestController
@RequestMapping("/api/msm")
@CrossOrigin
public class MsmApiController {

    @Resource
    private MsmService msmService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping(value = "send/{phone}")
    public CommonResult sendPhone(@PathVariable String phone) {
        // 从redis中获取code
        String code = stringRedisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)) {
            return CommonResult.ok();
        }
        // 如果获取不到，生成验证码
        code = RandomUtil.getFourBitRandom();
        HashMap<String, String> map = new HashMap<>();
        map.put("code",code);
        // 发送验证码到手机
        Boolean isSend = msmService.sendMsm(phone, "SMS_180051135", map);
        if(isSend)  {
            stringRedisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return CommonResult.ok();
        }
        return CommonResult.error().message("发送验证码失败");
    }


}
