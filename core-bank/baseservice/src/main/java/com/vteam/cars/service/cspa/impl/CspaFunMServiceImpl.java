package com.vteam.cars.service.cspa.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vteam.cars.config.SmeConfiguration;
import com.vteam.cars.constant.FieldConstant;
import com.vteam.cars.entity.mapper.CspaFunMMapper;
import com.vteam.cars.entity.model.CspaFunM;
import com.vteam.cars.entity.vo.CspaFunMVo;
import com.vteam.cars.service.cspa.CspaFunMService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统资源表 服务实现类
 * </p>
 *
 * @author vteam-generator
 * @since 2022-10-14
 */
@Service
public class CspaFunMServiceImpl extends ServiceImpl<CspaFunMMapper, CspaFunM> implements CspaFunMService {

    @Resource(type = SmeConfiguration.class)
    private SmeConfiguration smeConfiguration;

    @Override
    public List<CspaFunMVo> listCspaFunMvo(List<Integer> funRefcodeList, String systemType, String subUserType) {
        // 获取公共的查询条件对象
        LambdaQueryWrapper<CspaFunM> wr = this.getCommonQueryWrapper();
        // 设定查询条件
        wr.in(CspaFunM::getRefcode, funRefcodeList);

        // 需要屏蔽的菜单权限
        List<Integer> excludeSeqList = this.getOperationExcludeMenuSeqList();
        if (null != excludeSeqList && excludeSeqList.size()>0) {
            wr.notIn(CspaFunM::getSeqno, excludeSeqList);
        }
        // 增加根据序号排序的规则
        wr.orderByAsc(CspaFunM::getSeqno);
        List<CspaFunM> cspaFunMList = super.list(wr);
        // 根据资源信息组装父子节点树状结构
        List<CspaFunMVo> cspaFunRootList = this.getAllChildMenu(cspaFunMList, null);
        return cspaFunRootList;
    }

    @Override
    public List<CspaFunM> listFunByFunRefcodeList(List<Integer> funRefcodeList, String funSystemType) {
        LambdaQueryWrapper<CspaFunM> wr = new QueryWrapper<CspaFunM>().lambda();
        wr.in(CspaFunM::getRefcode, funRefcodeList);
        wr.eq(CspaFunM::getUseFlag, FieldConstant.PublicFieldValue.USE_FLAG_YES);
        // 资源类型[11=运营端/21=企业端/22=机构端/23=合伙人端/24=核心企业端/81-微信小程序]
        if (StringUtils.isNotBlank(funSystemType)) {
            wr.eq(CspaFunM::getSystemType, funSystemType);
        }
        return list(wr);
    }

    public List<Integer> listFunCodesBySystemType(String systemType, List<Integer> funCodeList, String brandStyle) {
        List<Integer> funRefcodeList = new ArrayList<>();
        LambdaQueryWrapper<CspaFunM> wr = new QueryWrapper<CspaFunM>().lambda();
        wr.select(CspaFunM::getRefcode);
        wr.eq(CspaFunM::getUseFlag, FieldConstant.CspaFun.USE_FLAG_YES);
        wr.eq(CspaFunM::getSystemType, systemType);
        wr.like(CspaFunM::getScpid, brandStyle);
        if (CollectionUtils.isNotEmpty(funCodeList)
                && FieldConstant.CspaFun.SYSTEM_TYPE_OPERATION.equals(systemType)) {
            wr.in(CspaFunM::getRefcode, funCodeList);
        }
        List<CspaFunM> cspaFunMList = super.list(wr);
        if (CollectionUtils.isNotEmpty(cspaFunMList)) {
            for (CspaFunM cspaFunM : cspaFunMList) {
                funRefcodeList.add(cspaFunM.getRefcode());
            }
        }
        return funRefcodeList;
    }

