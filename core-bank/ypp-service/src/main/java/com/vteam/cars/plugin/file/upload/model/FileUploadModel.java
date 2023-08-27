package com.vteam.cars.plugin.file.upload.model;

import com.alibaba.fastjson.JSONObject;
import com.vteam.cars.plugin.file.upload.enums.StorageMode;
import com.vteam.cars.util.StringUtils;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import java.io.File;
import java.io.InputStream;
import java.util.UUID;

/**
 * 文件上传模型 .<br>
 *
 * @author andy.sher
 * @date 2018/11/15 13:31
 */
@Getter
@ToString(callSuper = true)
public class FileUploadModel {

    private MultipartFile multipartFile;

    private File file;

    private byte[] data;

    private InputStream inputStream;

    private String remoteUrl;

    private String fileName;

    private String author;

    private String code;

    private JSONObject model;

    private String bkeyValue;

    /**
     * 文件上传对象惟一编号，对应fileUuid
     */
    private String uuid;

    /**
     * 资源唯一编号，对应resourceUuid
     */
    private String resourceUuid;

    private StorageMode storageMode;

    private FileUploadModel() {
    }

    private FileUploadModel(String fileName, MultipartFile multipartFile) {
        this.fileName = fileName;
        this.multipartFile = multipartFile;
        this.uuid = defaultUuid();
        this.storageMode = StorageMode.RAM;
    }

    private FileUploadModel(String fileName, byte[] data) {
        this.fileName = fileName;
        this.data = data;
        this.uuid = defaultUuid();
        this.storageMode = StorageMode.RAM;
    }

    private FileUploadModel(String fileName, String author, String code, JSONObject model, String uuid,
            MultipartFile multipartFile) {
        this.fileName = fileName;
        this.author = author;
        this.code = code;
        this.model = model;
        this.uuid = StringUtils.isNotBlank(uuid) ? uuid : defaultUuid();
        this.multipartFile = multipartFile;
        this.storageMode = StorageMode.TX;
    }

    private FileUploadModel(String fileName, String author, String code, JSONObject model, String uuid, File file) {
        this.fileName = fileName;
        this.author = author;
        this.code = code;
        this.model = model;
        this.uuid = StringUtils.isNotBlank(uuid) ? uuid : defaultUuid();
        this.file = file;
        this.storageMode = StorageMode.TX;
    }

    private FileUploadModel(String fileName, String author, String code, JSONObject model, String uuid, String resourceUuid,
            File file) {
        this.fileName = fileName;
        this.author = author;
        this.code = code;
        this.model = model;
        this.uuid = StringUtils.isNotBlank(uuid) ? uuid : defaultUuid();
        this.resourceUuid = resourceUuid;
        this.file = file;
        this.storageMode = StorageMode.TX;
    }

    private FileUploadModel(String fileName, String author, String code, JSONObject model, String uuid, byte[] data) {
        this.fileName = fileName;
        this.author = author;
        this.code = code;
        this.model = model;
        this.uuid = StringUtils.isNotBlank(uuid) ? uuid : defaultUuid();
        this.data = data;
        this.storageMode = StorageMode.TX;
    }

    private FileUploadModel(String fileName, String author, String bkeyValue, String uuid, byte[] data) {
        this.fileName = fileName;
        this.author = author;
        this.bkeyValue = bkeyValue;
        this.uuid = StringUtils.isNotBlank(uuid) ? uuid : defaultUuid();
        this.data = data;
        this.storageMode = StorageMode.TX;
    }
    
    private FileUploadModel(String fileName, String author, String bkeyValue, String uuid, String resourceUuid, byte[] data) {
        this.fileName = fileName;
        this.author = author;
        this.bkeyValue = bkeyValue;
        this.uuid = StringUtils.isNotBlank(uuid) ? uuid : defaultUuid();
        this.resourceUuid = resourceUuid;
        this.data = data;
        this.storageMode = StorageMode.TX;
    }

    private FileUploadModel(String fileName, String author, String code, JSONObject model, String uuid, InputStream inputStream) {
        this.fileName = fileName;
        this.author = author;
        this.code = code;
        this.model = model;
        this.uuid = StringUtils.isNotBlank(uuid) ? uuid : defaultUuid();
        this.inputStream = inputStream;
        this.storageMode = StorageMode.TX;
    }

    private FileUploadModel(String fileName, String author, String code, JSONObject model, String uuid, String remoteUrl) {
        this.fileName = fileName;
        this.author = author;
        this.code = code;
        this.model = model;
        this.uuid = StringUtils.isNotBlank(uuid) ? uuid : defaultUuid();
        this.remoteUrl = remoteUrl;
        this.storageMode = StorageMode.TX;
    }

