package com.vteam.cars.api.controller.operation.authz;

import com.alibaba.fastjson.JSONObject;
import com.vteam.cars.common.RequestStore;
import com.vteam.cars.constant.FieldConstant;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.entity.model.CspaFunM;
import com.vteam.cars.entity.vo.CspaFunMVo;
import com.vteam.cars.entity.vo.SipaBurMVo;
import com.vteam.cars.entity.vo.SipaRtfMVo;
import com.vteam.cars.service.cspa.CspaFunMService;
import com.vteam.cars.service.sipa.SipaRtfMService;
import com.vteam.vtarm.api.RespEntity;
import com.vteam.vtarm.status.GlobalStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 安控模块控制器
 *
 * @author li.jin
 * @date 2022/10/25 15:41
 */
@Slf4j
@RestController
@RequestMapping("/operation/authz")
public class OperationAuthzController {


    @Resource(type = SipaRtfMService.class)
    private SipaRtfMService sipaRtfMService;

    @Resource(type = CspaFunMService.class)
    private CspaFunMService cspaFunMService;

    /**
     * 获取权限列表
     *
     * @author andy.sher
     * @date 2018/8/15 13:59
     */
    @GetMapping(value = "/getFunidList")
    public RespEntity getFunidList() {
        SipaBurMVo loginUser = RequestStore.getLoginUser();

        // 根据用户信息及 品牌风格查询权限流水号集合
        SipaRtfMVo sipaRtfMVo = new SipaRtfMVo();
        sipaRtfMVo.setUserid(loginUser.getUserid());
        sipaRtfMVo.setOrgRefcode(RequestStore.getLoginUser().getOrgRefcode());
        List<Integer> funRefcodeList = sipaRtfMService.listFunRefcodesByUserInfo(sipaRtfMVo);
        // 查询权限列表
        // 获取资源类型[11=运营端]
        String funSystemType = FieldConstant.CspaFun.SYSTEM_TYPE_OPERATION;
        List<CspaFunM> funList = cspaFunMService.listFunByFunRefcodeList(funRefcodeList, funSystemType);

        // [1.1] 2022-07-18 mod 授予权限,增加新的权限后,前端刷新页面时更新权限缓存列表
        Set<String> permissions = new HashSet<>();

        // 逗号拼接权限ID
        List<String> funidList = new ArrayList<>();
        String funid = StringUtils.EMPTY;
        if (CollectionUtils.isNotEmpty(funList)) {
            for (CspaFunM fun : funList) {
                funidList.add(fun.getFunid());
                // [1.1] 授予权限
                permissions.add(fun.getFunid());
            }
            funid = StringUtils.join(funidList, GlobalConstants.Symbol.COMMA);
            // [1.1] 更新权限列表
            GlobalStatus.getAuthenticationInfo().setPermissions(permissions);
            GlobalStatus.getAuthenticationInfo().setAdditional(JSONObject.toJSONString(loginUser), GlobalStatus.getAuthenticationInfo());
        }
        JSONObject data = new JSONObject();
        data.put("funids", funid);
        return RespEntity.ok(data);
    }

    /**
     * 获取运营菜单 .
     *
     * @return com.vteam.sme.api.entity.RespEntity
     * @author fu.tong
     * @date 2019/3/26 09:39
     */
    @GetMapping("/getManageMenu")
    public RespEntity getManageMenu() {
        // 获取品牌风格
        // 根据用户信息及 品牌风格查询权限流水号集合
        List<CspaFunMVo> menu = null;
        SipaRtfMVo sipaRtfMVo = new SipaRtfMVo();
        sipaRtfMVo.setUserid(RequestStore.getLoginUser().getUserid());
        // 运营端企业流水号为0
        sipaRtfMVo.setOrgRefcode(0);
        List<Integer> funRefcodeList = sipaRtfMService.listFunRefcodesByUserInfo(sipaRtfMVo);
        if (CollectionUtils.isNotEmpty(funRefcodeList)) {
            final String systemTypeManage = FieldConstant.PublicFieldValue.SYSTEM_TYPE_MANAGE;
            menu = cspaFunMService.listFunMenuByFunRefcodeList(funRefcodeList, systemTypeManage, null, null);
        }
        JSONObject data = new JSONObject();
        data.put("menuList", menu);
        return RespEntity.ok(data);
    }


}
