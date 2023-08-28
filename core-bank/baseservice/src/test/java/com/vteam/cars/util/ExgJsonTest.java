package com.vteam.cars.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import org.apache.commons.text.StringSubstitutor;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class ExgJsonTest {

    @Test
    public void testExgJson() {
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get("E:\\constant_zh-CN.js")));

            // 解析 JSON 字符串为 JSONObject
            JSONObject jsonObject = JSONObject.parseObject(jsonString, Feature.OrderedField);

            // 输出整个 JSON 文件的内容
            System.out.println("JSON Object:");
            System.out.println(jsonObject.toJSONString());

            // 传入要转换的属性
            String type="lineUnit";
            JSONArray jsonArray = jsonObject.getJSONArray(type);
            if (jsonArray != null) {
                System.out.println("\nArray Property:");
                for (Object obj : jsonArray) {
                    generateSql(type,(Map<String,String>)obj);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 根据类型生成后端参数配置（数据字典）表数据
    public void generateSql(String type, Map<String,String> model){
        model.put("type",type);
        String fspa_exg_m="INSERT INTO `fspa_exg_m` (`REFCODE`, `PARM_TYPE`, `PARM_NAME`, `PARM_VALUE`, `PARM_DESC`, `PARENT_ID`, `PARENT_TYPE`, `PARM_FLAG`) VALUES (nextval('SEQUENCE_FSPA_EXG_M'), '${type}', '${key}', '${type}_${key}', '${value}', '', '', '2');";
        String fspa_col_m="INSERT INTO `fspa_col_m` (`REFCODE`, `COL_ID`, `LANGUAGE_ID`, `COL_DESC`) VALUES (nextval('SEQUENCE_FSPA_COL_M'), '${type}_${key}', 'zh_CN', '${value}');";
        String fspa_exg_z=StringSubstitutor.replace(fspa_exg_m, model);
        System.out.println(fspa_exg_z);
        String fspa_col_z=StringSubstitutor.replace(fspa_col_m, model);
        System.out.println(fspa_col_z);
    }
}
