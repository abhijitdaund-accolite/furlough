package com.accolite.furlough.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.furlough.entity.MSEmployee;
import com.accolite.furlough.repository.MSEmployeeRepository;
import com.accolite.furlough.service.MSEmployeePopulatorService;
import com.accolite.furlough.utils.Constants;

@RestController
public class MSEmployeeController {
    static final String LOCATION = Constants.HRDATA_FIL_LOCATION;

    @Autowired
    private MSEmployeeRepository msEmployeeRepository;

    @Autowired
    private MSEmployeePopulatorService populator;

    @RequestMapping(value = "/ms/populate")
    public String populateMSEmployees() {
        populator.populateMSEmployees(LOCATION);
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
        final Optional<MSEmployee> msEmployeeOptional = msEmployeeRepository.findById(msid);
        if (msEmployeeOptional.isPresent()) {
            return msEmployeeOptional.get();
        } else {
            return null;
        }
    }

    @PostMapping("/ms_employees")
    @ResponseBody
    public MSEmployee postEmployee(@RequestBody final MSEmployee msEmployee) {
        return msEmployeeRepository.save(msEmployee);
    }

    @PutMapping("/ms_employees/{mSID}")
    public MSEmployee updateMSEmployee(@PathVariable final String mSID,
            @Valid @RequestBody final MSEmployee msEmployeeRequest) {
        final Optional<MSEmployee> msEmployeeOptional = msEmployeeRepository.findById(mSID);
        if (msEmployeeOptional.isPresent()) {
            final MSEmployee employee = msEmployeeOptional.get();
            msEmployeeRepository.delete(employee);
            final MSEmployee modifiedEmployee = new MSEmployee();
            modifiedEmployee.setmSID(msEmployeeRequest.getmSID());
            modifiedEmployee.setAccoliteEmployee(msEmployeeRequest.getAccoliteEmployee());
            modifiedEmployee.setEmail(msEmployeeRequest.getEmail());
            modifiedEmployee.setOfficeLocation(msEmployeeRequest.getOfficeLocation());
            modifiedEmployee.setResourceName(msEmployeeRequest.getResourceName());
            modifiedEmployee.setActive(msEmployeeRequest.isActive());
            return msEmployeeRepository.save(modifiedEmployee);
        }
        return null;
    }

}
