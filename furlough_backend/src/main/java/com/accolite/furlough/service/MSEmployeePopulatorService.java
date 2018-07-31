package com.accolite.furlough.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.furlough.entity.MSEmployee;
import com.accolite.furlough.repository.MSEmployeeRepository;

@Service
public class MSEmployeePopulatorService {

    final DataFormatter formatter = new DataFormatter();
    private final static Logger log = LoggerFactory.getLogger(FileStorageService.class);

    @Autowired
    private MSEmployeeRepository msEmployeeRepository;

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
                        formatter.formatCellValue(row.getCell(1)), formatter.formatCellValue(row.getCell(3)),
                        formatter.formatCellValue(row.getCell(4)), formatter.formatCellValue(row.getCell(5)), true, "");
                msEmployeeRepository.save(msEmployee);
            }
            myWorkBook.close();
            fis.close();

        } catch (final IOException e) {
            log.error("Error in reading file from system with error message " + e.getMessage());
        }
    }

}
