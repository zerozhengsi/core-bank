package com.vteam.cars.plugin.file.upload;

import com.alibaba.fastjson.JSONObject;
import com.vteam.cars.common.RequestStore;
import com.vteam.cars.config.SmeConfiguration;
import com.vteam.cars.constant.FieldConstant;
import com.vteam.cars.constant.GlobalConstants;
//import com.vteam.cars.entity.model.FbpaOrgM;
import com.vteam.cars.entity.model.FbtxApxM;
import com.vteam.cars.entity.vo.FbtxApxMVo;
import com.vteam.cars.entity.vo.SipaBurMVo;
import com.vteam.cars.plugin.file.upload.enums.FileBKeyEnum;
import com.vteam.cars.plugin.file.upload.enums.StorageMode;
import com.vteam.cars.plugin.file.upload.handler.LocalFileHandler;
import com.vteam.cars.plugin.file.upload.handler.RAMFileHandler;
import com.vteam.cars.plugin.file.upload.handler.RemoteFileHandler;
import com.vteam.cars.plugin.file.upload.model.FileUploadModel;
//import com.vteam.cars.service.fbpa.FbpaOrgMService;
import com.vteam.cars.service.fbtx.FbtxApxMService;
import com.vteam.cars.util.FileUtils;
import com.vteam.cars.util.PdfUtils;
import com.vteam.cars.util.SmeAssert;
import com.vteam.cars.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文件管理类 .<br>
 *
 * @author andy.sher
 * @date 2018/11/15 13:17
 */
@Slf4j
@Service
public class FileManager {

    @Resource(type = SmeConfiguration.class)
    private SmeConfiguration smeConfiguration;

    @Resource(type = LocalFileHandler.class)
    private LocalFileHandler localFileHandler;

    @Resource(type = RemoteFileHandler.class)
    private RemoteFileHandler remoteFileHandler;

    @Resource(type = RAMFileHandler.class)
    private RAMFileHandler ramFileHandler;

    @Resource(type = FbtxApxMService.class)
    private FbtxApxMService fbtxApxMService;

//    @Resource(type = FbpaOrgMService.class)
//    private FbpaOrgMService fbpaOrgMService;

    /**
     * 压缩文件格式
     */
    public static final String COMPRESS_FORMAT = GlobalConstants.Symbol.SPOT + GlobalConstants.FileFormat.ZIP;

    /**
     * 文件上传 .
     * <p>
     * 必须确保以下两个属性不能为空
     * </p>
     * <ol>
     * <li>fileName</li>
     * <li>multipartFile || file || data || inputStream</li>
     * </ol>
     *
     * @param fileUploadModel 文件上传模型
     * @return com.vteam.sme.entity.vo.FbtxApxMVo 附件信息
     * @author andy.sher
     * @date 2018/11/15 13:55
     */
    public FbtxApxMVo upload(@NotNull FileUploadModel fileUploadModel) {
        String filePath = null;
        final String fileName = fileUploadModel.getFileName();
        // 将文件存入临时目录
        if (null != fileUploadModel.getMultipartFile()) {
            filePath = FileUtils.saveTmpFile(fileName, fileUploadModel.getMultipartFile());
        } else if (null != fileUploadModel.getFile()) {
            // 直接传入文件的不用建立临时文件
            // filePath = FileUtils.saveTmpFile(fileUploadModel.getFileName(), fileUploadModel.getFile());
            filePath = fileUploadModel.getFile().getPath();
        } else if (null != fileUploadModel.getData()) {
            byte[] data = fileUploadModel.getData().clone();
            filePath = FileUtils.saveTmpFile(fileName, data);
        } else if (null != fileUploadModel.getInputStream()) {
            filePath = FileUtils.saveTmpFile(fileName, fileUploadModel.getInputStream());
        } else if (StringUtils.isNotBlank(fileUploadModel.getRemoteUrl())) {
            filePath = FileUtils.saveTmpFile(fileName, fileUploadModel.getRemoteUrl());
        }

        SmeAssert.notBlank(filePath, "文件缓存失败。");
        FbtxApxMVo fbtxApxMVo = null;
        // 持久化模式
        final String fileUuid = fileUploadModel.getUuid();
        final String resourceUuid = fileUploadModel.getResourceUuid();
        if (StorageMode.TX.equals(fileUploadModel.getStorageMode())) {
            // 获取文档中心业务主键（存储路径）
            String bkey = fileUploadModel.getBkeyValue();
            if (StringUtils.isBlank(bkey)) {
                if (StringUtils.isBlank(fileUploadModel.getCode())) {
                    fileUploadModel.setCode(FileBKeyEnum.DEFAULT.getCode());
                }
                bkey = this.initBKey(fileUploadModel);
            }
            // 文件中心
            if (GlobalConstants.Flag.FILE_ACTIVE_REMOTE.equals(smeConfiguration.getFileActive())) {
                fbtxApxMVo = remoteFileHandler.save(fileName, fileUploadModel.getAuthor(), fileName, bkey, filePath, fileUuid, resourceUuid);
            }
            // 本地仓库
            else if (GlobalConstants.Flag.FILE_ACTIVE_LOCAL.equals(smeConfiguration.getFileActive())) {
                fbtxApxMVo = localFileHandler.save(fileName, fileUploadModel.getAuthor(), fileName, bkey, filePath, fileUuid, resourceUuid);
            }
            SmeAssert.notNull(fbtxApxMVo, "文件上传失败。");
            fbtxApxMService.save(fbtxApxMVo);
        }
        // 内存模式
        else if (StorageMode.RAM.equals(fileUploadModel.getStorageMode())) {
            // 将文件映射存放到内存中
            fbtxApxMVo = ramFileHandler.save(fileUuid, fileUuid, fileUuid, fileUuid, filePath, fileUuid);
        }

        return fbtxApxMVo;
    }

