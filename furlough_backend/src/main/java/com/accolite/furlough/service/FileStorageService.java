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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

// import com.accolite.furlough.entity.AccoliteEmployee;
import com.accolite.furlough.entity.FurloughData;
import com.accolite.furlough.entity.FurloughLog;
import com.accolite.furlough.entity.FurloughRequests;
import com.accolite.furlough.entity.MSEmployee;
import com.accolite.furlough.parserinput.SendJavaMail;
// import com.accolite.furlough.repository.AccoliteEmployeeRepository;
import com.accolite.furlough.repository.FurloughLogRepository;
import com.accolite.furlough.repository.FurloughRequestsRepository;
import com.accolite.furlough.repository.MSEmployeeRepository;
import com.accolite.furlough.utils.Constants;
import com.accolite.furlough.utils.FurloughRequestsIDTracker;

@Service
public class FileStorageService {

    final DataFormatter formatter = new DataFormatter();

    @Autowired
    private FurloughRequestsRepository furloughRequestsRepository;

    @Autowired
    private FurloughLogRepository furloughLogRepository;

    @Autowired
    private MSEmployeeRepository msEmployeeRepository;

    private final static Logger log = LoggerFactory.getLogger(FileStorageService.class);
    private final Path rootLocation = Paths.get(Constants.ROOT_PATH + Constants.UPLOAD_DIR);

