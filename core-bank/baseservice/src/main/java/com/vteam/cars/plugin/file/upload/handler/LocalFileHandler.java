package com.vteam.cars.plugin.file.upload.handler;

import com.vteam.cars.config.SmeConfiguration;
import com.vteam.cars.constant.FieldConstant;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.entity.model.FbtxApxM;
import com.vteam.cars.entity.vo.FbtxApxMVo;
import com.vteam.cars.plugin.file.upload.enums.FileBKeyEnum;
import com.vteam.cars.plugin.file.upload.handler.base.BaseFileHandler;
import com.vteam.cars.service.common.CommonService;
import com.vteam.cars.service.fbtx.FbtxApxMService;
import com.vteam.cars.util.FileUtils;
import com.vteam.cars.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import javax.crypto.Cipher;
import jakarta.validation.constraints.NotNull;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 本地文件处理器 .<br>
 *
 * @author andy.sher
 * @date 2018/7/16 11:17
 */
@Slf4j
@Service
public class LocalFileHandler extends BaseFileHandler {

    @Autowired
    private FbtxApxMService fbtxApxMService;

    @Resource(type = CommonService.class)
    private CommonService commonService;

    @Resource(type = SmeConfiguration.class)
    private SmeConfiguration smeConfiguration;

    public LocalFileHandler() {
        log.info("本地文件处理器初始化成功。");
    }

    /**
     * 上传附件 .
     *
     * @param file
     * @param fileName
     * @return com.vteam.sme.entity.model.FbtxApxM
     * @author fu.tong
     * @date 2018/10/10 15:43
     */
    public FbtxApxM uploadApx(File file, String fileName) {
        // 文件名后缀修复
        if (StringUtils.isNotBlank(fileName) && fileName.indexOf(GlobalConstants.Symbol.SPOT) == -1) {
            // 获取文件的后缀名
            String fileSuffix = this.obtainFileSuffix(file.getName());
            fileName += GlobalConstants.Symbol.SPOT + fileSuffix;
        }
        return this.uploadApx(file, fileName, null);
    }

    /**
     * 上传附件 .
     *
     * @param file
     * @param fileName
     * @param inUuid
     * @return com.vteam.sme.entity.model.FbtxApxM
     * @author zack.yin
     * @date 2018/8/8 22:20
     */
    public FbtxApxM uploadApx(File file, String fileName, String inUuid) {
        // uuid生成的存储文件名
        String uuid = StringUtils.isBlank(inUuid) ? UUID.randomUUID().toString() : inUuid;
        FbtxApxMVo fbtxApxMVo = this.doSave(file, fileName, uuid, null, null, null);
        // 新增附件信息
        fbtxApxMService.save(fbtxApxMVo);
        return fbtxApxMVo;
    }

    /**
     * 获取文件名后缀
     *
     * @param fileName
     * @return java.lang.String
     * @author zack.yin
     * @date 2018/7/16 14:20
     */
    private String obtainFileSuffix(String fileName) {
        String suffix = StringUtils.EMPTY;
        int suffixIndex = fileName.lastIndexOf(GlobalConstants.Symbol.SPOT);
        if (suffixIndex > 0) {
            suffix = fileName.substring((suffixIndex + 1));
        }
        return suffix;
    }

    /**
     * 获取文件类型[D-Word文件/E-Excel文件/M-图片/P-PDF/O-其他]
     *
     * @param fileName
     * @return java.lang.String
     * @author zack.yin
     * @date 2018/7/16 14:29
     */
    private String obtainFileStyle(String fileName) {
        String extend = "O";
        String suffix = this.obtainFileSuffix(fileName);
        if (GlobalConstants.FileFormat.DOC.equalsIgnoreCase(suffix) || GlobalConstants.FileFormat.DOCX.equalsIgnoreCase(suffix)) {
            extend = "D";
        } else if (GlobalConstants.FileFormat.TIF.equalsIgnoreCase(suffix)
                || GlobalConstants.FileFormat.JPEG.equalsIgnoreCase(suffix)
                || GlobalConstants.FileFormat.GIF.equalsIgnoreCase(suffix)
                || GlobalConstants.FileFormat.BMP.equalsIgnoreCase(suffix)
                || GlobalConstants.FileFormat.JPG.equalsIgnoreCase(suffix)) {
            extend = "M";
        } else if (GlobalConstants.FileFormat.XLS.equalsIgnoreCase(suffix)
                || GlobalConstants.FileFormat.XLSX.equalsIgnoreCase(suffix)) {
            extend = "X";
        } else if (GlobalConstants.FileFormat.PDF.equalsIgnoreCase(suffix)) {
            extend = "P";
        } else if (GlobalConstants.FileFormat.TXT.equalsIgnoreCase(suffix)) {
            extend = "T";
        }
        return extend;
    }

