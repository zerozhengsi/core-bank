package com.vteam.cars.api.controller.operation.sysmanage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vteam.cars.common.RequestStore;
import com.vteam.cars.constant.FieldConstant;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.constant.PermissionConstants;
import com.vteam.cars.entity.model.CspaFunM;
import com.vteam.cars.entity.model.SipaBtrM;
import com.vteam.cars.entity.model.SipaRolM;
import com.vteam.cars.entity.vo.CspaFunMVo;
import com.vteam.cars.entity.vo.SipaBurMVo;
import com.vteam.cars.entity.vo.SipaRolMVo;
import com.vteam.cars.service.cspa.CspaFunMService;
import com.vteam.cars.service.sipa.SipaBtrMService;
import com.vteam.cars.service.sipa.SipaRolMService;
import com.vteam.cars.service.sipa.SipaRtfMService;
import com.vteam.cars.util.SmeAssert;
import com.vteam.cars.util.StringUtils;
import com.vteam.vtarm.api.ReqEntity;
import com.vteam.vtarm.api.RespCodeEnum;
import com.vteam.vtarm.api.RespEntity;
import com.vteam.vtarm.security.RequiredAuthorize;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 平台角色管理 .
 *
 * @author zack.yin
 * @date 2018/9/10 9:05
 * @modify 202207151412 ARM-1638 7450606：运营端：修改角色，去掉功能权限后，再查询，在角色中看不到对应的菜单
 */
@RestController
@RequestMapping(value = "/operation/platformRoleManage")
public class PlatformRoleManageController {

    @Resource(type = SipaRolMService.class)
    private SipaRolMService sipaRolMService;

    @Resource(type = SipaBtrMService.class)
    private SipaBtrMService sipaBtrMService;

    @Resource(type = CspaFunMService.class)
    private CspaFunMService cspaFunMService;

    @Resource(type = SipaRtfMService.class)
    private SipaRtfMService sipaRtfMService;


    /**
     * 获取角色列表 .
     *
     * @param reqEntity
     * @return com.vteam.sme.api.entity.RespEntity
     * @author zack.yin
     * @date 2018/9/10 18:03
     */
    @PostMapping(value = "/findRolePage")
    @RequiredAuthorize(permissions = {PermissionConstants.Operation.SYSMANAGE_ROLE_ROLEMANAGE,
            PermissionConstants.Operation.SYSMANAGE_ROLE_ROLEMANAGE_NEW,
            PermissionConstants.Operation.SYSMANAGE_ROLE_ROLEMANAGE_MOD,
            PermissionConstants.Operation.SYSMANAGE_ROLE_ROLEMANAGE_USE,
            PermissionConstants.Operation.SYSMANAGE_ROLE_ROLEMANAGE_DEL})
    public RespEntity findRolePage(@RequestBody ReqEntity reqEntity) {
        SipaRolMVo condition = JSON.parseObject(reqEntity.getData(), SipaRolMVo.class);

        // 判断登陆用户为管理员时  查询所有的角色  不是管理员则只能看到自己的角色以及自己创建的角色
        // fixbug此处暂时先清除分页对象
        PageHelper.clearPage();
        this.obtainParentRoleid(condition);
        if (FieldConstant.SipaRolM.ROLE_ID_ADMIN.equals(condition.getParentRoleid())) {
            condition.setParentRoleid(null);
        }
        // fix此处还原分页对象
        Page<Object> page = PageHelper.startPage(RequestStore.getPageNum(), RequestStore.getPageSize());
        RequestStore.setPage(page);
        List<SipaRolMVo> sipaRolMList = sipaRolMService.listRoleInfoByCondition(condition);
        JSONObject data = new JSONObject();
        data.put("roleList", sipaRolMList);
        return new RespEntity(RespCodeEnum.SUCCESS, data.toJSONString());
    }

