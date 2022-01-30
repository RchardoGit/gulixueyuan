package com.atguigu.cms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import org.junit.Test;

public class MybatisPlusTest {

    /**
     * 代码生成，示例代码
     */
    @Test
    public void testGenerator()  {


        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig.Builder()
                .author("kly")// 设置作者
                .outputDir("F:\\JavaStudy\\gulixuyuan\\guli_parent\\service\\service_cms\\src\\main\\java")
                /* 设置生成路径*/
                .fileOverride() // 设置文件覆盖
                .dateType(DateType.ONLY_DATE)
                .commentDate("yyyy-MM-dd")
                .enableSwagger().build();// 开启swagger模式


        // 2.数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder("jdbc:mysql://127.0.0.1:3306/guli", "root", "kly")
                .dbQuery(new MySqlQuery())
                .schema("mybatis-plus")
                .typeConvert(new MySqlTypeConvert())
                .keyWordsHandler(new MySqlKeyWordsHandler())
                .build();

        // 3.策略配置
        StrategyConfig strategyConfig = new StrategyConfig.Builder()
                .enableCapitalMode()  // 全局大写命名 默认为true
                .addTablePrefix("crm_")  // 设置表前缀
                .addInclude("crm_banner")
                // enties
                .entityBuilder()
                .enableLombok()
                .enableTableFieldAnnotation()
                .enableActiveRecord()
                .logicDeleteColumnName("id_deleted")
                .logicDeletePropertyName("idDeleted")
                .columnNaming(NamingStrategy.underline_to_camel)
                .idType(IdType.ASSIGN_ID)
                .naming(NamingStrategy.underline_to_camel)
                // service
                .serviceBuilder()
                .formatServiceFileName("%sService")
                .formatServiceImplFileName("%sServiceImp")
                .build();// 设置逆向工程生成的表

        // 4.包名策略配置
        PackageConfig packageConfig = new PackageConfig.Builder()
                .parent("com.atguigu.cms")
                .mapper("dao")
                .service("service")
                .controller("controller")
                .entity("enties")
                .xml("mapper")
                .build();


        // 5.整合配置
        AutoGenerator autoGenerator = new AutoGenerator(dataSourceConfig);
        autoGenerator.global(globalConfig)
                .strategy(strategyConfig)
                .packageInfo(packageConfig);

        // 6.执行
        autoGenerator.execute();

    }


}
