package com.accolite.furlough.dto;

import java.util.List;

public class FurloughReport {

	private String MSID;
	private List<DateAndOverlap> dateAndOverlaps;
	private String resourceName;
	private String vendorName;
	
	
	public FurloughReport(String mSID, List<DateAndOverlap> dateAndOverlaps, String resourceName, String vendorName) {
		super();
		MSID = mSID;
		this.dateAndOverlaps = dateAndOverlaps;
		this.resourceName = resourceName;
		this.vendorName = vendorName;
	}
	@Override
	public String toString() {
		return "FurloughReport [MSID=" + MSID + ", dateAndOverlaps=" + dateAndOverlaps + ", resourceName="
				+ resourceName + ", vendorName=" + vendorName + "]";
	}
	public String getMSID() {
		return MSID;
	}
	public void setMSID(String mSID) {
		MSID = mSID;
	}
	public List<DateAndOverlap> getDateAndOverlaps() {
		return dateAndOverlaps;
	}
	public void setDateAndOverlaps(List<DateAndOverlap> dateAndOverlaps) {
		this.dateAndOverlaps = dateAndOverlaps;
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
	
	
	
}
