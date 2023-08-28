package com.vteam.cars.entity.vo;

import com.vteam.cars.entity.model.FipaAreM;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 地区信息扩展表
 *
 * @author li.jin
 * @date 2022/10/20 9:31
 */
@Getter
@Setter
@ToString
public class FipaAreMVo extends FipaAreM {

    private static final long serialVersionUID = 7101121320071654834L;

    /**
     * 所属市
     */
    private List<FipaAreMVo> children;

    /**
     * 值
     */
    private String value;

    /**
     * 属性
     */
    private String label;

    /**
     * 包含的所属地区代码
     */
    private List<String> includeCodeList;

    /**
     * 不包含的地区代码
     */
    private List<String> excludeCodeList;
}
