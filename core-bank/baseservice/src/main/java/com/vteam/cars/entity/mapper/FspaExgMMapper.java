package com.vteam.cars.entity.mapper;

import com.vteam.cars.entity.model.FspaExgM;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vteam.cars.entity.vo.FspaExgMVo;

import java.util.List;

/**
 * <p>
 * 参数配置表 Mapper 接口
 * </p>
 *
 * @author vteam-generator
 * @since 2022-10-14
 */
public interface FspaExgMMapper extends BaseMapper<FspaExgM> {

    /**
     * 获取字典项 .
     *
     * @param fspaExgMVo
     * @return java.lang.String
     * @author zack.yin
     * @date 2018/9/9 22:59
     */
    String getParmValueByCondition(FspaExgMVo fspaExgMVo);

    /**
     * 根据条件获取字典数据
     *
     * @param fspaExgMVo
     * @return
     * @author zhuang.shao
     * @date 2018年9月11日 下午11:22:19
     */
    List<FspaExgMVo> listExgInfoByCondition(FspaExgMVo fspaExgMVo);

}
