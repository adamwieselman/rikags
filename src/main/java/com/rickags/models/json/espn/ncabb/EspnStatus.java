package com.rickags.models.json.espn.ncabb;

import com.fasterxml.jackson.annotation.JsonAlias;

public class EspnStatus {

    @JsonAlias("period")
    private int period;

    @JsonAlias("clock")
    private int clock;

    @JsonAlias("displayClock")
    private String displayClock;

    @JsonAlias("type")
    private EspnType espnType;

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getClock() {
        return clock;
    }

    public void setClock(int clock) {
        this.clock = clock;
    }

    public String getDisplayClock() {
        return displayClock;
    }

    public void setDisplayClock(String displayClock) {
        this.displayClock = displayClock;
    }

    public EspnType getEspnType() {
        return espnType;
    }

    public void setEspnType(EspnType espnType) {
        this.espnType = espnType;
    }
}
