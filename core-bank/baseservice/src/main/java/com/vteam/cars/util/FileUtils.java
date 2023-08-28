package com.vteam.cars.util;

import com.vteam.cars.config.SmeConfiguration;
import com.vteam.cars.constant.GlobalConstants;
import com.vteam.cars.constant.MeasureConstants;
import com.vteam.vtarm.codec.CodecProperties;
import com.vteam.vtarm.utils.AesUtils;
import com.vteam.vtarm.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.util.UriUtils;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jakarta.validation.constraints.NotNull;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 文件相关工具类
 *
 * @author zack.yin
 * @date 2018/7/10 17:14
 */
@Slf4j
public final class FileUtils extends org.apache.commons.io.FileUtils {

    private FileUtils() {
    }

    /**
     * 默认读取数据缓冲大小
     */
    private static final int DEFAULT_BUFFER_SIZE = 4 * MeasureConstants.K;

    /**
     * 在已有文件名后增加一个以精确到毫秒的当前时间戳生成随机文件名。
     *
     * @param fileName 已有文件名
     * @return 返回生成的随机文件名。
     * @author zack.yin
     * @date 2018/7/10 17:16
     */
    public static String getRandomFileName(String fileName) {
        return getFileName(fileName) + GlobalConstants.Symbol.MINUS
                + LocalDateTime.now().format(DateUtils.FMT_MILLISECOND_N)
                + getFileType(fileName);
    }

    /**
     * 从带有类型后缀的文件名中获取不带类型后缀的文件名。
     *
     * @param fileName 带有类型后缀的文件名
     * @return java.lang.String 返回不带类型后缀的文件名。
     * @author zack.yin
     * @date 2018/7/10 17:17
     */
    public static String getFileName(String fileName) {
        return fileName.substring(0, fileName.lastIndexOf(GlobalConstants.Symbol.SPOT));
    }

    /**
     * 从带有类型后缀的文件名中获取文件名的类型后缀。
     *
     * @param fileName 带有类型后缀的文件名
     * @return java.lang.String 返回文件名的类型后缀。
     * @author zack.yin
     * @date 2018/7/10 17:18
     */
    public static String getFileType(String fileName) {
        return fileName.substring(fileName.lastIndexOf(GlobalConstants.Symbol.SPOT));
    }

    /**
     * 从文件完整路径中截取完整的文件名。
     *
     * @param filePath 文件完整路径
     * @return java.lang.String 返回从文件完整路径中截取完整的文件名
     * @author zack.yin
     * @date 2018/7/10 17:19
     */
    public static String getFullFileName(String filePath) {
        int lastSlashIndex = filePath.lastIndexOf(GlobalConstants.Symbol.SLASH);
        int lastBackSlashIndex = filePath.lastIndexOf(GlobalConstants.Symbol.BACKSLASH);
        // 如果没找到斜杠和反斜杠则返回原文件路径
        // 如果斜杠位置在反斜杠位置之后则从斜杠位置截取文件名
        // 否则从反斜杠位置截取文件名
        if (lastSlashIndex == -1 && lastBackSlashIndex == -1) {
            return filePath;
        } else if (lastSlashIndex > lastBackSlashIndex) {
            return StringUtils.substringAfterLast(filePath, GlobalConstants.Symbol.SLASH);
        } else {
            return StringUtils.substringAfterLast(filePath, GlobalConstants.Symbol.BACKSLASH);
        }
    }

    /**
     * 从文件完整路径中截取完整的文件目录
     *
     * @param filePath 文件完整路径
     * @return java.lang.String 返回从文件完整路径中截取完整的文件目录
     * @author zack.yin
     * @date 2018/7/10 17:19
     */
    public static String getFullFileDir(String filePath) {
        int lastSlashIndex = filePath.lastIndexOf(GlobalConstants.Symbol.SLASH);
        int lastBackSlashIndex = filePath.lastIndexOf(GlobalConstants.Symbol.BACKSLASH);
        // 如果没找到斜杠和反斜杠则返回空字符串
        // 如果斜杠位置在反斜杠位置之后则从斜杠位置截取文件目录
        // 否则从反斜杠位置截取文件目录
        if (lastSlashIndex == -1 && lastBackSlashIndex == -1) {
            return "";
        } else if (lastSlashIndex > lastBackSlashIndex) {
            return StringUtils.substringBeforeLast(filePath, GlobalConstants.Symbol.SLASH);
        } else {
            return StringUtils.substringBeforeLast(filePath, GlobalConstants.Symbol.BACKSLASH);
        }
    }

