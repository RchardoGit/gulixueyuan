package com.atguigu.order.controller;


import com.atguigu.commonutils.CommonResult;
import com.atguigu.order.service.PayLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author kly
 * @since 2022-01-27
 */
@RestController
@RequestMapping("/orderservice/payLog")
public class PayLogController {

    @Resource
    private PayLogService payLogService;

    // 生成付款二维码
    @GetMapping(value = "createNative/{orderNo}")
    public CommonResult createNative(@PathVariable String orderNo) {
        Map map = payLogService.createNative(orderNo);

        return CommonResult.ok().data(map);
    }

    @GetMapping("/queryPayStatus/{orderNo}")
    public CommonResult queryPayStatus(@PathVariable String orderNo) {
        //调用查询接口
        Map<String, String> map = payLogService.queryPayStatus(orderNo);
        if (map == null) {//出错
            return CommonResult.error().message("支付出错");
        }
        if("SUCCESS".equals(map.get("trade_state"))) {

            // 更改订单状态
            payLogService.updateOrderStatus(map);
            return CommonResult.ok().message("支付成功");
        }
        return CommonResult.ok().code(25000).message("支付中");
    }


}

