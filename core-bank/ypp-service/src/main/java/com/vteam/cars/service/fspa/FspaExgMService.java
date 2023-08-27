package com.vteam.cars.service.fspa;

import com.vteam.cars.entity.model.FspaExgM;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vteam.cars.entity.vo.FspaExgMVo;

import java.util.List;

/**
 * <p>
 * 参数配置表 服务类
 * </p>
 *
 * @author vteam-generator
 * @since 2022-10-14
 */
public interface FspaExgMService extends IService<FspaExgM> {

    /**
     * 获取字典项 .
     *
     * @param parmType 参数类型
     * @param parmName 参数名称
     * @return java.lang.String
     * @author zack.yin
     * @date 2018/9/7 19:49
     */
    String getParmValueByCondition(String parmType, String parmName);

    /**
     * 根据条件获取字典数据
     *
     * @param fspaExgMVo
     * @return
     * @author zhuang.shao
     * @date 2018年9月11日 下午11:21:58
     */
    List<FspaExgMVo> listExgInfoByCondition(FspaExgMVo fspaExgMVo);

}
