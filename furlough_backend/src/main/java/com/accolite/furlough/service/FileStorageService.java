package com.accolite.furlough.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.accolite.furlough.parserinput.ParseInput;
import com.accolite.furlough.utils.Constants;

@Service
public class FileStorageService {

    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    private final Path rootLocation = Paths.get(Constants.UPLOAD_DIR);

    public void store(final MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
            final ParseInput parser = new ParseInput();
            final String finalString = rootLocation.toString() + "\\" + file.getOriginalFilename();
            System.out.println(finalString);
            // parser.mapExcelToHashmp(finalString);
            parser.mapExcelToHashmp(finalString);
        } catch (final Exception e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public Resource loadFile(final String filename) {
        try {
            final Path file = rootLocation.resolve(filename);
            final Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("FAIL!");
            }
        } catch (final MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (final IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }
}
