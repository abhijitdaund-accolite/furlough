package com.accolite.furlough.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.accolite.furlough.dto.FileDetailsList;
import com.accolite.furlough.utils.Constants;

@Service
public class FileListService {

    List<FileDetailsList> fileDetails = new ArrayList<>();
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    public List<FileDetailsList> getlistOfFileDetails() {
        final File[] listOfFiles = new File(Constants.ROOT_PATH + Constants.UPLOAD_DIR).listFiles();
        for (final File file : listOfFiles) {
            final FileDetailsList fdl = new FileDetailsList(file.getName(), new Date(file.lastModified() * 1000));
            fileDetails.add(fdl);
        }
        return fileDetails;
    }

}
