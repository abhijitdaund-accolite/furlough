package com.accolite.furlough.rest;
/*
 * @author = guru shankar accepts cross origin from localhost:4200
 * @RequestParam should be similar as that of input tag Validations done 1.
 * Check for file type extensions 2. Upload only .xls Upload is done by file
 * storage service reviewed by Vignesh.B
 */

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.accolite.furlough.service.FileStorageService;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class FileUploadController {

    @Autowired
    FileStorageService filestorageService;

    List<String> files = new ArrayList<String>();

    @PostMapping("/post")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") final MultipartFile file) {
        String message = "";
        try {
            files.add(file.getOriginalFilename());
            final String fullName = file.getOriginalFilename();
            final String fileName = new File(fullName).getName();
            final int dotIndex = fileName.lastIndexOf('.');
            final String extension = (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
            if (extension.equals("xls")) {
                filestorageService.store(file);
                message = "You successfully uploaded " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } else {
                message = "wrong file type " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        }

        catch (final Exception e) {
            message = "FAIL to upload " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @GetMapping("/getallfiles")
    public ResponseEntity<List<String>> getListFiles(final Model model) {
        final List<String> fileNames = files
                .stream().map(fileName -> MvcUriComponentsBuilder
                        .fromMethodName(FileUploadController.class, "getFile", fileName).build().toString())
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(fileNames);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable final String filename) {
        final Resource file = filestorageService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
