package com.rickags.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gamedatainfoteam", schema = "basketball")
public class GameDataTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "espngameid")
    private String espnGameId;

    @Column(name = "espnteamid")
    private String espnTeamId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEspnGameId() {
        return espnGameId;
    }

    public void setEspnGameId(String espnGameId) {
        this.espnGameId = espnGameId;
    }

    public String getEspnTeamId() {
        return espnTeamId;
    }

    public void setEspnTeamId(String espnTeamId) {
        this.espnTeamId = espnTeamId;
    }
}
