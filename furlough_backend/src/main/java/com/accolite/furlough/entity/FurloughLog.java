package com.accolite.furlough.entity;

import java.sql.Date;
import java.time.LocalDateTime;

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
    @GenericGenerator(name = "requestIDGenerator", strategy = "com.accolite.furlough.utils.RequestIDGenerator")
    @GeneratedValue(generator = "requestIDGenerator")
    @Column(name = "requestID")
    private String requestID;

    @ManyToOne
    @JoinColumn(name = "MSID")
    private MSEmployee msEmployee;

    @Column(name = "furloughDate")
    private Date furloughDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "furloughStatus")
    private FurloughStatus furloughStatus;

    @Column(name = "logTime")
    @CreationTimestamp
    private LocalDateTime logTime;

}
