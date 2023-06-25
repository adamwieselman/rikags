package com.rickags.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "teamvenue", schema = "basketball")
public class TeamVenue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "espnteamid")
    private String espnTeamId;

    @Column(name = "espnvenueid")
    private String espnVenueId;

    @Column(name = "startdate")
    private LocalDate startDate;

    @Column(name = "enddate")
    private LocalDate endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEspnTeamId() {
        return espnTeamId;
    }

    public void setEspnTeamId(String espnTeamId) {
        this.espnTeamId = espnTeamId;
    }

    public String getEspnVenueId() {
        return espnVenueId;
    }

    public void setEspnVenueId(String espnVenueId) {
        this.espnVenueId = espnVenueId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
