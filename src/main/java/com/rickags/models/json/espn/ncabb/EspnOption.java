package com.rickags.models.json.espn.ncabb;

import com.fasterxml.jackson.annotation.JsonAlias;

public class EspnOption {

    @JsonAlias("isSelected")
    private boolean selected;

    @JsonAlias("data")
    private EspnData espnData;

    @JsonAlias("label")
    private String label;

    @JsonAlias("value")
    private String value;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public EspnData getEspnData() {
        return espnData;
    }

    public void setEspnData(EspnData espnData) {
        this.espnData = espnData;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
