package com.atguigu.edu.service;

import com.atguigu.edu.enties.EduTeacher;
import com.atguigu.edu.enties.vo.EduTeacherQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author kly
 * @since 2022-01-14
 */
public interface EduTeacherService extends IService<EduTeacher> {


    Page<EduTeacher> pageQuery(Page<EduTeacher> page, EduTeacherQuery eduTeacherQuery);

    List<EduTeacher> getTeacherListByLimit();

    Map<String, Object> getTeacherListWeb(Page<EduTeacher> eduTeacherPage);
}
