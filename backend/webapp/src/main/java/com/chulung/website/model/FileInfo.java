package com.chulung.website.model;

/**
 * Created by chulung on 2017/5/9.
 */
public class FileInfo extends BaseModel {

    private static final long serialVersionUID = -7242910934019103119L;
    private String fileName;
    private String fullPath;
    private String matedata;
    private String fid;


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public String getMatedata() {
        return matedata;
    }

    public void setMatedata(String matedata) {
        this.matedata = matedata;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }
}
