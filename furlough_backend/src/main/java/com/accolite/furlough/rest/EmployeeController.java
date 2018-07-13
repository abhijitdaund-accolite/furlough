package com.accolite.furlough.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.furlough.entity.MSEmployee;
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
    @GetMapping("/employees/{msID}")
    @ResponseBody
    public MSEmployee getEmployeeDetails(@PathVariable final String msID) {
        final MSEmployee msEmployee;
        msEmployee = msEmployeeRepository.getOne(msID);
        return msEmployee;
    }

}
