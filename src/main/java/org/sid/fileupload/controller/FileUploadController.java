package org.sid.fileupload.controller;

import org.sid.fileupload.exception.FileUploadException;
import org.sid.fileupload.store.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author siddesh
 * @since 18 Dec 2019
 */

@Controller
public class FileUploadController {

    private final UploadService uploadService;

    @Autowired
    public FileUploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping("/fileupload")
    @ResponseBody
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {

        uploadService.uploadFile(file);
        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully uploaded the file");
    }

    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<?> handleFileUploadError(FileUploadException err) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("File upload failed!");
    }
}
