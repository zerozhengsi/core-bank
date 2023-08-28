package com.vteam.cars.api.controller.operation.sysmanage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vteam.cars.common.RequestStore;
import com.vteam.cars.constant.PermissionConstants;
import com.vteam.cars.entity.model.SipaBurM;
import com.vteam.cars.entity.vo.SipaBurMVo;
import com.vteam.cars.entity.vo.SipaRolMVo;
import com.vteam.cars.service.sipa.SipaBtrMService;
import com.vteam.cars.service.sipa.SipaBurMService;
import com.vteam.cars.service.sipa.SipaRolMService;
import com.vteam.cars.util.SmeAssert;
import com.vteam.vtarm.api.ReqEntity;
import com.vteam.vtarm.api.RespCodeEnum;
import com.vteam.vtarm.api.RespEntity;
import com.vteam.vtarm.security.RequiredAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 运营人员管理 .
 *
 * @author zack.yin
 * @date 2018/9/3 14:04
 */

@RestController
@RequestMapping(value = "/operation/operationPersonalManage")
public class OperationPersonalManageController {

    @Resource(type = SipaBurMService.class)
    private SipaBurMService sipaBurMService;

    @Resource(type = SipaBtrMService.class)
    private SipaBtrMService sipaBtrMService;

    @Resource(type = SipaRolMService.class)
    private SipaRolMService sipaRolMService;

    /**
     * 获取运营人员用户列表 .
     *
     * @param reqEntity
     * @return com.vteam.sme.api.entity.RespEntity
     * @author zack.yin
     * @date 2018/9/3 14:23
     */
    @PostMapping(value = "/findOperationUserPage")
    @RequiredAuthorize(permissions = {PermissionConstants.Operation.SYSMANAGE_USER_OPERATIONPERSONALMANAGE,
            PermissionConstants.Operation.SYSMANAGE_USER_OPERATIONPERSONALMANAGE_NEW,
            PermissionConstants.Operation.SYSMANAGE_USER_OPERATIONPERSONALMANAGE_MOD,
            PermissionConstants.Operation.SYSMANAGE_USER_OPERATIONPERSONALMANAGE_USE,
            PermissionConstants.Operation.SYSMANAGE_USER_OPERATIONPERSONALMANAGE_DEL})
    public RespEntity findOperationUserPage(@RequestBody ReqEntity reqEntity) {
        SipaBurMVo condition = JSON.parseObject(reqEntity.getData(), SipaBurMVo.class);
        List<SipaBurMVo> sipaBurMVoList = sipaBurMService.listOperationUserByCondition(condition);
        JSONObject data = new JSONObject();
        data.put("burList", sipaBurMVoList);
        return new RespEntity(RespCodeEnum.SUCCESS, data.toJSONString());
    }

    /**
     * 新增运营人员用户信息 .
     *
     * @param reqEntity
     * @return com.vteam.sme.api.entity.RespEntity
     * @author zack.yin
     * @date 2018/9/3 18:28
     */
    @PostMapping(value = "/doSaveOperationUserInfo")
    @RequiredAuthorize(permissions = {PermissionConstants.Operation.SYSMANAGE_USER_OPERATIONPERSONALMANAGE_NEW})
    public RespEntity doSaveOperationUserInfo(@RequestBody ReqEntity reqEntity) {
        SipaBurMVo condition = JSON.parseObject(reqEntity.getData(), SipaBurMVo.class);
        SmeAssert.notBlank(condition.getUserid(), "用户账号不能为空。");
        SmeAssert.notBlank(condition.getUserName(), "姓名不能为空。");
        SmeAssert.notBlank(condition.getMobilephone(), "手机号码不能为空。");
        SmeAssert.isPhoneNum(condition.getMobilephone(), "手机号格式不合法。");
        SmeAssert.notBlank(condition.getEmail(), "电子邮箱不能为空。");
        SmeAssert.isEmail(condition.getEmail(), "电子邮箱格式不合法。");
        SmeAssert.notBlank(condition.getRoleid(), "所属角色不能为空。");
        SmeAssert.notBlank(condition.getPassword(), "登录密码不能为空。");
        SmeAssert.notBlank(condition.getConfirmPassword(), "确认密码不能为空。");
        SmeAssert.isTrue(condition.getConfirmPassword().equals(condition.getPassword()), "确认密码与登录密码不一致");

        sipaBurMService.saveOperationUserInfo(condition);
        sipaBtrMService.saveOperationUserInfo(condition);
        return new RespEntity(RespCodeEnum.SUCCESS);
    }

    /**
     * 查询运营人员用户信息 .
     *
     * @param refcode
     * @return com.vteam.sme.api.entity.RespEntity
     * @author zack.yin
     * @date 2018/9/4 10:25
     */
    @GetMapping(value = "/getOperationUserInfo/{refcode}")
    @RequiredAuthorize(permissions = {PermissionConstants.Operation.SYSMANAGE_USER_OPERATIONPERSONALMANAGE_QUERY,
            PermissionConstants.Operation.SYSMANAGE_USER_OPERATIONPERSONALMANAGE_MOD})
    public RespEntity getOperationUserInfo(@PathVariable("refcode") Integer refcode) {
        SipaBurMVo sipaBurMVo = sipaBurMService.getOperationUserInfo(refcode);
        return new RespEntity(RespCodeEnum.SUCCESS, JSONObject.toJSONString(sipaBurMVo));
    }

