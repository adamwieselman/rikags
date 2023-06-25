package com.rickags.models.json.backupfile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GameResult
{
   private LocalDate gameDate;
   private String gameId;
   private String opponentId;
   private String opponentConferenceId;
   private String venueId;
   private boolean designatedHost;
   private List<GameResultBreakdownSummary> offensiveGameResultSummaries;
   private List<GameResultBreakdownSummary> defensiveGameResultSummaries;
   private List<GameResultBreakdown> gameResultBreakdowns;

   public GameResult ()
   {
      gameResultBreakdowns = new ArrayList<>();
      offensiveGameResultSummaries = new ArrayList<>();
      defensiveGameResultSummaries = new ArrayList<>();
   }

   public LocalDate getGameDate ()
   {
      return gameDate;
   }

   public void setGameDate (LocalDate gameDate)
   {
      this.gameDate = gameDate;
   }

   public String getGameId ()
   {
      return gameId;
   }

   public void setGameId (String gameId)
   {
      this.gameId = gameId;
   }

   public String getOpponentId ()
   {
      return opponentId;
   }

   public void setOpponentId (String opponentId)
   {
      this.opponentId = opponentId;
   }

   public String getVenueId ()
   {
      return venueId;
   }

   public void setVenueId (String venueId)
   {
      this.venueId = venueId;
   }

   public boolean getDesignatedHost ()
   {
      return designatedHost;
   }

   public void setDesignatedHost (boolean designatedHost)
   {
      this.designatedHost = designatedHost;
   }

   public List<GameResultBreakdown> getGameResultBreakdowns ()
   {
      return gameResultBreakdowns;
   }

   public void setGameResultBreakdowns (List<GameResultBreakdown> gameResultBreakdowns)
   {
      this.gameResultBreakdowns = gameResultBreakdowns;
   }

   public String getOpponentConferenceId ()
   {
      return opponentConferenceId;
   }

   public void setOpponentConferenceId (String opponentConferenceId)
   {
      this.opponentConferenceId = opponentConferenceId;
   }

   public List<GameResultBreakdownSummary> getOffensiveGameResultSummaries ()
   {
      return offensiveGameResultSummaries;
   }

   public void setOffensiveGameResultSummaries (List<GameResultBreakdownSummary> offensiveGameResultSummaries)
   {
      this.offensiveGameResultSummaries = offensiveGameResultSummaries;
   }

   public List<GameResultBreakdownSummary> getDefensiveGameResultSummaries ()
   {
      return defensiveGameResultSummaries;
   }

   public void setDefensiveGameResultSummaries (List<GameResultBreakdownSummary> defensiveGameResultSummaries)
   {
      this.defensiveGameResultSummaries = defensiveGameResultSummaries;
   }
}
