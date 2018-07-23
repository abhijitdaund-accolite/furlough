package com.accolite.furlough.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

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

import com.accolite.furlough.entity.MSEmployee;
import com.accolite.furlough.repository.MSEmployeeRepository;
import com.accolite.furlough.utils.Constants;

@Service
public class FileStorageService {

    final DataFormatter formatter = new DataFormatter();

    @Autowired
    private MSEmployeeRepository msEmployeeRepository;

    @Autowired
    private ParserService parserService;
    private final static Logger log = LoggerFactory.getLogger(FileStorageService.class);
    private final Path rootLocation = Paths.get(Constants.ROOT_PATH + Constants.UPLOAD_DIR);

    public void store(final MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
            final String finalString = rootLocation.toString() + Constants.URL_SEP + file.getOriginalFilename();
            parserService.mapExcelToHashmap(finalString);
        } catch (final Exception e) {
            log.error("Failed to store the file. Error: " + e.getMessage());
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

}
