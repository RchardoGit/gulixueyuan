package com.atguigu.edu.controller;

import com.atguigu.commonutils.CommonResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @author konglingyang
 * @date 2022/1/17 10:33
 */
@Api(value = "登录")
@RestController
@CrossOrigin
@RequestMapping(value = "/eduservice/user")
public class EduLoginController {

    @PostMapping(value = "/login")
    public CommonResult login() {
        return CommonResult.ok().data("token","admin");
    }
    @GetMapping(value = "/info")
    public CommonResult info() {
        return CommonResult.ok()
                .data("roles","[admin]")
                .data("name","admin")
                .data("avatar","https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fup.enterdesk.com%2Fedpic%2Fbd%2Fbb%2F6f%2Fbdbb6fede8497ddb835ce5630db28be0.jpeg&refer=http%3A%2F%2Fup.enterdesk.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1644990523&t=76d2464a6eb00c349eeae4488627cce9");

    }
}
