package com.accolite.furlough.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.accolite.furlough.entity.FurloughData;
import com.accolite.furlough.entity.FurloughLog;
import com.accolite.furlough.entity.FurloughRequests;
import com.accolite.furlough.repository.FurloughRequestsRepository;
import com.accolite.furlough.utils.Constants;
import com.accolite.furlough.utils.FurloughRequestsIDTracker;

@Service
public class FileStorageService {

    @Autowired
    private FurloughRequestsRepository furloughRequestsRepository;

    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    private final Path rootLocation = Paths.get(Constants.UPLOAD_DIR);

    public void store(final MultipartFile file) {
        try {
            System.out.println("Creating furloughRequestsRepository object in store : " + furloughRequestsRepository);
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));

            final String finalString = rootLocation.toString() + "\\" + file.getOriginalFilename();
            mapExcelToHashmap(finalString);
        } catch (final Exception e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public Map<String, FurloughData> mapExcelToHashmap(final String location) throws InterruptedException {

        try {
            final Map<String, FurloughData> map = new HashMap<String, FurloughData>();
            final File inputExcel = new File(Constants.ROOT_PATH + location);
            final FileInputStream fis = new FileInputStream(inputExcel);
            final HSSFWorkbook myWorkBook = new HSSFWorkbook(fis);
            final HSSFSheet furloughSheet = myWorkBook.getSheetAt(0);

            final Iterator<Row> rowIterator = furloughSheet.iterator();
            while (rowIterator.hasNext()) {
                final Row row = rowIterator.next();
                if (row.getCell(0) == null) // To break the moment we are done with rows having data
                    break;
                if (row.getCell(0).toString().equals("MSID")) // Skipping the first header row
                    continue;

                // If the employee has already been parsed in a previous row, we just update/add
                // FurloughDate and Status
                if (map.containsKey(row.getCell(0).toString())) {
                    final FurloughData tempFurlough = map.get(row.getCell(0).toString());

                    final Date furloughDate = new SimpleDateFormat("MM/dd/yyyy").parse(row.getCell(3).toString());
                    final Map<Date, String> dateMap = tempFurlough.getFurloughDates();
                    dateMap.put(furloughDate, row.getCell(4).toString());
                    tempFurlough.setFurloughDates(dateMap);

                    map.put(tempFurlough.getMSID(), tempFurlough);
                }

                // Employee found in the excel file for the first time. So adding it to Java Map
                else {
                    final FurloughData furlough = new FurloughData();

                    furlough.setMSID(row.getCell(0).toString());
                    furlough.setResourceName(row.getCell(1).toString());
                    furlough.setVendorName(row.getCell(2).toString());

                    final Date furloughDate = new SimpleDateFormat(Constants.DATE_FORMAT)
                            .parse(row.getCell(3).toString());
                    final Map<Date, String> dateMap = new HashMap<Date, String>();
                    dateMap.put(furloughDate, row.getCell(4).toString());
                    furlough.setFurloughDates(dateMap);

                    furlough.setDivision(row.getCell(5).toString());
                    furlough.setLocation(row.getCell(6).toString());
                    furlough.setComments(row.getCell(7).toString());

                    map.put(furlough.getMSID(), furlough);
                }

            }
            myWorkBook.close();
            final ParseInput inp = new ParseInput();
            inp.printMapDetails(map);
            System.out.println("Object is : " + furloughRequestsRepository);
            updateDB(null, map);
            return map;

        } catch (final IOException e) {
            System.out.println("Error in reading file from system with error message " + e.getMessage());
            e.printStackTrace();
        } catch (final ParseException e) {
            System.out.println("Error in parsing date with error message " + e.getMessage());
            e.printStackTrace();
        }
        return null;
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

    public FurloughRequests requestInserter(final FurloughRequests request) {

        return furloughRequestsRepository.findById(request.getFurloughID()).map(currentRequest -> {
            currentRequest.setFurloughStatus(request.getFurloughStatus());
            return furloughRequestsRepository.save(currentRequest);
        }).orElse(furloughRequestsRepository.save(request));

    }

    // public FurloughLog logInserter( FurloughLog request) {
    // return furloughLogRepository.save(request);
    // }

    public FurloughRequests updateDB(final List<FurloughLog> logList, final Map<String, FurloughData> map) {

        for (final Entry<String, FurloughData> entry : map.entrySet()) {
            final FurloughData tempObj = entry.getValue();
            System.out.println(tempObj);
            final Map<Date, String> requestDates = tempObj.getFurloughDates();
            for (final Entry<Date, String> entry1 : requestDates.entrySet()) {

                final FurloughRequests fRequest = new FurloughRequests();
                fRequest.setMailSent(false);
                fRequest.setFurloughStatus(entry1.getValue());
                fRequest.setResourceConfirmed(false);
                final FurloughRequestsIDTracker fur = new FurloughRequestsIDTracker(entry.getKey(), entry1.getKey());

                fRequest.setFurloughID(fur);
                System.out.println(fRequest);
                requestInserter(fRequest);
            }
        }
        return null;
    }
}
