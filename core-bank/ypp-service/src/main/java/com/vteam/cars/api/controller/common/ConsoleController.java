package com.vteam.cars.api.controller.common;

import com.vteam.cars.plugin.provider.I18NResourceProvider;
import com.vteam.cars.plugin.provider.SystemConfigProvider;
import com.vteam.vtarm.data.DataProvider;
import com.vteam.vtarm.lucene.LuceneDispatcher;
import com.vteam.vtarm.task.Task;
import com.vteam.vtarm.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.annotation.Resource;
import java.io.IOException;

/**
 * 控制台 .<br>
 *
 * @author andy.sher
 * @date 2019/1/24 16:34
 */
@Slf4j
@Controller
@RequestMapping("/console")
public class ConsoleController {

    @Resource(type = LuceneDispatcher.class)
    private LuceneDispatcher luceneDispatcher;

    @Resource(type = DataProvider.class)
    private DataProvider dataProvider;

    /**
     * 控制台主面板 .
     *
     * @return java.lang.String
     * @author andy.sher
     * @date 2019/1/24 16:35
     */
    @RequestMapping("/index")
    public String console() {
        return "console";
    }

    /**
     * 重新加载缓存 .
     *
     * @return java.lang.String
     * @author andy.sher
     * @date 2019/1/29 11:17
     */
    @RequestMapping("/reloadCache")
    public ModelAndView reloadCache() {
        dataProvider.rebuild(SystemConfigProvider.class);

        return new ModelAndView("console");
    }

    /**
     * 重新加载I18N资源 .
     *
     * @return java.lang.String
     * @author andy.sher
     * @date 2019/1/29 11:22
     */
    @RequestMapping("/reloadI18NResource")
    public ModelAndView reloadI18NResource() {
        dataProvider.rebuild(I18NResourceProvider.class);
        return new ModelAndView("console");
    }

    /**
     * 触发定时任务 .
     *
     * @param name 定时任务名
     * @return org.springframework.web.servlet.ModelAndView
     * @author andy.sher
     * @date 2019/4/28 10:59
     */
    @GetMapping("/triggerJob/{name}")
    public ModelAndView triggerJob(@PathVariable("name") String name) throws IOException {

        char[] nameChars = name.toCharArray();
        nameChars[0] = Character.toLowerCase(name.charAt(0));
        String realName = new String(nameChars);
        Task task = SpringContextUtils.getBean(realName, Task.class);
        task.execute();

        return new ModelAndView("console");
    }
}
