package com.vteam.cars.plugin.file.upload.handler;

import com.vteam.cars.constant.FieldConstant;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.entity.model.FbtxApxM;
import com.vteam.cars.entity.vo.FbtxApxMVo;
import com.vteam.cars.plugin.file.upload.enums.FileBKeyEnum;
import com.vteam.cars.plugin.file.upload.handler.base.BaseFileHandler;
import com.vteam.cars.util.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * 文件中心文件处理器 .<br>
 *
 * @author andy.sher
 * @date 2018/7/16 11:18
 */
@Slf4j
@Service
public class RemoteFileHandler extends BaseFileHandler {

//    @Resource(type = FileCenterService.class)
//    private FileCenterService fileCenterService;

    public RemoteFileHandler() {
        log.info("文件中心文件处理器初始化成功。");
    }

    @Override
    public FbtxApxMVo save(@NotNull String fileName, @NotNull String author, String fileDesc, String bkey,
                           @NotNull String filePath, String uuid) {
        return this.save(fileName, author, fileDesc, bkey, filePath, uuid, null);
    }

    @Override
    public FbtxApxMVo save(@NotNull String fileName, String author, String fileDesc, String bkey, @NotNull String filePath,
            String fileUuid, String resourceUuid) {
        // 文件标签
        String tag = bkey;
        byte[] data = FileUtils.readAllBytes(filePath);
        // 存储文件并获取文件ID
//        String fileId = fileCenterService.save(fileName, fileDesc, tag, author, bkey, data, fileUuid);
//        if (StringUtils.isNotBlank(fileId)) {
//            // 创建附件对象
//            FbtxApxMVo fbtxApxMVo = new FbtxApxMVo();
//            fbtxApxMVo.setFileType(getFileNameSuffix(fileName));
//            // 兼容需要指定资源唯一标识的情况
//            if (StringUtils.isNotBlank(resourceUuid)) {
//                fbtxApxMVo.setResourceUuid(resourceUuid);
//            }
//            fbtxApxMVo.setFileUuid(fileUuid);
//            fbtxApxMVo.setBizKey(bkey);
//            fbtxApxMVo.setFileName(fileName);
//            fbtxApxMVo.setFileId(fileId);
//            fbtxApxMVo.setFileSource(FieldConstant.FbtxApx.FILESOURCE_FC);
//            fbtxApxMVo.setDelFlag(FieldConstant.PublicFieldValue.DEL_FLAG_NO);
//            return fbtxApxMVo;
//        }
        return null;
    }

    @Override
    public FbtxApxMVo saveTppApx(@NotNull String fileName, String bkey, String uuid, String resourceUuid, Integer tppRefcode) {
        // 创建附件对象
        FbtxApxMVo fbtxApxMVo = new FbtxApxMVo();
        fbtxApxMVo.setFileType(getFileNameSuffix(fileName));
        fbtxApxMVo.setResourceUuid(resourceUuid);
        fbtxApxMVo.setFileUuid(uuid);
        fbtxApxMVo.setBizKey(bkey);
        fbtxApxMVo.setFileName(fileName);
        fbtxApxMVo.setFileSource(FieldConstant.FbtxApx.FILESOURCE_FC);
        // 来源其它平台、需下载到本地
        fbtxApxMVo.setDataSource(FieldConstant.FbtxApx.DATA_SOURCE_TPP);
        fbtxApxMVo.setTppRefcode(tppRefcode);
        fbtxApxMVo.setDownloaded(GlobalConstants.Flag.FALSE);
        fbtxApxMVo.setDownloadCount(0);
        fbtxApxMVo.setDelFlag(FieldConstant.PublicFieldValue.DEL_FLAG_NO);
        return fbtxApxMVo;
    }

    @Override
    public FbtxApxM uploadTppApx(byte[] fileData, FbtxApxM fbtxApxM) {
        // 文件标签
        final String tag = fbtxApxM.getBizKey();
        final String fileName = fbtxApxM.getFileName();
        final String author = fbtxApxM.getCreateUser();
        // 存储文件并获取文件ID
//        String fileId = fileCenterService.save(fileName, fileName, tag, author, tag, fileData, fbtxApxM.getFileUuid());
//        if (StringUtils.isNotBlank(fileId)) {
//            fbtxApxM.setFileId(fileId);
//            fbtxApxM.setDownloaded(GlobalConstants.Flag.TRUE);
//            fbtxApxM.setDownloadCount(fbtxApxM.getDownloadCount() + 1);
//        }
        return fbtxApxM;
    }

    @Override
    public FbtxApxMVo slicedSave(@NotNull String fileName, @NotNull String author, String fileDesc, @NotNull FileBKeyEnum bkey, String uuid, @NotNull String filePath) throws Exception {
        // 文件标签
        String tag = bkey.getBkey();

        return null;
    }

    @Override
    public void delete(@NotNull FbtxApxMVo fbtxApxMVo) {
        if (null != fbtxApxMVo) {
//            fileCenterService.delete(fbtxApxMVo.getFileId());
            fbtxApxMVo.setDelFlag(FieldConstant.PublicFieldValue.DEL_FLAG_YES);
        }
    }

    @Override
    public void get(@NotNull FbtxApxMVo fbtxApxMVo) {
//        AttachmentPojo attachmentPojo = fileCenterService.get(fbtxApxMVo.getFileId());
//        fbtxApxMVo.setData(null != attachmentPojo.getFileBlob() ? attachmentPojo.getFileBlob().clone() : null);
    }

    @Override
    public FbtxApxMVo getPreviewFile(@NotNull FbtxApxMVo fbtxApxMVo) {
//        AttachmentPojo attachmentPojo = fileCenterService.getPreviewFile(fbtxApxMVo.getFileId());
//        fbtxApxMVo.setData(null != attachmentPojo.getFileBlob() ? attachmentPojo.getFileBlob().clone() : null);
        return fbtxApxMVo;
    }

    @Override
    public List<FbtxApxMVo> listByRefcodes(@NotNull List<FbtxApxMVo> fbtxApxMVoList) {
//        if (CollectionUtils.isNotEmpty(fbtxApxMVoList)) {
//            AttachmentPojo attachmentPojo;
//            for (FbtxApxMVo fbtxApxMVo : fbtxApxMVoList) {
//                attachmentPojo = fileCenterService.get(fbtxApxMVo.getFileId());
//                fbtxApxMVo.setData(null != attachmentPojo.getFileBlob() ? attachmentPojo.getFileBlob().clone() : null);
//            }
//        }

        return fbtxApxMVoList;
    }

}
