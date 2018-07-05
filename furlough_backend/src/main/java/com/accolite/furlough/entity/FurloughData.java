package com.accolite.furlough.entity;

import java.util.Date;
import java.util.Map;

public class FurloughData {
	
	@Override
	public String toString() {
		return "FurloughData [\nMSID=" + MSID + "\nrsourceName=" + resourceName + "\nvendorName=" + vendorName
				+ "\nfurloughDates=" + furloughDates + "\ndivision=" + division + "\nlocation=" + location
				+ "\ncomments=" + comments + "\n]";
	}
	
	private String MSID;
	private String resourceName;
	private String vendorName;
	private Map<Date, String> furloughDates;
	private String division;
	private String location;
	private String comments;
	
	public String getMSID() {
		return MSID;
	}
	public void setMSID(String mSID) {
		MSID = mSID;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Map<Date, String> getFurloughDates() {
		return furloughDates;
	}
	public void setFurloughDates(Map<Date, String> furloughDates) {
		this.furloughDates = furloughDates;
	}
}
