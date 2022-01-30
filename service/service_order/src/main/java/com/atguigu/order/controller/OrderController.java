package com.atguigu.order.controller;


import com.atguigu.commonutils.CommonResult;
import com.atguigu.commonutils.JwtUtils;
import com.atguigu.order.enties.Order;
import com.atguigu.order.service.OrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author kly
 * @since 2022-01-27
 */
@RestController
@CrossOrigin
@RequestMapping("/orderservice/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping(value = "saveOrder/{courseId}")
    private CommonResult addOrder(HttpServletRequest request,
                                  @PathVariable String courseId) {
       // 保存订单，返回订单编号
        String orderId = orderService.saveOrder(JwtUtils.getMemberIdByJwtToken(request), courseId);

        return CommonResult.ok().data("orderId",orderId);
    }

    @GetMapping(value = "getOrder/{orderNo}")
    public CommonResult getOrderById(@PathVariable String oderNo) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no",oderNo);
        Order order = orderService.getOne(queryWrapper);
        return CommonResult.ok().data("item",order);
    }

    // 根据课程id和用户id获取order状态
    @GetMapping(value = "getOrderByCourseAndUser/{courseId}")
    public Boolean getOrderByCourseAndUserId(@PathVariable String courseId,String memberId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        queryWrapper.eq("member_id",memberId);
        queryWrapper.eq("status",1);
        long count = orderService.count(queryWrapper);
        if(count >0) {
            return true;
        } else {
            return false;
        }

    }

}