    /**
     * 获取功能列表 .
     *
     * @param
     * @return com.vteam.sme.api.entity.RespEntity
     * @author zack.yin
     * @date 2018/9/10 18:24
     */
    @GetMapping(value = "/getFunList/{systemType}/{roleid}")
    @RequiredAuthorize(permissions = {PermissionConstants.Operation.SYSMANAGE_ROLE_ROLEMANAGE,
            PermissionConstants.Operation.SYSMANAGE_ROLE_ROLEMANAGE_QUERY})
    public RespEntity getFunList(@PathVariable String systemType, @PathVariable String roleid) {
        // 根据品牌区分角色信息 获取品牌风格
        List<Integer> funCodeList = null;
        if (FieldConstant.CspaFun.SYSTEM_TYPE_OPERATION.equals(systemType)) { // 运营端角色维护
            // why?为什么要关联查询权限表，改成了直接查询菜单表 modify by chad.mei 梅旸明 202207151412
            // ARM-1638 7450606：运营端：修改角色，去掉功能权限后，再查询，在角色中看不到对应的菜单
            // 运营端角色维护，需要控制可维护的资源在当前用户所属角色资源范围下
//            SipaRolMVo sipaRoleMVo = new SipaRolMVo();
//            sipaRoleMVo.setRoleid(this.obtainCurrUserRoleid());
//            sipaRoleMVo.setBrandStyle(brandStyle);
//            List<Integer> currUserFunRefcodeList = sipaRtfMService.listRoleFunByRoleId(sipaRoleMVo);
            systemType = FieldConstant.CspaFun.SYSTEM_TYPE_OPERATION;
            funCodeList = cspaFunMService.listFunCodesBySystemType(systemType, null, null);
        } else { // 用户端角色维护
            // 用户端角色维护，根据资源类型查出全部资源
        	// 一般企业管理员和核心企业管理员为 企业端类型
            if (FieldConstant.SipaRolM.ROLE_ID_GENERAL.equals(roleid)) {
                systemType = FieldConstant.SipaRolM.ROLE_TYPE_COMPANY;
            }
            if (FieldConstant.SipaRolM.ROLE_ID_CORE.equals(roleid)) {
                systemType = FieldConstant.SipaRolM.ROLE_TYPE_CORE_COMPANY;
            }
            // 机构端管理员为机构端
            if (FieldConstant.SipaRolM.ROLE_ID_FINANCIAL.equals(roleid)) {
                systemType = FieldConstant.SipaRolM.ROLE_TYPE_FINANCIAL;
            }
            // 合伙人管理员为合伙人端
            if (FieldConstant.SipaRolM.ROLE_ID_PARTNER.equals(roleid)) {
                systemType = FieldConstant.SipaRolM.ROLE_TYPE_PARTNER;
            }
            funCodeList = cspaFunMService.listFunCodesBySystemType(systemType, null, null);
        }
        List<CspaFunMVo> cspaFunMS = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(funCodeList)) {
            cspaFunMS = cspaFunMService.listCspaFunMvo(funCodeList, systemType, null);
        }
        JSONObject data = new JSONObject();
        data.put("funList", cspaFunMS);
        return new RespEntity(RespCodeEnum.SUCCESS, data.toJSONString());
    }

    /**
     * 获取角色信息 .
     *
     * @param refcode
     * @return com.vteam.sme.api.entity.RespEntity
     * @author zack.yin
     * @date 2018/9/10 18:25
     */
    @GetMapping(value = "/getRoleInfo/{refcode}")
    @RequiredAuthorize(permissions = {PermissionConstants.Operation.SYSMANAGE_ROLE_ROLEMANAGE_QUERY,
            PermissionConstants.Operation.SYSMANAGE_ROLE_ROLEMANAGE_MOD})
    public RespEntity getRoleInfo(@PathVariable String refcode) {
        // 根据品牌区分角色信息 获取品牌风格

        // 根据角色refcode查询出角色信息
        SipaRolM sipaRolM = sipaRolMService.getById(refcode);
        // 根据roleid查询所有权限
        SipaRolMVo sipaRoleMVo = com.vteam.cars.util.BeanUtils.copy(sipaRolM, SipaRolMVo.class);
        sipaRoleMVo.setBrandStyle(null);
        List<Integer> funRefcodeList = sipaRtfMService.listRoleFunByRoleId(sipaRoleMVo);
        sipaRoleMVo.setFunCodes(funRefcodeList);
        return new RespEntity(RespCodeEnum.SUCCESS, JSON.toJSONString(sipaRoleMVo));
    }

    /**
     * 新增角色信息 .
     *
     * @param reqEntity
     * @return com.vteam.sme.api.entity.RespEntity
     * @author zack.yin
     * @date 2018/9/10 18:04
     */
    @PostMapping(value = "/doSaveRoleInfo")
    @RequiredAuthorize(permissions = {PermissionConstants.Operation.SYSMANAGE_ROLE_ROLEMANAGE_NEW})
    public RespEntity doSaveRoleInfo(@RequestBody ReqEntity reqEntity) {
        // 将JSON对象转换为JavaBean
        SipaRolMVo sipaRoleMVo = JSON.parseObject(reqEntity.getData(), SipaRolMVo.class);
        SmeAssert.notBlank(sipaRoleMVo.getRoleName(), "角色名称为空。");
        SmeAssert.notBlank(sipaRoleMVo.getRoledesc(), "角色名描述为空。");
        sipaRoleMVo.setSystemType(FieldConstant.SipaRolM.SYSTEM_TYPE_BACK);
        // 获取父级角色
        this.obtainParentRoleid(sipaRoleMVo);
        // 新增角色信息添加角色ID
        sipaRoleMVo = sipaRolMService.saveBackRoleInfo(sipaRoleMVo);

        // 获取权限流水号
        String[] funRefcodes = sipaRoleMVo.getFunRefcodes().split(GlobalConstants.Symbol.COMMA);
        // 根据权限refcode查询权限循环添加权限关联
        this.saveRoleFunRelation(sipaRoleMVo, funRefcodes);
        return new RespEntity(RespCodeEnum.SUCCESS);
    }

    /**
     * 修改角色状态 .
     *
     * @param reqEntity
     * @return com.vteam.sme.api.entity.RespEntity
     * @author zack.yin
     * @date 2018/9/10 18:26
     */
    @PostMapping(value = "/doUpdateRoleState")
    @RequiredAuthorize(permissions = {PermissionConstants.Operation.SYSMANAGE_ROLE_ROLEMANAGE,
            PermissionConstants.Operation.SYSMANAGE_ROLE_ROLEMANAGE_USE})
    public RespEntity doUpdateRoleState(@RequestBody ReqEntity reqEntity) {
        SipaRolMVo condition = JSON.parseObject(reqEntity.getData(), SipaRolMVo.class);
        // 根据refcode获得roleID
        SipaRolM role = sipaRolMService.getById(condition.getRefcode());
        SmeAssert.le(sipaBtrMService.countUserByRoleInfo(role), 0, "该角色已被使用，无法禁用");
        // 根据refcode修改角色信息
        SipaRolM sipaRolM = new SipaRolM();
        sipaRolM.setRefcode(condition.getRefcode());
        sipaRolM.setUseFlag(condition.getUseFlag());
        sipaRolMService.updateById(sipaRolM);
        return new RespEntity(RespCodeEnum.SUCCESS);
    }

    /**
     * 修改角色信息 .
     *
     * @param reqEntity
     * @return com.vteam.sme.api.entity.RespEntity
     * @author zack.yin
     * @date 2018/9/10 18:27
     */
    @PostMapping(value = "doUpdateRoleInfo")
    @RequiredAuthorize(permissions = {PermissionConstants.Operation.SYSMANAGE_ROLE_ROLEMANAGE_MOD})
    public RespEntity doUpdateRoleInfo(@RequestBody ReqEntity reqEntity) {
        SipaRolMVo sipaRoleMVo = JSON.parseObject(reqEntity.getData(), SipaRolMVo.class);
        SmeAssert.notBlank(sipaRoleMVo.getRoleName(), "角色名称为空。");
        SmeAssert.notBlank(sipaRoleMVo.getRoledesc(), "角色名描述为空。");
        // 根据角色refcode获得role
        SipaRolMVo role = sipaRolMService.updateBackRoleInfo(sipaRoleMVo);
        // 删除角色下所属权限
        sipaRtfMService.removeRtfByRoleId(role);

        // 获取权限流水号
        String[] funRefcodes = sipaRoleMVo.getFunRefcodes().split(GlobalConstants.Symbol.COMMA);
        // 根据权限refcode查询权限循环添加权限关联
        this.saveRoleFunRelation(role, funRefcodes);
        return new RespEntity(RespCodeEnum.SUCCESS);
    }

    /**
     * 删除角色 .
     *
     * @param reqEntity
     * @return com.vteam.sme.api.entity.RespEntity
     * @author zack.yin
     * @date 2018/9/10 18:27
     */
    @PostMapping(value = "doRemoveRole")
    @RequiredAuthorize(permissions = {PermissionConstants.Operation.SYSMANAGE_ROLE_ROLEMANAGE_DEL})
    public RespEntity doRemoveRole(@RequestBody ReqEntity reqEntity) {
        SipaRolMVo condition = JSON.parseObject(reqEntity.getData(), SipaRolMVo.class);
        // 根据refcode获得roleID
        SipaRolM role = sipaRolMService.getById(condition.getRefcode());
        SmeAssert.le(sipaBtrMService.countUserByRoleInfo(role), 0, "该角色已被使用，无法删除");

        SipaRolMVo sipaRoleMVo = new SipaRolMVo();
        BeanUtils.copyProperties(role, sipaRoleMVo);
        // 删除角色下所属权限
        sipaRtfMService.removeRtfByRoleId(sipaRoleMVo);

        // 删除角色
        sipaRolMService.deleteRoleInfo(sipaRoleMVo);
        return RespEntity.ok();
    }

    /**
     * 根据权限refcode查询权限循环添加权限关联
     *
     * @param role
     * @param funRefcodes
     */
    private void saveRoleFunRelation(SipaRolMVo role, String[] funRefcodes) {
        if (ArrayUtils.isNotEmpty(funRefcodes)) {
            for (int i = 0; i < funRefcodes.length; i++) {
                if (StringUtils.isNotBlank(funRefcodes[i])) {
                    // 获取权限信息
                    CspaFunM funM = cspaFunMService.getById(funRefcodes[i]);
                    if (funM != null) {
                        sipaRtfMService.saveRoleFunRelation(role, funM);
                    }
                }
            }
        }
    }

    /**
     * 获取父级角色 .
     *
     * @param sipaRoleMVo
     * @return void
     * @author zack.yin
     * @date 2019/6/26 11:08
     */
    private void obtainParentRoleid(SipaRolMVo sipaRoleMVo) {
        // 获取当前用户角色代号
        String roleid = this.obtainCurrUserRoleid();
        // 设定父级角色
        sipaRoleMVo.setParentRoleid(roleid);
    }

    /**
     * 获取当前用户角色代号(目前只兼容一个用户一个角色)
     *
     * @return
     */
    private String obtainCurrUserRoleid() {
        SipaBurMVo tmpBurVo = new SipaBurMVo();
        tmpBurVo.setUserid(RequestStore.getLoginUser().getUserid());
        tmpBurVo.setSystemType(FieldConstant.SipaBurM.SYSTEM_TYPE_OPERATION);
        List<SipaBtrM> sipaBtrMList = sipaBtrMService.listRoleInfoByUserId(tmpBurVo);
        SmeAssert.notEmpty(sipaBtrMList, "角色为空。");
        return sipaBtrMList.get(0).getRoleid();
    }
}
