package com.accolite.furlough.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.accolite.furlough.utils.FurloughRequestsIDTracker;

@Entity
@Table(name = "FurloughRequests")
public class FurloughRequests {

    @Override
	public String toString() {
		return "FurloughRequests [furloughID=" + furloughID + ", furloughStatus=" + furloughStatus + ", mailSent="
				+ mailSent + ", resourceConfirmed=" + resourceConfirmed + "]";
	}

	@EmbeddedId
    private FurloughRequestsIDTracker furloughID;

    @Column(name = "furloughStatus")
    private String furloughStatus;

    @Column(name = "mailSent")
    private Boolean mailSent;

    @Column(name = "resourceConfirmed")
    private Boolean resourceConfirmed;

    public FurloughRequestsIDTracker getFurloughID() {
        return furloughID;
    }

    public void setFurloughID(final FurloughRequestsIDTracker furloughID) {
        this.furloughID = furloughID;
    }

    public String getFurloughStatus() {
        return furloughStatus;
    }

    public void setFurloughStatus(final String string) {
        this.furloughStatus = string;
    }

    public Boolean getMailSent() {
        return mailSent;
    }

    public void setMailSent(final Boolean mailSent) {
        this.mailSent = mailSent;
    }

    public Boolean getResourceConfirmed() {
        return resourceConfirmed;
    }

    public void setResourceConfirmed(final Boolean resourceConfirmed) {
        this.resourceConfirmed = resourceConfirmed;
    }

}