    /**
     * 获取公共的查询条件对象
     *
     * @return
     * @author oscar.yu
     * @date 2019/8/20 11:19
     */
    @SuppressWarnings("unchecked")
    protected LambdaQueryWrapper<CspaFunM> getCommonQueryWrapper() {
        LambdaQueryWrapper<CspaFunM> wr = new QueryWrapper<CspaFunM>().lambda();
        wr.select(CspaFunM::getFunid, CspaFunM::getFunDesc,
                CspaFunM::getFunPath, CspaFunM::getParentid,
                CspaFunM::getSeqno, CspaFunM::getLayid, CspaFunM::getRefcode,
                CspaFunM::getFunType, CspaFunM::getIcon, CspaFunM::getSystemType);
        // 查询可用权限
        wr.eq(CspaFunM::getUseFlag, FieldConstant.CspaFun.USE_FLAG_YES);
        // 增加根据序号排序的规则
        wr.orderByAsc(CspaFunM::getSeqno);
        return wr;
    }

    /**
     * 根据资源信息组装父子节点树状结构
     *
     * @param cspaFunMS
     * @param systemType
     * @return
     */
    private List<CspaFunMVo> getAllChildMenu(List<CspaFunM> cspaFunMS, String systemType) {
        List<CspaFunMVo> cspaFunRootList = new ArrayList<>();
        // 缓存资源节点代号及对象信息(此处借用引用类型特性)
        Map<Integer, CspaFunMVo> funSeqnoMap = new HashMap<>();
        CspaFunMVo cspaFunMVo;
        for (CspaFunM currFunM : cspaFunMS) {
            cspaFunMVo = new CspaFunMVo();
            BeanUtils.copyProperties(currFunM, cspaFunMVo);
            final Integer seqno = currFunM.getSeqno();
            final Integer parentid = currFunM.getParentid();
            // 1.首先获取根节点菜单
            if (this.isTopMenu(cspaFunMVo)) {
                cspaFunRootList.add(cspaFunMVo);
            } else {
                // 2.循环所有的子节点
                // 获取子节点对应父节点的对象
                CspaFunMVo parentFunVo = funSeqnoMap.get(parentid);
                // 将资源信息放入到父节点中
                if (null != parentFunVo) {
                    parentFunVo.getChildFuns().add(cspaFunMVo);
                }
            }
            // 将当前节点放入缓存集合中
            funSeqnoMap.put(seqno, cspaFunMVo);
        }
        funSeqnoMap = null; // 释放内存
        return cspaFunRootList;
    }

    /**
     * 检查菜单是否为顶层菜单
     *
     * @param cspaFunMVo
     * @return
     */
    private boolean isTopMenu(CspaFunMVo cspaFunMVo) {
        boolean flag = false;
        if (null != cspaFunMVo) {
            final Integer layid = cspaFunMVo.getLayid();
            if (null != layid && layid == FieldConstant.CspaFun.LAYID_TOP) {
                flag = true;
            }
        }
        return flag;
    }


    /**
     * 根据系统配置获取(运营端)需要屏蔽的菜单权限
     *
     * @return
     * @author oscar.yu
     * @date 2019/11/20 13:39
     */
    private List<Integer> getOperationExcludeMenuSeqList() {
        List<Integer> seqList = new ArrayList<>();
        return seqList;
    }

    @Override
    public List<CspaFunMVo> listFunMenuByFunRefcodeList(List<Integer> funRefcodeList, String systemType,String brandStyle, String funModule) {
        // 获取公共的查询条件对象
        LambdaQueryWrapper<CspaFunM> wr = this.getCommonQueryWrapper();
        // 设定查询条件
        wr.in(CspaFunM::getRefcode, funRefcodeList);
        wr.like(CspaFunM::getScpid, brandStyle);
        // 获取资源类型[11=运营端/21=企业端/22=机构端/23=合伙人端/24=核心企业端]
        String funSystemType = FieldConstant.CspaFun.SYSTEM_TYPE_OPERATION;
        if (StringUtils.isNotBlank(funSystemType)) {
            wr.eq(CspaFunM::getSystemType, funSystemType);
        }
        // 需要屏蔽的菜单权限
        List<Integer> excludeSeqList = this.getOperationExcludeMenuSeqList();

        if (null != excludeSeqList && excludeSeqList.size()>0) {
            wr.notIn(CspaFunM::getSeqno, excludeSeqList);
        }
        // 排除按钮权限
        wr.ne(CspaFunM::getFunType, FieldConstant.CspaFun.FUN_TYPE_BUTTON);
        List<CspaFunM> cspaFunMList = super.list(wr);
        // 根据资源信息组装父子节点树状结构
        return this.getAllChildMenu(cspaFunMList, systemType);
    }
}
