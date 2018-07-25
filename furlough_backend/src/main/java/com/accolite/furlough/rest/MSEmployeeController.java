package com.accolite.furlough.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    final String location = Constants.HRDATA_FIL_LOCATION;

    @Autowired
    private MSEmployeeRepository msEmployeeRepository;

    @Autowired
    private MSEmployeePopulatorService populator;

    @RequestMapping(value = "/ms/populate")
    public String populateMSEmployees() {
        populator.populateMSEmployees(location);
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

    @PostMapping("/ms_employees")
    @ResponseBody
    public MSEmployee postEmployee(@RequestBody final MSEmployee msEmployee) {
        return msEmployeeRepository.save(msEmployee);
    }

    @DeleteMapping("/ms_employees/{mSID}")
    public ResponseEntity<?> deleteMSEmployee(@PathVariable final String mSID) {

        if (msEmployeeRepository.existsById(mSID)) {
            final MSEmployee employee = msEmployeeRepository.findById(mSID).get();
            msEmployeeRepository.delete(employee);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/ms_employees/{mSID}")
    public MSEmployee updateMSEmployee(@PathVariable final String mSID,
            @Valid @RequestBody final MSEmployee msEmployeeRequest) {
        if (msEmployeeRepository.existsById(mSID)) {
            final MSEmployee employee = msEmployeeRepository.findById(mSID).get();
            employee.setmSID(msEmployeeRequest.getmSID());
            employee.setAccoliteEmployee(msEmployeeRequest.getAccoliteEmployee());
            employee.setEmail(msEmployeeRequest.getEmail());
            employee.setOfficeLocation(msEmployeeRequest.getOfficeLocation());
            employee.setResourceName(msEmployeeRequest.getResourceName());
            return msEmployeeRepository.save(employee);
        }
        return null;
    }

}
