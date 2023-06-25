package com.rickags.models.json.espn.ncabb;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

public class EspnCompetitor {

    @JsonAlias("team")
    private EspnTeam espnTeam;

    @JsonAlias("type")
    private String type;

    @JsonAlias("uid")
    private String uid;

    @JsonAlias("homeAway")
    private String homeAway;

    @JsonAlias("score")
    private String score;

    @JsonAlias("winner")
    private boolean winner;

    @JsonAlias("id")
    private String id;

    @JsonAlias("linescores")
    private List<EspnLineScore> espnLineScores;

    public EspnTeam getEspnTeam() {
        return espnTeam;
    }

    public void setEspnTeam(EspnTeam espnTeam) {
        this.espnTeam = espnTeam;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getHomeAway() {
        return homeAway;
    }

    public void setHomeAway(String homeAway) {
        this.homeAway = homeAway;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public List<EspnLineScore> getEspnLineScores() {
        return espnLineScores;
    }

    public void setEspnLineScores(List<EspnLineScore> espnLineScores) {
        this.espnLineScores = espnLineScores;
    }
}
