package com.accolite.furlough.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.accolite.furlough.utils.Constants;

@Service
public class FileStorageService {

    final DataFormatter formatter = new DataFormatter();

    @Autowired
    private ParserService parserService;
    private static final Logger log = LoggerFactory.getLogger(FileStorageService.class);
    private final Path rootLocation = Paths.get(Constants.ROOT_PATH + Constants.UPLOAD_DIR);

    public void store(final MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
            final String finalString = rootLocation.toString() + Constants.URL_SEP + file.getOriginalFilename();
            parserService.mapExcelToHashmap(finalString);
        } catch (final Exception e) {
            log.error("Failed to store the file. Error: {}", e.getMessage());
        }
    }

    public Resource loadFile(final String filename) {
        try {
            final Path file = rootLocation.resolve(filename);
            final Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                log.error("Fail to load the file");
            }
        } catch (final MalformedURLException e) {
            log.error("Failed to load the file Error: {}", e.getMessage());
        }
        return null;
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (final IOException e) {
            log.error("Could not initialize storage Error: {}", e.getMessage());
        }
    }

}
