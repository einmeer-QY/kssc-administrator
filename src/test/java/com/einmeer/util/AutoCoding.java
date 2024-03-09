package com.einmeer.util;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 芊嵛
 * @date 2024/3/5
 */
public class AutoCoding {
    public static void main(String[] args) {
        // 表名
        String table = "sys_file";
        // 获取项目目录
        String path = System.getProperty("user.dir");
        String url = "jdbc:mysql://192.168.21.130:3306/ks_second_classroom?useUnicode=true&characterEncoding=utf8";
        String username = "root";
        String password = "2459689935";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    // 配置作者
                    builder.author("芊嵛")
                            // 代码生成的路径
                            .outputDir(path + "/src/main/java");
                })
                .packageConfig(builder -> {
                    // 自定义Mapper.xml的生成路径
                    Map<OutputFile, String> pathInfo = new HashMap<>();
                    pathInfo.put(OutputFile.xml, path + "/src/main/resources/com/einmeer/mapper");
                    // 设置父包名
                    builder.parent("com.einmeer")
                            // 给resources文件下生成mapper.xml文件
                            .pathInfo(pathInfo);
                })
                .strategyConfig(builder -> {
                    builder
                            //设置需要生成的表名
                            .addInclude(table)
                            .build()
                            // 让所有Controller都加RestController注解
                            .controllerBuilder()
                            .enableRestStyle();
                    // 让所有的entity都加lombok注解
                    builder.build().entityBuilder().enableLombok();
                    // 让service接口的名字不要前面的I
                    builder.build().serviceBuilder().formatServiceFileName("%sService");
                })
                // 使用Freemarker引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

    }
}
