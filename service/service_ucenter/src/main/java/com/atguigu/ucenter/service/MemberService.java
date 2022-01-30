package com.atguigu.ucenter.service;

import com.atguigu.ucenter.enties.Member;
import com.atguigu.ucenter.enties.vo.LoginVO;
import com.atguigu.ucenter.enties.vo.RegisterVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author kly
 * @since 2022-01-24
 */
public interface MemberService extends IService<Member> {

    String login(LoginVO loginVO);

    Boolean register(RegisterVO registerVO);

    Member getLoginInfo(String memberId);

    Member getOpenIdMember(String openid);

    Integer getCountRegisterByDay(String day);
}
