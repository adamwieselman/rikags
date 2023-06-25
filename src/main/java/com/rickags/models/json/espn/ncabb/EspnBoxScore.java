package com.rickags.models.json.espn.ncabb;

import com.fasterxml.jackson.annotation.JsonAlias;

public class EspnBoxScore {

    @JsonAlias("playerName")
    private String playerName;

    @JsonAlias("playerMin")
    private String playerMin;

    @JsonAlias("playerFg")
    private String playerFg;

    @JsonAlias("player3pt")
    private String player3pt;

    @JsonAlias("playerFt")
    private String playerFt;

    @JsonAlias("playerOreb")
    private String playerOreb;

    @JsonAlias("playerDreb")
    private String playerDreb;

    @JsonAlias("playerAst")
    private String playerAst;

    @JsonAlias("playerStl")
    private String playerStl;

    @JsonAlias("playerBlk")
    private String playerBlk;

    @JsonAlias("playerTo")
    private String playerTo;

    @JsonAlias("playerPf")
    private String playerPf;

    @JsonAlias("playerPts")
    private String playerPts;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerMin() {
        return playerMin;
    }

    public void setPlayerMin(String playerMin) {
        this.playerMin = playerMin;
    }

    public String getPlayerFg() {
        return playerFg;
    }

    public void setPlayerFg(String playerFg) {
        this.playerFg = playerFg;
    }

    public String getPlayer3pt() {
        return player3pt;
    }

    public void setPlayer3pt(String player3pt) {
        this.player3pt = player3pt;
    }

    public String getPlayerFt() {
        return playerFt;
    }

    public void setPlayerFt(String playerFt) {
        this.playerFt = playerFt;
    }

    public String getPlayerOreb() {
        return playerOreb;
    }

    public void setPlayerOreb(String playerOreb) {
        this.playerOreb = playerOreb;
    }

    public String getPlayerDreb() {
        return playerDreb;
    }

    public void setPlayerDreb(String playerDreb) {
        this.playerDreb = playerDreb;
    }

    public String getPlayerAst() {
        return playerAst;
    }

    public void setPlayerAst(String playerAst) {
        this.playerAst = playerAst;
    }

    public String getPlayerStl() {
        return playerStl;
    }

    public void setPlayerStl(String playerStl) {
        this.playerStl = playerStl;
    }

    public String getPlayerBlk() {
        return playerBlk;
    }

    public void setPlayerBlk(String playerBlk) {
        this.playerBlk = playerBlk;
    }

    public String getPlayerTo() {
        return playerTo;
    }

    public void setPlayerTo(String playerTo) {
        this.playerTo = playerTo;
    }

    public String getPlayerPf() {
        return playerPf;
    }

    public void setPlayerPf(String playerPf) {
        this.playerPf = playerPf;
    }

    public String getPlayerPts() {
        return playerPts;
    }

    public void setPlayerPts(String playerPts) {
        this.playerPts = playerPts;
    }
}
