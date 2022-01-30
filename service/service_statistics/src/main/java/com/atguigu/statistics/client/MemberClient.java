package com.atguigu.statistics.client;

import com.atguigu.commonutils.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author konglingyang
 * @date 2022/1/29 22:12
 */
@Component
@FeignClient(value = "service-ucenter")
public interface MemberClient {

    @GetMapping(value = "/ucenterservice/member/countRegister/{day}")
    CommonResult getRegisterCount(@PathVariable("day") String day);
}
