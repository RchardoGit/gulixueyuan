package com.atguigu.edu.service.impl;

import com.atguigu.edu.enties.EduTeacher;
import com.atguigu.edu.dao.EduTeacherMapper;
import com.atguigu.edu.enties.vo.EduTeacherQuery;
import com.atguigu.edu.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author kly
 * @since 2022-01-14
 */
@Service
public class EduTeacherServiceImp extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Resource
    private EduTeacherMapper teacherMapper;

    @Override
    public Page<EduTeacher> pageQuery(Page<EduTeacher> page, EduTeacherQuery eduTeacherQuery) {
        // 构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        if(eduTeacherQuery == null) {
            Page<EduTeacher> eduTeacherPage = teacherMapper.selectPage(page,null);
            return eduTeacherPage;
        }
        String name = eduTeacherQuery.getName();
        Integer level = eduTeacherQuery.getLevel();
        String begin = eduTeacherQuery.getBegin();
        String end = eduTeacherQuery.getEnd();
        // 判断条件
        if(!StringUtils.isEmpty(name)) {
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)) {
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create",end);
        }
        wrapper.orderByDesc("gmt_create");

        return teacherMapper.selectPage(page, wrapper);
    }

    @Cacheable(value = "teacher", key = "'selectindexListByLimit'")
    @Override
    public List<EduTeacher> getTeacherListByLimit() {
        QueryWrapper<EduTeacher> eduTeacherQueryWrapper = new QueryWrapper<>();
        eduTeacherQueryWrapper.orderByDesc("id");
        eduTeacherQueryWrapper.last("limit 4");
        return baseMapper.selectList(eduTeacherQueryWrapper);

    }

    @Override
    public Map<String, Object> getTeacherListWeb(Page<EduTeacher> eduTeacherPage) {
        Page<EduTeacher> page = baseMapper.selectPage(eduTeacherPage, null);

        List<EduTeacher> records = page.getRecords(); // 获取讲师数据
        long pages = page.getPages();  // 获取总页数
        long current = page.getCurrent();  // 获取当前页
        long size = page.getSize();  // 每页数据数
        long total = page.getTotal();  // 总数据数
        boolean hasNext = page.hasNext();  // 是否有下一页
        boolean hasPrevious = page.hasPrevious();  // 是否有上一页

        HashMap<String, Object> map = new HashMap<>();
        map.put("items",records);
        map.put("pages",pages);
        map.put("current",current);
        map.put("size",size);
        map.put("total",total);
        map.put("hasNext",hasNext);
        map.put("hasPrevious",hasPrevious);
        return map;
    }
}
