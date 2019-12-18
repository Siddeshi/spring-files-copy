package org.sid.fileupload.exception;

/**
 * @author siddesh
 * @since 18 Dec 2019
 */


public class FileDeleteException extends RuntimeException {
    public FileDeleteException(String message) {
        super(message);
    }

    public FileDeleteException(String message, Throwable cause) {
        super(message, cause);
    }
}