    /**
     * 保存第三方平台文件信息(仅信息，附件异步下载).
     *
     * @param fileUploadModel
     * @param resourceUuid
     * @param tppRefcode
     * @return com.vteam.sme.entity.vo.FbtxApxMVo 附件信息
     * @author oscar.yu
     * @date 2020/3/9 17:14
     */
    public FbtxApxMVo saveTppApx(@NotNull FileUploadModel fileUploadModel, String resourceUuid, Integer tppRefcode) {
        final String fileName = fileUploadModel.getFileName();
        // 第三方平台的附件通过接口异步下载
        FbtxApxMVo fbtxApxMVo = null;
        final String fileUuid = fileUploadModel.getUuid();
        // 获取文档中心业务主键（存储路径）
        String bkey = fileUploadModel.getBkeyValue();
        if (StringUtils.isBlank(bkey)) {
            if (StringUtils.isBlank(fileUploadModel.getCode())) {
                fileUploadModel.setCode(FileBKeyEnum.DEFAULT.getCode());
            }
            bkey = this.initBKey(fileUploadModel);
        }
        // 文件中心
        if (GlobalConstants.Flag.FILE_ACTIVE_REMOTE.equals(smeConfiguration.getFileActive())) {
            fbtxApxMVo = remoteFileHandler.saveTppApx(fileName, bkey, fileUuid, resourceUuid, tppRefcode);
        }
        // 本地仓库
        else if (GlobalConstants.Flag.FILE_ACTIVE_LOCAL.equals(smeConfiguration.getFileActive())) {
            fbtxApxMVo = localFileHandler.saveTppApx(fileName, bkey, fileUuid, resourceUuid, tppRefcode);
        }
        SmeAssert.notNull(fbtxApxMVo, "文件上传失败。");
        fbtxApxMService.save(fbtxApxMVo);
        return fbtxApxMVo;
    }

    /**
     * 写入第三方平台文件信息(文件及下载状态).
     * 
     * @param dataByte
     * @param fbtxApxM
     * @param fileName
     * @param fileType
     * @return
     * @author oscar.yu
     * @date 2020/3/15 09:27
     */
    public FbtxApxM uploadTppApx(byte[] dataByte, FbtxApxM fbtxApxM, String fileName, String fileType) {
        // 将文件存入临时目录
        byte[] data = dataByte.clone();
        if (StringUtils.isNotBlank(fileName)) {
            fbtxApxM.setFileName(fileName);
        }
        if (StringUtils.isNotBlank(fileType)) {
            fbtxApxM.setFileType(fileType);
        }
        final String fileSource = fbtxApxM.getFileSource();
        // 文件中心
        if (FieldConstant.FbtxApx.FILESOURCE_FC.equals(fileSource)) {
            fbtxApxM = remoteFileHandler.uploadTppApx(data, fbtxApxM);
        }
        // 本地仓库
        else if (FieldConstant.FbtxApx.FILESOURCE_LOCAL.equals(fileSource)) {
            fbtxApxM = localFileHandler.uploadTppApx(data, fbtxApxM);
        }
        fbtxApxMService.updateById(fbtxApxM);
        return fbtxApxM;
    }

