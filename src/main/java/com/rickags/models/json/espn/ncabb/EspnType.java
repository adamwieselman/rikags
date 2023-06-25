package com.rickags.models.json.espn.ncabb;

import com.fasterxml.jackson.annotation.JsonAlias;

public class EspnType {

    @JsonAlias("name")
    private String name;

    @JsonAlias("description")
    private String description;

    @JsonAlias("id")
    private String id;

    @JsonAlias("state")
    private String state;

    @JsonAlias("completed")
    private boolean completed;

    @JsonAlias("detail")
    private String detail;

    @JsonAlias("shortDetail")
    private String shortDetail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getShortDetail() {
        return shortDetail;
    }

    public void setShortDetail(String shortDetail) {
        this.shortDetail = shortDetail;
    }
}
