package com.rickags.models.json.espn.ncabb;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

public class EspnEvent {

    @JsonAlias("date")
    private String date;

    @JsonAlias("uid")
    private String uid;

    @JsonAlias("name")
    private String name;

    @JsonAlias("competitions")
    private List<EspnCompetition> espnCompetitions;

    @JsonAlias("id")
    private String id;

    @JsonAlias("shortName")
    private String shortName;

    @JsonAlias("status")
    private EspnStatus espnStatus;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EspnCompetition> getEspnCompetitions() {
        return espnCompetitions;
    }

    public void setEspnCompetitions(List<EspnCompetition> espnCompetitions) {
        this.espnCompetitions = espnCompetitions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public EspnStatus getEspnStatus() {
        return espnStatus;
    }

    public void setEspnStatus(EspnStatus espnStatus) {
        this.espnStatus = espnStatus;
    }
}