    /** 保存第三方平台附件信息，文件异步通过接口下载 */
    private FileUploadModel(String fileName, String code, JSONObject model, String uuid) {
        this.fileName = fileName;
        this.code = code;
        this.model = model;
        this.uuid = uuid;
        this.storageMode = StorageMode.TX;
    }

    /**
     * 创建文件上传数据模型 .
     * <p>
     * 存储模式默认为内存模式
     * </p>
     *
     * @param fileName 文件名
     * @param multipartFile 多媒体文件
     * @return com.vteam.sme.plugin.file.upload.model.FileUploadModel 文件上传数据模型
     * @author andy.sher
     * @date 2018/11/15 14:42
     */
    public static FileUploadModel createFileUploadModel(@NotNull String fileName, @NotNull MultipartFile multipartFile) {
        FileUploadModel fileUploadModel = new FileUploadModel(fileName, multipartFile);
        return fileUploadModel;
    }

    /**
     * 创建文件上传数据模型 .
     * <p>
     * 存储模式默认为内存模式
     * </p>
     *
     * @param fileName 文件名
     * @param data 文件字节数组
     * @return com.vteam.sme.plugin.file.upload.model.FileUploadModel 文件上传数据模型
     * @author jiangming.huang
     * @date 2019/3/19 10:25
     */
    public static FileUploadModel createFileUploadModel(@NotNull String fileName, @NotNull byte[] data) {
        FileUploadModel fileUploadModel = new FileUploadModel(fileName, data);
        return fileUploadModel;
    }

    /**
     * 创建文件上传数据模型 .
     * <p>
     * 存储模式默认为持久化
     * </p>
     *
     * @param fileName 文件名
     * @param author 作者
     * @param code bkey代号
     * @param multipartFile 多媒体文件
     * @param model 模型填充数据
     * @param uuid 文件唯一标识符
     * @return com.vteam.sme.plugin.file.upload.model.FileUploadModel 文件上传数据模型
     * @author andy.sher
     * @date 2018/11/15 14:42
     */
    public static FileUploadModel createFileUploadModel(@NotNull String fileName, @NotNull String author, @NotNull String code,
            JSONObject model, String uuid, @NotNull MultipartFile multipartFile) {
        FileUploadModel fileUploadModel = new FileUploadModel(fileName, author, code, model, uuid, multipartFile);
        return fileUploadModel;
    }

    /**
     * 创建文件上传数据模型 .
     * <p>
     * 存储模式默认为持久化
     * </p>
     *
     * @param fileName 文件名
     * @param author 作者
     * @param code bkey代号
     * @param file 文件
     * @param model 模型填充数据
     * @param uuid 文件唯一标识符
     * @return com.vteam.sme.plugin.file.upload.model.FileUploadModel 数据模型
     * @author andy.sher
     * @date 2018/11/15 14:42
     */
    public static FileUploadModel createFileUploadModel(@NotNull String fileName, @NotNull String author, @NotNull String code,
            JSONObject model, String uuid, @NotNull File file) {
        FileUploadModel fileUploadModel = new FileUploadModel(fileName, author, code, model, uuid, file);
        return fileUploadModel;
    }

    /**
     * 创建文件上传数据模型(可设置资源唯一标识符resourceUuid) .
     * <p>
     * 存储模式默认为持久化
     * </p>
     *
     * @param fileName 文件名
     * @param author 作者
     * @param code bkey代号
     * @param file 文件
     * @param model 模型填充数据
     * @param uuid 文件唯一标识符
     * @param resourceUuid 文件资源唯一标识符
     * @return com.vteam.sme.plugin.file.upload.model.FileUploadModel 数据模型
     * @author andy.sher
     * @date 2018/11/15 14:42
     */
    public static FileUploadModel createFileUploadModel(@NotNull String fileName, @NotNull String author, @NotNull String code,
            JSONObject model, String uuid, String resourceUuid, @NotNull File file) {
        FileUploadModel fileUploadModel = new FileUploadModel(fileName, author, code, model, uuid, resourceUuid, file);
        return fileUploadModel;
    }

    /**
     * 创建文件上传数据模型 .
     * <p>
     * 存储模式默认为持久化
     * </p>
     *
     * @param fileName 文件名
     * @param author 作者
     * @param code bkey代号
     * @param data 文件字节数组
     * @param model 模型填充数据
     * @param uuid 文件唯一标识符
     * @return com.vteam.sme.plugin.file.upload.model.FileUploadModel 数据模型
     * @author andy.sher
     * @date 2018/11/15 14:43
     */
    public static FileUploadModel createFileUploadModel(@NotNull String fileName, @NotNull String author, @NotNull String code,
            JSONObject model, String uuid, @NotNull byte[] data) {
        FileUploadModel fileUploadModel = new FileUploadModel(fileName, author, code, model, uuid, data);
        return fileUploadModel;
    }

