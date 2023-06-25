package com.rickags.models.json.espn.ncabb;

import com.fasterxml.jackson.annotation.JsonAlias;

public class EspnContent {

    @JsonAlias("league")
    private String league;

    @JsonAlias("sbGroup")
    private EspnSbGroup espnSbGroup;

    @JsonAlias("sbData")
    private EspnSbData espnSbData;

    @JsonAlias("altSbDropdown")
    private EspnAltSbDropdown espnAltSbDropdown;

    @JsonAlias("html")
    private String html;

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public EspnAltSbDropdown getEspnAltSbDropdown() {
        return espnAltSbDropdown;
    }

    public void setEspnAltSbDropdown(EspnAltSbDropdown espnAltSbDropdown) {
        this.espnAltSbDropdown = espnAltSbDropdown;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public EspnSbGroup getEspnSbGroup() {
        return espnSbGroup;
    }

    public void setEspnSbGroup(EspnSbGroup espnSbGroup) {
        this.espnSbGroup = espnSbGroup;
    }

    public EspnSbData getEspnSbData() {
        return espnSbData;
    }

    public void setEspnSbData(EspnSbData espnSbData) {
        this.espnSbData = espnSbData;
    }
}
