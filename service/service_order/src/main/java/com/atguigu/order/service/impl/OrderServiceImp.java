package com.atguigu.order.service.impl;

import com.atguigu.commonutils.CourseWebVO;
import com.atguigu.commonutils.Member;
import com.atguigu.commonutils.OrderNoUtil;
import com.atguigu.order.client.EduClient;
import com.atguigu.order.client.UcenterClient;
import com.atguigu.order.enties.Order;
import com.atguigu.order.dao.OrderMapper;
import com.atguigu.order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author kly
 * @since 2022-01-27
 */
@Service
public class OrderServiceImp extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private UcenterClient ucenterClient;
    @Resource
    private EduClient eduClient;

    @Override
    public String saveOrder(String memberId, String courseId) {

        // 根据memberId获取用户信息
        Member member = ucenterClient.getMemberById(memberId);
        // 根据courseId获取课程信息
        CourseWebVO courseWebVO = eduClient.getCourseInfoById(courseId);
        Order order = new Order();
        String orderNo = OrderNoUtil.getOrderNo();
        order.setOrderNo(orderNo);
        order.setCourseId(courseId);
        order.setCourseTitle(courseWebVO.getTitle());
        order.setCourseCover(courseWebVO.getCover());
        order.setTotalFee(courseWebVO.getPrice());
        order.setMemberId(memberId);
        order.setMobile(member.getMobile());
        order.setNickname(member.getNickname());
        order.setTeacherName(courseWebVO.getTeacherName());
        order.setStatus(0);
        order.setPayType(1);
        baseMapper.insert(order);
        return orderNo;
    }
}