    /**
     * 创建文件上传数据模型 .
     * <p>
     * 存储模式默认为持久化
     * </p>
     *
     * @param fileName 文件名
     * @param author 作者
     * @param bkeyValue bkey对应值
     * @param uuid 文件唯一标识符
     * @param data 文件字节数组
     * @return com.vteam.sme.plugin.file.upload.model.FileUploadModel 数据模型
     * @author andy.sher
     * @date 2018/11/15 14:43
     */
    public static FileUploadModel createFileUploadModel(@NotNull String fileName, @NotNull String author,
            @NotNull String bkeyValue, String uuid, @NotNull byte[] data) {
        FileUploadModel fileUploadModel = new FileUploadModel(fileName, author, bkeyValue, uuid, data);
        return fileUploadModel;
    }

    /**
     * 创建文件上传数据模型(可设置资源唯一标识符resourceUuid) .
     * <p>
     * 存储模式默认为持久化
     * </p>
     *
     * @param fileName 文件名
     * @param author 作者
     * @param bkeyValue bkey对应值
     * @param uuid 文件唯一标识符
     * @param resourceUuid 文件资源唯一标识符
     * @param data 文件字节数组
     * @return com.vteam.sme.plugin.file.upload.model.FileUploadModel 数据模型
     * @author andy.sher
     * @date 2018/11/15 14:43
     */
    public static FileUploadModel createFileUploadModel(@NotNull String fileName, @NotNull String author,
            @NotNull String bkeyValue, String uuid, String resourceUuid, @NotNull byte[] data) {
        FileUploadModel fileUploadModel = new FileUploadModel(fileName, author, bkeyValue, uuid, resourceUuid, data);
        return fileUploadModel;
    }

    /**
     * 创建文件上传数据模型 .
     * <p>
     * 存储模式默认为持久化
     * </p>
     *
     * @param fileName 文件名
     * @param author 作者
     * @param code bkey代号
     * @param inputStream 输入流
     * @param model 模型填充数据
     * @param uuid 文件唯一标识符
     * @return com.vteam.sme.plugin.file.upload.model.FileUploadModel 数据模型
     * @author andy.sher
     * @date 2018/11/15 14:43
     */
    public static FileUploadModel createFileUploadModel(@NotNull String fileName, @NotNull String author, @NotNull String code,
            JSONObject model, String uuid, @NotNull InputStream inputStream) {
        FileUploadModel fileUploadModel = new FileUploadModel(fileName, author, code, model, uuid, inputStream);
        return fileUploadModel;
    }

    /**
     * 创建文件上传数据模型 .
     * <p>
     * 存储模式默认为持久化
     * </p>
     *
     * @param fileName 文件名
     * @param author 作者
     * @param code bkey代号
     * @param remoteUrl 远程资源地址
     * @param model 模型填充数据
     * @param uuid 文件唯一标识符
     * @return com.vteam.sme.plugin.file.upload.model.FileUploadModel 数据模型
     * @author andy.sher
     * @date 2018/11/15 14:43
     */
    public static FileUploadModel createFileUploadModel(@NotNull String fileName, @NotNull String author, @NotNull String code,
            JSONObject model, String uuid, @NotNull String remoteUrl) {
        FileUploadModel fileUploadModel = new FileUploadModel(fileName, author, code, model, uuid, remoteUrl);
        return fileUploadModel;
    }

    /**
     * 保存第三方平台附件信息，文件异步通过接口下载.
     * 
     * @param fileName
     * @param code
     * @param model
     * @param fileUuid
     * @return com.vteam.sme.plugin.file.upload.model.FileUploadModel 数据模型
     * @author oscar.yu
     * @date 2020/3/9 17:24
     */
    public static FileUploadModel createTppModel(@NotNull String fileName, @NotNull String code, JSONObject model,
            String fileUuid) {
        FileUploadModel fileUploadModel = new FileUploadModel(fileName, code, model, fileUuid);
        return fileUploadModel;
    }

    /**
     * 获取随机UUID .
     *
     * @param
     * @return java.lang.String 随机UUID
     * @author andy.sher
     * @date 2019/3/14 10:53
     */
    private String defaultUuid() {
        if (StringUtils.isBlank(uuid)) {
            return UUID.randomUUID().toString();
        } else {
            return uuid;
        }
    }

    public void setCode(String code) {
        this.code = code;
    }

}
