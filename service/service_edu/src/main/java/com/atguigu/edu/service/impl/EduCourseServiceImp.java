package com.atguigu.edu.service.impl;

import com.atguigu.edu.enties.EduCourse;
import com.atguigu.edu.dao.EduCourseMapper;
import com.atguigu.edu.enties.EduCourseDescription;
import com.atguigu.edu.enties.vo.*;
import com.atguigu.edu.service.EduChapterService;
import com.atguigu.edu.service.EduCourseDescriptionService;
import com.atguigu.edu.service.EduCourseService;
import com.atguigu.edu.service.EduVideoService;
import com.atguigu.servicebase.exception.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author kly
 * @since 2022-01-19
 */
@Service
public class EduCourseServiceImp extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Resource
    private EduCourseDescriptionService eduCourseDescriptionService;
    @Resource
    private EduVideoService eduVideoService;
    @Resource
    private EduChapterService eduChapterService;
    @Resource
    private EduCourseMapper eduCourseMapper;


    @Override
    public String saveCourse(CourseInfoVO courseInfoVO) {
        // 判断传输的数据是否为空，为空则抛出异常
        if(courseInfoVO == null) {
            throw new GuliException(20001, "数据为空");
        }
        // 保存course
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVO,eduCourse);
        boolean saveCourceFlag = this.save(eduCourse);
        if(!saveCourceFlag) {
            throw new GuliException(20001, "数据保存失败");
        }
        // 保存couxseDescription
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVO,eduCourseDescription);
        // 获取课程id
        String cid = eduCourse.getId();
        eduCourseDescription.setId(cid);
        boolean saveCourseDesFlag = eduCourseDescriptionService.save(eduCourseDescription);
        if(!saveCourseDesFlag) {
            throw new GuliException(20001, "数据保存失败");
        }
        return cid;
    }

    @Override
    public void updateCourse(CourseInfoVO courseInfoVO) {

        // 修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVO,eduCourse);
        boolean flag = this.updateById(eduCourse);
        if(!flag) {
            throw new GuliException(20001, "修改课程失败");
        }
        // 修改描述表
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVO,eduCourseDescription);
        boolean descriptionFlag = eduCourseDescriptionService.updateById(eduCourseDescription);
        if(!descriptionFlag) {
            throw new GuliException(20001, "修改课程描述失败");
        }
    }

    @Override
    public CourseInfoVO getCourseInfoById(String id) {
        // 查询课程表
        EduCourse eduCourse = this.getById(id);
        CourseInfoVO courseInfoVO = new CourseInfoVO();
        BeanUtils.copyProperties(eduCourse,courseInfoVO);

        // 查询描述表
        EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(id);
        courseInfoVO.setDescription(eduCourseDescription.getDescription());

        return courseInfoVO;
    }
    // 获取要发布的课程信息
    @Override
    public CoursePublishVO getCoursePublish(String id) {
        CoursePublishVO coursePublishVO = baseMapper.selectCoursePublish(id);
        return coursePublishVO;
    }

    // 发布课程
    @Override
    public Boolean publishCourseById(String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        int result = baseMapper.updateById(eduCourse);
        return result > 0;
    }

    @Override
    public Page<EduCourse> pageQuery(Page<EduCourse> page, EduCourseQuery eduCourseQuery) {
        QueryWrapper<EduCourse> eduCourseWrapper = new QueryWrapper<>();
        if(eduCourseQuery == null) {
            Page<EduCourse> eduCoursePage = baseMapper.selectPage(page, null);
            return eduCoursePage;
        }
        String title = eduCourseQuery.getTitle();
        String status = eduCourseQuery.getStatus();

        if(!StringUtils.isEmpty(title)) {
            eduCourseWrapper.eq("title",title);
        }
        if(!StringUtils.isEmpty(status)) {
            eduCourseWrapper.eq("status",status);
        }
        Page<EduCourse> eduCoursePage = baseMapper.selectPage(page, eduCourseWrapper);
        return eduCoursePage;
    }

    @Override
    public Boolean removeCourseById(String courseId) {

        if(courseId == null || courseId.equals("")) {
            throw new GuliException(20001,"删除数据id为空");
        }
        // 删除小节（包含删除阿里云视频）
        boolean removeVideoResult = eduVideoService.removeVideoByCourseId(courseId);

        // 删除章节
        boolean removeChapterResult = eduChapterService.removeChapterByCourseId(courseId);

        // 删除课程
        int count = baseMapper.deleteById(courseId);

        return count > 0 && removeChapterResult && removeVideoResult;


    }

    @Cacheable(value = "course", key = "'selectindexListByLimit'")
    @Override
    public List<EduCourse> getCourseListByLimit() {
        QueryWrapper<EduCourse> eduCourseQueryWrapper = new QueryWrapper<>();
        eduCourseQueryWrapper.orderByDesc("view_count");
        eduCourseQueryWrapper.last("limit 8");
        return baseMapper.selectList(eduCourseQueryWrapper);
    }

    @Override
    public List<EduCourse> getCouresListByTeacherId(String teacherId) {

        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id",teacherId);
        List<EduCourse> courseList = baseMapper.selectList(queryWrapper);
        return courseList;
    }

    @Override
    public Map<String, Object> coursePageWeb(Page<EduCourse> coursePage, CourseFrontQueryVO courseFrontQueryVO) {

        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        String subjectParentId = courseFrontQueryVO.getSubjectParentId();  // 一级分类
        String subjectId = courseFrontQueryVO.getSubjectId();  // 二级分类
        String buyCountSort = courseFrontQueryVO.getBuyCountSort();
        String gmtCreateSort = courseFrontQueryVO.getGmtCreateSort();
        String priceSort = courseFrontQueryVO.getPriceSort();

        if(!StringUtils.isEmpty(subjectParentId)) {
            queryWrapper.eq("subject_parent_id",subjectParentId);
        }
        if(!StringUtils.isEmpty(subjectId)) {
            queryWrapper.eq("subject_id", subjectId);
        }
        if(!StringUtils.isEmpty(buyCountSort)) {
            queryWrapper.orderByDesc("buy_count");
        }
        if(!StringUtils.isEmpty(gmtCreateSort)) {
            queryWrapper.orderByDesc("gmt_create");
        }
        if(!StringUtils.isEmpty(priceSort)) {
            queryWrapper.orderByDesc("price");
        }
        Page<EduCourse> pageParam = baseMapper.selectPage(coursePage, queryWrapper);
        List<EduCourse> records = pageParam.getRecords();
        long current = pageParam.getCurrent();
        long pages = pageParam.getPages();
        long size = pageParam.getSize();
        long total = pageParam.getTotal();
        boolean hasNext = pageParam.hasNext();
        boolean hasPrevious = pageParam.hasPrevious();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        return map;
    }

    @Override
    public CourseWebVO getCourseWebInfoById(String courseId) {

        this.updateCourseViewCount(courseId);
        CourseWebVO courseWebVO = eduCourseMapper.selectWebInfo(courseId);
        return courseWebVO;
    }
    //更新观看次数

    @Override
    public void updateCourseViewCount(String courseId) {
        EduCourse eduCourse = baseMapper.selectById(courseId);
        eduCourse.setViewCount(eduCourse.getViewCount() + 1);
        baseMapper.updateById(eduCourse);
    }


}
