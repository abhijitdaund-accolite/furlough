package com.accolite.furlough.service.api;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.accolite.furlough.repository.FurloughRequestsRepository;
import com.accolite.furlough.repository.MSEmployeeRepository;

@Service
public class ReportsService {

	@Autowired
	private FurloughRequestsRepository furloughRequestsRepository;
	
	@Autowired
	private MSEmployeeRepository msEmployeeRepository;
	
	
}
