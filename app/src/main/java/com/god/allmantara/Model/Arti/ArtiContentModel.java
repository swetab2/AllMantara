package com.god.allmantara.Model.Arti;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArtiContentModel {

    @SerializedName("artiid")
    @Expose
    private String artiid;
    @SerializedName("articontent")
    @Expose
    private String articontent;
    @SerializedName("agodref")
    @Expose
    private String agodref;
    @SerializedName("artiidref")
    @Expose
    private String artiidref;

    public String getArtiid() {
        return artiid;
    }

    public void setArtiid(String artiid) {
        this.artiid = artiid;
    }

    public String getArticontent() {
        return articontent;
    }

    public void setArticontent(String articontent) {
        this.articontent = articontent;
    }

    public String getAgodref() {
        return agodref;
    }

    public void setAgodref(String agodref) {
        this.agodref = agodref;
    }

    public String getArtiidref() {
        return artiidref;
    }

    public void setArtiidref(String artiidref) {
        this.artiidref = artiidref;
    }

}
