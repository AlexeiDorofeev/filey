package com.doro.filey.services;

import com.doro.filey.config.StorageProperties;
import com.doro.filey.exceptions.StorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FileService {

    private final Path rootLocation;

    @Autowired
    public FileService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    public Path saveFile(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);

            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
        return rootLocation.resolve(filename);
    }

    public Path loadFileAsResource(String fileName) {
        try {
            Path filePath = rootLocation.resolve(fileName);
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return filePath;
            } else {
                throw new StorageException("Could not read file: " + fileName);
            }
        } catch (MalformedURLException e) {
            throw new StorageException("Could not read file: " + fileName, e);
        }
    }

    public List<String> listAllFiles() {
        List<String> fileNames = new ArrayList<>();
        try (Stream<Path> files = Files.list(rootLocation)) {
            files.forEach(file -> fileNames.add(file.getFileName().toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileNames;
    }
}

