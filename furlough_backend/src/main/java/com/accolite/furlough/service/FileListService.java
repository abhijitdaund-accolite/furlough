package com.accolite.furlough.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.accolite.furlough.dto.FileDetailsList;
import com.accolite.furlough.utils.Constants;

@Service
public class FileListService {

    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    public List<FileDetailsList> getlistOfFileDetails() {
        final List<FileDetailsList> fileDetails = new ArrayList<>();
        final File[] listOfFiles = new File(Constants.ROOT_PATH + Constants.UPLOAD_DIR).listFiles();
        for (final File file : listOfFiles) {
            final FileDetailsList fdl = new FileDetailsList(file.getName(), new Date(file.lastModified()));
            fileDetails.add(fdl);
        }
        Collections.sort(fileDetails, new Comparator<FileDetailsList>() {
            public int compare(final FileDetailsList m1, final FileDetailsList m2) {
                return m1.getLastModified().compareTo(m2.getLastModified());
            }
        });
        Collections.reverse(fileDetails);
        return fileDetails;
    }

}
