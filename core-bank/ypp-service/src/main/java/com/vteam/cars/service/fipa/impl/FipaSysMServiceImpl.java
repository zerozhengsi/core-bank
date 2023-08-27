package com.vteam.cars.service.fipa.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vteam.cars.constant.FieldConstant;
import com.vteam.cars.entity.mapper.FipaSysMMapper;
import com.vteam.cars.entity.model.FipaSysM;
import com.vteam.cars.entity.vo.FipaSysMVo;
import com.vteam.cars.service.fipa.FipaSysMService;
import com.vteam.cars.util.BeanUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统参数设定表 服务实现类
 * </p>
 *
 * @author vteam-generator
 * @since 2022-10-14
 */
@Service
public class FipaSysMServiceImpl extends ServiceImpl<FipaSysMMapper, FipaSysM> implements FipaSysMService {

    @Override
    public List<FipaSysMVo> listSystemConfig() {
        List<FipaSysMVo> fipaSysMVoList = null;

        // 查询参数列表
        LambdaQueryWrapper<FipaSysM> queryWrapper = new QueryWrapper<FipaSysM>().lambda();
        queryWrapper.eq(FipaSysM::getDelFlag, FieldConstant.PublicFieldValue.DEL_FLAG_NO);
        List<FipaSysM> fipaSysMList = list(queryWrapper);

        // 装配参数列表
        if (CollectionUtils.isNotEmpty(fipaSysMList)) {
            fipaSysMVoList = BeanUtils.copyList(fipaSysMList, FipaSysMVo.class);
        }

        return fipaSysMVoList;
    }

}
