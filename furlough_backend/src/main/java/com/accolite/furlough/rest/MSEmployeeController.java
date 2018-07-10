package com.accolite.furlough.rest;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.furlough.entity.AccoliteEmployee;
import com.accolite.furlough.entity.MSEmployee;
import com.accolite.furlough.repository.MSEmployeeRepository;

@RestController
public class MSEmployeeController {

	@Autowired
	private MSEmployeeRepository msEmployeeRepository;
	
	
	@RequestMapping(value="/ms_employees")
	public List<MSEmployee> getEmployees(){
		
		return this.msEmployeeRepository.findAll();
	}
	
	@RequestMapping(value="/ms_employee/{id}")
	public MSEmployee getEmployee(@PathVariable("id") String msid) {
		
		return this.msEmployeeRepository.findById(msid).get();
	}

}
