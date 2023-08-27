package com.vteam.cars.service.fbtx;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vteam.cars.entity.model.FbtxApxM;
import com.vteam.cars.entity.vo.FbtxApxMVo;

import java.util.List;

/**
 * <p>
 * 附件存储表 服务类
 * </p>
 *
 * @author vteam-generator
 * @since 2022-10-14
 */
public interface FbtxApxMService extends IService<FbtxApxM> {

    /**
     * 按批次号获取文件 .<br>
     *
     * @param uuid UUID
     * @return java.util.List<com.vteam.sme.entity.model.FbtxApxM>
     * @author andy.sher
     * @date 2018/8/3 13:16
     */
    List<FbtxApxMVo> listContractByUUID(String uuid);


    /**
     * 通过附件流水号删除附件 .
     *
     * @param refcode
     * @return void
     * @author zack.yin
     * @date 2018/8/29 19:20
     */
    void removeFileByRefcode(Integer refcode);

    /**
     * 通过资源唯一UUID删除附件
     *
     * @param resourceUuid
     * @return void
     * @author jiangming.huang
     * @date 2019/5/15 9:35
     */
    void removeFileByResourceUuid(String resourceUuid);

    /**
     * 通过附件流水号数组批量删除附件
     *
     * @param refcodes
     * @return void
     * @author jiangming.huang
     * @date 2018/12/4 0004 上午 11:03
     */
    void removeFileByRefcodes(Integer[] refcodes);

    /**
     * 通过资源唯一UUID数组删除附件
     *
     * @param resourceUuids
     * @return void
     * @author jiangming.huang
     * @date 2019/5/15 16:08
     */
    void removeFileByResourceUuids(String[] resourceUuids);


    /**
     * 条件获取附件信息 .
     *
     * @param fbtxApxMVo
     * @return java.util.List<com.vteam.sme.entity.vo.FbtxApxMVo>
     * @author zack.yin
     * @date 2019/1/23 15:54
     */
    List<FbtxApxMVo> listFbpaApxMByCondition(FbtxApxMVo fbtxApxMVo);

    /**
     * 根据资源唯一UUID获取文件信息
     *
     * @param resourceUuid
     * @return com.vteam.cars.entity.model.FbtxApxM
     * @author jiangming.huang
     * @date 2019/5/14 15:24
     */
    FbtxApxM getByResourceUuid(String resourceUuid);

    /**
     * 通过文件uuid获取附件信息 .
     *
     * @param fileUuid
     * @return com.vteam.cars.entity.model.FbtxApxM
     * @author fu.tong
     * @date 2019/7/19 16:53
     */
    FbtxApxM getFileByFileUuid(String fileUuid);

    /**
     * 根据唯一标识获取文件信息(可能为resourceUuid或fileUuid，优先通过resourceUuid获取)
     *
     * @param uuid
     * @return com.vteam.cars.entity.vo.FbtxApxMVo
     * @author oscar.yu
     * @date 2020/4/7 14:37
     */
    FbtxApxMVo getFileByUuid(String uuid);


    /**
     * 根据条件获取附件信息
     * @param condition 条件
     * @return java.util.List<com.vteam.cars.entity.vo.FbtxApxMVo>
     * @author shiping.song
     * @date 2022/12/20 15:23
     */
    List<FbtxApxMVo> listFbtxApxMListByCondition(FbtxApxMVo condition);

}
