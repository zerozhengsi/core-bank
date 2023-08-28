package com.vteam.cars.plugin.file.upload.support;

import com.vteam.cars.entity.model.FbtxApxM;
import com.vteam.cars.entity.vo.FbtxApxMVo;
import com.vteam.cars.plugin.file.upload.enums.FileBKeyEnum;

import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * 文件处理 .<br>
 *
 * @author andy.sher
 * @date 2018/11/14 10:49
 */
public interface FileHandler {

    /**
     * 保存文件 .
     *
     * @param fileName 文件名称
     * @param fileDesc 文件描述
     * @param bkey 存储路径
     * @param filePath 文件临时路径
     * @param fileUuid 文件唯一标识
     * @return 附件信息
     * @author andy.sher
     * @date 2018/11/14 17:29
     */
    FbtxApxMVo save(@NotNull String fileName, String author, String fileDesc, String bkey, @NotNull String filePath, String fileUuid);

    /**
     * 保存文件 .
     *
     * @param fileName 文件名称
     * @param fileDesc 文件描述
     * @param bkey 存储路径
     * @param filePath 文件临时路径
     * @param fileUuid 文件唯一标识
     * @param resourceUuid 文件资源唯一标识
     * @return 附件信息
     * @author andy.sher
     * @date 2018/11/14 17:29
     */
    FbtxApxMVo save(@NotNull String fileName, String author, String fileDesc, String bkey, @NotNull String filePath,
            String fileUuid, String resourceUuid);

    /**
     * 保存第三方平台文件信息(仅信息，附件异步下载).
     *
     * @param fileName 文件名称
     * @param bkey 存储路径
     * @param fileUuid 文件唯一标识
     * @param resourceUuid 文件资源唯一编号
     * @param tppRefcode 第三方平台流水号
     * @return 附件信息
     * @author oscar.yu
     * @date 2020/3/9 16:41:47
     */
    FbtxApxMVo saveTppApx(@NotNull String fileName, String bkey, String fileUuid, String resourceUuid, Integer tppRefcode);

    /**
     * 写入第三方平台文件信息(文件及下载状态).
     * 
     * @param fileData
     * @param fbtxApxM
     * @return
     * @author oscar.yu
     * @date 2020/3/15 09:37
     */
    FbtxApxM uploadTppApx(byte[] fileData, FbtxApxM fbtxApxM);

    /**
     * 分片保存文件 .
     *
     * @param fileName 文件名称
     * @param fileDesc 文件描述
     * @param bkey 存储路径
     * @param uuid 文件唯一标识
     * @param filePath 文件分片临时目录
     * @return com.vteam.sme.entity.vo.FbtxApxMVo 附件信息
     * @author andy.sher
     * @date 2018/11/15 9:44
     */
    FbtxApxMVo slicedSave(@NotNull String fileName, @NotNull String author, String fileDesc, @NotNull FileBKeyEnum bkey, String uuid, @NotNull String filePath) throws Exception;

    /**
     * 删除文件 .
     *
     * @param fbtxApxMVo 附件信息
     * @return void
     * @author andy.sher
     * @date 2018/11/14 11:00
     */
    void delete(@NotNull FbtxApxMVo fbtxApxMVo);

    /**
     * 获取文件 .
     *
     * @param fbtxApxMVo 附件信息
     * @return com.vteam.sme.entity.vo.FbtxApxMVo 附件信息
     * @author andy.sher
     * @date 2018/11/14 11:00
     */
    void get(@NotNull FbtxApxMVo fbtxApxMVo);

    /**
     * 获取历史文件列表 .
     *
     * @param orgRefcode  企业流水号
     * @param archiveType 附件类型
     * @return java.util.List<com.vteam.sme.entity.vo.FbtxApxMVo> 附件信息列表
     * @author andy.sher
     * @date 2018/11/14 11:01
     */
    List<FbtxApxMVo> listHistoryFile(@NotNull Integer orgRefcode, @NotNull String archiveType);

    /**
     * 预览文件 .
     *
     * @param fbtxApxMVo 附件信息
     * @return com.vteam.sme.entity.vo.FbtxApxMVo 附件信息
     * @author andy.sher
     * @date 2018/11/14 11:01
     */
    FbtxApxMVo getPreviewFile(@NotNull FbtxApxMVo fbtxApxMVo);

    /**
     * 按多个主键查询多个文件 .
     *
     * @param fbtxApxMVoList 附件信息集合
     * @return java.util.List<com.vteam.sme.entity.vo.FbtxApxMVo> 附件信息集合
     * @author andy.sher
     * @date 2018/11/14 11:02
     */
    List<FbtxApxMVo> listByRefcodes(@NotNull List<FbtxApxMVo> fbtxApxMVoList);

    /**
     * 按存放路径查询多个文件 .
     *
     * @param bkeys 路径
     * @return java.util.List<com.vteam.sme.entity.vo.FbtxApxMVo> 文件集合
     * @author andy.sher
     * @date 2018/11/14 11:03
     */
    List<FbtxApxMVo> listByBKeys(@NotNull String bkeys);

}
