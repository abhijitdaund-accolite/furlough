package com.accolite.furlough.dto;

import java.util.Date;

public class DateAndOverlap {

	private Date furloughDate;
	private String overlap;
	
	
	public DateAndOverlap(Date furloughDate, String overlap) {
		super();
		this.furloughDate = furloughDate;
		this.overlap = overlap;
	}
	
	public Date getFurloughDate() {
		return furloughDate;
	}
	public void setFurloughDate(Date furloughDate) {
		this.furloughDate = furloughDate;
	}
	public String getOverlap() {
		return overlap;
	}
	public void setOverlap(String overlap) {
		this.overlap = overlap;
	}
	
	
	
}
