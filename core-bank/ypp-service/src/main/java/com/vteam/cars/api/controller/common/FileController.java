package com.vteam.cars.api.controller.common;

import com.alibaba.fastjson.JSONObject;
import com.vteam.cars.common.RequestStore;
import com.vteam.cars.config.SmeConfiguration;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.constant.MeasureConstants;
import com.vteam.cars.entity.model.FbtxApxM;
import com.vteam.cars.entity.vo.FbtxApxMVo;
import com.vteam.cars.exception.MSSmeBusinessException;
import com.vteam.cars.plugin.file.upload.FileManager;
import com.vteam.cars.plugin.file.upload.enums.FileBKeyEnum;
import com.vteam.cars.plugin.file.upload.enums.StorageMode;
import com.vteam.cars.plugin.file.upload.model.FileUploadModel;
import com.vteam.cars.util.LocalFileSystemStorage;
import com.vteam.cars.service.fbtx.FbtxApxMService;
import com.vteam.cars.util.*;
import com.vteam.vtarm.api.ReqEntity;
import com.vteam.vtarm.api.RespEntity;
import com.vteam.vtarm.cache.StringValueContainer;
import com.vteam.vtarm.security.InternalService;
import com.vteam.vtarm.utils.AesUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 文件上传控制器
 *
 * @author zack.yin
 * @date 2018/7/25 18:53
 */
@RestController
@Slf4j
@RequestMapping("/file")
public class FileController {

    @Resource(type = FbtxApxMService.class)
    private FbtxApxMService fbtxApxMService;

//    @Resource(type = FbpaOrgMService.class)
//    private FbpaOrgMService fbpaOrgMService;

    @Resource(type = SmeConfiguration.class)
    private SmeConfiguration smeConfiguration;

    @Resource(type = StringValueContainer.class)
    private StringValueContainer stringValueContainer;

    @Resource(type = FileManager.class)
    private FileManager fileManager;

    private final String REFCODES = "refcodes";

    private final String FILE_TYPE = "fileType";

    @Resource(type = LocalFileSystemStorage.class)
    private LocalFileSystemStorage lfsStorage;

    /**
     * 临时文件上传 .
     *
     * @param request
     * @return com.vteam.sme.api.entity.RespEntity
     * @author andy.sher
     * @date 2019/3/14 9:48
     */
    @PostMapping(value = "/doUploadTmpFile")
    public RespEntity doUploadTmpFile(HttpServletRequest request) {
        MultiValueMap<String, MultipartFile> multipartFileMultiValueMap = ((MultipartHttpServletRequest) request).getMultiFileMap();
        MultipartFile file = multipartFileMultiValueMap.getFirst(GlobalConstants.Flag.FILE);
        String fileHash = request.getParameter(GlobalConstants.Flag.HASH);

        SmeAssert.isFalse(multipartFileMultiValueMap.isEmpty(), "文件资源不能为空。");

        // 文件防篡改
        this.checkFile(fileHash, file);

        // 检查文件名称
        this.doCheckFileName(file.getOriginalFilename());

        // 构建文件上传模型
        FileUploadModel fileUploadModel = FileUploadModel.createFileUploadModel(file.getOriginalFilename(), file);
        // 上传文件并获取附件信息
        FbtxApxMVo fbtxApxMVo = fileManager.upload(fileUploadModel);
        SmeAssert.notNull(fbtxApxMVo, "文件上传失败。");

        JSONObject data = new JSONObject();
        data.put("uuid", fbtxApxMVo.getFileUuid());

        return RespEntity.ok(data);
    }

    // 支持大文件及多文件上传、增加Ukey校验的功能改造需求：7450610  modify by jun.wang begin
    /**
     * 获取文件随机UUID .
     *
     * @param
     * @return com.vteam.sme.api.entity.RespEntity 响应对象
     * @author jun.wang
     * @date 2022/7/8 9:01
     */
    @GetMapping("/getDefaultUuid")
    public RespEntity getDefaultUuid(){
        String uuid = UUID.randomUUID().toString();
        JSONObject data = new JSONObject();
        data.put("uuid",uuid);
        return RespEntity.ok(data);
    }
    // 支持大文件及多文件上传、增加Ukey校验的功能改造需求：7450610  modify by jun.wang end

    // 支持大文件及多文件上传、增加Ukey校验的功能改造需求：7450610  modify by jun.wang begin
    /**
     * 获取随机UUID做存储临时文件的文件夹名 .
     *
     * @param
     * @return com.vteam.sme.api.entity.RespEntity 响应对象
     * @author jun.wang
     * @date 2022/7/8 9:01
     */
    @GetMapping("/getFolderName")
    public RespEntity getFolderName(){
        String folderName = UUID.randomUUID().toString();
        JSONObject data = new JSONObject();
        data.put("folderName",folderName);
        return RespEntity.ok(data);
    }
    // 支持大文件及多文件上传、增加Ukey校验的功能改造需求：7450610  modify by jun.wang end

