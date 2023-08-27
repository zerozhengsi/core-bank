package com.vteam.cars.service.fipa.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vteam.cars.constant.FieldConstant;
import com.vteam.cars.entity.mapper.FipaOtpMMapper;
import com.vteam.cars.entity.model.FipaOtpM;
import com.vteam.cars.entity.vo.FipaOtpMVo;
import com.vteam.cars.service.fipa.FipaOtpMService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 行业类别表 服务实现类
 * </p>
 *
 * @author vteam-generator
 * @since 2022-10-14
 */
@Service
public class FipaOtpMServiceImpl extends ServiceImpl<FipaOtpMMapper, FipaOtpM> implements FipaOtpMService {

    @Override
    public List<FipaOtpM> listFipaOtpMByParams(FipaOtpMVo fipaOtpMVo) {
        LambdaQueryWrapper<FipaOtpM> wr = new QueryWrapper<FipaOtpM>().lambda();
        wr.eq(FipaOtpM::getDelFlag, FieldConstant.PublicFieldValue.DEL_FLAG_NO);
        return super.list(wr);
    }

}
