package org.sid.fileupload.store;

import org.sid.fileupload.exception.FileCreationException;
import org.sid.fileupload.exception.FileDeleteException;
import org.sid.fileupload.exception.FileDownloadException;
import org.sid.fileupload.exception.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.stream.Stream;

/**
 * @author siddesh
 * @since 18 Dec 2019
 */

@Service
public class UploadServiceImplementation implements UploadService {

    private final Path filelocation;

    @Override
    public void init() {

        try {
            Files.createDirectories(filelocation);
        } catch (IOException e) {
            throw new FileCreationException("Couldn't initiate the initial repository "+filelocation, e);
        }
    }

    @Autowired
    public UploadServiceImplementation(UploadProperties uploadProperties) {
        this.filelocation = Paths.get(uploadProperties.getLocation());
    }


    @Override
    public void deleteAllFiles() {
        FileSystemUtils.deleteRecursively(filelocation.toFile());
    }

    @Override
    public Stream<Path> getAllFiles() {

        try {
            return Files.walk(this.filelocation, 1)
                    .filter(path -> !path.equals(this.filelocation))
                    .map(path -> this.filelocation.relativize(path));
        } catch (IOException ex) {
            throw new FileDownloadException("Couldn't get all the files", ex);
        }

    }

    @Override
    public void uploadFile(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (file.isEmpty()) {
                throw new FileUploadException("Can't upload empty file :" + filename);
            }
            if (filename.contains("..")) {
                throw new FileUploadException(" Can't store file with relative path outside current directory :" +filename);
            }
            Files.copy(file.getInputStream(), this.filelocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException ex) {
            throw new FileUploadException("Failed to upload file :" + filename, ex);
        }

    }

    @Override
    public Path getFile(String filename) {
        return filelocation.resolve(filename);
    }

    @Override
    public Resource getFileAsResource(String filename) {

        try {
            Path path = getFile(filename);
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileDownloadException("Couldn't read file :"+filename);
            }
        } catch (MalformedURLException e) {
            throw new FileDownloadException("Couldn't read file :"+filename, e);
        }
    }

    @Override
    public void deleteFile(String filename) {
        try {
            Path path = getFile(filename);
            if(!Files.deleteIfExists(path))
                throw new FileDeleteException("Couldn't delete the file :" +filename);
        } catch (IOException ex) {
            throw new FileDeleteException("Couldn't delete the file :"+filename, ex);
        }


    }
}