    /**
     * 文件上传 .<br>
     *
     * @param
     * @return com.vteam.sme.api.entity.RespEntity 响应对象
     * @author andy.sher
     * @date 2018/8/3 13:00
     */
    // 支持大文件及多文件上传、增加Ukey校验的功能改造需求：7450610  modify by jun.wang begin
    @PostMapping(value = "/doUploadFile", consumes = "multipart/form-data")
    public RespEntity doUploadFile(HttpServletRequest request,
                                   @RequestParam("file") MultipartFile file,
                                   @RequestParam(value = "chunk", required = false, defaultValue = "-1") int chunk,
                                   @RequestParam(value = "chunkSize", required = false) long[] chunkSize) {
        String uuid = request.getParameter(GlobalConstants.Flag.UUID);
        // 由于后端的保存临时文件需要用uuid做存放文件的文件夹，防止出现不同用户上传同一个文件名的文件时直接存放在临时目录下，而出现文件上传冲突等其他情况，所以必须保证folderName不为空。
        String folderName = request.getParameter("folderName");
        if(StringUtils.isEmpty(folderName)){
            folderName = UUID.randomUUID().toString();
        }
        String bkeyCode = request.getParameter(GlobalConstants.Flag.BKEY_CODE);
        String modelStr = request.getParameter(GlobalConstants.Flag.MODEL);
        String watermark = request.getParameter(GlobalConstants.Flag.WATERMARK);
        // 上传的文件名  带后缀
        String fileName = request.getParameter(GlobalConstants.Flag.FILE_NAME);
        String fileHash = request.getParameter(GlobalConstants.Flag.HASH);

        SmeAssert.isFalse(file.isEmpty(), "文件资源不能为空。");

        // 文件防篡改
        this.checkFile(fileHash, file);

        if (StringUtils.isBlank(bkeyCode)) {
            bkeyCode = FileBKeyEnum.DEFAULT.getCode();
        }
        SmeAssert.notNull(FileBKeyEnum.get(bkeyCode), "文件BKey不存在");

        // bkey占位符填充数据
        final String bkeyValue = FileBKeyEnum.get(bkeyCode).getBkey();
        JSONObject model = JSONObject.parseObject(modelStr);
        // 设置默认的企业名称
        model = fileManager.putDefaultFileDataModel(model, bkeyValue);
        // 检查文件名称
        this.doCheckFileName(file.getOriginalFilename());

        // 上传的文件名
        if (StringUtils.isBlank(fileName)) {
            fileName = file.getOriginalFilename();
        }
        // 接收分块文件并进行合并，并保存到临时文件中
        lfsStorage.saveChunk(file, chunk, chunkSize, folderName);
        // 检查是否合并为完整文件
        if (lfsStorage.getIsMergeSuccess()) {
            //获取临时文件，完整的文件
            File realFile = new File(smeConfiguration.getTmpPath() + folderName + StringUtils.getSysSeparator() + file.getOriginalFilename());
        // 构建文件上传模型
        FileUploadModel fileUploadModel = null;
        final String currUserid = RequestStore.getLoginUser().getUserid();
        // 添加水印
        if (StringUtils.isNotBlank(watermark) && GlobalConstants.Flag.YES.equals(watermark)) {
                //获取临时文件地址
                String filePath = realFile.getPath();
                String descFilePath = smeConfiguration.getTmpPath() + UUID.randomUUID().toString()+ file.getOriginalFilename();
            // 获取文件名
            String realFileName = file.getOriginalFilename();
            String suffix = StringUtils.EMPTY;
            int suffixIndex = realFileName.lastIndexOf(GlobalConstants.Symbol.SPOT);
            if (suffixIndex > 0) {
                suffix = realFileName.substring((suffixIndex + 1));
            }
            if (GlobalConstants.FileFormat.TIF.equalsIgnoreCase(suffix)
                    || GlobalConstants.FileFormat.JPEG.equalsIgnoreCase(suffix)
                    || GlobalConstants.FileFormat.GIF.equalsIgnoreCase(suffix)
                    || GlobalConstants.FileFormat.BMP.equalsIgnoreCase(suffix)
                    || GlobalConstants.FileFormat.JPG.equalsIgnoreCase(suffix)) {
                boolean waterFlag = ImageIO.waterMark(filePath, descFilePath);
                if (!waterFlag) {
                    descFilePath = filePath;
                }
            } else if (GlobalConstants.FileFormat.PDF.equalsIgnoreCase(suffix)) {
//                PdfUtils.waterMark(filePath, descFilePath, null);
            }
            File watermarkPathFile = new File(descFilePath);
            fileUploadModel = FileUploadModel.createFileUploadModel(fileName, currUserid, bkeyCode, model, uuid, watermarkPathFile);
        } else {
            // 构建文件上传模型
                fileUploadModel = FileUploadModel.createFileUploadModel(fileName, currUserid, bkeyCode, model, uuid, realFile);
        }

        // 上传文件并获取附件信息
        FbtxApxMVo fbtxApxMVo = fileManager.upload(fileUploadModel);
        SmeAssert.notNull(fbtxApxMVo, "文件上传失败。");

        JSONObject data = new JSONObject();
        data.put("refcode", fbtxApxMVo.getRefcode());
        data.put("uuid", fbtxApxMVo.getFileUuid());
        data.put("resourceUuid", fbtxApxMVo.getResourceUuid());
        data.put("fileName", fbtxApxMVo.getFileName());

        return RespEntity.ok(data);
    }

        return RespEntity.ok();
    }
    // 支持大文件及多文件上传、增加Ukey校验的功能改造需求：7450610  modify by jun.wang end

