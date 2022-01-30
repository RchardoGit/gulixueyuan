package com.atguigu.edu.enties.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author konglingyang
 * @date 2022/1/20 11:56
 */
@Data
public class ChapterVO {

    private String id;
    private String title;

    List<VideoVO> children = new ArrayList<>();
}
