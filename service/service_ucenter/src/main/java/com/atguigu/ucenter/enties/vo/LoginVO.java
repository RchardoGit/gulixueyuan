package com.atguigu.ucenter.enties.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author konglingyang
 * @date 2022/1/24 21:13
 */
@Data
public class LoginVO {

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String password;
}
