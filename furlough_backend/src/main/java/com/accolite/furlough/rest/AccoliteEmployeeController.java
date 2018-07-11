package com.accolite.furlough.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.furlough.entity.AccoliteEmployee;
import com.accolite.furlough.repository.AccoliteEmployeeRepository;
import com.accolite.furlough.service.FileStorageService;

@RestController
public class AccoliteEmployeeController {
    final String location = "C:\\Users\\Raunak.Maheshwari\\Documents\\hrdata.xls";

    @Autowired
    private AccoliteEmployeeRepository accoliteEmployeeRepository;

    @Autowired
    FileStorageService filestorageService;

    @RequestMapping(value = "/accolite/populate")
    public String populateAccoliteEmployees() {
        filestorageService.populateAccoliteEmployees(location);
        return "Populated Accolite Employees successfully!";
    }

    @RequestMapping(value = "/accolite_employees")
    @ResponseBody
    public List<AccoliteEmployee> getAllAccoliteEmployees() {
        return this.accoliteEmployeeRepository.findAll();
    }

    @RequestMapping(value = "/accolite_employees/{id}")
    public AccoliteEmployee getAccoliteEmployee(@PathVariable("id") final String id) {
        return this.accoliteEmployeeRepository.findById(id).get();
    }
}
