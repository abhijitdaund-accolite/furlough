package com.accolite.furlough.utils;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FurloughRequestsIDTracker implements Serializable{

	@Column(name="MS_ID")
	private String msId;
	
	@Column(name="FURLOUGH_DATE")
	private Date furloughDate;
	
	public FurloughRequestsIDTracker(String msId,Date furloughDate) {
		this.furloughDate=furloughDate;
		this.msId=msId;
	}

	public String getMsId() {
		return msId;
	}

	public void setMsId(String msId) {
		this.msId = msId;
	}

	public Date getFurloughDate() {
		return furloughDate;
	}

	public void setFurloughDate(Date furloughDate) {
		this.furloughDate = furloughDate;
	}
	
	
	@Override
	public boolean equals(Object arg0) {
		if(arg0 == null) {return (Boolean) null;}
		if(!(arg0 instanceof FurloughRequestsIDTracker)) {return false;}
		FurloughRequestsIDTracker arg1=(FurloughRequestsIDTracker) arg0;
		
		return this.getMsId()==arg1.getMsId() && this.getFurloughDate()==arg1.getFurloughDate();
	}
	
	/*@Override
	public int hashCode() {
		//TODO code to calculate HashCode PRIORITY HIGH
	}*/
	
}
