package com.vteam.cars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 项目启动类 <br>
 *
 * @author Tianci.li
 * @date 2023/2/3 17:21
 */
@SpringBootApplication()
public class CarsApplication extends SpringBootServletInitializer {

    /**
     * 主入口
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(CarsApplication.class, args);
    }
}
