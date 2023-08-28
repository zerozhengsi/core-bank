package com.vteam.cars.service.flog.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vteam.cars.entity.mapper.FlogApiMMapper;
import com.vteam.cars.entity.model.FlogApiM;
import com.vteam.cars.entity.vo.FlogApiMVo;
import com.vteam.cars.service.flog.FlogApiMService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 应用程序接口记录表 服务实现类
 * </p>
 *
 * @author vteam-generator
 * @since 2019-02-18
 */
@Service
public class FlogApiMServiceImpl extends ServiceImpl<FlogApiMMapper, FlogApiM> implements FlogApiMService {


    @Resource(type = FlogApiMMapper.class)
    private FlogApiMMapper flogApiMMapper;


    @Override
    public List<FlogApiMVo> listApiLogByCondition(FlogApiMVo flogApiMVo) {
        return flogApiMMapper.listApiLogByCondition(flogApiMVo);
    }

}
