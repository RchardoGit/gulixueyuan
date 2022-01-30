package com.atguigu.order.service;

import com.atguigu.order.enties.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author kly
 * @since 2022-01-27
 */
public interface PayLogService extends IService<PayLog> {

    Map<String, String> createNative(String orderNo);

    void updateOrderStatus(Map<String, String> map);

    Map<String, String> queryPayStatus(String orderNo);
}
