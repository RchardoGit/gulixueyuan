package com.atguigu.edu.enties.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author konglingyang
 * @date 2022/1/19 14:17
 */
@Data
public class OneSubjectVO {

    private String id;
    private String title;
    private List<TwoSubjectVO> children = new ArrayList<>();
}
