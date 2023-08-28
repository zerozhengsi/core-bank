package com.vteam.cars.plugin.file.upload.handler;

import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.entity.model.FbtxApxM;
import com.vteam.cars.entity.vo.FbtxApxMVo;
import com.vteam.vtarm.cache.StringValueContainer;
import com.vteam.cars.plugin.file.upload.enums.FileBKeyEnum;
import com.vteam.cars.plugin.file.upload.handler.base.BaseFileHandler;
import com.vteam.cars.util.FileUtils;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import java.io.File;
import java.util.List;

/**
 * 内存文件处理器 .<br>
 *
 * @author andy.sher
 * @date 2019/3/14 10:13
 */
@Service
public class RAMFileHandler extends BaseFileHandler {

    @Resource(type = StringValueContainer.class)
    private StringValueContainer stringValueContainer;

    @Override
    public FbtxApxMVo save(@NotNull String fileName, @NotNull String author, String fileDesc, String bkey,
            @NotNull String filePath, String uuid) {
        return this.save(fileName, author, fileDesc, bkey, filePath, uuid, null);
    }

    @Override
    public FbtxApxMVo save(@NotNull String fileName, String author, String fileDesc, String bkey, @NotNull String filePath,
            String fileUuid, String resourceUuid) {
        stringValueContainer.set(fileUuid, filePath);
        FbtxApxMVo fbtxApxMVo = new FbtxApxMVo();
        fbtxApxMVo.setFileUuid(fileUuid);
        return fbtxApxMVo;
    }

    @Override
    public FbtxApxMVo saveTppApx(@NotNull String fileName, String bkey, String fileUuid, String resourceUuid, Integer tppRefcode) {
        // do nothing
        return null;
    }

    @Override
    public FbtxApxM uploadTppApx(byte[] fileData, FbtxApxM fbtxApxM) {
        // do nothing
        return null;
    }

    @Override
    public FbtxApxMVo slicedSave(@NotNull String fileName, @NotNull String author, String fileDesc, @NotNull FileBKeyEnum bkey, String uuid, @NotNull String filePath) throws Exception {
        return null;
    }

    @Override
    public void delete(@NotNull FbtxApxMVo fbtxApxMVo) {
        stringValueContainer.delete(fbtxApxMVo.getFileUuid());
    }

    @Override
    public void get(@NotNull FbtxApxMVo fbtxApxMVo) {
        String filePath = stringValueContainer.get(fbtxApxMVo.getFileUuid());
        fbtxApxMVo.setData(FileUtils.readAllBytes(filePath));
        fbtxApxMVo.setFileType(filePath.substring(filePath.lastIndexOf(GlobalConstants.Symbol.SPOT) + 1));
        fbtxApxMVo.setFileName(new File(filePath).getName());
    }

    @Override
    public FbtxApxMVo getPreviewFile(@NotNull FbtxApxMVo fbtxApxMVo) {
        FbtxApxMVo realFbtxApxMVo = new FbtxApxMVo();
        String filePath = stringValueContainer.get(fbtxApxMVo.getFileUuid());
        realFbtxApxMVo.setFileUuid(fbtxApxMVo.getFileUuid());
        realFbtxApxMVo.setData(FileUtils.readAllBytes(filePath));
        return realFbtxApxMVo;
    }

    @Override
    public List<FbtxApxMVo> listByRefcodes(@NotNull List<FbtxApxMVo> fbtxApxMVoList) {
        return null;
    }

}