    /**
     * 修改运营人员用户状态 .
     *
     * @param reqEntity
     * @return com.vteam.sme.api.entity.RespEntity
     * @author zack.yin
     * @date 2018/11/26 10:53
     */
    @PostMapping(value = "/doUpdateOperationUserStatus")
    @RequiredAuthorize(permissions = {PermissionConstants.Operation.SYSMANAGE_USER_OPERATIONPERSONALMANAGE,
            PermissionConstants.Operation.SYSMANAGE_USER_OPERATIONPERSONALMANAGE_USE})
    public RespEntity doUpdateOperationUserStatus(@RequestBody ReqEntity reqEntity) {
        SipaBurMVo condition = JSON.parseObject(reqEntity.getData(), SipaBurMVo.class);
        sipaBurMService.updateOperationUserStatus(condition);
        return new RespEntity(RespCodeEnum.SUCCESS);
    }

    /**
     * 修改运营人员用户信息 .
     *
     * @param reqEntity
     * @return com.vteam.sme.api.entity.RespEntity
     * @author zack.yin
     * @date 2018/9/3 20:43
     */
    @PostMapping(value = "/doUpdateOperationUserInfo")
    @RequiredAuthorize(permissions = {PermissionConstants.Operation.SYSMANAGE_USER_OPERATIONPERSONALMANAGE_MOD})
    public RespEntity doUpdateOperationUserInfo(@RequestBody ReqEntity reqEntity) {
        SipaBurMVo condition = JSON.parseObject(reqEntity.getData(), SipaBurMVo.class);
        SmeAssert.notBlank(condition.getUserid(), "用户账号不能为空。");
        SmeAssert.notBlank(condition.getUserName(), "姓名不能为空。");
        SmeAssert.notBlank(condition.getMobilephone(), "手机号码不能为空。");
        SmeAssert.isPhoneNum(condition.getMobilephone(), "手机号格式不合法。");
        SmeAssert.notBlank(condition.getEmail(), "电子邮箱不能为空。");
        SmeAssert.isEmail(condition.getEmail(), "电子邮箱格式不合法。");
        SmeAssert.notBlank(condition.getRoleid(), "所属角色不能为空。");
        // 可以不修改登录密码
        // SmeAssert.notBlank(condition.getPassword(), "登录密码不能为空。");

        sipaBurMService.updateOperationUserInfo(condition);
        // 修改角色信息
        sipaBtrMService.updateOperationUserInfo(condition);
        return new RespEntity(RespCodeEnum.SUCCESS);
    }

    /**
     * 修改运营人员用户密码
     *
     * @param reqEntity
     * @return
     * @author zhuang.shao
     * @date 2019年4月23日 下午4:27:12
     */
    @PostMapping(value = "/doUpdateOperationPassword")
    public RespEntity doUpdateOperationPassword(@RequestBody ReqEntity reqEntity) {
        SipaBurMVo condition = JSON.parseObject(reqEntity.getData(), SipaBurMVo.class);
        SmeAssert.notNull(condition.getRefcode(), "请先登陆");
        // 此处验证当前用户流水号，放置脚本攻击
        final Integer currUserRefcode = RequestStore.getLoginUser().getRefcode();
        SmeAssert.eq(currUserRefcode, condition.getRefcode(), "认证失败。");
        SipaBurM oldUserInfo = sipaBurMService.getById(condition.getRefcode());
        String oldPassWord = oldUserInfo.getPassword();
        String password = condition.getPassword();
        SmeAssert.eq(oldPassWord, password, "旧密码不正确");
        SmeAssert.notBlank(condition.getNewPassword(), "新密码不能为空");
        oldUserInfo.setPassword(condition.getNewPassword());
        oldUserInfo.setChgpwdFlag("0");
        sipaBurMService.updateById(oldUserInfo);
        return new RespEntity(RespCodeEnum.SUCCESS);
    }

    /**
     * 删除运营人员用户信息 .
     *
     * @param reqEntity
     * @return com.vteam.sme.api.entity.RespEntity
     * @author zack.yin
     * @date 2018/9/3 21:19
     */

    @PostMapping(value = "/doRemoveOperationUserInfo")
    @RequiredAuthorize(permissions = {PermissionConstants.Operation.SYSMANAGE_USER_OPERATIONPERSONALMANAGE_DEL})
    public RespEntity doRemoveOperationUserInfo(@RequestBody ReqEntity reqEntity) {
        SipaBurMVo condition = JSON.parseObject(reqEntity.getData(), SipaBurMVo.class);
        // 删除用户
        sipaBurMService.removeOperationUserInfo(condition);
        // 删除用户与角色关联
        sipaBtrMService.removeOperationUserInfo(condition);
        return new RespEntity(RespCodeEnum.SUCCESS);
    }

    /**
     * 获取后台角色信息 .
     *
     * @param
     * @return com.vteam.sme.api.entity.RespEntity
     * @author zack.yin
     * @date 2018/11/26 10:53
     */
    @GetMapping(value = "/getBackRoleInfo")
    @RequiredAuthorize(permissions = {PermissionConstants.Operation.SYSMANAGE_USER_OPERATIONPERSONALMANAGE,
            PermissionConstants.Operation.SYSMANAGE_USER_OPERATIONPERSONALMANAGE_NEW,
            PermissionConstants.Operation.SYSMANAGE_USER_OPERATIONPERSONALMANAGE_MOD})
    public RespEntity getBackRoleInfo() {
        List<SipaRolMVo> sipaRolMVoList = sipaRolMService.listBackRoleInfo();
        JSONObject data = new JSONObject();
        data.put("roleList", sipaRolMVoList);
        return new RespEntity(RespCodeEnum.SUCCESS, data.toJSONString());
    }



}
