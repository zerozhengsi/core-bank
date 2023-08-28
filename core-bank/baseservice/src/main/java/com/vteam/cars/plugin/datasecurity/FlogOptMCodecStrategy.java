package com.vteam.cars.plugin.datasecurity;

import com.vteam.cars.entity.vo.FlogOptMVo;
import com.vteam.vtarm.codec.BaseDataCodecStrategy;
import org.springframework.stereotype.Service;


/**
 * 操作日主表数据解密策略 .
 *
 * @author zack.yin
 * @date 2019/6/5 10:11
 */

@Service
public class FlogOptMCodecStrategy extends BaseDataCodecStrategy {

    @Override
    public Class<?> getId() {
        return FlogOptMVo.class;
    }

    @Override
    public String[] getFields() {
        return new String[]{"orgname"};
    }

    @Override
    public void doEncrypt(Object target) {
    }
}
