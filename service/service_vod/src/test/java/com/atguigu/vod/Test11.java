package com.atguigu.vod;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.atguigu.servicebase.exception.GuliException;
import com.atguigu.vod.utils.AliyunVodSDKUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author konglingyang
 * @date 2022/1/22 18:04
 */
@SpringBootTest
public class Test11 {

    @Test
    public void removeVideo() {

        try{

            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(
                    "***************",
                    "********************");

            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds("****************");
            DeleteVideoResponse response = client.getAcsResponse(request);
            System.out.print("RequestId = " + response.getRequestId() + "\n");

        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            throw new GuliException(20001, "视频删除失败");
        }
    }
}
