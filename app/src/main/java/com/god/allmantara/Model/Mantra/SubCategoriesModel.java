package com.god.allmantara.Model.Mantra;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubCategoriesModel {

    @SerializedName("mantraspecficid")
    @Expose
    private String mantraspecficid;
    @SerializedName("mantraspecficname")
    @Expose
    private String mantraspecficname;
    @SerializedName("refmantaspecfic")
    @Expose
    private String refmantaspecfic;
    @SerializedName("godproductionid")
    @Expose
    private String godproductionid;

    public String getMantraspecficid() {
        return mantraspecficid;
    }

    public void setMantraspecficid(String mantraspecficid) {
        this.mantraspecficid = mantraspecficid;
    }

    public String getMantraspecficname() {
        return mantraspecficname;
    }

    public void setMantraspecficname(String mantraspecficname) {
        this.mantraspecficname = mantraspecficname;
    }

    public String getRefmantaspecfic() {
        return refmantaspecfic;
    }

    public void setRefmantaspecfic(String refmantaspecfic) {
        this.refmantaspecfic = refmantaspecfic;
    }

    public String getGodproductionid() {
        return godproductionid;
    }

    public void setGodproductionid(String godproductionid) {
        this.godproductionid = godproductionid;
    }

}
