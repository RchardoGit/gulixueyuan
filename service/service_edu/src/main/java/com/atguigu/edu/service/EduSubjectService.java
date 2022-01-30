package com.atguigu.edu.service;

import com.atguigu.edu.enties.EduSubject;
import com.atguigu.edu.enties.vo.OneSubjectVO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author kly
 * @since 2022-01-18
 */
public interface EduSubjectService extends IService<EduSubject> {


    Boolean saveSubject(MultipartFile file, EduSubjectService eduSubjectService);

    List<OneSubjectVO> getAllSubjectList();
}
