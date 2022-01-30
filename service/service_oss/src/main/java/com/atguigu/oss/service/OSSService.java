package com.atguigu.oss.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author konglingyang
 * @date 2022/1/18 10:37
 */
public interface OSSService {
    String uploadAvatarFile(MultipartFile file) throws IOException;
}
