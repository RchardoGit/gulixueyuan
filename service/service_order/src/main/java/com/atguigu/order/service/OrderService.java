package com.atguigu.order.service;

import com.atguigu.order.enties.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author kly
 * @since 2022-01-27
 */
public interface OrderService extends IService<Order> {

    String saveOrder(String memberId, String courseId);
}
