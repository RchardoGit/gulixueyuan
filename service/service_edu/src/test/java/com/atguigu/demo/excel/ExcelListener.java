package com.atguigu.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

/**
 * @author konglingyang
 * @date 2022/1/18 20:40
 */
public class ExcelListener extends AnalysisEventListener<DataDemo> {

    // 读取表格内容
    @Override
    public void invoke(DataDemo dataDemo, AnalysisContext analysisContext) {
        System.out.println("****" + dataDemo);
    }

    // 读取完成之后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
