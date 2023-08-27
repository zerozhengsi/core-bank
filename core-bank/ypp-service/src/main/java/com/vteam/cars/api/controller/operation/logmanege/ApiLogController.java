package com.vteam.cars.api.controller.operation.logmanege;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vteam.cars.aspect.ExportFileAspect;
import com.vteam.cars.common.RequestStore;
import com.vteam.cars.constant.FieldConstant;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.constant.MessageTemplateConstants;
import com.vteam.cars.constant.PermissionConstants;
import com.vteam.cars.entity.model.FlogApiM;
import com.vteam.cars.entity.vo.FlogApiMVo;
import com.vteam.cars.entity.vo.SipaBurMVo;
import com.vteam.cars.plugin.activemq.producer.MessageProducer;
import com.vteam.cars.plugin.excel.exp.ExcelExportExecutor;
import com.vteam.cars.plugin.excel.exp.SXSSFExportExecutor;
import com.vteam.cars.plugin.message.model.MessageModel;
import com.vteam.cars.service.common.MapCacheService;
import com.vteam.cars.service.flog.FlogApiMService;
import com.vteam.cars.service.sipa.SipaBurMService;
import com.vteam.cars.util.DateUtils;
import com.vteam.cars.util.SmeAssert;
import com.vteam.vtarm.api.ReqEntity;
import com.vteam.vtarm.api.RespCodeEnum;
import com.vteam.vtarm.api.RespEntity;
import com.vteam.vtarm.cache.StringValueContainer;
import com.vteam.vtarm.security.RequiredAuthorize;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.time.LocalTime;
import java.util.*;

/**
 * 接口日志记录表 .
 *
 * @author fu.tong
 * @date 2019/6/4 16:10
 */
@RequestMapping(value = "/operation/apiLog")
@RestController
public class ApiLogController {

    @Resource(type = FlogApiMService.class)
    private FlogApiMService flogApiMService;

    @Resource(type = SXSSFExportExecutor.class)
    private SXSSFExportExecutor sxssfExportExecutor;

    @Resource(type = MapCacheService.class)
    private MapCacheService mapCacheService;

    @Resource(type = MessageProducer.class)
    private MessageProducer messageProducer;

    @Resource(name = "exportTaskExecutor")
    private TaskExecutor exportTaskExecutor;

    @Resource(type = SipaBurMService.class)
    private SipaBurMService sipaBurMService;

    @Resource(type = StringValueContainer.class)
    private StringValueContainer stringValueContainer;

    @Resource(type = ExcelExportExecutor.class)
    private ExcelExportExecutor excelExportExecutor;

    /**
     * 接口日志列表查询 .
     *
     * @param reqEntity
     * @return com.vteam.mssme.api.entity.RespEntity
     * @author fu.tong
     * @date 2019/6/4 16:13
     */
    @PostMapping(value = "/findApiLogPage")
    @RequiredAuthorize(permissions = {PermissionConstants.Operation.SYSMANAGE_OPERATIONLOG_LOGLIST})
    public RespEntity findApiLogPage(@RequestBody ReqEntity reqEntity) {
        FlogApiMVo flogApiMVo = JSON.parseObject(reqEntity.getData(), FlogApiMVo.class);
        if (null != flogApiMVo.getResponseTimeStart()) {
            flogApiMVo.setResponseTimeStart(flogApiMVo.getResponseTimeStart().with(LocalTime.MIN));
        }
        if (null != flogApiMVo.getResponseTimeEnd()) {
            flogApiMVo.setResponseTimeEnd(flogApiMVo.getResponseTimeEnd().with(LocalTime.MAX));
        }
        List<FlogApiMVo> flogApiMVoList = flogApiMService.listApiLogByCondition(flogApiMVo);
        JSONObject data = new JSONObject();
        data.put("apiList", flogApiMVoList);
        return RespEntity.ok(data.toJSONString());
    }


    /**
     * 获取接口信息 .
     *
     * @param refcode
     * @return com.vteam.mssme.api.entity.RespEntity
     * @author fu.tong
     * @date 2019/6/4 16:58
     */
    @GetMapping(value = "/getApiLogInfo/{refcode}")
    @RequiredAuthorize(permissions = {PermissionConstants.Operation.SYSMANAGE_APOLOG_QUERY})
    public RespEntity getApiLogInfo(@PathVariable("refcode") Integer refcode) {
        FlogApiM flogApiM = flogApiMService.getById(refcode);
        return RespEntity.ok(JSONObject.toJSONString(flogApiM));
    }

