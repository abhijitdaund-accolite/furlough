package com.accolite.furlough.utils;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FurloughRequestsIDTracker
        implements
        Serializable {

    @Column(name = "MSID")
    private String MSID;

    @Column(name = "furloughDate")
    private Date furloughDate;

    public FurloughRequestsIDTracker(final String MSID, final Date furloughDate) {
        this.furloughDate = furloughDate;
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

    /*
     * @Override public int hashCode() { //TODO code to calculate HashCode PRIORITY
     * HIGH }
     */

}
