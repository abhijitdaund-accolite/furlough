package com.accolite.furlough.dto;

import com.accolite.furlough.utils.FurloughRequestsIDTracker;

public class FurloughRequestDTO {

	private FurloughRequestsIDTracker furlouughId;
	private String furloughStatus;
	private Boolean mailSent;
	private Boolean resourceConfirmed;
	public FurloughRequestsIDTracker getFurlouughId() {
		return furlouughId;
	}
	public void setFurlouughId(FurloughRequestsIDTracker furlouughId) {
		this.furlouughId = furlouughId;
	}
	public String getFurloughStatus() {
		return furloughStatus;
	}
	public void setFurloughStatus(String furloughStatus) {
		this.furloughStatus = furloughStatus;
	}
	public Boolean getMailSent() {
		return mailSent;
	}
	public void setMailSent(Boolean mailSent) {
		this.mailSent = mailSent;
	}
	public Boolean getResourceConfirmed() {
		return resourceConfirmed;
	}
	public void setResourceConfirmed(Boolean resourceConfirmed) {
		this.resourceConfirmed = resourceConfirmed;
	}
	
}
