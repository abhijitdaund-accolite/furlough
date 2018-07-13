package com.accolite.furlough.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "FurloughLog")
public class FurloughLog {

    @Id
    @GenericGenerator(name = "requestIDGenerator", strategy = "com.accolite.furlough.generator.RequestIDGenerator")
    @GeneratedValue(generator = "requestIDGenerator")
    @Column(name = "requestID")
    private String requestID;

    @Column(name = "mSID")
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

    public void setRequestID(final String requestID) {
        this.requestID = requestID;
    }

    public String getmSID() {
        return mSID;
    }

    public void setmSID(final String mSID) {
        this.mSID = mSID;
    }

    public Date getFurloughDate() {
        return furloughDate;
    }

    public void setFurloughDate(final Date furloughDate) {
        this.furloughDate = furloughDate;
    }

    public String getFurloughStatus() {
        return furloughStatus;
    }

    public void setFurloughStatus(final String furloughStatus) {
        this.furloughStatus = furloughStatus;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(final Date logTime) {
        this.logTime = logTime;
    }

    public FurloughLog(final String requestID, final String mSID, final Date furloughDate, final String furloughStatus,
            final Date logTime) {
        super();
        this.requestID = requestID;
        this.mSID = mSID;
        this.furloughDate = furloughDate;
        this.furloughStatus = furloughStatus;
        this.logTime = logTime;
    }

    public FurloughLog() {
        super();
        // TODO Auto-generated constructor stub
    }

}
