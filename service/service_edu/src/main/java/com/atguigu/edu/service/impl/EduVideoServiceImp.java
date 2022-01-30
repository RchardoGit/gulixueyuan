package com.atguigu.edu.service.impl;

import com.atguigu.edu.client.VodClient;
import com.atguigu.edu.enties.EduVideo;
import com.atguigu.edu.dao.EduVideoMapper;
import com.atguigu.edu.enties.vo.VideoInfoVO;
import com.atguigu.edu.service.EduVideoService;
import com.atguigu.servicebase.exception.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author kly
 * @since 2022-01-19
 */
@Service
public class EduVideoServiceImp extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Resource
    private VodClient vodClient;

    @Override
    public void saveEduVideoInfo(VideoInfoVO videoInfoVO) {
        if(videoInfoVO == null) {
            throw new GuliException(20001,"小节数据不能为空");
        }
        EduVideo eduVideo = new EduVideo();
        BeanUtils.copyProperties(videoInfoVO,eduVideo);
        int insert = baseMapper.insert(eduVideo);
        if(insert <=0) {
            throw new GuliException(20001,"小节保存失败");
        }

    }

    @Override
    public VideoInfoVO getVideoInfoById(String videoId) {

        EduVideo eduVideo = baseMapper.selectById(videoId);
        if(eduVideo == null) {
            throw new GuliException(20001,"查询小节失败");
        }
        VideoInfoVO videoInfoVO = new VideoInfoVO();
        BeanUtils.copyProperties(eduVideo,videoInfoVO);
        return videoInfoVO;
    }

    @Override
    public Boolean updateVideoInfo(VideoInfoVO videoInfoVO) {
        if(videoInfoVO == null) {
            throw new GuliException(20001,"小节修改数据为空");
        }
        EduVideo eduVideo = new EduVideo();
        BeanUtils.copyProperties(videoInfoVO,eduVideo);
        int result = baseMapper.updateById(eduVideo);
        return result > 0;
    }

    @Override
    public Boolean removeVideoById(String videoId) {

        //to do
        // 删除视频资源
        EduVideo eduVideo = baseMapper.selectById(videoId);
        String videoSourceId = eduVideo.getVideoSourceId();
        if(!StringUtils.isEmpty(videoSourceId)){
            vodClient.removeVideo(videoSourceId);
        }
        // 删除小节
        int result = baseMapper.deleteById(videoId);
        return result > 0;
    }

    @Override
    public boolean removeVideoByCourseId(String courseId) {

        // to do
        // 删除视频资源
        // 1.根据课程id获取小节
        QueryWrapper<EduVideo> eduVideoQueryWrapper = new QueryWrapper<>();
        eduVideoQueryWrapper.eq("course_id",courseId);
        eduVideoQueryWrapper.select("video_source_id");
        List<EduVideo> eduVideoList = baseMapper.selectList(eduVideoQueryWrapper);

        // 获取所有小节中的阿里云视频id
        ArrayList<String> videoIdList = new ArrayList<>();
        for (EduVideo eduVideo : eduVideoList) {
            String videoSourceId = eduVideo.getVideoSourceId();
            if(!StringUtils.isEmpty(videoSourceId)) {
                videoIdList.add(videoSourceId);
            }
        }

        // 调用vod远程删除视频
        if(videoIdList.size() > 0) {
            vodClient.removeVideoList(videoIdList);
        }

        // 删除小节
        QueryWrapper<EduVideo> eduVideoWrapper = new QueryWrapper<>();
        eduVideoWrapper.eq("course_id",courseId);
        return this.remove(eduVideoWrapper);
    }


}
