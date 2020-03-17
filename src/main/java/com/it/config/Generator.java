package com.it.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wang_zy
 * @Date 2019/12/16 16:06
 */
public class Generator {

    public static void main(String[] args) {


        AutoGenerator mpg = new AutoGenerator();
        // 选择 freemarker 引擎，默认 Velocity
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setAuthor("zzzz");
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setFileOverride(false);// 是否覆盖同名文件，默认是false
        gc.setActiveRecord(true);// 不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setServiceImplName("%s");

        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MARIADB);
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("slave");
        dsc.setPassword("slave");
        dsc.setUrl("jdbc:mysql://192.168.85.22:3306/test001?useUnicode=true&serverTimezone=UTC&characterEncoding=utf8");
        mpg.setDataSource(dsc);

//        // 策略配置
//        StrategyConfig strategy = new StrategyConfig();
//        //strategy.setTablePrefix(new String[] { "buy_" });// 此处可以修改为您的表前缀
//        strategy.setNaming(NamingStrategy.no_change);// 表名生成策略
//        strategy.setInclude("wcw_customer_info", "wcw_manager", "wcw_emp_customer", "wcw_emp_manager", "wcw_talent");// 需要生成的表
//        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.it.plus");
        pc.setModuleName("wcw");
        pc.setEntity("entity");
        pc.setController("controller");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        /**
         * 自定义配置
         */
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        /**
         * 模板
         */
        //如果模板引擎是 freemarker
        //String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";
        /**
         * 自定义输出配置
         */
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName() + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        /**
         * 配置模板
         */
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
//        templateConfig.setEntity("templates/entity2.java");
//        templateConfig.setService(new TemplateConfig().getService());
//        templateConfig.setController(new TemplateConfig().getController());

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        /**
         * 策略配置
         */
        StrategyConfig strategy = new StrategyConfig();
        //设置命名格式
        strategy.setNaming(NamingStrategy.underline_to_camel);

        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setInclude(scanner("表名,多个英文逗号分割").split(","));
        strategy.setInclude("wcw_pagroup");// 需要生成的表
        //实体是否为lombok模型（默认 false）
        strategy.setEntityLombokModel(true);
        //生成 @RestController 控制器
        strategy.setRestControllerStyle(true);
        //设置自定义继承的Entity类全称，带包名
        //strategy.setSuperEntityClass("com.jiangfeixiang.mpdemo.BaseEntity");
        //设置自定义继承的Controller类全称，带包名
        //strategy.setSuperControllerClass("com.jiangfeixiang.mpdemo.BaseController");
        //设置自定义基础的Entity类，公共字段
//        strategy.setSuperEntityColumns("id");
        //驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        //表名前缀
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new VelocityTemplateEngine());

        // 执行生成
        mpg.execute();
    }

}
