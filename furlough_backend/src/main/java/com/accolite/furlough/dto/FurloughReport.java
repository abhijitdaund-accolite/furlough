package com.accolite.furlough.dto;

import java.util.List;

public class FurloughReport {

    private String MSID;
    private List<DateAndOverlap> dateAndOverlaps;
    private String resourceName;

    public FurloughReport(final String mSID, final List<DateAndOverlap> dateAndOverlaps, final String resourceName) {
        super();
        MSID = mSID;
        this.dateAndOverlaps = dateAndOverlaps;
        this.resourceName = resourceName;
    }

    public String getMSID() {
        return MSID;
    }

    public void setMSID(final String mSID) {
        MSID = mSID;
    }

    public List<DateAndOverlap> getDateAndOverlaps() {
        return dateAndOverlaps;
    }

    public void setDateAndOverlaps(final List<DateAndOverlap> dateAndOverlaps) {
        this.dateAndOverlaps = dateAndOverlaps;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(final String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public String toString() {
        return "FurloughReport [MSID=" + MSID + ", dateAndOverlaps=" + dateAndOverlaps + ", resourceName="
                + resourceName + "]";
    }

}
