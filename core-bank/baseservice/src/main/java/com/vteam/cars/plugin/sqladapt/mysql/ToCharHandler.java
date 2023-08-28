package com.vteam.cars.plugin.sqladapt.mysql;

import com.vteam.cars.util.StringUtils;
import com.vteam.vtarm.sql.BaseMYSQLHandler;
import com.vteam.vtarm.sql.SQLModel;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TO_CHAR函数
 *
 * @author jiangming.huang
 * @date 2019/7/8 13:49
 */
@Component
public class ToCharHandler extends BaseMYSQLHandler {

    public static final Pattern pattern = Pattern.compile("TO_CHAR\\s*\\((.*?)\\)");

    @Override
    public void doHandler(SQLModel model) {
        // 获取SQL语句
        String sql = model.getSql();
        if (StringUtils.isNotBlank(sql)) {
            sql = sql.toUpperCase();
            //     sql = sql.replaceAll("to_char", "TO_CHAR");
            //     sql = sql.replaceAll("now()", "NOW()");
            // now()和to_char()嵌套处理;now()转成now--;最后将now--还原成now()
            sql = sql.replaceAll("NOW\\(\\)", "NOW99999");
            if (sql.indexOf("TO_CHAR") > -1) {
                Matcher matcher = pattern.matcher(sql);
                while (matcher.find()) {
                    // 转换后的函数
                    String exp;
                    String matchParm = matcher.group(1);
                    //由于mysql自定义函数to_char不支持一个参数，所以删除to_char
                    int pos = matchParm.indexOf(",");
                    if (pos > -1) {
                        // 获取第一个参数
                        //    String dateParm =  matcher.group(1).toUpperCase();
                        String dateParm = matchParm.substring(0, pos);
                        // 获取第二个参数
                        // String formatParm = matcher.group(2).toUpperCase();
                        String formatParm = matchParm.substring(pos + 1).trim();
                        if (formatParm.indexOf("Q") > -1) {
                            exp = "QUARTER(" + dateParm + ")";
                            sql = matcher.replaceFirst(exp);
                        } else {
                            formatParm = formatParm.replaceAll("YYYY-MM-DD HH24:MI:SS", "%Y-%m-%d %H:%i:%s")
                                    .replaceAll("YYYY-MM-DD HH:MI:SS", "%Y-%m-%d %h:%i:%s")
                                    .replaceAll("YYYY-MM-DD", "%Y-%m-%d")
                                    .replaceAll("YYYY-MM", "%Y-%m")
                                    .replaceAll("YYYY", "%Y")
                                    .replaceAll("MM", "%m")
                                    .replaceAll("DD", "%d")
                                    .replaceAll("D", "%w" + 1);
                            exp = "DATE_FORMAT(" + dateParm + "," + formatParm + ")";
                            sql = matcher.replaceFirst(exp);
                        }
                    } else {
                        exp = matchParm;
                        sql = matcher.replaceFirst(exp);
                    }
                    matcher = pattern.matcher(sql);
                }
                // 替换完成后从新装入SQL
                sql = sql.replaceAll("NOW99999", "NOW\\(\\)");
                model.setSql(sql);
            }
            // 判断是否有下一个处理器
            if (this.hasNext()) {
                // 调用下一个处理器执行
                this.getNextHandler().doHandler(model);
            }
        }
    }


    @PostConstruct
    @Override
    public void init() {
    }

    @PreDestroy
    @Override
    public void destroy() {
    }

}
