package com.vteam.cars.service.fspa.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vteam.cars.config.SmeConfiguration;
import com.vteam.cars.entity.mapper.FspaExgMMapper;
import com.vteam.cars.entity.model.FspaExgM;
import com.vteam.cars.entity.vo.FspaExgMVo;
import com.vteam.cars.service.fspa.FspaExgMService;
import com.vteam.cars.util.StringUtils;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 参数配置表 服务实现类
 * </p>
 *
 * @author vteam-generator
 * @since 2022-10-14
 */
@Service
public class FspaExgMServiceImpl extends ServiceImpl<FspaExgMMapper, FspaExgM> implements FspaExgMService {

    @Resource(type = FspaExgMMapper.class)
    private FspaExgMMapper fspaExgMMapper;

    @Resource(type = SmeConfiguration.class)
    private SmeConfiguration smeConfiguration;

    @Override
    public String getParmValueByCondition(String parmType, String parmName) {
        FspaExgMVo fspaExgMVo = new FspaExgMVo();
        fspaExgMVo.setParmType(parmType);
        if (StringUtils.isEmpty(fspaExgMVo.getLanguageId())) {
            fspaExgMVo.setLanguageId(smeConfiguration.getSystemLanguage());
        }
        String colDesc = StringUtils.EMPTY;
        if (StringUtils.isNotEmpty(parmName)) {
            // 改造为支持多字典项数据
            String[] parmNames = parmName.split(",");

            for (String parm : parmNames) {
                fspaExgMVo.setParmName(parm);
                String desc = fspaExgMMapper.getParmValueByCondition(fspaExgMVo);
                if (StringUtils.isNotEmpty(desc)) {
                    if (StringUtils.isNotBlank(colDesc)) {
                        colDesc = desc;
                    } else {
                        colDesc = StringUtils.getCommaSeparated(colDesc, desc);
                    }
                }
            }
        }
        return colDesc;
    }

    @Override
    public List<FspaExgMVo> listExgInfoByCondition(FspaExgMVo fspaExgMVo) {
        if (StringUtils.isEmpty(fspaExgMVo.getLanguageId())) {
            fspaExgMVo.setLanguageId(smeConfiguration.getSystemLanguage());
        }
        List<FspaExgMVo> fspaExgMVoList = fspaExgMMapper.listExgInfoByCondition(fspaExgMVo);
        return fspaExgMVoList;
    }

}
