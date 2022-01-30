package com.atguigu.edu.service.impl;

import com.atguigu.edu.enties.EduChapter;
import com.atguigu.edu.dao.EduChapterMapper;
import com.atguigu.edu.enties.EduVideo;
import com.atguigu.edu.enties.vo.ChapterVO;
import com.atguigu.edu.enties.vo.VideoVO;
import com.atguigu.edu.service.EduChapterService;
import com.atguigu.edu.service.EduVideoService;
import com.atguigu.servicebase.exception.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author kly
 * @since 2022-01-19
 */
@Service
public class EduChapterServiceImp extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Resource
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVO> getChapterVideoById(String courseId) {

        // 根据课程id获取章节
        QueryWrapper<EduChapter> chapterWrapper = new QueryWrapper<>();
        chapterWrapper.eq("course_id", courseId);
        List<EduChapter> chapterList = this.list(chapterWrapper);

        // 根据课程id获取小节
        QueryWrapper<EduVideo> videoWrapper = new QueryWrapper<>();
        videoWrapper.eq("course_id", courseId);
        List<EduVideo> videoList = eduVideoService.list(videoWrapper);

        // 创建章节集合
        ArrayList<ChapterVO> chapterVOList = new ArrayList<>();
        // 便利获取章节chapter， 将内容拷贝到chapterVO,并存入集合
        for (EduChapter eduChapter :chapterList) {
            ChapterVO chapterVO = new ChapterVO();
            BeanUtils.copyProperties(eduChapter,chapterVO);
            // 创建小节集合
            ArrayList<VideoVO> videoVOList = new ArrayList<>();
            chapterVOList.add(chapterVO);
            // 遍历小姐集合，判断是否属于该章节，并将其拷贝存入小结集合
            for (EduVideo eduVideo : videoList) {
                if(eduVideo.getChapterId().equals(chapterVO.getId())) {
                    VideoVO videoVO = new VideoVO();
                    BeanUtils.copyProperties(eduVideo,videoVO);
                    videoVOList.add(videoVO);
                }
            }
            // 将小姐集合封装到章节类中
            chapterVO.setChildren(videoVOList);
        }

        return chapterVOList;
    }

    @Override
    public Boolean deleteChapterById(String chapterId) {
        // 若章节中有小结则不删除
        // 查询小结
        QueryWrapper<EduVideo> eduVideoWrapper = new QueryWrapper<>();
        eduVideoWrapper.eq("chapter_id", chapterId);
        int count = (int) eduVideoService.count(eduVideoWrapper);
        if(count >0) {
            throw new GuliException(20001,"不能删除章节");
        } else {
            int result = baseMapper.deleteById(chapterId);
            return result > 0;
        }

    }

    @Override
    public boolean removeChapterByCourseId(String courseId) {

        QueryWrapper<EduChapter> chapterWrapper = new QueryWrapper<>();
        chapterWrapper.eq("course_id",courseId);

        return this.remove(chapterWrapper);
    }
}