    /**
     * 内部文件上传 .<br>
     *
     * @param
     * @return com.vteam.sme.api.entity.RespEntity 响应对象
     * @author andy.sher
     * @date 2018/8/3 13:00
     */
    @InternalService(segment = "192.168.*.*")
    @PostMapping(value = "/doUploadInternalFile")
    public RespEntity doUploadInternalFile(HttpServletRequest request) {
        MultiValueMap<String, MultipartFile> multipartFileMap = ((MultipartHttpServletRequest) request).getMultiFileMap();
        MultipartFile file = multipartFileMap.getFirst(GlobalConstants.Flag.FILE);
        String uuid = request.getParameter(GlobalConstants.Flag.UUID);
        String bkeyCode = request.getParameter(GlobalConstants.Flag.BKEY_CODE);
        String modelStr = request.getParameter(GlobalConstants.Flag.MODEL);
        String watermark = request.getParameter(GlobalConstants.Flag.WATERMARK);
        // 上传的文件名  带后缀
        String fileName = request.getParameter(GlobalConstants.Flag.FILE_NAME);
        String fileHash = request.getParameter(GlobalConstants.Flag.HASH);

        SmeAssert.isFalse(multipartFileMap.isEmpty(), "文件资源不能为空。");

        // 文件防篡改
        this.checkFile(fileHash, file);

        JSONObject data;
        if (StringUtils.isBlank(bkeyCode)) {
            bkeyCode = FileBKeyEnum.DEFAULT.getCode();
        }

        // bkey占位符填充数据
        JSONObject model = JSONObject.parseObject(modelStr);
        FileUploadModel fileUploadModel = null;
        // 检查文件名称
        this.doCheckFileName(file.getOriginalFilename());

        // 上传的文件名
        if (StringUtils.isBlank(fileName)) {
            fileName = file.getOriginalFilename();
        }
        // 添加水印
        final String currUserid = RequestStore.getLoginUser().getUserid();
        if (StringUtils.isNotBlank(watermark) && GlobalConstants.Flag.YES.equals(watermark)) {
            String filePath = FileUtils.saveTmpFile(file.getOriginalFilename(), file);
            String descFilePath = smeConfiguration.getTmpPath() + UUID.randomUUID().toString() + file.getOriginalFilename();
            // 获取文件名
            String realFileName = file.getOriginalFilename();
            String suffix = StringUtils.EMPTY;
            int suffixIndex = realFileName.lastIndexOf(GlobalConstants.Symbol.SPOT);
            if (suffixIndex > 0) {
                suffix = realFileName.substring((suffixIndex + 1));
            }
            if (GlobalConstants.FileFormat.TIF.equalsIgnoreCase(suffix)
                    || GlobalConstants.FileFormat.JPEG.equalsIgnoreCase(suffix)
                    || GlobalConstants.FileFormat.GIF.equalsIgnoreCase(suffix)
                    || GlobalConstants.FileFormat.BMP.equalsIgnoreCase(suffix)
                    || GlobalConstants.FileFormat.JPG.equalsIgnoreCase(suffix)) {
                boolean waterFlag = ImageIO.waterMark(filePath, descFilePath);
                if (!waterFlag) {
                    descFilePath = filePath;
                }
            } else if (GlobalConstants.FileFormat.PDF.equalsIgnoreCase(suffix)) {
//                PdfUtils.waterMark(filePath, descFilePath, null);
            }
            File watermarkPathFile = new File(descFilePath);
            fileUploadModel = FileUploadModel.createFileUploadModel(fileName, currUserid, bkeyCode, model, uuid, watermarkPathFile);
        } else {
            // 构建文件上传模型
            fileUploadModel = FileUploadModel.createFileUploadModel(fileName, currUserid, bkeyCode, model, uuid, file);
        }

        // 上传文件并获取附件信息
        FbtxApxMVo fbtxApxMVo = fileManager.upload(fileUploadModel);
        SmeAssert.notNull(fbtxApxMVo, "文件上传失败。");

        data = new JSONObject();
        data.put("refcode", fbtxApxMVo.getRefcode());
        data.put("uuid", fbtxApxMVo.getFileUuid());
        data.put("resourceUuid", fbtxApxMVo.getResourceUuid());

        return RespEntity.ok(data);
    }

