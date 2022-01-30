package com.atguigu.demo.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author konglingyang
 * @date 2022/1/18 17:29
 */
public class ExcelTest {

    public static void main(String[] args) {

//        // 实现写的操作
        String filePath = "F:/write.xlsx";
//        EasyExcel.write(filePath, DataDemo.class).sheet("学生列表").doWrite(getList());
        EasyExcel.read(filePath, DataDemo.class, new ExcelListener()).sheet().doRead();

    }

    public static List<DataDemo> getList() {

        ArrayList<DataDemo> dataDemos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DataDemo dataDemo = new DataDemo();
            dataDemo.setStudentNo(i);
            dataDemo.setStudentName("jack" + i);
            dataDemos.add(dataDemo);
        }
        return dataDemos;
    }
}
