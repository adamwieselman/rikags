package com.rickags.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "conferenceteam", schema = "basketball")
public class ConferenceTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "espnconferenceid")
    private String espnConferenceId;

    @Column(name = "espnteamid")
    private String espnTeamId;

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

    public String getEspnConferenceId() {
        return espnConferenceId;
    }

    public void setEspnConferenceId(String espnConferenceId) {
        this.espnConferenceId = espnConferenceId;
    }

    public String getEspnTeamId() {
        return espnTeamId;
    }

    public void setEspnTeamId(String espnTeamId) {
        this.espnTeamId = espnTeamId;
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
