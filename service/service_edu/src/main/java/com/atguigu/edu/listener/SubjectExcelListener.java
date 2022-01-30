package com.atguigu.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.edu.enties.EduSubject;
import com.atguigu.edu.enties.excel.SubjectData;
import com.atguigu.edu.service.EduSubjectService;
import com.atguigu.servicebase.exception.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author konglingyang
 * @date 2022/1/18 22:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    private EduSubjectService eduSubjectService;


    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {

        //判断上传文件是否为空
        if(subjectData == null) {
            throw new GuliException(20001,"文件为空");
        }

        // 根据名称获取一级标题
        String oneSubjectName = subjectData.getOneSubjectName();
        EduSubject oneEduSubject = this.oneSubjectExist(eduSubjectService, oneSubjectName);
        // 判断一级标题是否已存在
        if( oneEduSubject == null) {
            oneEduSubject = new EduSubject();
            oneEduSubject.setTitle(subjectData.getOneSubjectName());
            oneEduSubject.setParentId("0");
            eduSubjectService.save(oneEduSubject);
        }
        String pid = oneEduSubject.getParentId();
        // 根据名称获取内容
        EduSubject twoEduSubject = this.twoSubjectExist(eduSubjectService,subjectData.getTwoSubjectName(),pid);
        if(twoEduSubject == null) {
            twoEduSubject = new EduSubject();
            twoEduSubject.setTitle(subjectData.getTwoSubjectName());
            twoEduSubject.setParentId(pid);
            eduSubjectService.save(twoEduSubject);
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    private EduSubject oneSubjectExist(EduSubjectService eduSubjectService, String title) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",title);
        wrapper.eq("parent_id","0");
        return eduSubjectService.getOne(wrapper);
    }

    private EduSubject twoSubjectExist(EduSubjectService eduSubjectService, String title, String pid) {

        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",title);
        wrapper.eq("parent_id",pid);
        return eduSubjectService.getOne(wrapper);
    }
}
