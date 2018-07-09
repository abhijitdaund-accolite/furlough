package com.accolite.furlough.parserinput;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import com.accolite.furlough.entity.FurloughData;
import com.accolite.furlough.utils.Constants;

public class ParseInput {

    // final private static String filePath =
    // "C:\\Users\\Raunak.Maheshwari\\file.xls";
    final private static String dateFormat = "MM/dd/yyyy";
    Map<String, FurloughData> map = new HashMap<String, FurloughData>();

    public Map<String, FurloughData> mapExcelToHashmp(final String location) {

        try {
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

                    final Date furloughDate = new SimpleDateFormat(dateFormat).parse(row.getCell(3).toString());
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

    public void printMapDetails(final Map<String, FurloughData> map) {
        for (final Entry<String, FurloughData> entry : map.entrySet())
            System.out.println("MSID : " + entry.getKey() + "\nEmployeeDetails:\n" + entry.getValue());
    }

    /*
     * public static void main(final String[] args) { final ParseInput input = new
     * ParseInput(); input.printMapDetails(input.mapExcelToHashmp()); }
     */

}