    /**
     * 上传远程文件 .<br>
     *
     * @param reqEntity 请求对象
     * @return com.vteam.sme.api.entity.RespEntity 响应对象
     * @author andy.sher
     * @date 2018/12/12 9:06
     */
    @PostMapping("/doUploadRemoteFile")
    public RespEntity doUploadRemoteFile(@RequestBody ReqEntity reqEntity) {
        JSONObject jsonObject = JSONObject.parseObject(reqEntity.getData());

        String url = jsonObject.getString(GlobalConstants.Flag.URL);
        String uuid = jsonObject.getString(GlobalConstants.Flag.UUID);
        String bkeyCode = jsonObject.getString(GlobalConstants.Flag.BKEY_CODE);
        String modelStr = jsonObject.getString(GlobalConstants.Flag.MODEL);

        SmeAssert.notBlank(url, "资源路径不能为空。");

        // 获取url参数部分
        String params = url.substring(url.indexOf(GlobalConstants.Symbol.QUESTION_EN) + 1);

        String sufix = null;
        // 获取文件后缀
        if (params.contains(GlobalConstants.FileFormat.DOC)) {
            sufix = GlobalConstants.Symbol.SPOT + GlobalConstants.FileFormat.DOC;
        } else if (params.contains(GlobalConstants.FileFormat.DOCX)) {
            sufix = GlobalConstants.Symbol.SPOT + GlobalConstants.FileFormat.DOCX;
        } else if (params.contains(GlobalConstants.FileFormat.PDF)) {
            sufix = GlobalConstants.Symbol.SPOT + GlobalConstants.FileFormat.PDF;
        } else if (params.contains(GlobalConstants.FileFormat.TIF)) {
            sufix = GlobalConstants.Symbol.SPOT + GlobalConstants.FileFormat.TIF;
        } else if (params.contains(GlobalConstants.FileFormat.WEBP)) {
            sufix = GlobalConstants.Symbol.SPOT + GlobalConstants.FileFormat.WEBP;
        } else if (params.contains(GlobalConstants.FileFormat.JPG)) {
            sufix = GlobalConstants.Symbol.SPOT + GlobalConstants.FileFormat.JPG;
        } else if (params.contains(GlobalConstants.FileFormat.JPEG)) {
            sufix = GlobalConstants.Symbol.SPOT + GlobalConstants.FileFormat.JPEG;
        } else if (params.contains(GlobalConstants.FileFormat.GIF)) {
            sufix = GlobalConstants.Symbol.SPOT + GlobalConstants.FileFormat.GIF;
        } else if (params.contains(GlobalConstants.FileFormat.PNG)) {
            sufix = GlobalConstants.Symbol.SPOT + GlobalConstants.FileFormat.PNG;
        } else if (params.contains(GlobalConstants.FileFormat.BMP)) {
            sufix = GlobalConstants.Symbol.SPOT + GlobalConstants.FileFormat.BMP;
        } else if (params.contains(GlobalConstants.FileFormat.XLS)) {
            sufix = GlobalConstants.Symbol.SPOT + GlobalConstants.FileFormat.XLS;
        } else if (params.contains(GlobalConstants.FileFormat.XLSX)) {
            sufix = GlobalConstants.Symbol.SPOT + GlobalConstants.FileFormat.XLSX;
        } else if (params.contains(GlobalConstants.FileFormat.PFX)) {
            sufix = GlobalConstants.Symbol.SPOT + GlobalConstants.FileFormat.PFX;
        } else {
            sufix = GlobalConstants.Symbol.SPOT + GlobalConstants.FileFormat.FILE;
        }

        // 生成文件名
        String fileName = UUID.randomUUID().toString() + sufix;

        // bkey占位符填充数据
        JSONObject model = JSONObject.parseObject(modelStr);

        if (StringUtils.isBlank(bkeyCode)) {
            bkeyCode = FileBKeyEnum.DEFAULT.getCode();
        }

        // 获取文件上传模型
        FileUploadModel fileUploadModel = FileUploadModel.createFileUploadModel(fileName,
                RequestStore.getLoginUser().getUserid(), bkeyCode, model, uuid, url);

        FbtxApxMVo fbtxApxMVo = fileManager.upload(fileUploadModel);

        return RespEntity.ok(JSONObject.toJSONString(fbtxApxMVo));
    }

    /**
     * 上传系统已有文件包 .
     *
     * @param reqEntity
     * @return com.vteam.sme.api.entity.RespEntity
     * @author zack.yin
     * @date 2019/1/23 14:47
     */
    @PostMapping("/doUploadFilePackage")
    public RespEntity doUploadFilePackage(@RequestBody ReqEntity reqEntity) {
        JSONObject jsonObject = JSONObject.parseObject(reqEntity.getData());

        String refcodes = jsonObject.getString(REFCODES);
        String fileType = jsonObject.getString(FILE_TYPE);
        String bkeyCode = jsonObject.getString(GlobalConstants.Flag.BKEY_CODE);
        String modelStr = jsonObject.getString(GlobalConstants.Flag.MODEL);
        String uuid = jsonObject.getString(GlobalConstants.Flag.UUID);
        String fileName = jsonObject.getString(GlobalConstants.Flag.FILE_NAME);
        String[] refcodeArr = refcodes.split(GlobalConstants.Symbol.COMMA);

        SmeAssert.notNull(refcodeArr, "附件流水号为空");
        SmeAssert.gt(refcodeArr.length, 0, "附件流水号为空");

        List<FbtxApxMVo> fbtxApxMVoList = null;
        // 附件
        if (GlobalConstants.Flag.FLAG_A.equals(fileType)) {
            FbtxApxMVo fbtxApxMVo = new FbtxApxMVo();
            fbtxApxMVo.setOrgApxRefcodes(refcodeArr);
            fbtxApxMVoList = fbtxApxMService.listFbpaApxMByCondition(fbtxApxMVo);
        }
        // 企业附件
//        else if (GlobalConstants.Flag.FLAG_B.equals(fileType)) {
//            FbtxApxMVo fbtxApxMVo = new FbtxApxMVo();
//            fbtxApxMVo.setOrgApxRefcodes(refcodeArr);
//            fbtxApxMVoList = fbtxApxMService.listEnclosureByCondition(fbtxApxMVo);
//        }
        SmeAssert.notEmpty(fbtxApxMVoList, "附件信息为空");

        File file = fileManager.downloadComporess(fbtxApxMVoList, fileName);

        // bkey占位符填充数据
        JSONObject model = JSONObject.parseObject(modelStr);

        if (StringUtils.isBlank(bkeyCode)) {
            bkeyCode = FileBKeyEnum.DEFAULT.getCode();
        }

        // 构建文件上传模型
        FileUploadModel fileUploadModel = FileUploadModel.createFileUploadModel(file.getName(),
                RequestStore.getLoginUser().getUserid(), bkeyCode, model, uuid, file);
        FbtxApxMVo fbtxApxMVo = fileManager.upload(fileUploadModel);

        return RespEntity.ok(JSONObject.toJSONString(fbtxApxMVo));
    }

