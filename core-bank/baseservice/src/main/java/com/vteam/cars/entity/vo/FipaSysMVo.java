package com.vteam.cars.entity.vo;

import com.vteam.cars.entity.model.FipaSysM;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 系统配置表 .<br>
 *
 * @author andy.sher
 * @date 2018/8/7 13:42
 */
@Getter
@Setter
@ToString
public class FipaSysMVo extends FipaSysM {

    private static final long serialVersionUID = -3022270906144478534L;

    /**
     * 产品栏目code
     */
    private String classifyCode;

    /**
     * 产品栏目名称
     */
    private String classifyName;

}
