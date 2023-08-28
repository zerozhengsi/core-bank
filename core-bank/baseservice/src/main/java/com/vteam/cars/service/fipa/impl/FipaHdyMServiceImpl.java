package com.vteam.cars.service.fipa.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vteam.cars.constant.FieldConstant;
import com.vteam.cars.entity.mapper.FipaHdyMMapper;
import com.vteam.cars.entity.model.FipaHdyM;
import com.vteam.cars.entity.vo.FipaHdyMVo;
import com.vteam.cars.service.fipa.FipaHdyMService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 节假日信息表 服务实现类
 * </p>
 *
 * @author vteam-generator
 * @since 2018-11-14
 */
@Service
public class FipaHdyMServiceImpl extends ServiceImpl<FipaHdyMMapper, FipaHdyM> implements FipaHdyMService {

    /**
     * 根据条件查询节假日信息表
     */
    @Override
    public List<FipaHdyM> listFipaHdyMByConditon(FipaHdyMVo conditon) {
        LambdaQueryWrapper<FipaHdyM> queryWrapper = new QueryWrapper<FipaHdyM>().lambda();
        queryWrapper.eq(FipaHdyM::getDelFlag, FieldConstant.PublicFieldValue.DEL_FLAG_NO);
        // 年
        if (null != conditon.getHdyYear()) {
            queryWrapper.eq(FipaHdyM::getHdyYear, conditon.getHdyYear());
        }
        // 月
        if (null != conditon.getHdyMonth()) {
            queryWrapper.eq(FipaHdyM::getHdyMonth, conditon.getHdyMonth());
        }
        // 日
        if (null != conditon.getHdyDay()) {
            queryWrapper.eq(FipaHdyM::getHdyDay, conditon.getHdyDay());
        }
        // 开始年
        if (null != conditon.getStartHdyDay()) {
            queryWrapper.ge(FipaHdyM::getHdyYear, conditon.getStartHdyYear());
        }
        // 开始月
        if (null != conditon.getStartHdyMonth()) {
            queryWrapper.ge(FipaHdyM::getHdyMonth, conditon.getStartHdyMonth());
        }
        // 开始日
        if (null != conditon.getStartHdyDay()) {
            queryWrapper.ge(FipaHdyM::getHdyDay, conditon.getStartHdyDay());
        }
        // 结束年
        if (null != conditon.getEndHdyYear()) {
            queryWrapper.le(FipaHdyM::getHdyYear, conditon.getEndHdyYear());
        }
        // 结束月
        if (null != conditon.getEndHdyMonth()) {
            queryWrapper.le(FipaHdyM::getHdyMonth, conditon.getEndHdyMonth());
        }
        // 结束日
        if (null != conditon.getEndHdyDay()) {
            queryWrapper.le(FipaHdyM::getHdyDay, conditon.getEndHdyDay());
        }
        return super.list(queryWrapper);
    }

    @Override
    public void doRemoveHoliday(FipaHdyMVo fipaHdyMVo) {
        LambdaQueryWrapper<FipaHdyM> wr = new QueryWrapper<FipaHdyM>().lambda();
        if (fipaHdyMVo.getHdyYear() != null) {
            wr.eq(FipaHdyM::getHdyYear, fipaHdyMVo.getHdyYear());
        }
        if (fipaHdyMVo.getHdyMonth() != null) {
            wr.eq(FipaHdyM::getHdyMonth, fipaHdyMVo.getHdyMonth());
        }
        if (fipaHdyMVo.getHdyDay() != null) {
            wr.eq(FipaHdyM::getHdyDay, fipaHdyMVo.getHdyDay());
        }
        fipaHdyMVo.setDelFlag(FieldConstant.PublicFieldValue.DEL_FLAG_YES);
        update(fipaHdyMVo, wr);
    }

	@Override
	public FipaHdyM getHolidayInfo(FipaHdyMVo fipaHdyMVo) {
		LambdaQueryWrapper<FipaHdyM> wr = new QueryWrapper<FipaHdyM>().lambda();
        if (fipaHdyMVo.getHdyYear() != null) {
            wr.eq(FipaHdyM::getHdyYear, fipaHdyMVo.getHdyYear());
        }
        if (fipaHdyMVo.getHdyMonth() != null) {
            wr.eq(FipaHdyM::getHdyMonth, fipaHdyMVo.getHdyMonth());
        }
        if (fipaHdyMVo.getHdyDay() != null) {
            wr.eq(FipaHdyM::getHdyDay, fipaHdyMVo.getHdyDay());
        }
        wr.eq(FipaHdyM::getDelFlag, FieldConstant.PublicFieldValue.DEL_FLAG_NO);
		return getOne(wr);
	}
}