    /**
     * 按文件资源唯一UUID下载文件
     *
     * @param resourceUuid 文件资源唯一UUID
     * @return 响应实体对象
     */
    @GetMapping(value = "/getFileByRefcode/{resourceUuid}")
    public void getFileByRefcode(@PathVariable(value = "resourceUuid") String resourceUuid, HttpServletRequest request,
            HttpServletResponse response) {
        String resourceUuidDecrypt = AesUtils.decrypt(Base64Utils.decode(resourceUuid), AesUtils.Mode.API);
        FbtxApxM fbtxApxM = fbtxApxMService.getByResourceUuid(resourceUuidDecrypt);
        SmeAssert.notNull(fbtxApxM, "文件资源不存在。");

        // 设置响应配置
        FileUtils.setResponse(fbtxApxM.getFileName(), fbtxApxM.getFileType(), request, response);

        // 设置附件信息条件
        FbtxApxMVo condition = new FbtxApxMVo();
        BeanUtils.copyProperties(fbtxApxM, condition);
        // 获取文件
        File file = fileManager.download(condition);
        SmeAssert.notNull(file, "文件资源不存在。");

        FileUtils.copy(file, response);
    }

    /**
     * 按文件资源唯一UUID下载文件
     *
     * @param resourceUuid 文件资源唯一UUID
     * @return 响应实体对象
     */
    @GetMapping(value = "/getFileByResourceUuid/{resourceUuid}")
    public void getFileByResourceUuid(@PathVariable(value = "resourceUuid") String resourceUuid, HttpServletRequest request,
            HttpServletResponse response) {
        String resourceUuidDecrypt = AesUtils.decrypt(Base64Utils.decode(resourceUuid), AesUtils.Mode.API);
        FbtxApxM fbtxApxM = fbtxApxMService.getByResourceUuid(resourceUuidDecrypt);
        SmeAssert.notNull(fbtxApxM, "文件资源不存在。");

        // 设置响应配置
        FileUtils.setResponse(fbtxApxM.getFileName(), fbtxApxM.getFileType(), request, response);

        // 设置附件信息条件
        FbtxApxMVo condition = new FbtxApxMVo();
        BeanUtils.copyProperties(fbtxApxM, condition);
        // 获取文件
        File file = fileManager.download(condition);
        SmeAssert.notNull(file, "文件资源不存在。");

        FileUtils.copy(file, response);
    }

    /**
     * 临时文件下载 .
     *
     * @param uuid
     * @param request
     * @param response
     * @return void
     * @author andy.sher
     * @date 2019/3/14 15:21
     */
    @GetMapping(value = "/getTmpFileByUuid/{uuid}")
    public void getTmpFileByUuid(@PathVariable(value = "uuid") String uuid, HttpServletRequest request,
            HttpServletResponse response) {
        FbtxApxMVo fbtxApxMVo = new FbtxApxMVo();
        fbtxApxMVo.setFileUuid(uuid);
        File file = fileManager.download(fbtxApxMVo, StorageMode.RAM);
        SmeAssert.notNull(file, "文件资源不存在。");

        FileUtils.setResponse(fbtxApxMVo.getFileName(), fbtxApxMVo.getFileType(), request, response);
        FileUtils.copy(file, response);
    }

    /**
     * 按UUID下载文件(兼容单个uuid以及多个uuid批量下载)
     *
     * @param uuid     文件流水号
     * @param showName 下载名称
     * @return 响应实体对象
     */
    @GetMapping(value = "/getFileByUuid/{uuid}/{showName}")
    public void getFileByUuid(@PathVariable(value = "uuid") String uuid,
            @PathVariable(value = "showName") String showName, HttpServletRequest request,
            HttpServletResponse response) {
        List<FbtxApxMVo> fbtxApxMVoList = fbtxApxMService.listContractByUUID(uuid);

        SmeAssert.notEmpty(fbtxApxMVoList, "文件资源不存在。");

        ((ArrayList<FbtxApxMVo>) fbtxApxMVoList).trimToSize();
        // 单个文件
        if (1 == fbtxApxMVoList.size()) {
            // 设置响应配置
            if (GlobalConstants.Symbol.MINUS.equals(showName)) {
                showName = fbtxApxMVoList.get(0).getFileName();
            } else {
                showName = showName + GlobalConstants.Symbol.SPOT + fbtxApxMVoList.get(0).getFileType();
            }
            FileUtils.setResponse(showName, fbtxApxMVoList.get(0).getFileType(), request, response);
            FileUtils.copy(fileManager.download(fbtxApxMVoList.get(0)), response);
        }
        // 多个文件
        else {
            // 实例化压缩包文件
            File zipFile = fileManager.downloadComporess(fbtxApxMVoList, null);
            // 设置响应配置
            if (GlobalConstants.Symbol.MINUS.equals(showName)) {
                showName = zipFile.getName();
            } else {
                showName = showName + GlobalConstants.Symbol.SPOT + GlobalConstants.FileFormat.ZIP;
            }
            FileUtils.setResponse(showName, GlobalConstants.FileFormat.ZIP, request, response);
            FileUtils.copy(zipFile, response);
        }
    }

    /**
     * 按文件名称获取导出文件 .
     *
     * @param filePath
     * @return com.vteam.sme.api.entity.RespEntity
     * @author zack.yin
     * @date 2018/8/21 13:05
     */
    @GetMapping(value = "/getExportFileByFileName/{filePath}/{fileName}")
    public void getExportFileByFileName(@PathVariable(value = "filePath") String filePath,
            @PathVariable(value = "fileName") String fileName, HttpServletRequest request,
            HttpServletResponse response) {
        SmeAssert.notNull(filePath, "文件资源不存在。");
        String showName = StringUtils.EMPTY;
        filePath = filePath.replaceAll(GlobalConstants.Symbol.UNDERLINE, GlobalConstants.Symbol.SLASH);
        String[] filePathSplit = filePath.split(GlobalConstants.Symbol.SLASH);
        String fileType = filePathSplit[1].substring(filePathSplit[1].lastIndexOf(GlobalConstants.Symbol.SPOT) + 1);
        if (GlobalConstants.Symbol.MINUS.equals(fileName)) {
            showName = filePathSplit[1];
        } else {
            showName = fileName + GlobalConstants.Symbol.SPOT + fileType;
        }
        // 设置Response参数
        FileUtils.setResponse(showName, fileType, request, response);
        File file = new File(smeConfiguration.getTmpPath() + filePath);
        FileUtils.copy(file, response);
    }

