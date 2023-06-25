package com.rickags.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gamedata", schema = "basketball")
public class GameData implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "gamedate")
    private LocalDateTime gameDateTime;

    @Column(name = "espngameid")
    private String espnGameId;

    @Column(name = "neutralsite")
    private boolean neutralSite;

    @Column(name = "conferencecompetition")
    private boolean conferenceCompetition;

    @Column(name = "venueId")
    private Long venueId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getGameDateTime() {
        return gameDateTime;
    }

    public void setGameDateTime(LocalDateTime gameDateTime) {
        this.gameDateTime = gameDateTime;
    }

    public String getEspnGameId() {
        return espnGameId;
    }

    public void setEspnGameId(String espnGameId) {
        this.espnGameId = espnGameId;
    }

    public boolean isNeutralSite() {
        return neutralSite;
    }

    public void setNeutralSite(boolean neutralSite) {
        this.neutralSite = neutralSite;
    }

    public boolean isConferenceCompetition() {
        return conferenceCompetition;
    }

    public void setConferenceCompetition(boolean conferenceCompetition) {
        this.conferenceCompetition = conferenceCompetition;
    }

    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }

    public Long getVenueId() {
        return venueId;
    }
}
