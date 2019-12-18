package org.sid.fileupload.exception;

/**
 * @author siddesh
 * @since 18 Dec 2019
 */


public class FileDownloadException extends RuntimeException {

    public FileDownloadException(String message) {
        super(message);
    }

    public FileDownloadException(String message, Throwable cause) {
        super(message, cause);
    }
}
