package com.atguigu.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.model.PutObjectResult;
import com.atguigu.oss.config.OSSProperties;
import com.atguigu.oss.service.OSSService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author konglingyang
 * @date 2022/1/18 10:38
 */
@Service
public class OSSServiceImpl implements OSSService {

    @Resource
    private OSSProperties ossProperties;

    @Override
    public String uploadAvatarFile(MultipartFile file) {

        // 获取工具值
        String endpoint = ossProperties.getEndpoint();
        String accessKeyId = ossProperties.getAccessKeyId();
        String accessKeySecret = ossProperties.getAccessKeySecret();
        String bucketName = ossProperties.getBucketName();
        String bucketDomain = ossProperties.getBucketDomain();

        if(file == null) {
            return null;
        }
        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // 生成上传文件的目录
           // String folderName = new SimpleDateFormat("yyyyMMdd").format(new Date());
            String folderName = new DateTime().toString("yyyyMMdd");

            // 生成上传文件在oss服务器上保存的文件名
            // 获取原始文件名
            String originalName =  file.getOriginalFilename();
            // 使用uuid生成文件主题名称
            String fileMainName = UUID.randomUUID().toString().replace("-", "");
            // 从原始文件中获取文件扩展名
            String extensionName = originalName.substring(originalName.lastIndexOf("."));
            // 使用目录，文件主题名称、文件扩展名拼接得到对象名称
            String objectName = folderName + "/" + fileMainName + extensionName;

            // 获取文件流
            InputStream uploadInputStream = file.getInputStream();

            // 调用OSS客户端对象的方法上传文件并取得响应结果数据
            PutObjectResult putObjectResult = ossClient.putObject(bucketName, objectName, uploadInputStream);

            // 从响应结果中获取具体响应信息
            ResponseMessage responseMessage = putObjectResult.getResponse();
            //log.info(responseMessage.toString());
            // 根据响应状态码判断请求是否成功
            if (responseMessage == null) {
                // 拼接访问刚刚上传的文件的路径
                String ossFileAccessPath ="http://" + bucketDomain + "/" + objectName;
                // 当前方法返回路径
                return ossFileAccessPath;
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            if(ossClient !=null) {
                ossClient.shutdown();
            }
        }
        return null;
    }

}
