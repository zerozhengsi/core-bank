package com.vteam.cars.exception;

/**
 * 文件后端存储异常类。
 * @author jun.wang
 * @date 2022/7/8 9:12
 */
public class FileUploaderStorageException extends RuntimeException{

    public FileUploaderStorageException() {
        super();
    }

    public FileUploaderStorageException(String message) {
        super(message);
    }

    public FileUploaderStorageException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUploaderStorageException(Throwable cause) {
        super(cause);
    }
}
