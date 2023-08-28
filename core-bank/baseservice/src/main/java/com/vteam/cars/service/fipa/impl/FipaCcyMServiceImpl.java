package com.vteam.cars.service.fipa.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vteam.cars.constant.FieldConstant;
import com.vteam.cars.entity.mapper.FipaCcyMMapper;
import com.vteam.cars.entity.model.FipaCcyM;
import com.vteam.cars.entity.vo.FipaCcyMVo;
import com.vteam.cars.service.fipa.FipaCcyMService;
import com.vteam.cars.util.StringUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 货币别信息表 服务实现类
 * </p>
 *
 * @author vteam-generator
 * @since 2018-07-20
 */
@Service
public class FipaCcyMServiceImpl extends ServiceImpl<FipaCcyMMapper, FipaCcyM> implements FipaCcyMService {

    @Override
    public List<FipaCcyMVo> listCurrency() {
        LambdaQueryWrapper<FipaCcyM> wr = new QueryWrapper<FipaCcyM>().lambda();
        wr.eq(FipaCcyM::getDelFlag, FieldConstant.PublicFieldValue.DEL_FLAG_NO);
        List<FipaCcyM> fipaCcyMList = list(wr);
        List<FipaCcyMVo> fipaCcyMVoList = null;
        if (CollectionUtils.isNotEmpty(fipaCcyMList)) {
            fipaCcyMVoList = new ArrayList<>(fipaCcyMList.size());
            FipaCcyMVo fipaCtyMVo;
            for (FipaCcyM fipaCtyM : fipaCcyMList) {
                fipaCtyMVo = new FipaCcyMVo();
                BeanUtils.copyProperties(fipaCtyM, fipaCtyMVo);
                fipaCcyMVoList.add(fipaCtyMVo);
            }
        }

        return fipaCcyMVoList;
    }

    @Override
    public List<FipaCcyMVo> listCurrencyByCondition(FipaCcyMVo fipaCcyMVo) {
        List<FipaCcyMVo> fipaCcyMVoList = new ArrayList<>();
        LambdaQueryWrapper<FipaCcyM> wr = new QueryWrapper<FipaCcyM>().lambda();
        wr.eq(FipaCcyM::getDelFlag, FieldConstant.PublicFieldValue.DEL_FLAG_NO);
        if (StringUtils.isNotEmpty(fipaCcyMVo.getCcyid())) {
            wr.eq(FipaCcyM::getCcyid, fipaCcyMVo.getCcyid());
        }
        List<FipaCcyM> fipaCcyMList = list(wr);
        if (CollectionUtils.isNotEmpty(fipaCcyMList)) {
            for (FipaCcyM fipaCcyM : fipaCcyMList) {
                FipaCcyMVo temp = new FipaCcyMVo();
                BeanUtils.copyProperties(fipaCcyM, temp);
                fipaCcyMVoList.add(temp);
            }
        }
        return fipaCcyMVoList;
    }
}
