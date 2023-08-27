package com.vteam.cars.plugin.provider;

import com.vteam.cars.entity.model.FipaOtpM;
import com.vteam.cars.entity.vo.FipaOtpMVo;
import com.vteam.cars.service.fipa.FipaOtpMService;
import com.vteam.vtarm.cache.MapContainer;
import com.vteam.vtarm.data.Provider;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 行业类别缓存数据提供者 .<br>
 *
 * @author andy.sher
 * @date 2019/6/21 16:10
 */
@Component
public class OtpInfoProvider implements Provider {

    public static final String OTP_CODE_LEVEL = "otp-code-level";

    public static final String OTP_NAME = "otp-name";

    public static final String OTP_ID = "otp-id";

    @Resource(type = FipaOtpMService.class)
    private FipaOtpMService fipaOtpMService;

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
        // 缓存所有的行业信息
        FipaOtpMVo otpCondition = new FipaOtpMVo();
        List<FipaOtpM> fipaOtpMList = fipaOtpMService.listFipaOtpMByParams(otpCondition);
        if (CollectionUtils.isNotEmpty(fipaOtpMList)) {
            // 行业级别
            Integer OTP_LEVEL = 1;
            for (FipaOtpM temp : fipaOtpMList) {
                mapContainer.put(OTP_NAME, temp.getOtpcode(), temp.getOtpname());
                mapContainer.put(OTP_ID, temp.getOtpcode(), temp.getOtpid());
                if (OTP_LEVEL.equals(temp.getOtplvl())) {
                    mapContainer.put(OTP_CODE_LEVEL, temp.getOtpname() + OTP_LEVEL, temp.getOtpcode());
                }
            }
        }
    }

    @Override
    public boolean needRebuild() {
        return false;
    }
}
