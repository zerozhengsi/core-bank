package com.vteam.cars.plugin.mybatisplus.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.vteam.cars.util.FastjsonUtils;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;
import java.sql.Types;
import java.util.Collections;
import java.util.Map;

/**
 * @className: MpGenerator
 * @Description: TODO
 * @version: v1.８.0
 * @author: care.zheng
 * @date: 2023/8/22 16:17
 */
@Slf4j
public class MpGenerator {
    public static void main(String[] args) {
        // 指定输出目录
        String path="E://generator";//(需修改)
        FastAutoGenerator.create("jdbc:mysql://192.168.10.145:3306/nfms?characterEncoding=utf8", "root", "ipu123")
                .globalConfig(builder -> {
                    builder.author("care.zheng") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .outputDir(path); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    if(typeCode == Types.DECIMAL && metaInfo.getScale() == 0){
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> {
                    builder.parent("com.vteam") // 设置父包名
                            .moduleName("cars") // 设置父包模块名
                            .entity("entity.model")
                            .mapper("entity.mapper")
                            .service("service.xxx") //xxx为所在模块包名(需修改)
                            .serviceImpl("service.xxx.impl") //(需修改)
                            .controller("api.controller.xxx") //(需修改)
                            .pathInfo(Collections.singletonMap(OutputFile.xml, path+"//mappers")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok().enableFileOverride().enableTableFieldAnnotation().logicDeleteColumnName("DEL_FLAG")
                            .addTableFills(new Column("RESOURCE_UUID", FieldFill.INSERT))
                            .addTableFills(new Column("CREATE_DATE", FieldFill.INSERT))
                            .addTableFills(new Column("LAST_MOD_DATE", FieldFill.INSERT_UPDATE))
                            .addTableFills(new Column("CREATE_USER", FieldFill.INSERT))
                            .addTableFills(new Column("LAST_MOD_USER", FieldFill.INSERT_UPDATE))
                            .addTableFills(new Column("EDTID", FieldFill.INSERT_UPDATE))
                            .addTableFills(new Column("DATA_STATUS", FieldFill.INSERT_UPDATE));
                    builder.mapperBuilder().enableFileOverride();
                    builder.serviceBuilder().enableFileOverride().formatServiceFileName("%sService");
                    builder.controllerBuilder().enableFileOverride().enableRestStyle();
                    builder.addInclude("facc_fcn_m"); // 设置需要生成的表名(需修改)
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