    /**
     * 文件下载 .
     *
     * @param fbtxApxMVo 附件信息
     * @return java.io.File 文件
     * @author andy.sher
     * @date 2018/11/15 13:57
     */
    public File download(@NotNull final FbtxApxMVo fbtxApxMVo, @NotNull StorageMode storageMode) {
        switch (storageMode) {
            // 持久化文件
            case TX:
                // 文件中心
                if (FieldConstant.FbtxApx.FILESOURCE_FC.equals(fbtxApxMVo.getFileSource())) {
                    remoteFileHandler.get(fbtxApxMVo);
                }
                // 本地仓库
                else if (FieldConstant.FbtxApx.FILESOURCE_LOCAL.equals(fbtxApxMVo.getFileSource())) {
                    localFileHandler.get(fbtxApxMVo);
                }
                break;
            // 内存文件
            case RAM:
                ramFileHandler.get(fbtxApxMVo);
                break;
            default:
                break;
        }
        // 将文件存入到临时目录
        String filePath = null;
        if (null != fbtxApxMVo.getData()) {
            SmeAssert.notNull(fbtxApxMVo.getData(), "文件不存在。");
            filePath = FileUtils.saveTmpFile(fbtxApxMVo.getFileName(), fbtxApxMVo.getData());
        }

        SmeAssert.notBlank(filePath, "文件下载失败。");

        return new File(filePath);
    }

    /**
     * 文件下载 .
     * <p>默认为持久化下载模式</p>
     *
     * @param fbtxApxMVo 附件信息
     * @return java.io.File 文件
     * @author andy.sher
     * @date 2018/11/15 13:57
     */
    public File download(@NotNull FbtxApxMVo fbtxApxMVo) {
        return download(fbtxApxMVo, StorageMode.TX);
    }

    /**
     * 根据fileUuid查找多个文件，存放到临时目录
     * @param fileUuid
     * @return
     */
    public List<File> getFilesByFileUuid(String fileUuid){
        List<FbtxApxMVo> fbtxApxMVoList = fbtxApxMService.listContractByUUID(fileUuid);
        List<File> files=new ArrayList<>();
        for(FbtxApxMVo fbtxApxMVo:fbtxApxMVoList){
            files.add(this.download(fbtxApxMVo));
        }
        return files;
    }

    /**
     * 根据fileUuid查找多个文件，设置密码，存放到临时目录
     * @param fileUuid
     * @param encryptionFile 设置密码
     * @return
     */
    public List<File> getFilesByFileUuidEncryption(String fileUuid,String encryptionFile){
        List<FbtxApxMVo> fbtxApxMVoList = fbtxApxMService.listContractByUUID(fileUuid);
        List<File> files=new ArrayList<>();
        for(FbtxApxMVo fbtxApxMVo:fbtxApxMVoList){
            File file = this.download(fbtxApxMVo);
            // 文件加密处理
            if("pdf".equals(fbtxApxMVo.getFileType()) && StringUtils.isNotBlank(encryptionFile)){
                String source = file.getAbsolutePath();
                log.info("对文件"+source+"设置密码："+encryptionFile);
                String protectTarget = source.replace(".pdf","p.pdf");
                PdfUtils.protectPdf(source,protectTarget,encryptionFile);
                files.add(new File(protectTarget));
            }else{
                files.add(file);
            }

        }
        return files;
    }

    /**
     * 压缩包下载 .
     *
     * @param fbtxApxMVoList 附件信息列表
     * @param fileName 压缩后文件名称(无需带后缀名)
     * @return java.io.File 压缩后的文件
     * @author andy.sher
     * @date 2018/11/15 13:57
     */
    public File downloadComporess(@NotNull List<FbtxApxMVo> fbtxApxMVoList, String fileName) {
        // 压缩文件保存路径
        String comporessPath = null;
        List<FbtxApxMVo> tempList = new ArrayList<>();
        // 修复处理逻辑，附件可能存在于不同的服务器
        if (CollectionUtils.isNotEmpty(fbtxApxMVoList)) {
            for (FbtxApxMVo fbtxApxMVo : fbtxApxMVoList) {
                // 文件中心
                if (FieldConstant.FbtxApx.FILESOURCE_FC.equals(fbtxApxMVo.getFileSource())) {
                    remoteFileHandler.get(fbtxApxMVo);
                }
                // 本地仓库
                else if (FieldConstant.FbtxApx.FILESOURCE_LOCAL.equals(fbtxApxMVo.getFileSource())) {
                    localFileHandler.get(fbtxApxMVo);
                }
                tempList.add(fbtxApxMVo);
            }
        }
        if (CollectionUtils.isNotEmpty(tempList)) {
            String filePath;
            List<File> fileList = new ArrayList<>(tempList.size());
            // 附件名称集合
            Set<String> set = new HashSet<>();
            for (FbtxApxMVo temp : tempList) {
                if (!set.add(temp.getFileName())) {
                    // 获取附件的新名称
                    temp.setFileName(this.getNewFileName(temp.getFileName(), set));
                }
                SmeAssert.notNull(temp.getData(), temp.getFileName() + "文件不存在");
                filePath = FileUtils.saveTmpFile(temp.getFileName(), temp.getData());
                fileList.add(new File(filePath));

            }

            if (StringUtils.isBlank(fileName)) {
                // 生成压缩名
                fileName = StringUtils.generateSequenceNo();
            }
            fileName = fileName + COMPRESS_FORMAT;
            comporessPath = smeConfiguration.getTmpPath() + UUID.randomUUID().toString() + StringUtils.getSysSeparator() + fileName;
            FileUtils.doZip(fileList, comporessPath, false);
        }

        SmeAssert.notBlank(comporessPath, "文件下载失败。");

        return new File(comporessPath);
    }

