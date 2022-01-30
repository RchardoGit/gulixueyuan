package com.atguigu.servicebase.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author konglingyang
 * @date 2022/1/18 22:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuliException extends RuntimeException {

    private Integer code;

    private String message;
}
