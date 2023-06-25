package com.rickags.testclasses;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Event {
    @JsonAlias("id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