    /**
     * 获取附件的新名称(ZIP压缩相同名称的附件)
     *
     * @param fileName
     * @param set
     * @return java.lang.String
     * @author jiangming.huang
     * @date 2018/12/25 0025 下午 6:02
     */
    private String getNewFileName(String fileName, Set<String> set) {
        String newFileName = StringUtils.EMPTY;
        for (int i = 1; i <= set.size(); i++) {
            StringBuilder fileNameBuilder = new StringBuilder();
            String fName = fileName.substring(0, fileName.lastIndexOf(GlobalConstants.Symbol.SPOT));
            String fileType = fileName.substring(fileName.lastIndexOf(GlobalConstants.Symbol.SPOT));
            fileNameBuilder.append(fName).append("(").append(i).append(")").append(fileType);
            if (set.add(fileNameBuilder.toString())) {
                newFileName = fileNameBuilder.toString();
                break;
            }
        }
        if (StringUtils.isBlank(newFileName)) {
            return fileName;
        } else {
            return newFileName;
        }
    }

    /**
     * 初始化BKEY .
     *
     * @param fileUploadModel 文件上传模型
     * @return java.lang.String bkey
     * @author andy.sher
     * @date 2018/12/17 14:37
     */
    private String initBKey(@NotNull FileUploadModel fileUploadModel) {
        // 拼接根节点名称
        String prefix = StringUtils.EMPTY;
        String active = smeConfiguration.getActive();
        switch (active) {
            case GlobalConstants.Flag.DEV:
                prefix =  "/开发环境/";
                break;
            case GlobalConstants.Flag.TEST:
                prefix =  "/测试环境/";
                break;
            case GlobalConstants.Flag.PREV:
                prefix =  "/演示环境/";
                break;
            case GlobalConstants.Flag.PROD:
                prefix =  "/生产环境/";
                break;
            default:
                prefix =  "/自定义环境/";
                break;
        }
        // 拼接上品牌名称
        String bkey = prefix + FileBKeyEnum.get(fileUploadModel.getCode()).getBkey();
        // 如果存在模型则进行模型填充
        final JSONObject dataModel = fileUploadModel.getModel();
        if (null != dataModel && !dataModel.isEmpty()) {
            Iterator<Map.Entry<String, Object>> model = dataModel.entrySet().iterator();
            Map.Entry<String, Object> entry;
            Pattern pattern;
            Matcher matcher;
            while (model.hasNext()) {
                entry = model.next();
                pattern = Pattern.compile(String.format("\\$\\{%s\\}", entry.getKey()));
                matcher = pattern.matcher(bkey);
                if (matcher.find()) {
                    bkey = matcher.replaceAll(null != entry.getValue() ? entry.getValue().toString() : StringUtils.EMPTY);
                } else {
                    log.error(String.format("填充BKey模型时出错！[%s]中没有找到[%s]", bkey, entry.getKey()));
                }
            }
        }
        return bkey;
    }

    /**
     * 设置默认的文档中心业务主键（存储路径）参数信息<br>
     * 目前主要为企业名称
     * 
     * @param model
     * @param bkeyValue
     * @return JSONObject 模型填充参数对象
     * @author oscar.yu
     * @date 2020/4/27 15:28
     */
    public JSONObject putDefaultFileDataModel(JSONObject model, String bkeyValue) {
        final SipaBurMVo loginUser = RequestStore.getLoginUser();
        if (null == loginUser || StringUtils.isBlank(bkeyValue)) {
            return null;
        }
        final String currUserid = loginUser.getUserid();
        final Integer orgRefcode = loginUser.getOrgRefcode();
        if (null == model && (null != currUserid && null != orgRefcode) && bkeyValue.contains("{orgname}")) {
            // 默认设置
            model = new JSONObject();
//            FbpaOrgM fbpaOrgM = fbpaOrgMService.getOrgBaseInfoByOrgRefcode(orgRefcode);
//            model.put("orgname", fbpaOrgM.getOrgname());
        }
        return model;
    }

}
