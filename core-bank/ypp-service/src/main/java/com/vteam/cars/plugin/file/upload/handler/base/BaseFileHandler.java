package com.vteam.cars.plugin.file.upload.handler.base;

import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.entity.vo.FbtxApxMVo;
import com.vteam.cars.plugin.file.upload.support.FileHandler;
import com.vteam.cars.util.StringUtils;

import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * 文件处理器基类 .<br>
 *
 * @author andy.sher
 * @date 2018/11/14 17:33
 */
public abstract class BaseFileHandler implements FileHandler {

    /**
     * 获取文件名后缀 .
     *
     * @param fileName 文件名
     * @return java.lang.String 文件后缀名
     * @author andy.sher
     * @date 2018/11/14 17:35
     */
    protected String getFileNameSuffix(String fileName) {
        String suffix = StringUtils.EMPTY;
        int suffixIndex = fileName.lastIndexOf(GlobalConstants.Symbol.SPOT);
        if (suffixIndex > 0) {
            suffix = fileName.substring((suffixIndex + 1));
        }
        return suffix;
    }

    @Override
    public List<FbtxApxMVo> listHistoryFile(@NotNull Integer orgRefcode, @NotNull String archiveType) {
        return null;
    }

    @Override
    public List<FbtxApxMVo> listByBKeys(@NotNull String bkeys) {
        return null;
    }
}
