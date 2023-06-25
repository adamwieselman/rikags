package com.rickags.models.json.file;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class ShotResultBreakdownSummary
{
   private String gameTimeFrame;
   private String possessionStart;
   private String overallPossessionResult;
   private String offensiveGroupingName;
   private String defensiveGroupingName;
   private BigInteger totalOccurrences;
   private BigInteger totalSuccesses;
   private BigDecimal totalSuccessPercentage;
   private BigDecimal totalSuccessPercentageStrength;
   private BigDecimal gameSuccessPercentageAverage;
   private BigDecimal gameSuccessPercentageStDev;
   private BigDecimal gameSuccessPercentageStrength;
   private BigDecimal weightedGameSuccessPercentageAverage;
   private BigDecimal weightedGameSuccessPercentageStDev;
   private BigDecimal weightedGameSuccessPercentageStrength;

   private List<IndividualShotResultDataSummary> individualShotResultDataSummaryList;

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

   public BigInteger getTotalSuccesses ()
   {
      return totalSuccesses;
   }

   public void setTotalSuccesses (BigInteger totalSuccesses)
   {
      this.totalSuccesses = totalSuccesses;
   }

   public BigDecimal getTotalSuccessPercentage ()
   {
      return totalSuccessPercentage;
   }

   public void setTotalSuccessPercentage (BigDecimal totalSuccessPercentage)
   {
      this.totalSuccessPercentage = totalSuccessPercentage;
   }

   public BigDecimal getTotalSuccessPercentageStrength ()
   {
      return totalSuccessPercentageStrength;
   }

   public void setTotalSuccessPercentageStrength (BigDecimal totalSuccessPercentageStrength)
   {
      this.totalSuccessPercentageStrength = totalSuccessPercentageStrength;
   }

   public BigDecimal getGameSuccessPercentageAverage ()
   {
      return gameSuccessPercentageAverage;
   }

   public void setGameSuccessPercentageAverage (BigDecimal gameSuccessPercentageAverage)
   {
      this.gameSuccessPercentageAverage = gameSuccessPercentageAverage;
   }

   public BigDecimal getGameSuccessPercentageStDev ()
   {
      return gameSuccessPercentageStDev;
   }

   public void setGameSuccessPercentageStDev (BigDecimal gameSuccessPercentageStDev)
   {
      this.gameSuccessPercentageStDev = gameSuccessPercentageStDev;
   }

   public BigDecimal getGameSuccessPercentageStrength ()
   {
      return gameSuccessPercentageStrength;
   }

   public void setGameSuccessPercentageStrength (BigDecimal gameSuccessPercentageStrength)
   {
      this.gameSuccessPercentageStrength = gameSuccessPercentageStrength;
   }

   public BigDecimal getWeightedGameSuccessPercentageAverage ()
   {
      return weightedGameSuccessPercentageAverage;
   }

   public void setWeightedGameSuccessPercentageAverage (BigDecimal weightedGameSuccessPercentageAverage)
   {
      this.weightedGameSuccessPercentageAverage = weightedGameSuccessPercentageAverage;
   }

   public BigDecimal getWeightedGameSuccessPercentageStDev ()
   {
      return weightedGameSuccessPercentageStDev;
   }

   public void setWeightedGameSuccessPercentageStDev (BigDecimal weightedGameSuccessPercentageStDev)
   {
      this.weightedGameSuccessPercentageStDev = weightedGameSuccessPercentageStDev;
   }

   public BigDecimal getWeightedGameSuccessPercentageStrength ()
   {
      return weightedGameSuccessPercentageStrength;
   }

   public void setWeightedGameSuccessPercentageStrength (BigDecimal weightedGameSuccessPercentageStrength)
   {
      this.weightedGameSuccessPercentageStrength = weightedGameSuccessPercentageStrength;
   }

   public List<IndividualShotResultDataSummary> getIndividualShotResultDataSummaryList ()
   {
      return individualShotResultDataSummaryList;
   }

   public void setIndividualShotResultDataSummaryList (List<IndividualShotResultDataSummary> individualShotResultDataSummaryList)
   {
      this.individualShotResultDataSummaryList = individualShotResultDataSummaryList;
   }
}
