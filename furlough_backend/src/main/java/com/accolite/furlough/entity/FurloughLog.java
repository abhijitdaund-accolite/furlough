package com.accolite.furlough.entity;

import java.sql.Date;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.accolite.furlough.utils.FurloughStatus;

public class FurloughLog {
	
	@Id
	private String requestId;
	
	@ManyToOne
	@JoinColumn(name="msId")
	private MSEmployee msEmployee;
	
	private Date furloughDate;
	private FurloughStatus furloughStatus;
	private Date logTime;
	
	
	
}
