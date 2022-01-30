package com.atguigu.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author konglingyang
 * @date 2022/1/18 17:27
 */
@Data
public class DataDemo {

    @ExcelProperty(value = "学生编号", index = 0)
    private Integer studentNo;
    @ExcelProperty(value = "学生姓名", index = 1)
    private String studentName;
}
