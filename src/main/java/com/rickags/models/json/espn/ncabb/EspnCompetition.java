package com.rickags.models.json.espn.ncabb;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

public class EspnCompetition {

    @JsonAlias("date")
    private String date;

    @JsonAlias("venue")
    private EspnVenue espnVenue;

    @JsonAlias("conferenceCompetition")
    private boolean conferenceCompetition;

    @JsonAlias("timeValid")
    private boolean timeValid;

    @JsonAlias("uid")
    private String uid;

    @JsonAlias("competitors")
    private List<EspnCompetitor> espnCompetitors;

    @JsonAlias("id")
    private String id;

    @JsonAlias("neutralSite")
    private boolean neutralSite;

    @JsonAlias("recent")
    private boolean recent;

    @JsonAlias("attendance")
    private int attendance;

    @JsonAlias("startDate")
    private String startDate;

    @JsonAlias("status")
    private EspnStatus espnStatus;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public EspnVenue getEspnVenue() {
        return espnVenue;
    }

    public void setEspnVenue(EspnVenue espnVenue) {
        this.espnVenue = espnVenue;
    }

    public boolean isConferenceCompetition() {
        return conferenceCompetition;
    }

    public void setConferenceCompetition(boolean conferenceCompetition) {
        this.conferenceCompetition = conferenceCompetition;
    }

    public boolean isTimeValid() {
        return timeValid;
    }

    public void setTimeValid(boolean timeValid) {
        this.timeValid = timeValid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<EspnCompetitor> getEspnCompetitors() {
        return espnCompetitors;
    }

    public void setEspnCompetitors(List<EspnCompetitor> espnCompetitors) {
        this.espnCompetitors = espnCompetitors;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isNeutralSite() {
        return neutralSite;
    }

    public void setNeutralSite(boolean neutralSite) {
        this.neutralSite = neutralSite;
    }

    public boolean isRecent() {
        return recent;
    }

    public void setRecent(boolean recent) {
        this.recent = recent;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public EspnStatus getEspnStatus() {
        return espnStatus;
    }

    public void setEspnStatus(EspnStatus espnStatus) {
        this.espnStatus = espnStatus;
    }
}