    /**
     * 导出接口日志
     *
     * @param
     * @return com.vteam.vtarm.api.RespEntity
     * @author xiu.fu
     * @date 2020/4/26 下午 3:36
     */
    @PostMapping(value = "/doExportApiLog")
    @RequiredAuthorize(permissions = {PermissionConstants.Operation.SYSMANAGE_APOLOG_EXPORT})
    public RespEntity doExportApiLog(@RequestBody ReqEntity reqEntity) {
        FlogApiMVo flogApiMVo = JSON.parseObject(reqEntity.getData(), FlogApiMVo.class);

        SmeAssert.notNull(flogApiMVo.getResponseTimeStart(), "请输入响应开始日期");
        flogApiMVo.setResponseTimeStart(flogApiMVo.getResponseTimeStart().with(LocalTime.MIN));
        SmeAssert.notNull(flogApiMVo.getResponseTimeEnd(), "请输入响应结束日期");
        flogApiMVo.setResponseTimeEnd(flogApiMVo.getResponseTimeEnd().with(LocalTime.MAX));
        long endDay = flogApiMVo.getResponseTimeEnd().getYear();
        long createDay = flogApiMVo.getResponseTimeStart().getYear();
        long days = endDay - createDay;
        boolean bl = days <= 366;
        SmeAssert.isTrue(bl, "响应日期相差不能大于一年");
        List<FlogApiMVo> flogApiMVoList = flogApiMService.listApiLogByCondition(flogApiMVo);
        SmeAssert.notEmpty(flogApiMVoList, "暂无数据，不可导出");
        final String userid = RequestStore.getLoginUser().getUserid();
        exportTaskExecutor.execute(() -> {

            if (CollectionUtils.isNotEmpty(flogApiMVoList)) {
                // 处理导出的数据
                this.doExportData(flogApiMVoList);
            }

            // 接口日志列表
            String fileName = sxssfExportExecutor.executeForLocal(flogApiMVoList, FlogApiMVo.class, "FlogApiMVo_ExportTemplate.xlsx");

            // 发送消息
            Set<SipaBurMVo> receiverSet = new HashSet<>();
            SipaBurMVo receiver = sipaBurMService.getUserInfoByUserid(userid);
            receiverSet.add(receiver);

            Map<String, Object> model = new HashMap<>(2);
            model.put("actionName", "接口日志");
            model.put("filePath", fileName);
            MessageModel messageModel = MessageModel.build().createMessageModel(null, receiverSet,
                    MessageTemplateConstants.EXPORT_FILE.getTemplateName(), FieldConstant.FbtxNot.PUSH_TYPE_SYSTEM, null, model,
                    new MessageModel.Channel[]{MessageModel.Channel.WS});
            messageProducer.send(messageModel);

            // 移除任务标记
            stringValueContainer.delete(ExportFileAspect.BASE_CACHE_KEY + userid);
        });
        return RespEntity.ok();
    }


    /**
     * 填充一行数据
     *
     * @param  sheet startRow flogApiMVo Serial
     * @return startRow + 1
     * @author ao.wei
     * @date 2022/3/30 下午 3:36
     */
    private int getOneRowData(Sheet sheet, int startRow, FlogApiMVo flogApiMVo) {

            // 合计数据
            excelExportExecutor.replaceContent(sheet, startRow, 0, flogApiMVo.getIndex());
            excelExportExecutor.replaceContent(sheet, startRow, 1, flogApiMVo.getApiName());
            excelExportExecutor.replaceContent(sheet, startRow, 2, flogApiMVo.getInterfaceType());
            excelExportExecutor.replaceContent(sheet, startRow, 3, null==flogApiMVo.getRequestTime()?0:flogApiMVo.getRequestTime().format(DateUtils.FMT_SECOND));
            excelExportExecutor.replaceContent(sheet, startRow, 4, flogApiMVo.getResponseTime().format(DateUtils.FMT_SECOND));
            excelExportExecutor.replaceContent(sheet, startRow, 5, flogApiMVo.getStatus());
            return startRow + 1;
    }

    /**
     * 处理导出的数据
     *
     * @author xiu.fu
     * @date 2020/4/30  下午 6:49
     */
    private void doExportData(List<FlogApiMVo> flogApiMVoList) {
        final String SUCCESS = "成功";
        final String FAILD = "失败";
        final String IN = "I";
        final String OUT = "O";
        final String CLIENT = "客户端";
        final String SERVICE = "服务端";
        if (CollectionUtils.isNotEmpty(flogApiMVoList)) {
            for (int i = 0; i < flogApiMVoList.size(); i++) {
                FlogApiMVo flogApiMVo = flogApiMVoList.get(i);
                // 序号
                flogApiMVo.setIndex(i + 1);
                flogApiMVo.setApiName(flogApiMVo.getApiName());
                flogApiMVo.setRequestDate(null != flogApiMVo.getRequestTime() ? flogApiMVo.getRequestTime().format(DateUtils.FMT_DATE) : null);
                flogApiMVo.setResponseDate(flogApiMVo.getResponseTime().format(DateUtils.FMT_DATE));
                // 接口类型
                if (IN.equals(flogApiMVo.getApiType())) {
                    flogApiMVo.setInterfaceType(SERVICE);
                } else if (OUT.equals(flogApiMVo.getApiType())) {
                    flogApiMVo.setInterfaceType(CLIENT);
                }
                // 响应状态
                if (FieldConstant.FlogApiM.RESULT_CODE_SUCCESS.equals(flogApiMVo.getResultCode())) {
                    flogApiMVo.setStatus(SUCCESS);
                } else {
                    flogApiMVo.setStatus(FAILD);
                }

            }
        }
    }

}
