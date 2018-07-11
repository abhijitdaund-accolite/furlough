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

    @Autowired
    private AccoliteEmployeeRepository accoliteEmployeeRepository;

    @Autowired
    FileStorageService filestorageService;

    @RequestMapping(value = "/accolite/populate")
    public void populateAccoliteEmployees() {
        final String location = "";
        // filestorageService.populateMSEmployees(location);
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
