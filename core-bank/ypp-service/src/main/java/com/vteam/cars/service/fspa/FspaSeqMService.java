package com.vteam.cars.service.fspa;

import com.vteam.cars.entity.model.FspaSeqM;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 数据库序列表 服务类
 * </p>
 *
 * @author vteam-generator
 * @since 2022-10-14
 */
public interface FspaSeqMService extends IService<FspaSeqM> {

    /**
     * 获取序列唯一key .
     *
     * @param key key
     * @return java.lang.String 处理后的唯一key
     * @author andy.sher
     * @date 2019/6/19 18:15
     */
    String getSequenceKey(String key);

    /**
     * 获取最大的流水号
     * @param sequenceName
     * @return
     */
    int nextval(String sequenceName);

}
