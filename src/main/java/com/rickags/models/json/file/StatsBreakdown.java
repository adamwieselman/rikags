package com.rickags.models.json.file;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StatsBreakdown
{
   private LocalDate asOfDate;
   private BigInteger overallGamesWeightedPct;
   private BigInteger conferenceGamesWeightedPct;
   private BigInteger nonConferenceGamesWeightedPct;
   private String gamesOrTimeframe;
   private BigInteger lastOverallGamesOrDays;
   private BigInteger lastConferenceGamesOrDays;
   private BigInteger lastNonConferenceGamesOrDays;

   private List<PossessionResultBreakdown> overallOffensivePossessionResultBreakdownList;
   private List<PossessionResultBreakdown> overallDefensivePossessionResultBreakdownList;

   private List<ShotResultBreakdownSummary> overallOffensiveShotResultBreakdownSummaryList;
   private List<ShotResultBreakdownSummary> overallDefensiveShotResultBreakdownSummaryList;

   private List<ReboundResultBreakdownSummary> overallOffensiveReboundResultBreakdownSummaryList;
   private List<ReboundResultBreakdownSummary> overallDefensiveReboundResultBreakdownSummaryList;

   private FreeThrowResultBreakdown overallFreeThrowResultBreakdown;

   public StatsBreakdown ()
   {
      overallOffensivePossessionResultBreakdownList = new ArrayList<>();
      overallDefensivePossessionResultBreakdownList = new ArrayList<>();
      overallOffensiveShotResultBreakdownSummaryList = new ArrayList<>();
      overallDefensiveShotResultBreakdownSummaryList = new ArrayList<>();
      overallOffensiveReboundResultBreakdownSummaryList = new ArrayList<>();
      overallDefensiveReboundResultBreakdownSummaryList = new ArrayList<>();
   }

   public LocalDate getAsOfDate ()
   {
      return asOfDate;
   }

   public void setAsOfDate (LocalDate asOfDate)
   {
      this.asOfDate = asOfDate;
   }

   public BigInteger getOverallGamesWeightedPct ()
   {
      return overallGamesWeightedPct;
   }

   public void setOverallGamesWeightedPct (BigInteger overallGamesWeightedPct)
   {
      this.overallGamesWeightedPct = overallGamesWeightedPct;
   }

   public BigInteger getConferenceGamesWeightedPct ()
   {
      return conferenceGamesWeightedPct;
   }

   public void setConferenceGamesWeightedPct (BigInteger conferenceGamesWeightedPct)
   {
      this.conferenceGamesWeightedPct = conferenceGamesWeightedPct;
   }

   public BigInteger getNonConferenceGamesWeightedPct ()
   {
      return nonConferenceGamesWeightedPct;
   }

   public void setNonConferenceGamesWeightedPct (BigInteger nonConferenceGamesWeightedPct)
   {
      this.nonConferenceGamesWeightedPct = nonConferenceGamesWeightedPct;
   }

   public String getGamesOrTimeframe ()
   {
      return gamesOrTimeframe;
   }

   public void setGamesOrTimeframe (String gamesOrTimeframe)
   {
      this.gamesOrTimeframe = gamesOrTimeframe;
   }

   public BigInteger getLastOverallGamesOrDays ()
   {
      return lastOverallGamesOrDays;
   }

   public void setLastOverallGamesOrDays (BigInteger lastOverallGamesOrDays)
   {
      this.lastOverallGamesOrDays = lastOverallGamesOrDays;
   }

   public BigInteger getLastConferenceGamesOrDays ()
   {
      return lastConferenceGamesOrDays;
   }

   public void setLastConferenceGamesOrDays (BigInteger lastConferenceGamesOrDays)
   {
      this.lastConferenceGamesOrDays = lastConferenceGamesOrDays;
   }

   public BigInteger getLastNonConferenceGamesOrDays ()
   {
      return lastNonConferenceGamesOrDays;
   }

   public void setLastNonConferenceGamesOrDays (BigInteger lastNonConferenceGamesOrDays)
   {
      this.lastNonConferenceGamesOrDays = lastNonConferenceGamesOrDays;
   }

   public List<PossessionResultBreakdown> getOverallOffensivePossessionResultBreakdownList ()
   {
      return overallOffensivePossessionResultBreakdownList;
   }

   public void setOverallOffensivePossessionResultBreakdownList (List<PossessionResultBreakdown> overallOffensivePossessionResultBreakdownList)
   {
      this.overallOffensivePossessionResultBreakdownList = overallOffensivePossessionResultBreakdownList;
   }

   public List<PossessionResultBreakdown> getOverallDefensivePossessionResultBreakdownList ()
   {
      return overallDefensivePossessionResultBreakdownList;
   }

   public void setOverallDefensivePossessionResultBreakdownList (List<PossessionResultBreakdown> overallDefensivePossessionResultBreakdownList)
   {
      this.overallDefensivePossessionResultBreakdownList = overallDefensivePossessionResultBreakdownList;
   }

   public List<ShotResultBreakdownSummary> getOverallOffensiveShotResultBreakdownSummaryList ()
   {
      return overallOffensiveShotResultBreakdownSummaryList;
   }

   public void setOverallOffensiveShotResultBreakdownSummaryList (List<ShotResultBreakdownSummary> overallOffensiveShotResultBreakdownSummaryList)
   {
      this.overallOffensiveShotResultBreakdownSummaryList = overallOffensiveShotResultBreakdownSummaryList;
   }

   public List<ShotResultBreakdownSummary> getOverallDefensiveShotResultBreakdownSummaryList ()
   {
      return overallDefensiveShotResultBreakdownSummaryList;
   }

   public void setOverallDefensiveShotResultBreakdownSummaryList (List<ShotResultBreakdownSummary> overallDefensiveShotResultBreakdownSummaryList)
   {
      this.overallDefensiveShotResultBreakdownSummaryList = overallDefensiveShotResultBreakdownSummaryList;
   }

   public List<ReboundResultBreakdownSummary> getOverallOffensiveReboundResultBreakdownSummaryList ()
   {
      return overallOffensiveReboundResultBreakdownSummaryList;
   }

   public void setOverallOffensiveReboundResultBreakdownSummaryList (List<ReboundResultBreakdownSummary> overallOffensiveReboundResultBreakdownSummaryList)
   {
      this.overallOffensiveReboundResultBreakdownSummaryList = overallOffensiveReboundResultBreakdownSummaryList;
   }

   public List<ReboundResultBreakdownSummary> getOverallDefensiveReboundResultBreakdownSummaryList ()
   {
      return overallDefensiveReboundResultBreakdownSummaryList;
   }

   public void setOverallDefensiveReboundResultBreakdownSummaryList (List<ReboundResultBreakdownSummary> overallDefensiveReboundResultBreakdownSummaryList)
   {
      this.overallDefensiveReboundResultBreakdownSummaryList = overallDefensiveReboundResultBreakdownSummaryList;
   }

   public FreeThrowResultBreakdown getOverallFreeThrowResultBreakdown ()
   {
      return overallFreeThrowResultBreakdown;
   }

   public void setOverallFreeThrowResultBreakdown (FreeThrowResultBreakdown overallFreeThrowResultBreakdown)
   {
      this.overallFreeThrowResultBreakdown = overallFreeThrowResultBreakdown;
   }
}
