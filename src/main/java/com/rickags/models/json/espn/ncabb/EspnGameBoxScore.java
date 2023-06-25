package com.rickags.models.json.espn.ncabb;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

public class EspnGameBoxScore {

    @JsonAlias("game-boxscores")
    private List<EspnBoxScore> espnBoxScores;

    public List<EspnBoxScore> getEspnBoxScores() {
        return espnBoxScores;
    }

    public void setEspnBoxScores(List<EspnBoxScore> espnBoxScores) {
        this.espnBoxScores = espnBoxScores;
    }
}
