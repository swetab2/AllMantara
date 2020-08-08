package com.god.allmantara.Model.Festival;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FestivalModel {

    @SerializedName("fid")
    @Expose
    private String fid;
    @SerializedName("fname")
    @Expose
    private String fname;
    @SerializedName("hindidate")
    @Expose
    private String hindidate;
    @SerializedName("impday")
    @Expose
    private String impday;

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getHindidate() {
        return hindidate;
    }

    public void setHindidate(String hindidate) {
        this.hindidate = hindidate;
    }

    public String getImpday() {
        return impday;
    }

    public void setImpday(String impday) {
        this.impday = impday;
    }

}