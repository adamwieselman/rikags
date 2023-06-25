package com.rickags.models.json.file;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class ReboundResultBreakdownSummary
{
   private String gameTimeFrame;
   private String possessionStart;
   private String overallPossessionResult;
   private String offensiveGroupingName;
   private String defensiveGroupingName;
   private BigInteger totalOccurrences;
   private BigInteger totalRebounds;
   private BigDecimal totalReboundPercentage;
   private BigDecimal totalReboundPercentageStrength;
   private BigDecimal gameReboundPercentageAverage;
   private BigDecimal gameReboundPercentageStDev;
   private BigDecimal gameReboundPercentageStrength;
   private BigDecimal weightedGameReboundPercentageAverage;
   private BigDecimal weightedGameReboundPercentageStDev;
   private BigDecimal weightedGameReboundPercentageStrength;

   private List<IndividualReboundResultDataSummary> individualReboundResultDataSummaryList;

   public String getGameTimeFrame ()
   {
      return gameTimeFrame;
   }

   public void setGameTimeFrame (String gameTimeFrame)
   {
      this.gameTimeFrame = gameTimeFrame;
   }

   public String getPossessionStart ()
   {
      return possessionStart;
   }

   public void setPossessionStart (String possessionStart)
   {
      this.possessionStart = possessionStart;
   }

   public String getOverallPossessionResult ()
   {
      return overallPossessionResult;
   }

   public void setOverallPossessionResult (String overallPossessionResult)
   {
      this.overallPossessionResult = overallPossessionResult;
   }

   public String getOffensiveGroupingName ()
   {
      return offensiveGroupingName;
   }

   public void setOffensiveGroupingName (String offensiveGroupingName)
   {
      this.offensiveGroupingName = offensiveGroupingName;
   }

   public String getDefensiveGroupingName ()
   {
      return defensiveGroupingName;
   }

   public void setDefensiveGroupingName (String defensiveGroupingName)
   {
      this.defensiveGroupingName = defensiveGroupingName;
   }

   public BigInteger getTotalOccurrences ()
   {
      return totalOccurrences;
   }

   public void setTotalOccurrences (BigInteger totalOccurrences)
   {
      this.totalOccurrences = totalOccurrences;
   }

   public BigInteger getTotalRebounds ()
   {
      return totalRebounds;
   }

   public void setTotalRebounds (BigInteger totalRebounds)
   {
      this.totalRebounds = totalRebounds;
   }

   public BigDecimal getTotalReboundPercentage ()
   {
      return totalReboundPercentage;
   }

   public void setTotalReboundPercentage (BigDecimal totalReboundPercentage)
   {
      this.totalReboundPercentage = totalReboundPercentage;
   }

   public BigDecimal getTotalReboundPercentageStrength ()
   {
      return totalReboundPercentageStrength;
   }

   public void setTotalReboundPercentageStrength (BigDecimal totalReboundPercentageStrength)
   {
      this.totalReboundPercentageStrength = totalReboundPercentageStrength;
   }

   public BigDecimal getGameReboundPercentageAverage ()
   {
      return gameReboundPercentageAverage;
   }

   public void setGameReboundPercentageAverage (BigDecimal gameReboundPercentageAverage)
   {
      this.gameReboundPercentageAverage = gameReboundPercentageAverage;
   }

   public BigDecimal getGameReboundPercentageStDev ()
   {
      return gameReboundPercentageStDev;
   }

   public void setGameReboundPercentageStDev (BigDecimal gameReboundPercentageStDev)
   {
      this.gameReboundPercentageStDev = gameReboundPercentageStDev;
   }

   public BigDecimal getGameReboundPercentageStrength ()
   {
      return gameReboundPercentageStrength;
   }

   public void setGameReboundPercentageStrength (BigDecimal gameReboundPercentageStrength)
   {
      this.gameReboundPercentageStrength = gameReboundPercentageStrength;
   }

   public BigDecimal getWeightedGameReboundPercentageAverage ()
   {
      return weightedGameReboundPercentageAverage;
   }

   public void setWeightedGameReboundPercentageAverage (BigDecimal weightedGameReboundPercentageAverage)
   {
      this.weightedGameReboundPercentageAverage = weightedGameReboundPercentageAverage;
   }

   public BigDecimal getWeightedGameReboundPercentageStDev ()
   {
      return weightedGameReboundPercentageStDev;
   }

   public void setWeightedGameReboundPercentageStDev (BigDecimal weightedGameReboundPercentageStDev)
   {
      this.weightedGameReboundPercentageStDev = weightedGameReboundPercentageStDev;
   }

   public BigDecimal getWeightedGameReboundPercentageStrength ()
   {
      return weightedGameReboundPercentageStrength;
   }

   public void setWeightedGameReboundPercentageStrength (BigDecimal weightedGameReboundPercentageStrength)
   {
      this.weightedGameReboundPercentageStrength = weightedGameReboundPercentageStrength;
   }

   public List<IndividualReboundResultDataSummary> getIndividualReboundResultDataSummaryList ()
   {
      return individualReboundResultDataSummaryList;
   }

   public void setIndividualReboundResultDataSummaryList (List<IndividualReboundResultDataSummary> individualReboundResultDataSummaryList)
   {
      this.individualReboundResultDataSummaryList = individualReboundResultDataSummaryList;
   }
}
