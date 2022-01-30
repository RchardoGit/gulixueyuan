package com.atguigu.ucenter.service.impl;

import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.MD5;
import com.atguigu.servicebase.exception.GuliException;
import com.atguigu.ucenter.enties.Member;
import com.atguigu.ucenter.dao.MemberMapper;
import com.atguigu.ucenter.enties.vo.LoginVO;
import com.atguigu.ucenter.enties.vo.RegisterVO;
import com.atguigu.ucenter.service.MemberService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author kly
 * @since 2022-01-24
 */
@Service
public class MemberServiceImp extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 会员登陆
     * @param loginVO
     * @return
     */
    @Override
    public String login(LoginVO loginVO) {
        String mobile = loginVO.getMobile();
        String password = loginVO.getPassword();
        // 校验参数
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new GuliException(20001,"账号或密码不能为空");
        }
        // 查询数据库
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        Member member = baseMapper.selectOne(queryWrapper);
        // 判断账户是否存在
        if(member == null) {
            throw new GuliException(20001,"账户不存在");
        }
        // 判断密码是否正确
        if(!MD5.encrypt(password).equals(member.getPassword())) {
            throw new GuliException(20001,"密码错误");
        }
        // 检验是否被禁用
        if(member.getIsDisabled()) {
            throw new GuliException(20001,"账户已被封禁");
        }

        // 使用Jwt生成token
        String jwtToken = JwtUtils.getJwtToken(member.getId(), member.getNickname());
        return jwtToken;
    }

    @Override
    public Boolean register(RegisterVO registerVO) {
        String mobile = registerVO.getMobile();
        String nickname = registerVO.getNickname();
        String password = registerVO.getPassword();
        String code = registerVO.getCode();

        // 校验参数
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(nickname)
          || StringUtils.isEmpty(password) || StringUtils.isEmpty(code)) {
            throw new GuliException(20001,"账户、昵称、密码或验证码为空");
        }
        // 判断验证码是否正确
        String mobileCode = stringRedisTemplate.opsForValue().get(mobile);
        if(!code.equals(mobileCode)) {
            throw new GuliException(20001,"验证码不正确或已过期");
        }
        // 判断用户是否存在
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        Long count = baseMapper.selectCount(queryWrapper);
        if(count > 0) {
            throw new GuliException(20001,"用户已存在");
        }
        // 添加用户到数据库
        Member member = new Member();
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        member.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        int insert = baseMapper.insert(member);
        return insert > 0;
    }

    @Override
    public Member getLoginInfo(String memberId) {
        Member member = baseMapper.selectById(memberId);
//        LoginVO loginVO = new LoginVO();
//        BeanUtils.copyProperties(member,loginVO);
        return member;
    }

    @Override
    public Member getOpenIdMember(String openid) {

        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("openid",openid);

        return baseMapper.selectOne(memberQueryWrapper);
    }

    @Override
    public Integer getCountRegisterByDay(String day) {

        return baseMapper.selectRegisterCount(day);
    }
}
