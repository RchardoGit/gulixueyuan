package com.atguigu.order.client;

import com.atguigu.commonutils.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author konglingyang
 * @date 2022/1/27 13:25
 */
@FeignClient(value = "service-ucenter")
public interface UcenterClient {

    @GetMapping(value = "/ucenterservice/member/getById/{memberId}")
    Member getMemberById(@PathVariable("memberId") String memberId);
}
