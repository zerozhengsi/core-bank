package com.vteam.cars.entity.vo;

import com.vteam.cars.entity.model.FlogOptM;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 操作日志扩展类
 *
 * @author zack.yin
 * @date 2018/7/25 19:10
 */
@Getter
@Setter
@ToString(callSuper = true)
public class FlogOptMVo extends FlogOptM {

    private static final long serialVersionUID = -1670675918634886756L;

    /**
     * 用户代号
     */
    private String userid;

    /**
     * 操作时间起
     */
    private LocalDateTime operateDateStart;

    /**
     * 操作时间止
     */
    private LocalDateTime operateDateEnd;

    /**
     * 操作时间
     */
    private String operateTime;

    /**
     * 多个企业流水号
     */
    private String[] orgRefcodes;
}