    /**
     * 下载资料包专用方法
     *
     * @param filePath
     * @return
     * @throws MSSmeBusinessException
     * @author zhuang.shao
     * @date 2018年9月21日 下午3:53:40
     */
    @GetMapping(value = "/getExportPackageByFileName/{filePath}")
    public void getExportPackageByFileName(@PathVariable(value = "filePath") String filePath,
            HttpServletRequest request, HttpServletResponse response) {
        try {
            filePath = URLDecoder.decode(filePath, smeConfiguration.getEncoding());
        } catch (UnsupportedEncodingException ignore) {
        }
        SmeAssert.notNull(filePath, "文件资源不存在。");

        filePath = filePath.replaceAll(GlobalConstants.Symbol.UNDERLINE, GlobalConstants.Symbol.SLASH);
        String[] filePathSplit = filePath.split(GlobalConstants.Symbol.SLASH);
        // 设置响应配置
        FileUtils.setResponse(filePathSplit[1], null, request, response);
        File file = new File(smeConfiguration.getTmpPath() + filePath);
        FileUtils.copy(file, response);
    }

    /**
     * 按UUID预览图片，返回base64
     *
     * @param uuid 文件流水号
     * @return 响应实体对象
     */
    @GetMapping(value = "/getPreviewFileByUuid/{uuid}")
    public RespEntity getPreviewFileByUuid(@PathVariable(value = "uuid") String uuid) {
        List<FbtxApxMVo> fbtxApxMVos = fbtxApxMService.listContractByUUID(uuid);
        String fileString = null;
        if (!CollectionUtils.isEmpty(fbtxApxMVos)) {
            // 获取文件
            File file = fileManager.download(fbtxApxMVos.get(0));
            fileString = Base64Utils.getImageStrFromFile(file);
        }
        JSONObject data = new JSONObject();
        data.put(GlobalConstants.FileFormat.FILE, fileString);
        return RespEntity.ok(data);
    }

    /**
     * 通过资源唯一UUID预览图片，返回base64 .
     *
     * @param resourceUuid
     * @return void
     * @author zack.yin
     * @date 2018/8/7 10:28
     */
    @GetMapping(value = "/getPreviewFileByApxId/{resourceUuid}")
    public RespEntity getPreviewFileByApxId(@PathVariable(value = "resourceUuid") String resourceUuid) {
        String resourceUuidDecrypt = AesUtils.decrypt(Base64Utils.decode(resourceUuid), AesUtils.Mode.API);
        FbtxApxM fbtxApxM = fbtxApxMService.getByResourceUuid(resourceUuidDecrypt);
        String fileString = null;
        if (fbtxApxM != null) {
            // 设置附件信息条件
            FbtxApxMVo condition = new FbtxApxMVo();
            BeanUtils.copyProperties(fbtxApxM, condition);
            // 获取文件
            File file = fileManager.download(condition);
            fileString = Base64Utils.getImageStrFromFile(file);
        }
        JSONObject data = new JSONObject();
        data.put(GlobalConstants.FileFormat.FILE, fileString);
        return RespEntity.ok(data);
    }

    /**
     * 通过文件资源唯一UUID预览返回流 .
     *
     * @param resourceUuid
     * @return void
     * @author zack.yin
     * @date 2018/11/16 16:15
     */
    @GetMapping(value = "/getPreviewFileInputStreamByApxId/{resourceUuid}")
    public void getPreviewFileInputStreamByApxId(@PathVariable(value = "resourceUuid") String resourceUuid,
            HttpServletRequest request, HttpServletResponse response) {
        FbtxApxM fbtxApxM = fbtxApxMService.getByResourceUuid(resourceUuid);
        if (fbtxApxM != null) {
            // 设置附件信息条件
            FbtxApxMVo condition = new FbtxApxMVo();
            BeanUtils.copyProperties(fbtxApxM, condition);
            // 获取原始文件
            File file = fileManager.download(condition);

            // 判断文件是否存在
            if (file.exists()) {
            	// 如果文件为JPG格式且图片超过10M才压缩，则压缩文件   (MeasureConstants.M * 10 ==> 10M)
                if (GlobalConstants.FileFormat.JPG.equals(condition.getFileType())
                		&& file.length() > MeasureConstants.M * 10) {
		            // 压缩文件
		            final String jpgSuffix = GlobalConstants.Symbol.SPOT + GlobalConstants.FileFormat.JPG;
		            String srcFilePath = smeConfiguration.getTmpPath() + UUID.randomUUID().toString() + jpgSuffix;
		            String descFilePath = smeConfiguration.getTmpPath() + UUID.randomUUID().toString() + jpgSuffix;
		            File compressPicFile = new File(srcFilePath);
		            try {
		                FileUtils.copyFile(file, compressPicFile);
		                ImageIO.compressPic(srcFilePath, descFilePath);
		            } catch (IOException e) {
		                log.error(FileController.class.getName(), e);
		            }
		            file = new File(descFilePath);
                }
            	
                byte[] data = null;
                try (FileInputStream input = new FileInputStream(file);) {
                    data = new byte[input.available()];
                    input.read(data);
                    // 调整为统一预览设置
                    FileUtils.setPreviewResponse(fbtxApxM.getFileName(), fbtxApxM.getFileType(), request, response);
                    response.getOutputStream().write(data);
                } catch (Exception e) {
                    log.error(FileController.class.getName(), e);
                }

            } else {
                return;
            }
        }

    }

