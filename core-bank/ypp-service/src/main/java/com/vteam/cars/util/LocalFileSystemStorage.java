package com.vteam.cars.util;

import ch.qos.logback.core.util.FileSize;
import com.beust.jcommander.Strings;
import com.vteam.cars.config.SmeConfiguration;
import com.vteam.cars.exception.FileUploaderStorageException;
import com.vteam.cars.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用webUploader处理多文件上传（断点上传），大文件上传(分块上传)，用于后端接收上传文件数据处理 <br>
 * 支持大文件及多文件上传、增加Ukey校验的功能改造需求：7450610. <br>
 *
 * @author jun.wang
 * @date 2022/7/8 9:15
 */

@Service
@Slf4j
public class LocalFileSystemStorage implements InitializingBean {

    @Resource(type = SmeConfiguration.class)
    private SmeConfiguration smeConfiguration;

    /**
     * 在临时目录下的临时文件存储文件夹
     */
    private String folderName;

    /**
     * 临时目录。
     */
    private String TEMP_DIR;

    /**
     * 文件合并线程数缺省最大值。
     */
    private static final int DEFAULT_MAX_MERGE_THREADS = 50;

    /**
     * 分块文件的分隔符。
     */
    private static final String CHUNK_FILE_DELIMITER = "_#";

    /**
     * 配置文件中指定的合并文件线程数。
     */
    @Value("${application.file.storage.threads}")
    private String threads;

    /**
     * 线程执行器。
     */
    private static Executor executor;

    /**
     * 判断一个完整文件的分块文件是否全部合并成功，成为一个完整的文件。
     * true表示全部合并成功，false表示合并失败
     * 如果不是分块文件，保存成功后设置为true
     */
    private boolean isMergeSuccess;

    public boolean getIsMergeSuccess() {
        return isMergeSuccess;
    }

    private CountDownLatch countDownLatch;

    @Override
    public void afterPropertiesSet() {

        // 准备线程池
        final int threads = Strings.isStringEmpty(this.threads) ? DEFAULT_MAX_MERGE_THREADS : Integer.parseInt(this.threads);
        if (log.isInfoEnabled()) {
            log.info("Initializing merge file thread pool size {}", threads);
        }
        executor = Executors.newFixedThreadPool(threads);
    }

