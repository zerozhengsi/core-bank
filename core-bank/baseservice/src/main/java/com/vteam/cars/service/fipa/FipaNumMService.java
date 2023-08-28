package com.vteam.cars.service.fipa;

import com.vteam.cars.entity.model.FipaNumM;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 * 系统编号生成表 服务类
 * </p>
 *
 * @author vteam-generator
 * @since 2022-10-14
 */
public interface FipaNumMService extends IService<FipaNumM> {

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
     * 获取序列组唯一key .
     *
     * @param key key
     * @return java.lang.String 处理后的唯一key
     * @author andy.sher
     * @date 2019/6/19 18:15
     */
    String getSequenceGroupKey(String key);


    /**
     * 获取对应编号目前序号值
     *
     * @param numType
     * @param numValue
     * @return 目前序号值
     * @author oscar.yu
     */
    Integer getMaxNum(Integer numType, String numValue);

    /**
     * 获取对应编号及日期目前序号值
     *
     * @param numType
     * @param numValue
     * @return 目前序号值
     * @author oscar.yu
     */
    Integer getMaxNum(Integer numType, String numValue, String numDate);

    /**
     * 获取对应编号目前填充序号值(默认填充在前面，填充值为0)
     *
     * @param numType
     * @param numValue
     * @param length
     * @return
     */
    String getMaxNumFillStr(Integer numType, String numValue, int length);

    /**
     * 获取对应编号及日期目前填充序号值(默认填充在前面，填充值为0)
     *
     * @param numType
     * @param numValue
     * @param length
     * @return
     */
    String getMaxNumFillStr(Integer numType, String numValue, String numDate, int length);

    /**
     * 获取对应编号目前填充序号值
     *
     * @param numType
     * @param numValue
     * @param length
     * @param filler
     * @param isAppend
     * @return 目前填充序号值
     * @author oscar.yu
     */
    String getMaxNumFillStr(Integer numType, String numValue, int length, String filler, boolean isAppend);

    /**
     * 获取对应编号及日期目前填充序号值
     *
     * @param numType
     * @param numValue
     * @param numDate
     * @param length
     * @param filler
     * @param isAppend
     * @return 目前填充序号值
     * @author oscar.yu
     */
    String getMaxNumFillStr(Integer numType, String numValue, String numDate, int length, String filler, boolean isAppend);

    /**
     * 获取当前交易流水号
     *
     * @return
     */
    String getCurrentTxNo();

    /**
     * 保存序列信息 .
     *
     * @param numTypeFundNo        序列类型
     * @param numTypeFundNoMembers 序列key集合
     * @author andy.sher
     * @date 2019/6/20 10:53
     */
    void saveFipaNum(int numTypeFundNo, Set<String> numTypeFundNoMembers);

    /**
     * 生成兑换码批次号 .<br>
     *
     * @since 2023/3/8 14:10
     * @author toby.tang
     * @return String
     */
    String getRedeemBatchno();

    /**
     * 投资申请编号<br>.
     *
     * @author xia.hang
     * @date 2023/5/24 11:16

     * @return String
    */
    String getAplCode();

    /**
     * 生成合同相关编号<br>.
     *
     * @author xia.hang
     * @date 2023/5/26 16:57
     * @param type
     * @return String
    */
    String getFcnCode(String type);

}
