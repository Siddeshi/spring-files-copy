package org.sid.fileupload.exception;

/**
 * @author siddesh
 * @since 18 Dec 2019
 */


public class FileUploadException extends RuntimeException {

    public FileUploadException(String message) {
        super(message);
    }

    public FileUploadException(String message, Throwable cause) {
        super(message, cause);
    }
}
