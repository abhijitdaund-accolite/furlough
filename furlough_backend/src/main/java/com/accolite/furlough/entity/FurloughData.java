package com.accolite.furlough.entity;

import java.util.Date;
import java.util.Map;

public class FurloughData {

    private String MSID;
    private String resourceName;
    private String vendorName;
    private Map<Date, String> furloughDates;
    private String division;
    private String location;
    private String comments;

    public String getMSID() {
        return MSID;
    }

    public void setMSID(final String mSID) {
        MSID = mSID;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(final String resourceName) {
        this.resourceName = resourceName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(final String vendorName) {
        this.vendorName = vendorName;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(final String division) {
        this.division = division;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(final String comments) {
        this.comments = comments;
    }

    public Map<Date, String> getFurloughDates() {
        return furloughDates;
    }

    public void setFurloughDates(final Map<Date, String> furloughDates) {
        this.furloughDates = furloughDates;
    }

    @Override
    public String toString() {
        return "FurloughData [\nMSID=" + MSID + "\nrsourceName=" + resourceName + "\nvendorName=" + vendorName
                + "\nfurloughDates=" + furloughDates + "\ndivision=" + division + "\nlocation=" + location
                + "\ncomments=" + comments + "\n]";
    }
}
