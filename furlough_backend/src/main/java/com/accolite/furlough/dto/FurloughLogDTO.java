package com.accolite.furlough.dto;

import java.util.Date;

public class FurloughLogDTO {
	private String requestID;
	private String mSID;
	private Date furloughDate;
	private String furloughStatus;
	private Date logTime;
	public String getRequestID() {
		return requestID;
	}
	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}
	public String getmSID() {
		return mSID;
	}
	public void setmSID(String mSID) {
		this.mSID = mSID;
	}
	public Date getFurloughDate() {
		return furloughDate;
	}
	public void setFurloughDate(Date furloughDate) {
		this.furloughDate = furloughDate;
	}
	public String getFurloughStatus() {
		return furloughStatus;
	}
	public void setFurloughStatus(String furloughStatus) {
		this.furloughStatus = furloughStatus;
	}
	public Date getLogTime() {
		return logTime;
	}
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	
}
