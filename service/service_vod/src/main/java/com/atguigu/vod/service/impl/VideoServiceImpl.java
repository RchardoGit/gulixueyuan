package com.atguigu.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.atguigu.servicebase.exception.GuliException;
import com.atguigu.vod.config.VodConfigProperties;
import com.atguigu.vod.service.VideoService;
import com.atguigu.vod.utils.AliyunVodSDKUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author konglingyang
 * @date 2022/1/21 23:14
 */
@Slf4j
@Service
public class VideoServiceImpl implements VideoService {

    @Resource
    private VodConfigProperties vodConfigProperties;

    @Override
    public String uploadVideo(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));

            UploadStreamRequest request = new UploadStreamRequest(
                    vodConfigProperties.getAccessKeyId(),
                    vodConfigProperties.getAccessKeySecret(),
                    title, originalFilename, inputStream);

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);

            //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。
            // 其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            String videoId = response.getVideoId();
            if (!response.isSuccess()) {
                String errorMessage = "阿里云上传错误：" + "code：" + response.getCode() + ", message：" + response.getMessage();
                log.warn(errorMessage);
                if(StringUtils.isEmpty(videoId)){
                    throw new GuliException(20001, errorMessage);
                }
            }

            return videoId;
        } catch (IOException e) {
            throw new GuliException(20001, "guli vod 服务上传失败");
        }
    }

    @Override
    public void removeVideo(String videoId) {

        try{

            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(
                    vodConfigProperties.getAccessKeyId(),
                    vodConfigProperties.getAccessKeySecret());

            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(videoId);
            DeleteVideoResponse response = client.getAcsResponse(request);
            System.out.print("RequestId = " + response.getRequestId() + "\n");

        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            throw new GuliException(20001, "视频删除失败");
        }
    }

    @Override
    public void removeVideoList(List<String> videoIdList) {

        try {
            // 初始化
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(vodConfigProperties.getAccessKeyId(),
                                                                        vodConfigProperties.getAccessKeySecret());
            // 创建请求对象
            // 一次只能请求批量删除20个
            String videoIds = org.apache.commons.lang.StringUtils.join(videoIdList.toArray(), ",");
            log.info(videoIds);
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(videoIds);
            DeleteVideoResponse response = client.getAcsResponse(request);
            System.out.print("RequestId = " + response.getRequestId() + "\n");

        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
