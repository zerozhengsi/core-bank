package com.vteam.cars.entity.vo;

import com.vteam.cars.entity.model.FipaHdyM;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 节假日信息表扩展类
 *
 * @author jiangming.huang
 * @date 2018/11/14 0014 下午 5:40
 */
@Getter
@Setter
@ToString(callSuper = true)
public class FipaHdyMVo extends FipaHdyM {
    private static final long serialVersionUID = 3685535236381514449L;

    /**
     * 开始年
     */
    private Integer startHdyYear;

    /**
     * 开始月
     */
    private Integer startHdyMonth;

    /**
     * 开始日
     */
    private Integer startHdyDay;

    /**
     * 结束年
     */
    private Integer endHdyYear;

    /**
     * 结束月
     */
    private Integer endHdyMonth;

    /**
     * 结束日
     */
    private Integer endHdyDay;

    /**
     * 是否节假日[1=是/0=否]
     */
    private String isHoliday;

}
