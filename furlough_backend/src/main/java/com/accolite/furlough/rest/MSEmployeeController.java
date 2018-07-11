package com.accolite.furlough.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.furlough.entity.MSEmployee;
import com.accolite.furlough.repository.MSEmployeeRepository;
import com.accolite.furlough.service.FileStorageService;

@RestController
public class MSEmployeeController {
    final String location = "C:\\Users\\Raunak.Maheshwari\\Documents\\hrdata.xls";

    @Autowired
    private MSEmployeeRepository msEmployeeRepository;

    @Autowired
    private FileStorageService filestorageService;

    @RequestMapping(value = "/ms/populate")
    public String populateMSEmployees() {
        filestorageService.populateMSEmployees(location);
        return "Populated MS Employees successfully!";
    }

    @RequestMapping(value = "/ms_employees")
    @ResponseBody
    public List<MSEmployee> getAllMSEmployees() {
        return this.msEmployeeRepository.findAll();
    }

    @RequestMapping(value = "/ms_employees/{id}")
    @ResponseBody
    public MSEmployee getMSEmployee(@PathVariable("id") final String msid) {

        return msEmployeeRepository.findById(msid).get();
    }

}
