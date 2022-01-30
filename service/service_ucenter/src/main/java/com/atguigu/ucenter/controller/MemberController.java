package com.atguigu.ucenter.controller;


import com.atguigu.commonutils.CommonResult;
import com.atguigu.commonutils.JwtUtils;
import com.atguigu.servicebase.exception.GuliException;
import com.atguigu.ucenter.enties.Member;
import com.atguigu.ucenter.enties.vo.LoginVO;
import com.atguigu.ucenter.enties.vo.RegisterVO;
import com.atguigu.ucenter.service.MemberService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author kly
 * @since 2022-01-24
 */
@RestController
@CrossOrigin
@RequestMapping("/ucenterservice/member")
public class MemberController {

    @Resource
    private MemberService memberService;

    /**
     * 用户登录
     * @param loginVO
     * @return
     */
    @PostMapping(value = "login")
    public CommonResult login(@RequestBody LoginVO loginVO) {
        String token = memberService.login(loginVO);
        System.out.println(token);
        return CommonResult.ok().data("token",token);
    }

    /**
     * 用户注册
     * @param registerVO
     * @return
     */
    @PostMapping(value = "register")
    public CommonResult register(@RequestBody RegisterVO registerVO) {
        Boolean registerResult = memberService.register(registerVO);
        if(registerResult) {
            return CommonResult.ok();
        } else {
            return CommonResult.error().message("注册失败，请重新注册");
        }
    }

    /**
     * 获取登陆用户信息
     * @param request
     * @return
     */
    @GetMapping(value = "auth/getLoginInfo")
    public CommonResult getLoginInfo(HttpServletRequest request) {
        try {
            String memberId = JwtUtils.getMemberIdByJwtToken(request);
           //System.out.println(memberId);
            Member member = memberService.getLoginInfo(memberId);
            return CommonResult.ok().data("item",member);
        } catch (Exception exception) {
            exception.printStackTrace();
            return CommonResult.error().message("用户登录信息加载失败");
        }

    }
    @GetMapping(value = "getById/{memberId}")
    public Member getMemberById(@PathVariable String memberId) {
        Member loginInfo = memberService.getLoginInfo(memberId);
        return loginInfo;
    }

    @GetMapping(value = "countRegister/{day}")
    public CommonResult getRegisterCount(@PathVariable String day) {
        Integer count = memberService.getCountRegisterByDay(day);
        return CommonResult.ok().data("countRegister",count);
    }

}

