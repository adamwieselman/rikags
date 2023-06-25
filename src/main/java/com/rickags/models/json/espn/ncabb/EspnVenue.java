package com.rickags.models.json.espn.ncabb;

import com.fasterxml.jackson.annotation.JsonAlias;

public class EspnVenue {

    @JsonAlias("address")
    private EspnAddress espnAddress;

    @JsonAlias("fullName")
    private String fullName;

    @JsonAlias("indoor")
    private boolean indoor;

    @JsonAlias("id")
    private String id;

    @JsonAlias("capacity")
    private int capacity;

    public EspnAddress getEspnAddress() {
        return espnAddress;
    }

    public void setEspnAddress(EspnAddress espnAddress) {
        this.espnAddress = espnAddress;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isIndoor() {
        return indoor;
    }

    public void setIndoor(boolean indoor) {
        this.indoor = indoor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
