package com.rickags.models.json.espn.ncabb;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

public class EspnScoreboard {

    @JsonAlias("type")
    private String type;

    @JsonAlias("content")
    private EspnContent espnContent;

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public EspnContent getEspnContent() {
        return espnContent;
    }

    public void setEspnContent(EspnContent espnContent) {
        this.espnContent = espnContent;
    }

    public List<EspnEvent> getEspnEvents()
    {
        return espnContent.getEspnSbData().getEspnEvents();
    }

}
