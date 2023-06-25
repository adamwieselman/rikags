package com.rickags.models.json.backupfile;

import java.util.ArrayList;
import java.util.List;

public class YearSummary
{
   private String year;
   private String conferenceId;
   private String homeArenaId;
   private List<GameResult> gameResults;

   public YearSummary ()
   {
      gameResults = new ArrayList<>();
   }

   public String getYear ()
   {
      return year;
   }

   public void setYear (String year)
   {
      this.year = year;
   }

   public String getConferenceId ()
   {
      return conferenceId;
   }

   public void setConferenceId (String conferenceId)
   {
      this.conferenceId = conferenceId;
   }

   public String getHomeArenaId ()
   {
      return homeArenaId;
   }

   public void setHomeArenaId (String homeArenaId)
   {
      this.homeArenaId = homeArenaId;
   }

   public List<GameResult> getGameResults ()
   {
      return gameResults;
   }

   public void setGameResults (List<GameResult> gameResults)
   {
      this.gameResults = gameResults;
   }
}
