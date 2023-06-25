package com.rickags.models.json.espn.ncabb;

import com.fasterxml.jackson.annotation.JsonAlias;

public class EspnSubSection {
    @JsonAlias("type")
    private String type;

    @JsonAlias("content")
    private EspnContent espnContent;

    @JsonAlias("gameId")
    private String gameId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public EspnContent getEspnContent() {
        return espnContent;
    }

    public void setEspnContent(EspnContent espnContent) {
        this.espnContent = espnContent;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

}
