package com.accolite.furlough.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.accolite.furlough.utils.FurloughStatus;
import com.accolite.furlough.utils.FurloughRequestsIDTracker;

@Entity
@Table(name="furlough_requests")
public class FurloughRequests {

	@Id
	private FurloughRequestsIDTracker furloughID;
	
	@Column(name="FURLOUGH_STATUS")
	private FurloughStatus furloughStatus;
	
	@Column(name="MAIL_SENT")
	private Boolean mailSent;
	
	@Column(name="RESOURCE_CONFIRMED")
	private Boolean resourceConfirmed;
	
}
