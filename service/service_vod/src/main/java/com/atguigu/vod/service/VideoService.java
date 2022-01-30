package com.atguigu.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author konglingyang
 * @date 2022/1/21 23:14
 */
public interface VideoService {

    String uploadVideo(MultipartFile file);

    void removeVideo(String videoId);

    void removeVideoList(List<String> videoIdList);
}
