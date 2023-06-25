package com.rickags.models.json.espn.ncabb;

import com.fasterxml.jackson.annotation.JsonAlias;

public class EspnPlayByPlay {

    @JsonAlias("home-team")
    private String homeTeam;

    @JsonAlias("away-team")
    private String awayTeam;

    @JsonAlias("off-team")
    private String offTeam;

    @JsonAlias("def-team")
    private String defTeam;

    @JsonAlias("home-score")
    private String homeScore;

    @JsonAlias("away-score")
    private String awayScore;

    @JsonAlias("drive-result")
    private String driveResult;

    @JsonAlias("time-stamp")
    private String timeStamp;

    @JsonAlias("team-logo")
    private String teamLogo;

    @JsonAlias("game-detail")
    private String gameDetail;

    @JsonAlias("combined-score")
    private String combinedScore;

    @JsonAlias("play-location")
    private String playLocation;

    @JsonAlias("game-half")
    private String gameHalf;


    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getOffTeam() {
        return offTeam;
    }

    public void setOffTeam(String offTeam) {
        this.offTeam = offTeam;
    }

    public String getDefTeam() {
        return defTeam;
    }

    public void setDefTeam(String defTeam) {
        this.defTeam = defTeam;
    }

    public String getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(String homeScore) {
        this.homeScore = homeScore;
    }

    public String getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(String awayScore) {
        this.awayScore = awayScore;
    }

    public String getDriveResult() {
        return driveResult;
    }

    public void setDriveResult(String driveResult) {
        this.driveResult = driveResult;
    }

    public String getPlayLocation() {
        return playLocation;
    }

    public void setPlayLocation(String playLocation) {
        this.playLocation = playLocation;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTeamLogo() {
        return teamLogo;
    }

    public void setTeamLogo(String teamLogo) {
        this.teamLogo = teamLogo;
    }

    public String getGameDetail() {
        return gameDetail;
    }

    public void setGameDetail(String gameDetail) {
        this.gameDetail = gameDetail;
    }

    public String getCombinedScore() {
        return combinedScore;
    }

    public void setCombinedScore(String combinedScore) {
        this.combinedScore = combinedScore;
    }

    public String getGameHalf() {return gameHalf;}

    public void setGameHalf(String gameHalf) {this.gameHalf = gameHalf; }
}
