package com.accolite.furlough.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import com.accolite.furlough.utils.FurloughStatus;

@Entity
@Table(name = "FurloughLog")
public class FurloughLog {

    @Id
    @GenericGenerator(name = "requestIDGenerator", strategy = "com.accolite.furlough.generator.RequestIDGenerator")
    @GeneratedValue(generator = "requestIDGenerator")
    @Column(name = "requestID")
    private String requestID;

    @Column(name="mSID")
    private String mSID;

    @Column(name = "furloughDate")
    private Date furloughDate;

  
    @Column(name = "furloughStatus")
    private String furloughStatus;

    @Column(name = "logTime")
    @CreationTimestamp
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
		mSID = mSID;
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

	public FurloughLog(String requestID, String mSID, Date furloughDate, String furloughStatus,
			Date logTime) {
		super();
		this.requestID = requestID;
		mSID = mSID;
		this.furloughDate = furloughDate;
		this.furloughStatus = furloughStatus;
		this.logTime = logTime;
	}

	public FurloughLog() {
		super();
		// TODO Auto-generated constructor stub
	}

}
