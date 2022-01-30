package com.atguigu.edu.enties.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author konglingyang
 * @date 2022/1/20 11:56
 */
@Data
public class VideoVO {

    private String id;

    private String title;

    @ApiModelProperty(value = "云服务器上存储的视频文件名称")
    private String videoOriginalName;
}
