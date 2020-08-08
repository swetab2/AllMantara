package com.god.allmantara.Model.Mantra;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContentModel {

    @SerializedName("contentid")
    @Expose
    private String contentid;
    @SerializedName("contaentmain")
    @Expose
    private String contaentmain;
    @SerializedName("refcontent")
    @Expose
    private String refcontent;
    @SerializedName("refmantaspecfic")
    @Expose
    private String refmantaspecfic;

    public String getContentid() {
        return contentid;
    }

    public void setContentid(String contentid) {
        this.contentid = contentid;
    }

    public String getContaentmain() {
        return contaentmain;
    }

    public void setContaentmain(String contaentmain) {
        this.contaentmain = contaentmain;
    }

    public String getRefcontent() {
        return refcontent;
    }

    public void setRefcontent(String refcontent) {
        this.refcontent = refcontent;
    }

    public String getRefmantaspecfic() {
        return refmantaspecfic;
    }

    public void setRefmantaspecfic(String refmantaspecfic) {
        this.refmantaspecfic = refmantaspecfic;
    }

}