    public void store(final MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
            final String finalString = rootLocation.toString() + Constants.URL_SEP + file.getOriginalFilename();
            mapExcelToHashmap(finalString);
        } catch (final Exception e) {
            log.error("Failed to store the file. Error: " + e.getMessage());
        }
    }

    public Map<String, FurloughData> mapExcelToHashmap(final String location) throws InterruptedException {

        try {
            final List<FurloughLog> listFurloughLog = new ArrayList<FurloughLog>();
            final Map<String, FurloughData> map = new HashMap<String, FurloughData>();
            final File inputExcel = new File(location);
            final FileInputStream fis = new FileInputStream(inputExcel);
            final HSSFWorkbook myWorkBook = new HSSFWorkbook(fis);
            final HSSFSheet furloughSheet = myWorkBook.getSheetAt(0);
            final Iterator<Row> rowIterator = furloughSheet.iterator();
            while (rowIterator.hasNext()) {
                final Row row = rowIterator.next();
                if (row.getCell(0) == null) // To break the moment we are done with rows having data
                    break;
                if (formatter.formatCellValue(row.getCell(0)).equals("MSID")) // Skipping the first header row
                    continue;
                final FurloughLog furloughLog = new FurloughLog();
                final Date furloughDate = new SimpleDateFormat(Constants.DATE_FORMAT)
                        .parse(formatter.formatCellValue(row.getCell(3)));

                furloughLog.setmSID(formatter.formatCellValue((row.getCell(0))));
                furloughLog.setFurloughStatus(formatter.formatCellValue(row.getCell(4)));
                furloughLog.setFurloughDate(furloughDate);
                furloughLog.setLogTime(new Date());

                listFurloughLog.add(furloughLog);

                // If the employee has already been parsed in a previous row, we just update/add
                // FurloughDate and Status
                if (map.containsKey(formatter.formatCellValue(row.getCell(0)))) {
                    final FurloughData tempFurlough = map.get(formatter.formatCellValue(row.getCell(0)));
                    final Map<Date, String> dateMap = tempFurlough.getFurloughDates();
                    dateMap.put(furloughDate, formatter.formatCellValue(row.getCell(4)));
                    tempFurlough.setFurloughDates(dateMap);

                    map.put(tempFurlough.getMSID(), tempFurlough);
                }

                // Employee found in the excel file for the first time. So adding it to Java Map
                else {
                    final FurloughData furlough = new FurloughData();

                    furlough.setMSID(formatter.formatCellValue(row.getCell(0)));
                    furlough.setResourceName(formatter.formatCellValue(row.getCell(1)));
                    furlough.setVendorName(formatter.formatCellValue(row.getCell(2)));

                    final Map<Date, String> dateMap = new HashMap<Date, String>();
                    dateMap.put(furloughDate, formatter.formatCellValue(row.getCell(4)));
                    furlough.setFurloughDates(dateMap);

                    furlough.setDivision(formatter.formatCellValue(row.getCell(5)));
                    furlough.setLocation(formatter.formatCellValue(row.getCell(6)));
                    furlough.setComments(formatter.formatCellValue(row.getCell(7)));

                    map.put(furlough.getMSID(), furlough);
                }

            }
            myWorkBook.close();
            fis.close();

            updateDB(listFurloughLog, map);
            sendMails(map);
            return map;

        } catch (final IOException e) {
            log.error("Error in reading file from system with error message " + e.getMessage());
        } catch (final ParseException e) {
            log.error("Error in parsing date with error message " + e.getMessage());
        }
        return null;
    }

    private void sendMails(final Map<String, FurloughData> map) throws IOException {

        final List<String> dateList = new ArrayList<String>();

        for (final Entry<String, FurloughData> entry : map.entrySet()) {

            String finalString = "Dear " + entry.getValue().getResourceName() + "\n\n";
            final String mailStart = "This is start of mail \n\n";
            finalString = finalString + mailStart;
            final String mailEnd = "\nThis is end of mail";

            String email = "raunak.maheshwari@accoliteindia.com";
            if (msEmployeeRepository.findById(entry.getValue().getMSID()).isPresent())
                email = msEmployeeRepository.findById(entry.getValue().getMSID()).get().getEmail();
            if (email.equals("N/A"))
                email = "raunak.maheshwari@accoliteindia.com";

            final Map<Date, String> requestDates = entry.getValue().getFurloughDates();
            dateList.clear();
            for (final Entry<Date, String> dateEntry : requestDates.entrySet()) {
                if (dateEntry.getValue().equals("PLANNED")) {
                    dateList.add(dateEntry.getKey().toString());
                }
            }
            final Iterator<String> it = dateList.iterator();
            while (it.hasNext())
                finalString = finalString + it.next() + "\n";
            finalString = finalString + mailEnd;
            System.out.println(finalString + "\n\n");

            final SendJavaMail m = new SendJavaMail(email, finalString);
            m.sendJavaMail();
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
            log.error("Failed to load the file Error: " + e.getMessage());
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
            log.error("Could not initialize storage Error: " + e.getMessage());
        }
    }

    public FurloughRequests requestInserter(final FurloughRequests request) {

        if (furloughRequestsRepository.existsById(request.getFurloughID())) {
            final FurloughRequests requestToBeUpdated = furloughRequestsRepository.getOne(request.getFurloughID());
            requestToBeUpdated.setFurloughStatus(request.getFurloughStatus());
            return furloughRequestsRepository.save(requestToBeUpdated);
        } else {
            return furloughRequestsRepository.save(request);
        }
    }

    public void populateMSEmployees(final String location) {
        try {
            final File inputExcel = new File(location);
            final FileInputStream fis = new FileInputStream(inputExcel);
            final HSSFWorkbook myWorkBook = new HSSFWorkbook(fis);
            final HSSFSheet furloughSheet = myWorkBook.getSheetAt(0);
            final Iterator<Row> rowIterator = furloughSheet.iterator();

            while (rowIterator.hasNext()) {
                final Row row = rowIterator.next();
                if (row.getCell(0) == null) // To break the moment we are done with rows having data
                    break;
                if (formatter.formatCellValue(row.getCell(0)).equals("MSID")) // Skipping the first header row
                    continue;

                final MSEmployee msEmployee = new MSEmployee(formatter.formatCellValue(row.getCell(0)),
                        formatter.formatCellValue(row.getCell(1)), formatter.formatCellValue(row.getCell(2)),
                        formatter.formatCellValue(row.getCell(3)), formatter.formatCellValue(row.getCell(4)),
                        formatter.formatCellValue(row.getCell(5)), formatter.formatCellValue(row.getCell(6)));
                msEmployeeRepository.save(msEmployee);
            }
            myWorkBook.close();
            fis.close();

        } catch (final IOException e) {
            log.error("Error in reading file from system with error message " + e.getMessage());
        }
    }

    public void updateDB(final List<FurloughLog> logList, final Map<String, FurloughData> map) {

        for (final Entry<String, FurloughData> entry : map.entrySet()) {
            final FurloughData tempObj = entry.getValue();
            final Map<Date, String> requestDates = tempObj.getFurloughDates();
            for (final Entry<Date, String> entry1 : requestDates.entrySet()) {

                final FurloughRequests fRequest = new FurloughRequests();
                fRequest.setMailSent(false);
                fRequest.setFurloughStatus(entry1.getValue());
                fRequest.setResourceConfirmed(false);
                final FurloughRequestsIDTracker fur = new FurloughRequestsIDTracker(entry.getKey(), entry1.getKey());

                fRequest.setFurloughID(fur);

                requestInserter(fRequest);
            }
        }

        final Iterator<FurloughLog> iterator = logList.iterator();
        while (iterator.hasNext()) {
            furloughLogRepository.save(iterator.next());
        }
    }
}
