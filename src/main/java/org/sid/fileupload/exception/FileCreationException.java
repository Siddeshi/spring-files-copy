package org.sid.fileupload.exception;

/**
 * @author siddesh
 * @since 18 Dec 2019
 */


public class FileCreationException extends RuntimeException {
    public FileCreationException(String message) {
        super(message);
    }

    public FileCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
