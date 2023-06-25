package com.rickags.models.json.espn.ncabb;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

public class EspnTeam {

    @JsonAlias("alternateColor")
    private String alternateColor;

    @JsonAlias("venue")
    private EspnVenue espnVenue;

    @JsonAlias("color")
    private String color;

    @JsonAlias("displayName")
    private String displayName;

    @JsonAlias("abbreviation")
    private String abbreviation;

    @JsonAlias("isActive")
    private boolean active;

//    @JsonAlias("triCode")
    @JsonAlias("shortDisplayName")
    private String shortDisplayName;

    @JsonAlias("uid")
    private String uid;

    @JsonAlias("conferenceId")
    private String conferenceId;

    @JsonAlias("name")
    private String name;

    @JsonAlias("logo")
    private String logo;

    @JsonAlias("location")
    private String location;

    //@JsonAlias("teamId")
    @JsonAlias("id")
    private String id;

    @JsonAlias("score")
    private String score;

    @JsonAlias("linescore")
    private List<EspnLineScore> espnLineScores;

    public String getAlternateColor() {
        return alternateColor;
    }

    public void setAlternateColor(String alternateColor) {
        this.alternateColor = alternateColor;
    }

    public EspnVenue getEspnVenue() {
        return espnVenue;
    }

    public void setEspnVenue(EspnVenue espnVenue) {
        this.espnVenue = espnVenue;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getShortDisplayName() {
        return shortDisplayName;
    }

    public void setShortDisplayName(String shortDisplayName) {
        this.shortDisplayName = shortDisplayName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(String conferenceId) {
        this.conferenceId = conferenceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public List<EspnLineScore> getEspnLineScores() {
        return espnLineScores;
    }

    public void setEspnLineScores(List<EspnLineScore> espnLineScores) {
        this.espnLineScores = espnLineScores;
    }
}
