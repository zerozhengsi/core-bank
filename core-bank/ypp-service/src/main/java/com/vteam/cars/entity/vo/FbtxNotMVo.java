package com.vteam.cars.entity.vo;

import com.vteam.cars.entity.model.FbtxNotM;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * 消息扩展类 .<br>
 *
 * @author andy.sher
 * @date 2018/7/25 22:14
 */
@Getter
@Setter
@ToString(callSuper = true)
public class FbtxNotMVo extends FbtxNotM {

    private static final long serialVersionUID = 3281574358032010691L;

    /**
     * 发送方名称
     */
    private String pushName;

    /**
     * 消息时间起
     */
    private LocalDate createDateStart;

    /**
     * 消息时间止
     */
    private LocalDate createDateEnd;

    /**
     * 流水号（多个间用逗号分隔）
     */
    private String refcodes;


    /**
     * 消息类型[1=正常/B=商承通/T=贸易通/P=保付单/F=供应链+反向保理业务/M=撮合/I=国际业务]
     */
    private String[] noticeTypes;

}
