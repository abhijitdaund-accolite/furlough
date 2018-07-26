package com.accolite.furlough.dto;

import java.util.Date;

public class FileDetailsList {
    String filename;
    Date lastModified;

    public FileDetailsList(final String filename, final Date lastModified) {
        super();
        this.filename = filename;
        this.lastModified = lastModified;

    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(final String filename) {
        this.filename = filename;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(final Date lastModified) {
        this.lastModified = lastModified;
    }
}