    /**
     * 通过附件uuid预览返回流 .
     *
     * @param uuid
     * @return void
     * @author zack.yin
     * @date 2018/11/19 13:08
     */
    @GetMapping(value = "/getPreviewFileInputStreamByUuid/{uuid}")
    public void getPreviewFileInputStreamByUuid(@PathVariable(value = "uuid") String uuid,
            HttpServletRequest request, HttpServletResponse response) {
        List<FbtxApxMVo> fbtxApxMVoList = fbtxApxMService.listContractByUUID(uuid);

        SmeAssert.notEmpty(fbtxApxMVoList, "文件资源不存在。");
        if (CollectionUtils.isNotEmpty(fbtxApxMVoList)) {
            // 设置附件信息条件
            FbtxApxMVo condition = new FbtxApxMVo();
            FbtxApxM fbtxApxM = fbtxApxMVoList.get(0);
            BeanUtils.copyProperties(fbtxApxM, condition);
            // 获取文件
            File file = fileManager.download(condition);
            SmeAssert.notNull(file, "文件资源不存在。");

            // 如果文件为JPG格式，则压缩文件
            if (GlobalConstants.FileFormat.JPG.equals(condition.getFileType())) {
                // 压缩文件
                final String jpgSuffix = GlobalConstants.Symbol.SPOT + GlobalConstants.FileFormat.JPG;
                String srcFilePath = smeConfiguration.getTmpPath() + UUID.randomUUID().toString() + jpgSuffix;
                String descFilePath = smeConfiguration.getTmpPath() + UUID.randomUUID().toString() + jpgSuffix;
                File compressPicFile = new File(srcFilePath);
                try {
                    FileUtils.copyFile(file, compressPicFile);
                    ImageIO.compressPic(srcFilePath, descFilePath);
                } catch (IOException e) {
                    log.error(FileController.class.getName(), e);
                }
                file = new File(descFilePath);
            }

            if (file.exists()) {
                byte[] data = null;
                try (FileInputStream input = new FileInputStream(file);) {
                    data = new byte[input.available()];
                    input.read(data);
                    // 调整为统一预览设置
                    FileUtils.setPreviewResponse(fbtxApxM.getFileName(), fbtxApxM.getFileType(), request, response);
                    response.getOutputStream().write(data);
                } catch (Exception e) {
                    log.error("文件处理异常：" + e.getMessage());
                }

            } else {
                return;
            }
        }

    }

    /**
     * 通过uuid获取附件信息 .
     *
     * @param uuid
     * @return com.vteam.sme.api.entity.RespEntity
     * @author zack.yin
     * @date 2018/8/27 13:49
     */
    @GetMapping(value = "/getApxInfoByUuid/{uuid}")
    public RespEntity getApxInfoByUuid(@PathVariable(value = "uuid") String uuid) {
        List<FbtxApxMVo> fbtxApxMVoList = fbtxApxMService.listContractByUUID(uuid);
        JSONObject data = new JSONObject();
        data.put("apxList", fbtxApxMVoList);
        return RespEntity.ok(data);
    }

    /**
     * 通过文件资源唯一UUID获取附件信息 .
     *
     * @param resourceUuid
     * @return com.vteam.sme.api.entity.RespEntity
     * @author zack.yin
     * @date 2018/9/21 13:15
     */
    @GetMapping(value = "/getApxInfoByRefcode/{resourceUuid}")
    public RespEntity getApxInfoByRefcode(@PathVariable(value = "resourceUuid") String resourceUuid) {
        String resourceUuidDecrypt = AesUtils.decrypt(Base64Utils.decode(resourceUuid), AesUtils.Mode.API);
        FbtxApxM fbtxApxM = fbtxApxMService.getByResourceUuid(resourceUuidDecrypt);
        SmeAssert.notNull(fbtxApxM, "文件为空。");
        return RespEntity.ok(JSONObject.toJSONString(fbtxApxM));
    }

    /**
     * 通过附件资源唯一UUID删除附件 .
     *
     * @param reqEntity
     * @return com.vteam.sme.api.entity.RespEntity
     * @author zack.yin
     * @date 2018/8/29 19:20
     */
    @PostMapping(value = "/doRemoveFileByRefcode")
    public RespEntity doRemoveFileByRefcode(@RequestBody ReqEntity reqEntity) {
        FbtxApxMVo fbtxApxMVo = JSONObject.parseObject(reqEntity.getData(), FbtxApxMVo.class);
        Assert.notNull(fbtxApxMVo.getResourceUuid(), "附件不存在。");
        fbtxApxMService.removeFileByResourceUuid(fbtxApxMVo.getResourceUuid());
        return RespEntity.ok();
    }

    /**
     * 通过附件资源唯一UUID数组删除附件
     *
     * @param reqEntity
     * @return com.vteam.sme.api.entity.RespEntity
     * @author jiangming.huang
     * @date 2018/12/4 0004 上午 10:55
     */
    @PostMapping(value = "/doRemoveFileByRefcodes")
    public RespEntity doRemoveFileByRefcodes(@RequestBody ReqEntity reqEntity) {
        FbtxApxMVo fbtxApxMVo = JSONObject.parseObject(reqEntity.getData(), FbtxApxMVo.class);
        SmeAssert.notNull(fbtxApxMVo, "附件信息为空。");
        SmeAssert.notEmpty(fbtxApxMVo.getResourceUuids(), "附件不存在。");
        fbtxApxMService.removeFileByResourceUuids(fbtxApxMVo.getResourceUuids());
        return RespEntity.ok();
    }

