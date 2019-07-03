package com.accolite.furlough.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.furlough.entity.FurloughData;
import com.accolite.furlough.entity.FurloughLog;
import com.accolite.furlough.utils.Constants;

@Service
public class ParserService {

    final DataFormatter formatter = new DataFormatter();
    private final static Logger log = LoggerFactory.getLogger(FileStorageService.class);

    @Autowired
    private DbUpdaterService dbUpdater;

    @Autowired
    private MailService mailer;

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

            dbUpdater.updateDB(listFurloughLog, map);
            mailer.sendMails(map);
            return map;

        } catch (final IOException e) {
            log.error("Error in reading file from system with error message " + e.getMessage());
        } catch (final ParseException e) {
            log.error("Error in parsing date with error message " + e.getMessage());
        }
        return null;
    }

}
