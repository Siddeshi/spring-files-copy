package org.sid.fileupload.store;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * @author siddesh
 * @since 18 Dec 2019
 */


public interface UploadService {

    void init();

    void deleteAllFiles();

    Stream<Path> getAllFiles();

    void uploadFile(MultipartFile file);

    Path getFile(String filename);

    Resource getFileAsResource(String filename);

    void deleteFile(String filename);

}
