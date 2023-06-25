package com.rickags.beans;

import java.util.ArrayList;
import java.util.List;

import com.rickags.models.GameIssue;
import com.rickags.models.json.espn.ncabb.EspnGamePlayByPlay;

public class TeamEspnPlayByPlay
{
   private List<String> teams;
   private String gameId;
   private List<GameIssue> issueList;
   private EspnGamePlayByPlay espnGamePlayByPlay;

   public TeamEspnPlayByPlay(){
      this.teams = new ArrayList<>();
      this.issueList = new ArrayList<>();
   }

   public List<String> getTeams ()
   {
      return teams;
   }

   public void setTeams (List<String> teams)
   {
      this.teams = teams;
   }

   public EspnGamePlayByPlay getEspnGamePlayByPlay ()
   {
      return espnGamePlayByPlay;
   }

   public void setEspnGamePlayByPlay (EspnGamePlayByPlay espnGamePlayByPlay)
   {
      this.espnGamePlayByPlay = espnGamePlayByPlay;
   }

   public String getGameId ()
   {
      return gameId;
   }

   public void setGameId (String gameId)
   {
      this.gameId = gameId;
   }

   public List<GameIssue> getIssueList ()
   {
      return issueList;
   }

   public void setIssueList (List<GameIssue> issueList)
   {
      this.issueList = issueList;
   }
}