    /**
     * 根据文件的完整路径创建一个新文件。如果目录不存在时先创建目录再创建文件。
     *
     * @param filePath 文件完整路径
     * @return java.io.File 返回创建的File文件对象
     * @author zack.yin
     * @date 2018/7/10 17:19
     */
    public static File createFile(String filePath) {
        String fullFileDir = getFullFileDir(filePath);
        File fileDir = new File(fullFileDir);
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }

        File file = new File(filePath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            log.error(FileUtils.class.getName(), e);
        }
        return file;
    }

    /**
     * 将输入流复制到文件
     *
     * @param input 输入流
     * @param file 目标文件
     * @author zack.yin
     * @date 2018/7/10 17:21
     */
    public static void copy(InputStream input, File file) {
        try (FileOutputStream output = new FileOutputStream(file)) {
            copy(input, output);
        } catch (Exception e) {
            log.error(FileUtils.class.getName(), e);
        }
    }

    /**
     * 将输入流复制到输出流
     *
     * @param input  输入流
     * @param output 输出流
     * @author zack.yin
     * @date 2018/7/10 17:21
     */
    public static void copy(InputStream input, OutputStream output) {
        try {
            IOUtils.copy(input, output);
        } catch (IOException e) {
            log.error(FileUtils.class.getName(), e);
        }
    }

    /**
     * 从文件复制到响应对象 .
     *
     * @param inputStream 输入流
     * @param response 响应对象
     * @author andy.sher
     * @date 2019/12/5 14:36
     */
    public static void copy(InputStream inputStream, HttpServletResponse response) {
        try (InputStream input = inputStream) {
            IOUtils.copy(input, response.getOutputStream());
        } catch (IOException e) {
            log.error(FileUtils.class.getName(), e);
        }
    }

    /**
     * 从文件复制到响应对象 .
     *
     * @param inFile   文件
     * @param response http响应对象
     * @author andy.sher
     * @date 2018/8/14 14:49
     */
    public static void copy(File inFile, HttpServletResponse response) {
        try (InputStream input = Files.newInputStream(inFile.toPath())) {
            IOUtils.copy(input, response.getOutputStream());
        } catch (IOException e) {
            log.error(FileUtils.class.getName(), e);
        }
    }

    /**
     * 创建文件夹
     *
     * @param strDir 文件夹目录
     * @return boolean
     * @author zack.yin
     * @date 2018/7/10 17:21
     */
    public static boolean makeDir(String strDir) {
        File fileNew = new File(strDir);
        if (!fileNew.exists()) {
            return fileNew.mkdirs();
        } else {
            return true;
        }
    }

    /**
     * 获取文件流
     *
     * @param filePath 文件路径
     * @return java.io.BufferedWriter
     * @author zack.yin
     * @date 2018/7/10 17:21
     */
    public static BufferedWriter getBufferedWriter(String filePath) {
        return getBufferedWriter(filePath, false);
    }

    /**
     * 获取文件流
     *
     * @param filePath   文件路径
     * @param appendFlag true表示以追加形式写文件
     * @return java.io.BufferedWriter
     * @author zack.yin
     * @date 2018/7/10 17:22
     */
    public static BufferedWriter getBufferedWriter(String filePath, boolean appendFlag) {
        BufferedWriter writer = null;

        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
                writer = new BufferedWriter(new FileWriter(filePath, appendFlag));
            } catch (IOException e) {
                log.error(FileUtils.class.getName(), e);
            }
        }
        return writer;
    }

    /**
     * 压缩文件 .
     *
     * @param files    需要压缩的文件数组
     * @param filePath 压缩路径
     * @author zack.yin
     * @date 2018/8/4 22:46
     */
    public static void doZip(List<File> files, String filePath) {
        File zipFile = new File(filePath);
        // 检测是否存在目录
        if (!zipFile.getParentFile().exists()) {
            // 新建文件夹
            zipFile.getParentFile().mkdirs();
        }
        try (ZipArchiveOutputStream zos = (ZipArchiveOutputStream) new ArchiveStreamFactory()
                .createArchiveOutputStream(GlobalConstants.FileFormat.ZIP, Files.newOutputStream(zipFile.toPath()))) {
            SmeConfiguration smeConfiguration = SpringContextUtils.getBean(SmeConfiguration.class);
            zos.setEncoding(smeConfiguration.getEncoding());
            // 获取要压缩文件根路径
            ZipArchiveEntry zae;
            for (File currFile : files) {
                if (!currFile.exists()) {
                    continue;
                }
                // 获取每个文件相对路径,作为在ZIP中路径
                zae = new ZipArchiveEntry(currFile.getName());
                zos.putArchiveEntry(zae);
                // folder
                if (zae.isDirectory()) {
                    zos.closeArchiveEntry();
                    continue;
                }
                try (FileInputStream fis = new FileInputStream(currFile)) {
                    IOUtils.copy(fis, zos, MeasureConstants.K);
                    zos.closeArchiveEntry();
                } catch (IOException e) {
                    log.error(FileUtils.class.getName(), e);
                }
            }
        } catch (Exception e) {
            log.error(FileUtils.class.getName(), e);
        }
    }

    /**
     * 压缩文件 .
     *
     * @param files    需要压缩的文件数组
     * @param filePath 压缩路径
     * @param flag     标识是上传打包(true)还是下载打包 (false)
     * @return void
     * @author zack.yin
     * @date 2018/8/4 22:46
     */
    public static void doZip(List<File> files, String filePath, boolean flag) {
        File zipFile = new File(filePath);
        // 检测是否存在目录
        if (!zipFile.getParentFile().exists()) {
            // 新建文件夹
            zipFile.getParentFile().mkdirs();
        }
        try (ZipArchiveOutputStream zos = (ZipArchiveOutputStream) new ArchiveStreamFactory()
                .createArchiveOutputStream(GlobalConstants.FileFormat.ZIP, new FileOutputStream(zipFile));) {
            SmeConfiguration smeConfiguration = SpringContextUtils.getBean(SmeConfiguration.class);
            zos.setEncoding(smeConfiguration.getEncoding());
            // 获取要压缩文件根路径
            ZipArchiveEntry zae = null;
            for (File currFile : files) {
                if (!currFile.exists()) {
                    continue;
                }
                // 获取每个文件相对路径,作为在ZIP中路径
                zae = new ZipArchiveEntry(currFile.getName());
                zos.putArchiveEntry(zae);
                // folder
                if (zae.isDirectory()) {
                    zos.closeArchiveEntry();
                    continue;
                }
                try (FileInputStream fis = new FileInputStream(currFile);) {
                    IOUtils.copy(fis, zos, MeasureConstants.K);
                    if (zos != null) {
                        zos.closeArchiveEntry();
                    }
                } catch (IOException e) {
                    log.error(FileUtils.class.getName(), e);
                }
            }
        } catch (Exception e) {
            log.error(FileUtils.class.getName(), e);
        }
    }

    /**
     * 压缩文件夹 .
     *
     * @param srcDir           压缩文件夹路径
     * @param filePath         压缩路径
     * @param keepDirStructure 是否保留原来的目录结构,true:保留目录结构;
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @author zack.yin
     * @date 2019/5/31 10:38
     */
    public static void toZip(String srcDir, String filePath, boolean keepDirStructure) throws RuntimeException {
        File dest = new File(filePath);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();// 新建文件夹
        }
        try (ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(dest.toPath()))) {
            File sourceFile = new File(srcDir);
            compress(sourceFile, zos, sourceFile.getName(), keepDirStructure);
        } catch (Exception e) {
            log.error(FileUtils.class.getName(), e);
        }
    }

    /**
     * 递归压缩方法
     *
     * @param sourceFile       源文件
     * @param zos              zip输出流
     * @param name             压缩后的名称
     * @param keepDirStructure 是否保留原来的目录结构,true:保留目录结构;
     *                         <p>
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     */
    private static void compress(File sourceFile, ZipOutputStream zos, String name, boolean keepDirStructure) throws Exception {
        byte[] buf = new byte[DEFAULT_BUFFER_SIZE];
        if (sourceFile.isFile()) {
            // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
            zos.putNextEntry(new ZipEntry(name));
            // copy文件到zip输出流中
            int len;
            try (FileInputStream in = new FileInputStream(sourceFile)) {
                while ((len = in.read(buf)) != -1) {
                    zos.write(buf, 0, len);
                }
                // Complete the entry
                zos.closeEntry();
            }
        } else {
            File[] listFiles = sourceFile.listFiles();
            if (null == listFiles || listFiles.length == 0) {
                // 需要保留原来的文件结构时,需要对空文件夹进行处理
                if (keepDirStructure) {
                    // 空文件夹的处理
                    zos.putNextEntry(new ZipEntry(name + StringUtils.getSysSeparator()));
                    // 没有文件，不需要文件的copy
                    zos.closeEntry();
                }
            } else {
                for (File file : listFiles) {
                    // 判断是否需要保留原来的文件结构
                    if (keepDirStructure) {
                        // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                        // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                        compress(file, zos, name + StringUtils.getSysSeparator() + file.getName(), true);
                    } else {
                        compress(file, zos, file.getName(), false);
                    }
                }
            }
        }

    }

    /**
     * 复制文件流到文件 .<br>
     *
     * @param bytes   文件字节流
     * @param outFile 文件
     * @author andy.sher
     * @date 2018/8/8 14:27
     */
    public static void copy(byte[] bytes, File outFile) {
        try (OutputStream outputStream = Files.newOutputStream(outFile.toPath())) {
            FileCopyUtils.copy(bytes, outputStream);
        } catch (IOException e) {
            log.error(FileUtils.class.getName(), e);
        }
    }

    /**
     * 复制文件到输出流 .
     *
     * @param inFilePath 文件路径
     * @param response   输出流
     * @author andy.sher
     * @date 2018/8/24 10:26
     */
    public static void copy(String inFilePath, HttpServletResponse response) {
        SmeConfiguration smeConfiguration = SpringContextUtils.getBean(SmeConfiguration.class);
        try (InputStream fis = smeConfiguration.getClass().getResourceAsStream(inFilePath);
             OutputStream outputStream = response.getOutputStream()) {
            assert fis != null;
            FileCopyUtils.copy(fis, outputStream);
        } catch (IOException e) {
            log.error(FileUtils.class.getName(), e);
        }
    }

    /**
     * 从多媒体请求中获取文件 .
     *
     * @param request 多媒体请求
     * @return jakarta.servlet.http.Part 文件
     * @author andy.sher
     * @date 2018/8/24 10:34
     */
    public static Part getPartFromRequest(MultipartHttpServletRequest request) {
        try {
            return ((DefaultMultipartHttpServletRequest) request).getRequest().getPart(GlobalConstants.FileFormat.FILE);
        } catch (IOException | ServletException e) {
            log.error(FileUtils.class.getName(), e);
        }
        return null;
    }

    /**
     * 删除文件夹 .
     *
     * @param folderPath 文件夹路径
     * @author andy.sher
     * @date 2018/9/7 14:13
     */
    public static void deleteFolder(String folderPath) {
        try {
            // 删除完里面所有内容
            deleteAllFile(folderPath);
            File file = new File(folderPath);
            // 删除空文件夹
            file.delete();
        } catch (Exception e) {
            log.error(FileUtils.class.getName(), e);
        }
    }

    /**
     * 删除文件夹下的所有文件 .
     *
     * @param folderPath 文件夹路径
     * @author andy.sher
     * @date 2018/9/7 14:16
     */
    public static void deleteAllFile(String folderPath) {
        File file = new File(folderPath);
        String[] tempList = file.list();
        File temp;
        if (ArrayUtils.isNotEmpty(tempList)) {
            for (String s : tempList) {
                if (folderPath.endsWith(StringUtils.getSysSeparator())) {
                    temp = new File(folderPath + s);
                } else {
                    temp = new File(folderPath + StringUtils.getSysSeparator() + s);
                }
                if (temp.isFile()) {
                    temp.delete();
                }
                if (temp.isDirectory()) {
                    // 先删除文件夹里面的文件
                    deleteAllFile(folderPath + GlobalConstants.Symbol.SLASH + s);
                    // 再删除空文件夹
                    deleteFolder(folderPath + GlobalConstants.Symbol.SLASH + s);
                }
            }
        }
    }

    /**
     * 递归扫描指定文件夹并获取文件夹下的文件名称 .
     *
     * @param folderPath 文件夹路径
     * @author andy.sher
     * @date 2018/9/13 10:34
     */
    public static void scanFilesWithRecursion(String folderPath, ArrayList<String> scanFiles) {
        File directory = new File(folderPath);
        if (!directory.isDirectory()) {
            return;
        }
        if (directory.isDirectory()) {
            File[] fileList = directory.listFiles();
            if (ArrayUtils.isNotEmpty(fileList)) {
                for (File file : fileList) {
                    // 如果当前是文件夹，进入递归扫描文件夹
                    if (file.isDirectory()) {
                        // 递归扫描下面的文件夹
                        scanFilesWithRecursion(file.getAbsolutePath(), scanFiles);
                    }
                    // 非文件夹
                    else {
                        scanFiles.add(file.getAbsolutePath());
                    }
                }
            }
        }
    }

    /**
     * 判断文件是否存在 .
     *
     * @param filePath 文件路径
     * @param isAbs    是否是绝对路径
     * @return boolean 是否存在
     * @author andy.sher
     * @date 2018/10/19 11:18
     */
    public static boolean exist(String filePath, boolean isAbs) {
        // 绝对路径
        if (isAbs) {
            try {
                File file = new File(filePath);
                return file.exists();
            } catch (Exception e) {
                log.error(FileUtils.class.getName(), e);
            }
        }
        // 相对路径
        else {
            return new ClassPathResource(filePath).exists();
        }

        return false;
    }

    /**
     * 创建目录 .
     *
     * @param filePath 目录路径
     * @return boolean 是否创建成功
     * @author andy.sher
     * @date 2018/10/19 11:18
     */
    public static boolean createDir(String filePath) {
        File file = new File(filePath);
        return file.mkdir();
    }

    /**
     * 读取文件Byte数组 .
     *
     * @param filePath 文件路径
     * @return byte[] byte数组
     * @author andy.sher
     * @date 2018/11/15 9:53
     */
    public static byte[] readAllBytes(@NotNull String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            try (FileInputStream fs = new FileInputStream(file)) {
                return readAllBytes(fs);
            } catch (IOException e) {
                log.error(FileUtils.class.getName(), e);
            }
        }
        return null;
    }

    /**
     * 读取文件Byte数组 .
     *
     * @param inputStream 文件输入流
     * @return byte[] byte数组
     * @throws IOException io异常
     */
    public static byte[] readAllBytes(@NotNull InputStream inputStream) throws IOException {
        return IOUtils.toByteArray(inputStream);
    }

    /**
     * 保存临时文件并获取临时文件路径 .
     *
     * @param fileName 文件名称
     * @param file     文件
     * @return java.lang.String 临时存储路径
     * @author andy.sher
     * @date 2018/11/15 11:16
     */
    public static String saveTmpFile(@NotNull String fileName, @NotNull MultipartFile file) {
        String tmpPath = getTmpPath();
        String realFileName = UUID.randomUUID() + StringUtils.getSysSeparator() + fileName;
        String fillPath = tmpPath + realFileName;
        createFile(fillPath);
        File tmpFile = new File(fillPath);
        try (InputStream inputStream = file.getInputStream()) {
            FileUtils.copy(inputStream, tmpFile);
        } catch (Exception e) {
            fillPath = null;
            log.error(FileUtils.class.getName(), e);
        }
        return fillPath;
    }

    /**
     * 保存临时文件并获取临时文件路径 .
     *
     * @param fileName 文件名称
     * @param data     文件数据
     * @return java.lang.String 临时存储路径
     * @author andy.sher
     * @date 2018/11/15 11:16
     */
    public static String saveTmpFile(@NotNull String fileName, @NotNull byte[] data) {
        String tmpPath = getTmpPath();
        String realFileName = UUID.randomUUID() + StringUtils.getSysSeparator() + fileName;
        String fillPath = tmpPath + realFileName;
        createFile(fillPath);
        File tmpFile = new File(fillPath);
        try {
            FileUtils.copy(data, tmpFile);
        } catch (Exception e) {
            fillPath = null;
            log.error(FileUtils.class.getName(), e);
        }
        return fillPath;
    }

    /**
     * 保存临时文件 .
     *
     * @param fileName 文件名称
     * @param file     文件
     * @return java.lang.String 临时存储路径
     * @author andy.sher
     * @date 2018/11/15 15:11
     */
    public static String saveTmpFile(@NotNull String fileName, File file) {
        String tmpPath = getTmpPath();
        String realFileName = UUID.randomUUID() + StringUtils.getSysSeparator() + fileName;
        String fillPath = tmpPath + realFileName;
        createFile(fillPath);
        File tmpFile = new File(fillPath);
        try {
            FileUtils.copyFile(file, tmpFile);
        } catch (Exception e) {
            fillPath = null;
            log.error(FileUtils.class.getName(), e);
        }
        return fillPath;
    }

    /**
     * 保存临时文件 .
     *
     * @param fileName    文件名称
     * @param inputStream 文件输入流
     * @return java.lang.String 临时存储路径
     * @author andy.sher
     * @date 2018/11/15 15:23
     */
    public static String saveTmpFile(String fileName, InputStream inputStream) {
        String tmpPath = getTmpPath();
        String realFileName = UUID.randomUUID() + StringUtils.getSysSeparator() + fileName;
        String fillPath = tmpPath + realFileName;
        createFile(fillPath);
        File tmpFile = new File(fillPath);
        try (InputStream is = inputStream) {
            FileUtils.copy(is, tmpFile);
        } catch (Exception e) {
            fillPath = null;
            log.error(FileUtils.class.getName(), e);
        }
        return fillPath;
    }

    /**
     * 保存临时文件 .
     *
     * @param fileName  文件名称
     * @param remoteUrl 网络资源地址
     * @return java.lang.String 临时存储路径
     * @author andy.sher
     * @date 2018/11/15 15:23
     */
    public static String saveTmpFile(String fileName, String remoteUrl) {
        String tmpPath = getTmpPath();
        String realFileName = UUID.randomUUID() + StringUtils.getSysSeparator() + fileName;
        String fillPath = tmpPath + realFileName;
        createFile(fillPath);
        File tmpFile = new File(fillPath);
        CloseableHttpClient client = SpringContextUtils.getBean("httpClientManagerFactoryBean", CloseableHttpClient.class);
        HttpGet httpGet = new HttpGet(remoteUrl);
        try (CloseableHttpResponse response = client.execute(httpGet); InputStream is = response.getEntity().getContent()) {
            FileUtils.copy(is, tmpFile);
        } catch (Exception e) {
            fillPath = null;
            log.error(FileUtils.class.getName(), e);
        }
        return fillPath;
    }

    /**
     * 获取临时路径
     *
     * @return 临时路径
     * @author oscar.yu
     * @date 2019/9/9 14:48
     */
    private static String getTmpPath() {
        SmeConfiguration smeConfiguration = SpringContextUtils.getBean(SmeConfiguration.class);
        return smeConfiguration.getTmpPath();
    }

    /**
     * 设置下载的Response参数
     *
     * @param showName 下载名称
     * @param fileType 文件类型
     * @param response httpResponse
     * @author jiangming.huang
     * @date 2018/10/15 0015 下午 1:09
     */
    public static void setResponse(String showName, String fileType, HttpServletRequest request, HttpServletResponse response) {
        try {
            // 设置文档类型
            setContentType(fileType, response);
            String codeFileName = URLEncoder.encode(showName, GlobalConstants.Character.UTF_8);
            codeFileName = fixSpecialSymbol(codeFileName, request);
            response.setHeader("Content-Disposition", "attachment; filename=" + codeFileName);
            setCommonHeader(response);
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
        } catch (UnsupportedEncodingException ignore) {
            // 捕获不处理
        }
    }

    /**
     * 设置预览的Response参数
     *
     * @param fileName 文件名
     * @param fileType 文件类型
     * @param response httpResponse
     */
    public static void setPreviewResponse(String fileName, String fileType, HttpServletRequest request, HttpServletResponse response) {
        try {
            // 设置文档类型
            setContentType(fileType, response);
            String codeFileName = URLEncoder.encode(fileName, GlobalConstants.Character.UTF_8);
            codeFileName = fixSpecialSymbol(codeFileName, request);
            response.setHeader("Content-Disposition", "inline; filename=\"" + codeFileName + "\"");
            setCommonHeader(response);
        } catch (UnsupportedEncodingException ignore) {
            // 捕获不处理
        }
    }

    /**
     * 设置单证委托制单下载的Response参数 .<br>
     *
     * @param showName 下载名称
     * @param fileType 文件类型
     * @param response 响应
     * @author shaoquan.wu
     * @date 2022/8/19 14:38:35
     */
    public static void setDocumentMakingResponse(String showName, String fileType, HttpServletResponse response) {
        // 设置文档类型
        setContentType(fileType, response);
        String codeFileName = UriUtils.encode(showName, GlobalConstants.Character.UTF_8);
        response.setHeader("Content-Disposition", "attachment; filename=" + codeFileName);
        setCommonHeader(response);
        response.setHeader("Set-Cookie", "fileDownload=true; path=/");
    }

    /**
     * 设置文档类型
     *
     * @param fileType 文件类型
     * @param response httpResponse
     * @author oscar.yu
     * @date 2019/9/9 14:46
     */
    private static void setContentType(String fileType, HttpServletResponse response) {
        if (GlobalConstants.FileFormat.DOC.equals(fileType) || GlobalConstants.FileFormat.DOCX.equalsIgnoreCase(fileType)) {
            response.setContentType("application/msword");
        } else if (GlobalConstants.FileFormat.PDF.equals(fileType)) {
            response.setContentType("application/pdf");
        } else if (GlobalConstants.FileFormat.TIF.equals(fileType)) {
            response.setContentType("image/tiff");
        } else if (GlobalConstants.FileFormat.JPG.equals(fileType) || GlobalConstants.FileFormat.JPEG.equals(fileType)
                || GlobalConstants.FileFormat.GIF.equals(fileType) || GlobalConstants.FileFormat.PNG.equals(fileType)
                || GlobalConstants.FileFormat.BMP.equals(fileType)) {
            response.setContentType("image/png");
        } else if (GlobalConstants.FileFormat.XLS.equals(fileType)
                || GlobalConstants.FileFormat.XLSX.equalsIgnoreCase(fileType)) {
            response.setContentType("application/vnd.ms-excel");
        } else if (GlobalConstants.FileFormat.PFX.equals(fileType)) {
            response.setContentType("pfx");
        } else {
            response.setContentType("application/x-download");
        }
    }

    /**
     * 设置公共头信息
     *
     * @param response httpResponse
     * @author oscar.yu
     * @date 2019/9/9 14:48
     */
    private static void setCommonHeader(HttpServletResponse response) {
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        response.setDateHeader("Expires", (System.currentTimeMillis() + 1000));
    }

    /**
     * 文件加解密(写入)
     *
     * @param srcFileStream 源文件输入流
     * @param destFile 目标文件
     * @param opMode 加解密类型(参考{@link Cipher}类中常量)
     * @author oscar.yu
     * @date 2019/9/9 13:45
     */
    public static void cryptFile(InputStream srcFileStream, File destFile, int opMode) {
        try {
            CipherInputStream cipherInputStream = getCryptCipherInputStream(srcFileStream, opMode);
            if (null != cipherInputStream) {
                FileUtils.copyInputStreamToFile(cipherInputStream, destFile);
            }
        } catch (Exception e) {
            log.error(FileUtils.class.getName(), e);
        }
    }

    /**
     * 解密指定的文件,返回字节流
     *
     * @param fileFullPath 文件全路径
     * @return byte[]
     * @author oscar.yu
     * @date 2019/9/9 17:42
     */
    public static byte[] decryptFile(String fileFullPath) {
        try (InputStream srcFileStream = new FileInputStream(fileFullPath)) {
            CipherInputStream cipherInputStream = getCryptCipherInputStream(srcFileStream, Cipher.DECRYPT_MODE);
            return readAllBytes(cipherInputStream);
        }catch(FileNotFoundException e) {
        	log.error( " 文件未找到："+ fileFullPath);
        }
        catch (Exception e) {
            log.error(FileUtils.class.getName(), e);
        }
        return null;
    }

    /**
     * 获取文件加解密输入流
     *
     * @param srcFileStream 源文件输入流
     * @param opMode 加解密类型(参考{@link Cipher}类中常量)
     * @author oscar.yu
     * @date 2019/9/9 18:02
     */
    public static CipherInputStream getCryptCipherInputStream(InputStream srcFileStream, int opMode) {
        CodecProperties codecProperties = SpringContextUtils.getBean(CodecProperties.class);
        final String dataSecurityKey = codecProperties.getAesKey();
        final String dataSecurityIv = codecProperties.getAesIv();
        CipherInputStream cipherInputStream = null;
        try {
            Cipher cipher = AesUtils.getCipher(dataSecurityKey, dataSecurityIv, opMode);
            cipherInputStream = new CipherInputStream(srcFileStream, cipher);
        } catch (Exception e) {
            log.error(FileUtils.class.getName(), e);
        }
        return cipherInputStream;
    }

    /**
     * 修复下载时,特殊符号无法正常显示的问题
     *
     * @param fileName 文件名
     * @author zhuang.shao
     * @date 2018年9月21日 下午6:27:37
     */
    private static String fixSpecialSymbol(String fileName, HttpServletRequest request) {
        String userAgent = request.getHeader("user-agent").toLowerCase();
        String s = fileName.replaceAll("%20", "\\+").replaceAll("%28", "\\(").replaceAll("%29", "\\)")
                .replaceAll("%3B", ";");
        String s1 = s.replaceAll("%40", "@").replaceAll("%23", "\\#").replaceAll("%26", "\\&")
                .replaceAll("%2C", "\\,");
        String s2 = s1.replaceAll("%24", "\\$").replaceAll("%25", "\\%").replaceAll("%5E", "\\^")
                .replaceAll("%3D", "\\=");
        if (userAgent.contains("msie")) {
            // IE浏览器
            fileName = s2.replaceAll("%2B", "\\+");
        } else if (userAgent.contains("like gecko")) {
            // 谷歌
            fileName = s2.replaceAll("%2B", "\\+").replaceAll("%5B", "[").replaceAll("%5D", "]")
                    .replaceAll("%7B", "\\{").replaceAll("%7D", "\\}");
        }
        return fileName;
    }

}
