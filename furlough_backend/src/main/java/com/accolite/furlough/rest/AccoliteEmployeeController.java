package com.accolite.furlough.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.furlough.entity.AccoliteEmployee;
import com.accolite.furlough.repository.AccoliteEmployeeRepository;

@RestController
public class AccoliteEmployeeController {

	@Autowired
	private AccoliteEmployeeRepository accoliteEmployeeRepository;
	
	
	@RequestMapping("/employee")
	public List<AccoliteEmployee> getAllEmployees(){
		
		return this.accoliteEmployeeRepository.findAll();
	}
	
	
	
	
	
}
