package com.vteam.cars.entity.vo;

import com.vteam.cars.entity.model.FlogApiM;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 应用程序接口日志表扩展视图类 .<br>
 *
 * @author andy.sher
 * @date 2019/2/18 11:03
 */
@Getter
@Setter
@ToString
public class FlogApiMVo extends FlogApiM {

    private static final long serialVersionUID = -2976597809626779563L;


    /**
     * 响应起始时间
     */
    private LocalDateTime responseTimeStart;

    /**
     * 响应结束时间
     */
    private LocalDateTime responseTimeEnd;

    /**
     * 序号
     */
    private Integer index;

    /**
     * 导出模板接口类型
     */
    private String interfaceType;

    /**
     * 请求时间
     */
    private String requestDate;

    /**
     * 响应时间
     */
    private String responseDate;

    /**
     * 响应状态
     */
    private String status;

}
