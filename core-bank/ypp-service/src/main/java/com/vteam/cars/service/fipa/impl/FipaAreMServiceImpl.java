package com.vteam.cars.service.fipa.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vteam.cars.constant.FieldConstant;
import com.vteam.cars.entity.mapper.FipaAreMMapper;
import com.vteam.cars.entity.model.FipaAreM;
import com.vteam.cars.entity.vo.FipaAreMVo;
import com.vteam.cars.service.fipa.FipaAreMService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 地区信息表 服务实现类
 * </p>
 *
 * @author vteam-generator
 * @since 2022-10-14
 */
@Service
public class FipaAreMServiceImpl extends ServiceImpl<FipaAreMMapper, FipaAreM> implements FipaAreMService {

    @Override
    public List<FipaAreMVo> listAreByCondition(FipaAreMVo fipaAreMVo) {
        List<FipaAreMVo> fipaAreMVoList = new ArrayList<>();
        LambdaQueryWrapper<FipaAreM> wr = new QueryWrapper<FipaAreM>().lambda();
        wr.eq(FipaAreM::getDelFlag, FieldConstant.PublicFieldValue.DEL_FLAG_NO);
        if (StringUtils.isNotEmpty(fipaAreMVo.getProvinceCode())) {
            wr.eq(FipaAreM::getProvinceCode, fipaAreMVo.getProvinceCode());
        }
        if (StringUtils.isNotEmpty(fipaAreMVo.getProvinceName())) {
            wr.eq(FipaAreM::getProvinceName, fipaAreMVo.getProvinceName());
        }
        if (StringUtils.isNotEmpty(fipaAreMVo.getCityName())) {
            wr.eq(FipaAreM::getCityName, fipaAreMVo.getCityName());
        }
        if (StringUtils.isNotEmpty(fipaAreMVo.getCountyName())) {
            wr.eq(FipaAreM::getCountyName, fipaAreMVo.getCountyName());
        }
        if (StringUtils.isNotEmpty(fipaAreMVo.getAreaCode())) {
            wr.eq(FipaAreM::getAreaCode, fipaAreMVo.getAreaCode());
        }
        if (StringUtils.isNotEmpty(fipaAreMVo.getAreaLevel())) {
            wr.eq(FipaAreM::getAreaLevel, fipaAreMVo.getAreaLevel());
        }
        if (CollectionUtils.isNotEmpty(fipaAreMVo.getIncludeCodeList())) {
            wr.in(FipaAreM::getAreaCode, fipaAreMVo.getIncludeCodeList());
        }
        if (CollectionUtils.isNotEmpty(fipaAreMVo.getExcludeCodeList())) {
            wr.notIn(FipaAreM::getAreaCode, fipaAreMVo.getExcludeCodeList());
        }
        wr.select(FipaAreM::getRefcode, FipaAreM::getAreaCode, FipaAreM::getProvinceCode, FipaAreM::getAreaLevel, FipaAreM::getAreaDesc, FipaAreM::getCityName, FipaAreM::getCountyName, FipaAreM::getProvinceName, FipaAreM::getAreaTypeCode, FipaAreM::getPostCode, FipaAreM::getTownName);
        wr.orderByDesc(FipaAreM::getAreaCode);
        List<FipaAreM> fipaAreMList = list(wr);
        for (FipaAreM fipaAreM : fipaAreMList) {
            FipaAreMVo temp = new FipaAreMVo();
            BeanUtils.copyProperties(fipaAreM, temp);
            fipaAreMVoList.add(temp);
        }
        return fipaAreMVoList;
    }


}
