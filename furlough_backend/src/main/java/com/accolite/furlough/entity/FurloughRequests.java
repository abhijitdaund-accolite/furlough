package com.accolite.furlough.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.accolite.furlough.utils.FurloughRequestsIDTracker;
import com.accolite.furlough.utils.FurloughStatus;

@Entity
@Table(name = "FurloughRequests")
public class FurloughRequests {

    @Id
    private FurloughRequestsIDTracker furloughID;

    @Column(name = "furloughStatus")
    private FurloughStatus furloughStatus;

    @Column(name = "mailSent")
    private Boolean mailSent;

    @Column(name = "resourceConfirmed")
    private Boolean resourceConfirmed;

}
