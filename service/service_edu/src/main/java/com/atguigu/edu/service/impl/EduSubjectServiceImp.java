package com.atguigu.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.edu.enties.EduSubject;
import com.atguigu.edu.dao.EduSubjectMapper;
import com.atguigu.edu.enties.excel.SubjectData;
import com.atguigu.edu.enties.vo.OneSubjectVO;
import com.atguigu.edu.enties.vo.TwoSubjectVO;
import com.atguigu.edu.listener.SubjectExcelListener;
import com.atguigu.edu.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author kly
 * @since 2022-01-18
 */
@Service
public class EduSubjectServiceImp extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public Boolean saveSubject(MultipartFile file, EduSubjectService eduSubjectService) {

        try {
            // 获取输入流
            InputStream in = file.getInputStream();
            // 读取文件信息并储存到数据库
            EasyExcel.read(in, SubjectData.class, new SubjectExcelListener(eduSubjectService));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<OneSubjectVO> getAllSubjectList() {

        // 获取所有一级分类
        QueryWrapper<EduSubject> oneQueryWrapper = new QueryWrapper<>();
        oneQueryWrapper.eq("parent_id","0");
        oneQueryWrapper.orderByDesc("sort","id");
        List<EduSubject> oneEduSubjectList = baseMapper.selectList(oneQueryWrapper);

        // 获取所有二级分类
        QueryWrapper<EduSubject> twoQueryWrapper = new QueryWrapper<>();
        oneQueryWrapper.ne("parent_id","0");
        oneQueryWrapper.orderByDesc("sort","id");
        List<EduSubject> twoEduSubjectList = baseMapper.selectList(twoQueryWrapper);

        // 遍历一级分类，将其拷贝属性，封装到集合中，并判断二级分类是否属于一级分类，并将其封装到一级分类中的集合中
        List<OneSubjectVO> oneSubjectVOList = new ArrayList<>();
        for (EduSubject oneSubject : oneEduSubjectList) {
            OneSubjectVO oneSubjectVO = new OneSubjectVO();
            BeanUtils.copyProperties(oneSubject, oneSubjectVO);
            //遍历二级分类，判断二级分类是否属于一级分类，并将其封装到一级分类中的集合中
            for (EduSubject twoSubject : twoEduSubjectList) {

                if(twoSubject.getParentId().equals(oneSubjectVO.getId())) {

                    TwoSubjectVO twoSubjectVO = new TwoSubjectVO();
                    BeanUtils.copyProperties(twoSubject,twoSubjectVO);
                    oneSubjectVO.getChildren().add(twoSubjectVO);
                }
            }
            // 将封装好的一级分类装入集合
            oneSubjectVOList.add(oneSubjectVO);
        }

        return oneSubjectVOList;
    }
}
