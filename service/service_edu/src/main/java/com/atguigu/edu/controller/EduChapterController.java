package com.atguigu.edu.controller;


import com.atguigu.commonutils.CommonResult;
import com.atguigu.edu.enties.EduChapter;
import com.atguigu.edu.enties.vo.ChapterVO;
import com.atguigu.edu.service.EduChapterService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author kly
 * @since 2022-01-19
 */
@Api(value = "chapter")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/chapter")
public class EduChapterController {

    @Resource
    private EduChapterService eduChapterService;

    @GetMapping(value = "getChapterVideo/{courseId}")
    public CommonResult getChapterVideo(@PathVariable("courseId") String courseId) {
      List<ChapterVO> chapterVOList = eduChapterService.getChapterVideoById(courseId);

      return CommonResult.ok().data("chapterVOList",chapterVOList);
    }
    // 添加章节
    @PostMapping(value = "save")
    public CommonResult addChapter(@RequestBody EduChapter eduChapter) {
        boolean flag = eduChapterService.save(eduChapter);
        if(flag) {
            return CommonResult.ok();
        } else {
            return CommonResult.error();
        }

    }
    // 根据id获取章节
    @GetMapping(value = "getById/{chapterId}")
    public CommonResult getChapter(@PathVariable String chapterId) {
        EduChapter eduChapter = eduChapterService.getById(chapterId);
        return CommonResult.ok().data("item",eduChapter);
    }

    // 更新章节
    @PostMapping(value = "update")
    public CommonResult updateChapter(@RequestBody EduChapter eduChapter) {
        boolean flag = eduChapterService.updateById(eduChapter);
        if(flag) {
            return CommonResult.ok();
        } else {
            return CommonResult.error().message("修改失败");
        }
    }

    @DeleteMapping(value = "delete/{chapterId}")
    public CommonResult deleteChapter(@PathVariable String chapterId) {

        Boolean flag = eduChapterService.deleteChapterById(chapterId);
        if(flag) {
            return CommonResult.ok();
        } else {
            return CommonResult.error().message("删除失败");
        }
    }

}

