package com.atguigu.edu.client;

import com.atguigu.commonutils.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author konglingyang
 * @date 2022/1/29 20:56
 */
@Component
@FeignClient(value = "service-order",fallback = OrderClientimpl.class)
public interface OrderClient {

    @GetMapping(value = "/orderservice/order/getOrderByCourseAndUser/{courseId}")
    Boolean getOrderByCourseAndUserId(@PathVariable String courseId, String memberId);
}
