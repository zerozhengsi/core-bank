package com.vteam.cars.service.fipa;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vteam.cars.entity.model.FipaHdyM;
import com.vteam.cars.entity.vo.FipaHdyMVo;

import java.util.List;

/**
 * <p>
 * 节假日信息表 服务类
 * </p>
 *
 * @author vteam-generator
 * @since 2018-11-14
 */
public interface FipaHdyMService extends IService<FipaHdyM> {

    /**
     * 根据条件查询节假日信息表
     *
     * @param conditon
     * @return java.util.List<com.vteam.sme.entity.model.FipaHdyM>
     * @author jiangming.huang
     * @date 2018/11/14 0014 下午 5:43
     */
    List<FipaHdyM> listFipaHdyMByConditon(FipaHdyMVo conditon);

    /**
     * 删除节假日 .
     *
     * @param fipaHdyMVo
     * @return void
     * @author fu.tong
     * @date 2019/10/17 14:03
     */
    void doRemoveHoliday(FipaHdyMVo fipaHdyMVo);
    
    /**
     * 删除节假日 .
     *
     * @param fipaHdyMVo
     * @return void
     * @author fu.tong
     * @date 2019/10/17 14:03
     */
    FipaHdyM getHolidayInfo(FipaHdyMVo fipaHdyMVo);
}
