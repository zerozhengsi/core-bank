package com.vteam.cars.api.controller.own;

import com.alibaba.fastjson.JSONObject;
import com.vteam.cars.common.RequestStore;
import com.vteam.cars.constant.PermissionConstants;
import com.vteam.cars.entity.vo.SipaBurMVo;
import com.vteam.vtarm.api.RespEntity;
import com.vteam.vtarm.security.RequiredAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页 - 我的
 * @author Tianci.li
 * @since 2022/10/17 16:06
 */
@RestController
@RequestMapping("/personal")
public class PersonalController {

    /**
     * 获取登陆用户信息
     *
     * @since 2022/10/17 16:27
     * @author Tianci.li
     * @return RespEntity
     */
    @GetMapping("/getUserInfo")
//    @RequiredAuthorize(permissions = {PermissionConstants.Operation.MEMBER_MYINFO_INFO})
    public RespEntity getUserInfo() {


        // 获取当前登陆用户信息
        SipaBurMVo loginUser = RequestStore.getLoginUser();


        JSONObject data = new JSONObject();
        data.put("loginUser", loginUser);
        return RespEntity.ok(data);
    }
}
