package com.rickags.models.json.espn.ncabb;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

public class EspnGamePlayByPlay {

    @JsonAlias("game-plays")
    private List<EspnPlayByPlay> espnPlayByPlays;

    public List<EspnPlayByPlay> getEspnPlayByPlays() {
        return espnPlayByPlays;
    }

    public void setEspnPlayByPlays(List<EspnPlayByPlay> espnPlayByPlays) {
        this.espnPlayByPlays = espnPlayByPlays;
    }
}