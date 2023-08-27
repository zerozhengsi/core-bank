package com.vteam.cars.entity.vo;

import com.vteam.cars.entity.model.EmailConfig;
import lombok.Getter;
import lombok.Setter;

/**
 * @className: EmailConfigVo
 * @Description: TODO
 * @version: v1.８.0
 * @author: care.zheng
 * @date: 2023/6/15 14:05
 */
@Getter
@Setter
public class EmailConfigVo extends EmailConfig {
    /**
     * 产品名称
     */
    private String productName;
}
