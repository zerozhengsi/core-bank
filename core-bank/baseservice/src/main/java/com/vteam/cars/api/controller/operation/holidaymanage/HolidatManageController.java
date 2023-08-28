package com.vteam.cars.api.controller.operation.holidaymanage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.entity.model.FipaHdyM;
import com.vteam.cars.entity.vo.FipaHdyMVo;
import com.vteam.cars.plugin.provider.HdyInfoProvider;
import com.vteam.cars.service.fipa.FipaHdyMService;
import com.vteam.cars.util.SmeAssert;
import com.vteam.vtarm.api.ReqEntity;
import com.vteam.vtarm.api.RespEntity;
import com.vteam.vtarm.cache.MapContainer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 节假日管理 .
 *
 * @author fu.tong
 * @date 2019/10/17 10:55
 */
@RestController
@RequestMapping(value = "/holidatManage")
public class HolidatManageController {

    @Resource(type = FipaHdyMService.class)
    private FipaHdyMService fipaHdyMService;


    @Resource
    private MapContainer mapContainer;

    /**
     * 获取当前月份节假日配置信息 .
     *
     * @param reqEntity
     * @return com.vteam.mssme.api.entity.RespEntity
     * @author fu.tong
     * @date 2019/10/17 10:57
     */
    @PostMapping(value = "/getMonthHoliday")
    public RespEntity getMonthHoliday(@RequestBody ReqEntity reqEntity) {
        FipaHdyMVo fipaHdyMVo = JSON.parseObject(reqEntity.getData(), FipaHdyMVo.class);
        List<FipaHdyM> fipaHdyMVoList = fipaHdyMService.listFipaHdyMByConditon(fipaHdyMVo);
        JSONObject data = new JSONObject();
        data.put("holiday", fipaHdyMVoList);
        return RespEntity.ok(data);
    }

    /**
     * 设置节假日 .
     *
     * @param reqEntity
     * @return com.vteam.mssme.api.entity.RespEntity
     * @author fu.tong
     * @date 2019/10/17 11:26
     */
    @PostMapping(value = "/setHoliday")
    public RespEntity setHoliday(@RequestBody ReqEntity reqEntity) {
        FipaHdyMVo fipaHdyMVo = JSON.parseObject(reqEntity.getData(), FipaHdyMVo.class);
        // 判断是否为节假日
        if (GlobalConstants.Flag.TRUE.equals(fipaHdyMVo.getIsHoliday())) {
            // 添加为节假日
            SmeAssert.isEmpty(fipaHdyMService.listFipaHdyMByConditon(fipaHdyMVo), "该日期已经是节假日!");
            fipaHdyMService.save(fipaHdyMVo);
            mapContainer.put(HdyInfoProvider.HDY, fipaHdyMVo.getHdyYear()+"-"+fipaHdyMVo.getHdyMonth()+"-"+fipaHdyMVo.getHdyDay(), "true");
        } else {
            // 删除节假日
            SmeAssert.notEmpty(fipaHdyMService.listFipaHdyMByConditon(fipaHdyMVo), "该日期不是节假日!");
            fipaHdyMService.doRemoveHoliday(fipaHdyMVo);
            mapContainer.delete(HdyInfoProvider.HDY, fipaHdyMVo.getHdyYear()+"-"+fipaHdyMVo.getHdyMonth()+"-"+fipaHdyMVo.getHdyDay());
        }
        return RespEntity.ok();
    }
    
    /**
     * 节假日查询 .
     *
     * @param reqEntity
     * @return com.vteam.mssme.api.entity.RespEntity
     * @author fu.tong
     * @date 2019/10/17 11:26
     */
    @PostMapping(value = "/getHoliday")
    public RespEntity getHoliday(@RequestBody ReqEntity reqEntity) {
    	FipaHdyMVo fipaHdyMVo = JSON.parseObject(reqEntity.getData(),FipaHdyMVo.class);
    	FipaHdyM fipaHdyM =fipaHdyMService.getHolidayInfo(fipaHdyMVo);
    	return RespEntity.ok(JSONObject.toJSONString(fipaHdyM));
    }
}
