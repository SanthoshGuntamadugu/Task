package com.example.tiffupload.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {

    private final Path rootLocation = Paths.get("upload-dir");

    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new IOException("Failed to store empty file.");
            }

            // Ensure the upload directory exists
            if (!Files.exists(rootLocation)) {
                Files.createDirectories(rootLocation);
            }

            // Save the file to the upload directory
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
        } catch (IOException e) {
            // Log the error
            e.printStackTrace();
            // Throw a more descriptive runtime exception
            throw new RuntimeException("Failed to store file: " + e.getMessage(), e);
        }
    }
}
