package com.accolite.furlough.rest;
/*
 * accepts cross origin from localhost:4200
 * @RequestParam should be similar as that of input tag Validations done 1.
 * Check for file type extensions 2. Upload only .xls Upload is done by file
 * storage service
 */

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.accolite.furlough.dto.FileDetailsList;
import com.accolite.furlough.service.FileListService;
import com.accolite.furlough.service.FileStorageService;
import com.accolite.furlough.utils.Constants;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class FileUploadController {

    @Autowired
    FileStorageService filestorageService;

    @Autowired
    FileListService fileListService;

    List<String> files = new ArrayList<String>();
    private final static Logger logger = LoggerFactory.getLogger(FileUploadController.class);

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
                final Path rootLocation = Paths.get(Constants.ROOT_PATH + Constants.UPLOAD_DIR);
                final File toFile = new File(rootLocation.toString() + Constants.URL_SEP + file.getOriginalFilename());
                if (toFile.exists()) {
                    message = "File " + file.getOriginalFilename() + " is already uploaded to the server";
                    logger.error(message);
                    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);

                }
                filestorageService.store(file);
                message = "You successfully uploaded " + file.getOriginalFilename() + "!";
                logger.info(message);
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } else {
                message = "wrong file type " + file.getOriginalFilename() + "!";
                logger.error(file.getOriginalFilename() + " is not an xls file");
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }

        }

        catch (final Exception e) {
            message = "FAIL to upload " + file.getOriginalFilename() + "!";
            logger.error("Failed to upload " + file.getOriginalFilename() + " error :" + e.getMessage());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    // @GetMapping("/getallfiles")
    // public ResponseEntity<List<String>> getListFiles(final Model model) {
    // final List<String> fileNames = files
    // .stream().map(fileName -> MvcUriComponentsBuilder
    // .fromMethodName(FileUploadController.class, "getFile",
    // fileName).build().toString())
    // .collect(Collectors.toList());
    //
    // return ResponseEntity.ok().body(fileNames);
    // }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable final String filename) {
        final Resource file = filestorageService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @GetMapping("/files")
    @ResponseBody
    public List<FileDetailsList> getFiles() {
        return fileListService.getlistOfFileDetails();
    }

}