    /**
     * 存储 WebUploader 从前端传送过来的分块。
     * @param file      文件
     * @param chunk     块编号
     * @param chunkSize 文件所有块大小信息。
     */
    public void saveChunk(final MultipartFile file, final int chunk, long[] chunkSize, String folderName) {
        TEMP_DIR = smeConfiguration.getTmpPath();
        this.folderName = folderName;
        isMergeSuccess = false;
        // 准备文件存储目录
        String storageDir = TEMP_DIR + folderName;
        try {
            if (log.isInfoEnabled()) {
                log.info("Prepare storage directory {}", storageDir);
            }
            FileUtils.forceMkdir(new File(storageDir));
        } catch (Throwable t) {
            if (log.isErrorEnabled()) {
                log.error("Prepare storage directory fail.", t);
            }
            throw new FileUploaderStorageException("Prepare storage directory fail.", t);
        }

        // 生成块文件。
        this.copyStreamToFile(file, chunk, chunkSize);

        if (isChunk(chunk, chunkSize)) {
            // 块文件检查，如果块文件完整，就启动文件合并线程。
            final String originalFilename = file.getOriginalFilename();
            if (this.checkChunks(originalFilename, chunkSize)) {
                countDownLatch = new CountDownLatch(chunkSize.length);
                executor.execute(new FileMergeThread(originalFilename));
                try {
                    countDownLatch.await();
                    isMergeSuccess = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将流保存为文件。
     * @param file  文件
     * @param chunk 分块号
     * @param chunkSize 块文件大小
     */
    private void copyStreamToFile(final MultipartFile file, final int chunk, final long[] chunkSize) {
        try {
            final String filename = file.getOriginalFilename();
            final InputStream inputStream = file.getInputStream();
            File targetFile;

            // 如果是分块文件，将块文件保存到临时目录，否则存储到 Storage 目录。
            if (isChunk(chunk, chunkSize)) { // 分块文件
                targetFile = new File(TEMP_DIR + folderName + StringUtils.getSysSeparator() + filename + CHUNK_FILE_DELIMITER + chunk);
                // 判断块文件是否已经存在并且完整，直接跳过。
                if (targetFile.exists() && FileUtils.sizeOf(targetFile) == chunkSize[chunk]) {
                    if (log.isInfoEnabled()) {
                        log.info("Ignore exists chunk file {}", targetFile.getAbsoluteFile());
                    }
                } else {
                    if (log.isInfoEnabled()) {
                        log.info("Save chunk {} to {}, chunk size {}", chunk, targetFile.getAbsoluteFile(), FileSize.valueOf(String.valueOf(file.getSize())));
                    }
                    FileUtils.copyInputStreamToFile(inputStream, targetFile);
                }
            } else { // 非分块
                targetFile = new File(TEMP_DIR + folderName + StringUtils.getSysSeparator() + filename);
                if (log.isInfoEnabled()) {
                    log.info("Save {} to {} directly", filename, targetFile.getAbsolutePath());
                }
                FileUtils.copyInputStreamToFile(inputStream, targetFile);
                isMergeSuccess = true;
            }

        } catch (Throwable t) {
            if (log.isErrorEnabled()) {
                log.error("Save chunk fail.", t);
            }
            throw new FileUploaderStorageException("Save chunk fail.", t);
        }
    }

    /**
     * 判断是否是分块文件。
     * @param chunk 块编号
     * @param chunkSize 块大小信息。
     * @return 如果是分块文件，返回 true，否则返回 false。
     */
    private boolean isChunk(final int chunk, final long[] chunkSize) {
        return chunk >= 0 && null != chunkSize && chunkSize.length > 0;
    }

    /**
     * 校验块文件是否完整。
     * @param filename  文件名
     * @param chunkSize 块文件大小
     */
    private boolean checkChunks(final String filename, final long[] chunkSize) {
        final File sourceDir = new File(TEMP_DIR + folderName);
        final String[] chunkFiles = Objects.requireNonNull(sourceDir.list((dir, name) -> name.startsWith(filename + CHUNK_FILE_DELIMITER)));

        // 判断块文件数量是否完整
        if (chunkSize.length != Arrays.stream(chunkFiles).count()) {
            return false;
        }

        // 检查每一个块文件的大小
        int beginIndex = filename.length() + CHUNK_FILE_DELIMITER.length();
        return chunkSize.length == Arrays.stream(chunkFiles).filter(i -> {
            int chunkNumber = Integer.parseInt(i.substring(beginIndex));
            return FileUtils.sizeOf(new File(sourceDir + StringUtils.getSysSeparator() + i)) == chunkSize[chunkNumber];
        }).count();
    }

    /**
     * 文件合并线程。
     */
    class FileMergeThread implements Runnable {

        // 原始文件名
        private final String filename;

        /**
         * 构造函数。
         * @param originalFileName 原始文件名
         */
        public FileMergeThread(final String originalFileName) {
            filename = originalFileName;
        }

        @Override
        public void run() {
            if (log.isInfoEnabled()) {
                log.info("Start merge thread for {}", this.filename);
            }

            final File sourceDir = new File(TEMP_DIR + folderName);
            final String targetFile = TEMP_DIR + folderName + StringUtils.getSysSeparator() + filename;
            final Path targetFilePath = Paths.get(targetFile);

            try {
                if (Files.deleteIfExists(targetFilePath)) {
                    if (log.isInfoEnabled()) {
                        log.info("Target file {} has exists, delete it", targetFile);
                    }
                }
                Files.createFile(targetFilePath);

                try (FileOutputStream os = new FileOutputStream(targetFile); FileChannel outChannel = os.getChannel()) {
                    // 列出文件的所有分块，并排序。
                    int beginIndex = filename.length() + CHUNK_FILE_DELIMITER.length();
                    Arrays.stream(Objects.requireNonNull(sourceDir.list((dir, name) -> name.startsWith(filename + CHUNK_FILE_DELIMITER))))
                            .sorted(Comparator.comparingInt(o -> Integer.parseInt(o.substring(beginIndex)))).forEach(i -> {
                                try {
                                    // 将块文件流追加到目标文件中
                                    String chunkFile = TEMP_DIR + folderName + StringUtils.getSysSeparator() + i;
                                    try (FileChannel inChannel = FileChannel.open(Paths.get(chunkFile))) {
                                        if (log.isInfoEnabled()) {
                                            log.info("Transfer chunk {}, chunk size {} done.", i, inChannel.size());
                                        }
                                        inChannel.transferTo(0, inChannel.size(), outChannel);
                                    }

                                    // 将已经合并的块文件删除
                                    if (FileUtils.deleteQuietly(new File(chunkFile))) {
                                        if (log.isInfoEnabled()) {
                                            log.info("Delete chunk file {}", chunkFile);
                                        }
                                    }
                                } catch (Throwable t) {
                                    if (log.isErrorEnabled()) {
                                        log.error("Transfer chunk fail.", t);
                                    }
                                    throw new FileUploaderStorageException("Transfer chunk fail.", t);
                                }finally {
                                    countDownLatch.countDown();
                                }
                            });
                }
            } catch (Throwable t) {
                if (log.isErrorEnabled()) {
                    log.error("Merge thread fail.", t);
                }
                throw new FileUploaderStorageException("Merge thread fail.", t);
            }

            if (log.isInfoEnabled()) {
                log.info("Mmierge thread for {} has ternated.", this.filename);
            }
        }
    }
}
