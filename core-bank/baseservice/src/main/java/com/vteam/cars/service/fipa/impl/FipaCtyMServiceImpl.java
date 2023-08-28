package com.vteam.cars.service.fipa.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vteam.cars.constant.FieldConstant;
import com.vteam.cars.entity.mapper.FipaCtyMMapper;
import com.vteam.cars.entity.model.FipaCtyM;
import com.vteam.cars.entity.vo.FipaCtyMVo;
import com.vteam.cars.service.fipa.FipaCtyMService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 国家别信息表 服务实现类
 * </p>
 *
 * @author vteam-generator
 * @since 2018-09-14
 */
@Service
public class FipaCtyMServiceImpl extends ServiceImpl<FipaCtyMMapper, FipaCtyM> implements FipaCtyMService {

    @Override
    public List<FipaCtyMVo> listCountry() {
        LambdaQueryWrapper<FipaCtyM> wr = new QueryWrapper<FipaCtyM>().lambda();
        wr.eq(FipaCtyM::getDelFlag, FieldConstant.PublicFieldValue.DEL_FLAG_NO);
        List<FipaCtyM> fipaCtyMList = list(wr);
        List<FipaCtyMVo> fipaCtyMVoList = null;
        if (CollectionUtils.isNotEmpty(fipaCtyMList)) {
            fipaCtyMVoList = new ArrayList<>(fipaCtyMList.size());
            FipaCtyMVo fipaCtyMVo;
            for (FipaCtyM fipaCtyM : fipaCtyMList) {
                fipaCtyMVo = new FipaCtyMVo();
                BeanUtils.copyProperties(fipaCtyM, fipaCtyMVo);
                fipaCtyMVoList.add(fipaCtyMVo);
            }
        }

        return fipaCtyMVoList;
    }

    @Override
    public FipaCtyMVo getCtyDescByCtyid(String ctyid) {
        FipaCtyMVo fipaCtyMVo = new FipaCtyMVo();
        LambdaQueryWrapper<FipaCtyM> wr = new QueryWrapper<FipaCtyM>().lambda();
        wr.eq(FipaCtyM::getDelFlag, FieldConstant.PublicFieldValue.DEL_FLAG_NO);
        wr.eq(FipaCtyM::getCtyid, ctyid);
        List<FipaCtyM> fipaCtyMList = list(wr);
        if (CollectionUtils.isNotEmpty(fipaCtyMList)) {
            BeanUtils.copyProperties(fipaCtyMList.get(0), fipaCtyMVo);
        }
        return fipaCtyMVo;
    }

}
