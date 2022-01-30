package com.atguigu.ucenter.controller;

import com.atguigu.commonutils.HttpClientUtils;
import com.atguigu.commonutils.JwtUtils;
import com.atguigu.servicebase.exception.GuliException;
import com.atguigu.ucenter.config.WxOpenConfigProperties;
import com.atguigu.ucenter.enties.Member;
import com.atguigu.ucenter.service.MemberService;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author konglingyang
 * @date 2022/1/25 17:31
 */
@Controller
@CrossOrigin
@RequestMapping("/api/ucenter/wx")
public class WxApiController {

    @Resource
    private WxOpenConfigProperties wxOpenConfigProperties;

    @Resource
    private MemberService memberService;

    @GetMapping(value = "login")
    public String getWxCode() {
        // 微信开放平台授权baseUrl
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";
        String redirect_url = wxOpenConfigProperties.getRedirectUrl();
        try {
            redirect_url = URLEncoder.encode(redirect_url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = String.format(baseUrl,
                wxOpenConfigProperties.getAppId(),
                redirect_url,
                "atguigu");
        // 对redisect_url进行URLEncoder编码

        // 请求微信地址
        return "redirect:" + url;
    }
    @GetMapping(value = "callback")
    public String callback(@RequestParam String code, @RequestParam String state) {

        String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=%s" +
                "&secret=%s" +
                "&code=%s" +
                "&grant_type=authorization_code";
        // 拼接三个参数
        try {
            String accessTokenUrl = String.format(baseAccessTokenUrl,
                    wxOpenConfigProperties.getAppId(),
                    wxOpenConfigProperties.getAppSecret(),
                    code);

            // 请求拼接好的地址，得到返回两个值access_token 和openid
            // 使用httpclient发送请求
            String accessTokenInfo = HttpClientUtils.get(accessTokenUrl);

            Gson gson = new Gson();
            Map mapAccessToken = gson.fromJson(accessTokenInfo, HashMap.class);

            String access_token = (String)mapAccessToken.get("access_token");
            String openid = (String)mapAccessToken.get("openid");

            // 判断数据库中是否存在，不存在则将微信用户添加到数据库
            Member member = memberService.getOpenIdMember(openid);

            if(member == null) {

                // 拿着得到的access_token 和 openid 再去请求微信提供的固定的地址，获取到扫描人信息
                // 访问微信的资源服务器，获取用户信息
                String baseUserInfoUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                        "?access_token=%s" +
                        "&openid=%s";
                String userInfoUrl = String.format(baseUserInfoUrl, access_token, openid);
                // 发送请求
                String userInfo = HttpClientUtils.get(userInfoUrl);

                // 获取微信扫码人信息
                HashMap userInfoMap = gson.fromJson(userInfo, HashMap.class);
                String nickname = (String) userInfoMap.get("nickname");
                String headimgurl = (String) userInfoMap.get("headimgurl");
                // 添加用户
                member = new Member();
                member.setOpenid(openid);
                member.setNickname(nickname);
                member.setAvatar(headimgurl);
                memberService.save(member);
            }
            String token = JwtUtils.getJwtToken(openid, member.getNickname());
            // 最后返回首页面
            return "redirect:http://localhost:3000" + token;
        } catch (Exception e) {
            throw new GuliException(20001,"登陆失败");
        }



    }


}
