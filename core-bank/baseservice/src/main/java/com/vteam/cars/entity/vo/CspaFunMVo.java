package com.vteam.cars.entity.vo;

import com.vteam.cars.entity.model.CspaFunM;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能权限扩展类
 *
 * @author fu.tong
 * @date 2018/7/31 0031 13:34
 */
@Getter
@Setter
@NoArgsConstructor()
public class CspaFunMVo extends CspaFunM {
    private static final long serialVersionUID = -499830777066819835L;

    /**
     * 子菜单
     */
    private List<CspaFunMVo> childFuns = new ArrayList<>();

}
