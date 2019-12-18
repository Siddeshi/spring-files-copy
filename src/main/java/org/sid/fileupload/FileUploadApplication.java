package org.sid.fileupload;

import org.sid.fileupload.store.UploadProperties;
import org.sid.fileupload.store.UploadService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author siddesh
 * @since 18 Dec 2019
 */

@SpringBootApplication
@EnableConfigurationProperties(UploadProperties.class)
public class FileUploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileUploadApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UploadService uploadService) {
        return (args) -> {
            uploadService.init();
        };
    }
}