    /**
     * 获取文件路径
     *
     * @param fileStyle
     * @return java.lang.String
     * @author zack.yin
     * @date 2018/7/16 14:25
     */
    private String obtainFileStorePath(String fileStyle) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        return year + GlobalConstants.Symbol.UNDERLINE + month + GlobalConstants.Symbol.UNDERLINE + fileStyle + GlobalConstants.Symbol.UNDERLINE;
    }

    /**
     * 将数据库中的GlobalConstants.Symbol.UNDERLINE替换成文件分隔符
     *
     * @param storePathInDB
     * @return java.lang.String
     * @author zack.yin
     * @date 2018/7/20 16:38
     */
    private static String parseStorePath(String storePathInDB) {
        if (storePathInDB == null) {
            storePathInDB = StringUtils.EMPTY;
        } else {
            storePathInDB = String.join(StringUtils.getSysSeparator(), storePathInDB.split("_")) + StringUtils.getSysSeparator();
        }
        return storePathInDB;
    }

    @Override
    public FbtxApxMVo save(@NotNull String fileName, String author, String fileDesc, String bizKey, @NotNull String filePath,
            String fileUuid) {
        return this.save(fileName, author, fileDesc, bizKey, filePath, fileUuid, null);
    }

    @Override
    public FbtxApxMVo save(@NotNull String fileName, String author, String fileDesc, String bizKey, @NotNull String filePath,
            String fileUuid, String resourceUuid) {
        File file = new File(filePath);
        // 处理保存文件逻辑
        return this.doSave(file, fileName, fileUuid, resourceUuid, fileDesc, bizKey);
    }

    @Override
    public FbtxApxMVo saveTppApx(@NotNull String fileName, String bizKey, String fileUuid, String resourceUuid, Integer tppRefcode) {
        // 创建附件对象
        FbtxApxMVo fbtxApxMVo = new FbtxApxMVo();
        // 附件流水号
        int refcode = commonService.getMaxRefcodeByBeanClass(FbtxApxM.class);
        // 构建附件存储表
        fbtxApxMVo.setRefcode(refcode);
        fbtxApxMVo.setResourceUuid(resourceUuid);
        fbtxApxMVo.setFileUuid(fileUuid);
        // 文件来源[1-本系统/2-信贷系统]
        fbtxApxMVo.setFileSource(FieldConstant.FbtxApx.FILESOURCE_LOCAL);
        fbtxApxMVo.setEncryptFlag(GlobalConstants.Flag.TRUE);
        fbtxApxMVo.setFileName(fileName);
        fbtxApxMVo.setBizKey(bizKey);
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
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(fileData)) {
            // 将文件写入到本地
            this.doSaveLocalFile(inputStream, fbtxApxM);
            // 更新下载状态
            fbtxApxM.setDownloaded(GlobalConstants.Flag.TRUE);
            fbtxApxM.setDownloadCount(fbtxApxM.getDownloadCount() + 1);
        } catch (Exception e) {
            log.error(LocalFileHandler.class.getName(), e);
            // 更新下载次数
            fbtxApxM.setDownloadCount(fbtxApxM.getDownloadCount() + 1);
        }
        return fbtxApxM;
    }

    /**
     * 处理保存文件逻辑.
     *
     * @param file 文件
     * @param fileName 文件名含后缀
     * @param fileUuid 文件唯一标识
     * @param resourceUuid 文件资源唯一标识
     * @param fileDesc 文件备注
     * @param bizKey
     * @return 附件信息
     * @author oscar.yu
     * @date 2019/9/9 17:05
     */
    private FbtxApxMVo doSave(File file, String fileName, String fileUuid, String resourceUuid, String fileDesc, String bizKey) {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            // 附件流水号
            int refcode = commonService.getMaxRefcodeByBeanClass(FbtxApxM.class);
            // 创建附件对象
            FbtxApxMVo fbtxApxMVo = new FbtxApxMVo();
            // 构建附件存储表
            fbtxApxMVo.setRefcode(refcode);
            // 兼容需要指定资源唯一标识的情况
            if (StringUtils.isNotBlank(resourceUuid)) {
                fbtxApxMVo.setResourceUuid(resourceUuid);
            }
            fbtxApxMVo.setFileUuid(fileUuid);
            // 文件来源[1-本系统/2-信贷系统]
            fbtxApxMVo.setFileSource(FieldConstant.FbtxApx.FILESOURCE_LOCAL);
            fbtxApxMVo.setEncryptFlag(GlobalConstants.Flag.TRUE);
            fbtxApxMVo.setFileName(fileName);
            // 将文件写入到本地
            this.doSaveLocalFile(inputStream, fbtxApxMVo);
            fbtxApxMVo.setMemo(fileDesc);
            fbtxApxMVo.setBizKey(bizKey);
            fbtxApxMVo.setDelFlag(FieldConstant.PublicFieldValue.DEL_FLAG_NO);
            return fbtxApxMVo;
        } catch (IOException e) {
            log.error(LocalFileHandler.class.getName(), e);
        } catch (Exception e) {
            log.error(LocalFileHandler.class.getName(), e);
        }
        return null;
    }

    /**
     * 将文件写入到本地，并记录文件存储信息
     *
     * @param inputStream
     * @param fbtxApxM
     * @author oscar.yu
     * @date 2020/3/15 09:17
     */
    private void doSaveLocalFile(InputStream inputStream, FbtxApxM fbtxApxM) {
        final int refcode = fbtxApxM.getRefcode();
        final String fileName = fbtxApxM.getFileName();
        // 获取文件的后缀名
        String fileSuffix = this.obtainFileSuffix(fileName);
        // 系统分盘符
        String fileSperator = StringUtils.getSysSeparator();
        // 获取存放附件的根目录（绝对路径）
        String rootPath = smeConfiguration.getFileRepositoryPath();
        // 防止配置路径不以"/"结尾
        rootPath = rootPath.trim();
        if (!rootPath.endsWith(fileSperator) && !rootPath.endsWith(GlobalConstants.Symbol.SLASH)) {
            rootPath = rootPath + fileSperator;
        }
        // 获取文件类型
        String fileStyle = this.obtainFileStyle(fileName);
        // 获取数据库文件存放路径
        String storePath = this.obtainFileStorePath(fileStyle);
        // 替换字符串 获取在磁盘中存放路径
        String storeAbsPath = parseStorePath(storePath);
        // 存储的文件名
        String storeName = refcode + GlobalConstants.Symbol.SPOT + GlobalConstants.FileFormat.FILE;
        String path = rootPath + storeAbsPath + storeName;
        File dest = new File(path);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            // 新建文件夹
            dest.getParentFile().mkdirs();
        }
        // 加密存储附件
        FileUtils.cryptFile(inputStream, dest, Cipher.ENCRYPT_MODE);
        // 记录对应信息
        fbtxApxM.setFileType(fileSuffix);
        fbtxApxM.setFilePath(storePath);
        fbtxApxM.setStoreName(storeName);
    }

    @Override
    public FbtxApxMVo slicedSave(@NotNull String fileName, @NotNull String author, String fileDesc, @NotNull FileBKeyEnum bkey, String uuid, @NotNull String filePath) throws Exception {
        return null;
    }

    @Override
    public void delete(@NotNull FbtxApxMVo fbtxApxMVo) {
        if (null != fbtxApxMVo) {
            fbtxApxMVo.setDelFlag(FieldConstant.PublicFieldValue.DEL_FLAG_YES);
        }
    }

    @Override
    public void get(@NotNull FbtxApxMVo fbtxApxMVo) {
        // 读取附件文件字节流
        this.readApxFileBytes(fbtxApxMVo);
    }

    @Override
    public FbtxApxMVo getPreviewFile(@NotNull FbtxApxMVo fbtxApxMVo) {
        // 读取附件文件字节流
        this.readApxFileBytes(fbtxApxMVo);
        return fbtxApxMVo;
    }

    @Override
    public List<FbtxApxMVo> listByRefcodes(@NotNull List<FbtxApxMVo> fbtxApxMVoList) {
        if (CollectionUtils.isNotEmpty(fbtxApxMVoList)) {
            for (FbtxApxMVo fbtxApxMVo : fbtxApxMVoList) {
                // 读取附件文件字节流
                this.readApxFileBytes(fbtxApxMVo);
            }
        }
        return fbtxApxMVoList;
    }

    /**
     * 读取附件文件字节流.
     * 
     * @param fbtxApxMVo
     * @author oscar.yu
     * @date 2019/9/9 15:23
     */
    private void readApxFileBytes(FbtxApxMVo fbtxApxMVo) {
        String rootPath = smeConfiguration.getFileRepositoryPath();
        String storePath = parseStorePath(fbtxApxMVo.getFilePath());
        String storeName = fbtxApxMVo.getStoreName();
        String filename = rootPath + storePath + storeName;
        byte[] data = null;
        // 文件解密处理
        if (GlobalConstants.Flag.TRUE.equals(fbtxApxMVo.getEncryptFlag())) {
            data = FileUtils.decryptFile(filename);
        } else {
            // 默认无需解密处理，兼容历史文件
            final byte[] fileBytes = FileUtils.readAllBytes(filename);
            data = null != fileBytes ? fileBytes.clone() : null;
        }
        fbtxApxMVo.setData(data);
    }
}
