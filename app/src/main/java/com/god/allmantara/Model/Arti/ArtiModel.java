package com.god.allmantara.Model.Arti;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArtiModel {


    @SerializedName("mgodid")
    @Expose
    private String mgodid;
    @SerializedName("mgodname")
    @Expose
    private String mgodname;
    @SerializedName("mgodimage")
    @Expose
    private String mgodimage;
    @SerializedName("godaudio")
    @Expose
    private String godaudio;
    @SerializedName("godvideo")
    @Expose
    private String godvideo;

    public String getMgodid() {
        return mgodid;
    }

    public void setMgodid(String mgodid) {
        this.mgodid = mgodid;
    }

    public String getMgodname() {
        return mgodname;
    }

    public void setMgodname(String mgodname) {
        this.mgodname = mgodname;
    }

    public String getMgodimage() {
        return mgodimage;
    }

    public void setMgodimage(String mgodimage) {
        this.mgodimage = mgodimage;
    }

    public String getGodaudio() {
        return godaudio;
    }

    public void setGodaudio(String godaudio) {
        this.godaudio = godaudio;
    }

    public String getGodvideo() {
        return godvideo;
    }

    public void setGodvideo(String godvideo) {
        this.godvideo = godvideo;
    }

}