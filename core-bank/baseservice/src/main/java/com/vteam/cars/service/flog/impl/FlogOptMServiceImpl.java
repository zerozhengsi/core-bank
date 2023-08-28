package com.vteam.cars.service.flog.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vteam.cars.common.RequestStore;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.entity.mapper.FlogOptMMapper;
import com.vteam.cars.entity.model.FlogOptM;
import com.vteam.cars.entity.vo.FlogOptMVo;
import com.vteam.cars.entity.vo.SipaBurMVo;
import com.vteam.cars.service.flog.FlogOptMService;
import com.vteam.cars.util.DateUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 操作日志表 服务实现类
 * </p>
 *
 * @author vteam-generator
 * @since 2018-07-20
 */
@Service
public class FlogOptMServiceImpl extends ServiceImpl<FlogOptMMapper, FlogOptM> implements FlogOptMService {

    @Resource(type = FlogOptMMapper.class)
    private FlogOptMMapper flogOptMMapper;

    @Override
    public List<FlogOptMVo> listOperationLogByCondition(FlogOptMVo condition) {
        // 获取符合条件的类表
        List<FlogOptMVo> flogOptMVoList = flogOptMMapper.listOperationLogByCondition(condition);
        if (CollectionUtils.isNotEmpty(flogOptMVoList)) {
            for (FlogOptMVo flogOptMVo : flogOptMVoList) {
                flogOptMVo.setOperateTime(flogOptMVo.getOperateDate().format(DateUtils.FMT_SECOND));
            }
        }
        return flogOptMVoList;
    }

    @Override
    public void saveLogoutInfo(SipaBurMVo sipaBurMVo) {
        final String editId = GlobalConstants.Flag.LOGOUT_CN;
        final String edtDesc = editId + GlobalConstants.Flag.SUCCESS_CN + GlobalConstants.Symbol.PERCENT_STOP;
        this.doSaveLog(sipaBurMVo, editId, edtDesc);
    }

    @Override
    public void saveLoginInfo(SipaBurMVo sipaBurMVo) {
        final String editId = GlobalConstants.Flag.LOGIN_CN;
        final String edtDesc = editId + GlobalConstants.Flag.SUCCESS_CN + GlobalConstants.Symbol.PERCENT_STOP;
        this.doSaveLog(sipaBurMVo, editId, edtDesc);
    }

    @Override
    public void saveLogInfo(String edtid, String edtDesc) {
        // 记录需要查询的冗余字段
        SipaBurMVo loginUser = RequestStore.getLoginUser();
        loginUser.setOrgRefcode(RequestStore.getLoginUser().getOrgRefcode());
        this.doSaveLog(loginUser, edtid, edtDesc);
    }

    @Override
    public void saveLogInfo(SipaBurMVo sipaBurMVo, String edtid, String edtDesc) {
        this.doSaveLog(sipaBurMVo, edtid, edtDesc);
    }

    /**
     * 保存操作日志信息
     *
     * @param sipaBurMVo
     * @param edtid
     * @param edtDesc
     */
    private void doSaveLog(SipaBurMVo sipaBurMVo, String edtid, String edtDesc) {
        FlogOptMVo flogOptMVo = new FlogOptMVo();

        final Integer orgRefcode = sipaBurMVo.getOrgRefcode();
        flogOptMVo.setOperator(sipaBurMVo.getUserid());
        flogOptMVo.setEdtid(edtid);
        // modify by chad.mei 202204271718 日志操作描述截取长度500
        flogOptMVo.setEdtDesc(edtDesc.length() <= 500 ? edtDesc : edtDesc.substring(0, 500));
        flogOptMVo.setOperateDate(LocalDateTime.now());
        flogOptMVo.setOperatorIp(RequestStore.getIpAddress());
        flogOptMVo.setOperatorOrgRefcode(orgRefcode);

        // 记录需要查询的冗余字段
        flogOptMVo.setUserName(sipaBurMVo.getUserName());
        flogOptMVo.setMobilephone(sipaBurMVo.getMobilephone());

        super.save(flogOptMVo);
    }

}

