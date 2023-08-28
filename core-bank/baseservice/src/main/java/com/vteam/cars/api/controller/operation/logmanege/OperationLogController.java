package com.vteam.cars.api.controller.operation.logmanege;

import com.alibaba.fastjson.JSONObject;
import com.vteam.cars.constant.PermissionConstants;
import com.vteam.cars.entity.vo.FlogOptMVo;
import com.vteam.cars.service.flog.FlogOptMService;
import com.vteam.vtarm.api.ReqEntity;
import com.vteam.vtarm.api.RespCodeEnum;
import com.vteam.vtarm.api.RespEntity;
import com.vteam.vtarm.security.RequiredAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.time.LocalTime;
import java.util.List;

/**
 * 操作日志管理 .
 *
 * @author zack.yin
 * @date 2019/6/3 14:31
 */
@RestController
@RequestMapping(value = "/operation/operationLog")
public class OperationLogController {

    @Resource(type = FlogOptMService.class)
    private FlogOptMService flogOptMService;

    /**
     * 获取操作日志列表 .
     *
     * @param reqEntity
     * @return com.vteam.mssme.api.entity.RespEntity
     * @author zack.yin
     * @date 2019/6/5 9:57
     */
    @PostMapping(value = "/findOperationLogPage")
    @RequiredAuthorize(permissions = { PermissionConstants.Operation.SYSMANAGE_OPERATIONLOG_LOGLIST })
    public RespEntity findOperationLogPage(@RequestBody ReqEntity reqEntity) {
        FlogOptMVo flogOptMVo = JSONObject.parseObject(reqEntity.getData(), FlogOptMVo.class);
        if (null != flogOptMVo.getOperateDateStart()) {
            flogOptMVo.setOperateDateStart(flogOptMVo.getOperateDateStart().with(LocalTime.MIN));
        }
        if (null != flogOptMVo.getOperateDateEnd()) {
            flogOptMVo.setOperateDateEnd(flogOptMVo.getOperateDateEnd().with(LocalTime.MAX));
        }
        List<FlogOptMVo> flogOptMVoList = flogOptMService.listOperationLogByCondition(flogOptMVo);
        JSONObject data = new JSONObject();
        data.put("logList", flogOptMVoList);
        return new RespEntity(RespCodeEnum.SUCCESS, data.toJSONString());
    }

}
