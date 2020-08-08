package com.god.allmantara.Model.Mantra;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllGodModel {

    @SerializedName("godid")
    @Expose
    private String godid;
    @SerializedName("godname")
    @Expose
    private String godname;
    @SerializedName("godimage")
    @Expose
    private String godimage;

    public String getGodid() {
        return godid;
    }

    public void setGodid(String godid) {
        this.godid = godid;
    }

    public String getGodname() {
        return godname;
    }

    public void setGodname(String godname) {
        this.godname = godname;
    }

    public String getGodimage() {
        return godimage;
    }

    public void setGodimage(String godimage) {
        this.godimage = godimage;
    }

}