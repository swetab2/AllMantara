package com.god.allmantara.Model.TodayPooja;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PoojaModel {

    @SerializedName("dayid")
    @Expose
    private String dayid;
    @SerializedName("todaymonth")
    @Expose
    private String todaymonth;
    @SerializedName("todayfestival")
    @Expose
    private String todayfestival;
    @SerializedName("todayday")
    @Expose
    private String todayday;

    public String getDayid() {
        return dayid;
    }

    public void setDayid(String dayid) {
        this.dayid = dayid;
    }

    public String getTodaymonth() {
        return todaymonth;
    }

    public void setTodaymonth(String todaymonth) {
        this.todaymonth = todaymonth;
    }

    public String getTodayfestival() {
        return todayfestival;
    }

    public void setTodayfestival(String todayfestival) {
        this.todayfestival = todayfestival;
    }

    public String getTodayday() {
        return todayday;
    }

    public void setTodayday(String todayday) {
        this.todayday = todayday;
    }

}
