package com.rickags.models.json.espn.ncabb;

import com.fasterxml.jackson.annotation.JsonAlias;

public class EspnSbGroup {
    @JsonAlias("pageTitle")
    private String pageTitle;

    @JsonAlias("altTitle")
    private String altTitle;

    @JsonAlias("scheduleStartDate")
    private String scheduleStartDate;

    @JsonAlias("isCollege")
    private boolean college;

    @JsonAlias("league")
    private String league;

    @JsonAlias("sport")
    private String sport;

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getAltTitle() {
        return altTitle;
    }

    public void setAltTitle(String altTitle) {
        this.altTitle = altTitle;
    }

    public String getScheduleStartDate() {
        return scheduleStartDate;
    }

    public void setScheduleStartDate(String scheduleStartDate) {
        this.scheduleStartDate = scheduleStartDate;
    }

    public boolean isCollege() {
        return college;
    }

    public void setCollege(boolean isCollege) {
        this.college = isCollege;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }
}
