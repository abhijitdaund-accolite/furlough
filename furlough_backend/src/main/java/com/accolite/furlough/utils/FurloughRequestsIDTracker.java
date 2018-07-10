package com.accolite.furlough.utils;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class FurloughRequestsIDTracker
        implements
        Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "FurloughRequestsIDTracker [MSID=" + MSID + ", furloughDate=" + furloughDate + "]";
    }

    @NotNull
    @Column(name = "MSID")
    private String MSID;

    @NotNull
    @Column(name = "furloughDate")
    private Date furloughDate;

    public FurloughRequestsIDTracker() {

    }

    public FurloughRequestsIDTracker(final String MSID, final Date date) {
        this.furloughDate = date;
        this.MSID = MSID;
    }

    public String getMSID() {
        return MSID;
    }

    public void setMsId(final String MSID) {
        this.MSID = MSID;
    }

    public Date getFurloughDate() {
        return furloughDate;
    }

    public void setFurloughDate(final Date furloughDate) {
        this.furloughDate = furloughDate;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof FurloughRequestsIDTracker)) {
            return false;
        }
        final FurloughRequestsIDTracker furloughRequestID = (FurloughRequestsIDTracker) obj;

        return this.getMSID() == furloughRequestID.getMSID()
                && this.getFurloughDate() == furloughRequestID.getFurloughDate();
    }

    @Override
    public int hashCode() {
        return 0; // TODO code to calculate HashCode PRIORITY
    }

}
