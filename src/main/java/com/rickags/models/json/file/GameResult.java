package com.rickags.models.json.file;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GameResult
{
   private LocalDate gameDate;
   private String gameId;
   private String opponentId;
   private String opponentName;
   private String opponentConferenceId;
   private String venueId;
   private boolean conferenceGame;
   private boolean designatedHost;
   private List<PossessionResultBreakdown> offensivePossessionResultBreakdownList;
   private List<PossessionResultBreakdown> defensivePossessionResultBreakdownList;
   private List<ShotResultBreakdown> offensiveShotResultBreakdownList;
   private List<ShotResultBreakdown> defensiveShotResultBreakdownList;
   private List<ReboundResultBreakdown> offensiveReboundResultBreakdownList;
   private List<ReboundResultBreakdown> defensiveReboundResultBreakdownList;
   private FreeThrowResultBreakdown freeThrowResultBreakdown;

   public GameResult ()
   {
      offensivePossessionResultBreakdownList = new ArrayList<>();
      defensivePossessionResultBreakdownList = new ArrayList<>();
      offensiveShotResultBreakdownList  = new ArrayList<>();
      defensiveShotResultBreakdownList = new ArrayList<>();
      offensiveReboundResultBreakdownList = new ArrayList<>();
      defensiveReboundResultBreakdownList = new ArrayList<>();
      freeThrowResultBreakdown = new FreeThrowResultBreakdown();
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

   public String getOpponentConferenceId ()
   {
      return opponentConferenceId;
   }

   public void setOpponentConferenceId (String opponentConferenceId)
   {
      this.opponentConferenceId = opponentConferenceId;
   }

   public List<PossessionResultBreakdown> getOffensivePossessionResultBreakdownList ()
   {
      return offensivePossessionResultBreakdownList;
   }

   public void setOffensivePossessionResultBreakdownList (List<PossessionResultBreakdown> offensivePossessionResultBreakdownList)
   {
      this.offensivePossessionResultBreakdownList = offensivePossessionResultBreakdownList;
   }

   public List<PossessionResultBreakdown> getDefensivePossessionResultBreakdownList ()
   {
      return defensivePossessionResultBreakdownList;
   }

   public void setDefensivePossessionResultBreakdownList (List<PossessionResultBreakdown> defensivePossessionResultBreakdownList)
   {
      this.defensivePossessionResultBreakdownList = defensivePossessionResultBreakdownList;
   }

   public List<ShotResultBreakdown> getOffensiveShotResultBreakdownList ()
   {
      return offensiveShotResultBreakdownList;
   }

   public void setOffensiveShotResultBreakdownList (List<ShotResultBreakdown> offensiveShotResultBreakdownList)
   {
      this.offensiveShotResultBreakdownList = offensiveShotResultBreakdownList;
   }

   public List<ShotResultBreakdown> getDefensiveShotResultBreakdownList ()
   {
      return defensiveShotResultBreakdownList;
   }

   public void setDefensiveShotResultBreakdownList (List<ShotResultBreakdown> defensiveShotResultBreakdownList)
   {
      this.defensiveShotResultBreakdownList = defensiveShotResultBreakdownList;
   }

   public List<ReboundResultBreakdown> getOffensiveReboundResultBreakdownList ()
   {
      return offensiveReboundResultBreakdownList;
   }

   public void setOffensiveReboundResultBreakdownList (List<ReboundResultBreakdown> offensiveReboundResultBreakdownList)
   {
      this.offensiveReboundResultBreakdownList = offensiveReboundResultBreakdownList;
   }

   public List<ReboundResultBreakdown> getDefensiveReboundResultBreakdownList ()
   {
      return defensiveReboundResultBreakdownList;
   }

   public void setDefensiveReboundResultBreakdownList (List<ReboundResultBreakdown> defensiveReboundResultBreakdownList)
   {
      this.defensiveReboundResultBreakdownList = defensiveReboundResultBreakdownList;
   }

   public FreeThrowResultBreakdown getFreeThrowResultBreakdown ()
   {
      return freeThrowResultBreakdown;
   }

   public void setFreeThrowResultBreakdown (FreeThrowResultBreakdown freeThrowResultBreakdown)
   {
      this.freeThrowResultBreakdown = freeThrowResultBreakdown;
   }

   public String getOpponentName ()
   {
      return opponentName;
   }

   public void setOpponentName (String opponentName)
   {
      this.opponentName = opponentName;
   }

   public boolean isConferenceGame ()
   {
      return conferenceGame;
   }

   public void setConferenceGame (boolean conferenceGame)
   {
      this.conferenceGame = conferenceGame;
   }
}
