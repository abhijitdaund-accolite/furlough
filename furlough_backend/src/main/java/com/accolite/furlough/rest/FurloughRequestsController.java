package com.accolite.furlough.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import static org.junit.Assume.assumeNoException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.furlough.dto.DateAndOverlap;
import com.accolite.furlough.dto.FurloughReport;
import com.accolite.furlough.entity.FurloughRequests;
import com.accolite.furlough.entity.MSEmployee;
import com.accolite.furlough.repository.FurloughRequestsRepository;
import com.accolite.furlough.repository.MSEmployeeRepository;
import com.accolite.furlough.utils.FurloughRequestsIDTracker;

@RestController
public class FurloughRequestsController {

	@Autowired
	private FurloughRequestsRepository furloughRequestRepository; 
	
	@Autowired
	private MSEmployeeRepository msEmployeeRepository;
	
	@RequestMapping("/logsrange/{from}/{to}")
	public List<FurloughReport> getRequestInDateRange(@PathVariable String from ,@PathVariable String to)  {
		
		SimpleDateFormat parser =new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date sDate;
		java.util.Date eDate;
		
		//System.out.println(from.toString());
		//System.out.println(to.toString());
		
		try {
			
			sDate = parser.parse(from);
			eDate = parser.parse(to);
			
		} catch (ParseException e) {
			return null;
		}
		
		
//		java.sql.Date startDate = new java.sql.Date(sDate.getTime());
//		java.sql.Date endDate = new java.sql.Date(eDate.getTime());
//
//		System.out.println(startDate);
//		System.out.println(endDate);
		
		
		
//	    List<FurloughRequests> totalFurloughsInRange= this.furloughRequestRepository.findRequestsByDateRange(startDate,endDate);
		List<FurloughRequests> totalFurloughsInRange= this.furloughRequestRepository.findByFurloughID_furloughDateBetween(sDate,eDate);
	    Set<String> distinctMSIDs=new HashSet();
	    List<FurloughReport> listReports = new ArrayList();
	    
	    
	   
	    
	    Iterator<FurloughRequests> itr = totalFurloughsInRange.iterator();
	    Map<String,ArrayList<DateAndOverlap> > datesMap =new HashMap();
	    
	    while(itr.hasNext())
	    {
	    	FurloughRequests requests= itr.next();
	    	System.out.println(requests);
	    	
	    	FurloughRequestsIDTracker idTracker = requests.getFurloughID();
		    distinctMSIDs.add(idTracker.getmSID());
		    
		    if(!datesMap.containsKey(idTracker.getmSID()))
		    		datesMap.put(idTracker.getmSID(), new ArrayList<DateAndOverlap>());
		    
		    List<DateAndOverlap> curentDateAndOverlap = datesMap.get(idTracker.getmSID());
		    curentDateAndOverlap.add(new DateAndOverlap(idTracker.getFurloughDate(),requests.getFurloughStatus()));
		    datesMap.put(idTracker.getmSID() , (ArrayList<DateAndOverlap>) curentDateAndOverlap);
	    }
	    
	    Iterator<String> itrMSIDs = distinctMSIDs.iterator();
	    
	    while(itrMSIDs.hasNext()) {
	    	
	    	String MSID=itrMSIDs.next();
	    	System.out.println(MSID);
//	    	MSEmployee emp = this.msEmployeeRepository.findById(MSID).get();
//	    	FurloughReport report=new FurloughReport(
//	    			emp.getMSID(),
//	    			datesMap.get(MSID),
//	    			emp.getResourceName(),
//	    			emp.getVendorName()
//	    			
//	    			);
//	    	
//	    	listReports.add(report);
	    	if(msEmployeeRepository.existsById(MSID)) {
	    		MSEmployee emp = this.msEmployeeRepository.findById(MSID).get();
		    	FurloughReport report=new FurloughReport(
		    			emp.getMSID(),
		    			datesMap.get(MSID),
		    			emp.getResourceName(),
		    			emp.getVendorName()
		    			
		    			);
		    	
		    	listReports.add(report);

	    	}
//	    	msEmployeeRepository.findById(MSID).map(emp -> {
//	    		FurloughReport report=new FurloughReport(
//		    			emp.getMSID(),
//		    			datesMap.get(MSID),
//		    			emp.getResourceName(),
//		    			emp.getVendorName()
//		    			
//		    			);
//		    	
//		    	listReports.add(report);
//	    	});
	    }
	    
	    
	    return listReports;
		
	}
}
