package com.accolite.furlough.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.furlough.repository.MSEmployeeRepository;

@RestController
public class EmployeeController {

    @Autowired
    private MSEmployeeRepository msEmployeeRepository;

    // @RequestMapping("/employees")
    // @ResponseBody
    // public List<MSEmployee> getAllEmployees() {
    // // final List<MSEmployee> list = this.msEmployeeRepository.findAll();
    // // return list;
    // return null;
    // }

}
