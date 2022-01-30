package com.atguigu.edu.service;

import com.atguigu.edu.enties.EduVideo;
import com.atguigu.edu.enties.vo.VideoInfoVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author kly
 * @since 2022-01-19
 */
public interface EduVideoService extends IService<EduVideo> {

    void saveEduVideoInfo(VideoInfoVO videoInfoVO);

    VideoInfoVO getVideoInfoById(String videoId);

    Boolean updateVideoInfo(VideoInfoVO videoInfoVO);

    Boolean removeVideoById(String videoId);

    boolean removeVideoByCourseId(String courseId);
}