    /**
     * 根据uuid判断文件是否生成
     *
     * @param uuid
     * @return com.vteam.sme.api.entity.RespEntity
     * @author fu.tong
     * @date 2018/10/10 16:32
     */
    @GetMapping(value = "getExistFileByFileUuid/{uuid}")
    public RespEntity getExistFileByFileUuid(@PathVariable("uuid") String uuid) {
        String[] fileUuid = uuid.split(GlobalConstants.Symbol.COMMA);
        JSONObject data = new JSONObject();
        boolean flag = true;
        for (int i = 0; i < fileUuid.length; i++) {
            List<FbtxApxMVo> fbtxApxMVoList = fbtxApxMService.listContractByUUID(fileUuid[i]);
            if (CollectionUtils.isEmpty(fbtxApxMVoList)) {
                flag = false;
                break;
            }
        }

        if (flag) {
            data.put("result", GlobalConstants.Flag.TRUE);
        } else {
            data.put("result", GlobalConstants.Flag.FALSE);
        }

        return RespEntity.ok(data);
    }

    /**
     * 根据流水号判断文件是否生成
     *
     * @param refcode
     * @return com.vteam.sme.api.entity.RespEntity
     * @author fu.tong
     * @date 2018/10/10 16:32
     */
    @GetMapping(value = "getExistFileByRefcode/{refcode}")
    public RespEntity getExistFileByRefcode(@PathVariable("refcode") Integer refcode) {
        FbtxApxM fbtxApxM = fbtxApxMService.getById(refcode);
        SmeAssert.notNull(fbtxApxM, "文件正在生成中。");
        JSONObject data = new JSONObject();
        data.put("result", null != fbtxApxM ? GlobalConstants.Flag.TRUE : GlobalConstants.Flag.FALSE);
        return RespEntity.ok(data);
    }

    /**
     * 根据路径判断文件是否生成
     *
     * @param filePath
     * @return com.vteam.sme.api.entity.RespEntity
     * @author fu.tong
     * @date 2018/10/10 16:32
     */
    @GetMapping(value = "getExistFileByFilePath/{filePath}")
    public RespEntity getExistFileByRefcode(@PathVariable("filePath") String filePath) {

        JSONObject data = new JSONObject();
        data.put("result", StringUtils.isEmpty(stringValueContainer.get(filePath)) ? GlobalConstants.Flag.TRUE
                : GlobalConstants.Flag.FALSE);
        return RespEntity.ok(data);
    }


    /**
     * 根据导入模板名称下载导入模板
     *
     * @param importTempName 导入文件模板名称
     * @param showName       下载名称
     * @return com.vteam.sme.api.entity.RespEntity
     * @author jiangming.huang
     * @date 2018/11/22 0022 下午 1:27
     */
    @GetMapping(value = "/getImportByImportTempName/{importTempName}/{showName}")
    public void getImportByImportTempName(@PathVariable("importTempName") String importTempName,
            @PathVariable("showName") String showName, HttpServletRequest request, HttpServletResponse response) {
        this.getFileByFilePathAndFileName(smeConfiguration.getImportPath(), importTempName, showName, request,
                response);
    }

    /**
     * 根据路径和名称下载resource下的文件
     *
     * @param filePath 文件目录
     * @param fileName 文件名称
     * @param showName 下载名称
     * @return void
     * @author jiangming.huang
     * @date 2018/11/22 0022 下午 1:25
     */
    private void getFileByFilePathAndFileName(String filePath, String fileName, String showName,
            HttpServletRequest request, HttpServletResponse response) {
        // 文件类型
        String fileType = fileName.substring(fileName.lastIndexOf(GlobalConstants.Symbol.SPOT) + 1);
        // 设置Response参数
        FileUtils.setResponse(showName, fileType, request, response);
        FileUtils.copy((filePath + fileName), response);
    }

    /**
     * 检查文件名称 .
     *
     * @param originalFilename 文件名称
     * @return void
     * @author andy.sher
     * @date 2019/2/26 11:03
     */
    private void doCheckFileName(String originalFilename) {
        String[] fileTypes = {GlobalConstants.FileFormat.TXT, GlobalConstants.FileFormat.HTML,
                GlobalConstants.FileFormat.PHP, GlobalConstants.FileFormat.JSP, GlobalConstants.FileFormat.EXE};
        for (String fileType : fileTypes) {
            SmeAssert.isFalse(originalFilename.contains((GlobalConstants.Symbol.SPOT + fileType)), "文件类型不合法，请重新上传。");
        }
        // add by chad.mei 20211130 增加文件名称校验
        SmeAssert.le(originalFilename.length(), 100, "文件名称过长，请重新上传。");
    }

    /**
     * 检查文件是否被篡改 .
     *
     * @param hash 文件Hash
     * @param file 文件
     * @return void
     * @author andy.sher
     * @date 2019/3/8 18:04
     */
    private void checkFile(@NotNull String hash, MultipartFile file) {
        if (null != file) {
            // 计算文件名hash
            String fileName = MD5Utils.encryptPwd(Base64Utils.encode(file.getOriginalFilename()));
            // 文件名和文件一起计算hash
            String realHash = MD5Utils.encryptPwd(fileName);

            SmeAssert.eq(realHash, hash, "文件已被篡改。");
        }
    }

}
