package com.vteam.cars.plugin.provider;

import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.entity.vo.FspaExgMVo;
import com.vteam.cars.service.fspa.FspaExgMService;
import com.vteam.vtarm.cache.MapContainer;
import com.vteam.vtarm.data.Provider;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * exg参数表数据提供者 .<br>
 *
 * @author andy.sher
 * @date 2019/6/21 13:13
 */
@Component
public class ExgParamProvider implements Provider {

    public static final String EXG_PARM = "exg-parm";

    @Resource(type = FspaExgMService.class)
    private FspaExgMService fspaExgMService;

    @Resource
    private MapContainer mapContainer;

    @Override
    public String get(String... key) {
        if (ArrayUtils.isNotEmpty(key)) {
            Object value = mapContainer.getHashValue(key[0], key[1]);
            if (null != value) {
                return value.toString();
            }
        }
        return StringUtils.EMPTY;
    }

    @Override
    public void build() {
        FspaExgMVo condition = null;
        List<FspaExgMVo> propertyList = null;

        condition = new FspaExgMVo();
        condition.setLanguageId(GlobalConstants.Language.zh_CN);
        propertyList = fspaExgMService.listExgInfoByCondition(condition);
        cacheExgParams(propertyList, GlobalConstants.Language.zh_CN);

        condition = new FspaExgMVo();
        condition.setLanguageId(GlobalConstants.Language.zh_TW);
        propertyList = fspaExgMService.listExgInfoByCondition(condition);
        cacheExgParams(propertyList, GlobalConstants.Language.zh_TW);
    }

    @Override
    public boolean needRebuild() {
        return false;
    }

    /**
     * 缓存参数 .
     *
     * @param propertyList
     * @return void
     * @author fu.tong
     * @date 2019/6/27 10:24
     */
    private void cacheExgParams(List<FspaExgMVo> propertyList, String language) {
        if (CollectionUtils.isNotEmpty(propertyList)) {
            String key;
            for (FspaExgMVo fspaExgMVo : propertyList) {
                key = EXG_PARM + GlobalConstants.Symbol.SEMICOLON + language + GlobalConstants.Symbol.SEMICOLON + fspaExgMVo.getParmType();
                mapContainer.put(key, fspaExgMVo.getParmName(), fspaExgMVo.getParmDesc());
                mapContainer.put(key, fspaExgMVo.getParmDesc(), fspaExgMVo.getParmName());
            }
        }
    }
}
