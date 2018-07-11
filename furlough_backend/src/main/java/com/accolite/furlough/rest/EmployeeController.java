package com.accolite.furlough.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.furlough.dto.EmployeeDetailsResponse;
import com.accolite.furlough.repository.AccoliteEmployeeRepository;
import com.accolite.furlough.repository.MSEmployeeRepository;

@RestController
public class EmployeeController {

    @Autowired
    private MSEmployeeRepository msEmployeeRepository;

    @Autowired
    private AccoliteEmployeeRepository accoliteEmployeeRepository;

    // @RequestMapping("/employees")
    // @ResponseBody
    // public List<MSEmployee> getAllEmployees() {
    // // final List<MSEmployee> list = this.msEmployeeRepository.findAll();
    // // return list;
    // return null;
    // }
    @GetMapping("/employees/{msID}")
    @ResponseBody
    public EmployeeDetailsResponse getEmployeeDetails(@PathVariable final String msID) {
        final EmployeeDetailsResponse response = new EmployeeDetailsResponse();
        response.setAccoliteEmployee(accoliteEmployeeRepository.findByMSID(msID));
        response.setMsEmployee(msEmployeeRepository.findById(msID).get());
        return response;
    }

}
