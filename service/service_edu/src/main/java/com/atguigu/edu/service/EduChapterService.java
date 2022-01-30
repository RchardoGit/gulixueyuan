package com.atguigu.edu.service;

import com.atguigu.edu.enties.EduChapter;
import com.atguigu.edu.enties.vo.ChapterVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author kly
 * @since 2022-01-19
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVO> getChapterVideoById(String courseId);

    Boolean deleteChapterById(String chapterId);

    boolean removeChapterByCourseId(String courseId);
}
