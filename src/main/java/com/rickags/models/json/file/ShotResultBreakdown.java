package com.rickags.models.json.file;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ShotResultBreakdown
{
   private String gameTimeFrame;
   private String possessionStart;
   private String overallPossessionResult;
   private String offensiveGroupingName;
   private String defensiveGroupingName;
   private BigInteger totalOccurrences;
   private BigInteger totalSuccesses;
   private BigDecimal successPercentage;
   private List<IndividualShotResultData> individualShotResultDataList;

   public ShotResultBreakdown ()
   {
      individualShotResultDataList = new ArrayList<>();
   }

   public String getOverallPossessionResult ()
   {
      return overallPossessionResult;
   }

   public void setOverallPossessionResult (String overallPossessionResult)
   {
      this.overallPossessionResult = overallPossessionResult;
   }

   public BigInteger getTotalSuccesses ()
   {
      return totalSuccesses;
   }

   public void setTotalSuccesses (BigInteger totalSuccesses)
   {
      this.totalSuccesses = totalSuccesses;
   }

   public BigDecimal getSuccessPercentage ()
   {
      return successPercentage;
   }

   public void setSuccessPercentage (BigDecimal successPercentage)
   {
      this.successPercentage = successPercentage;
   }

   public List<IndividualShotResultData> getIndividualShotResultDataList ()
   {
      return individualShotResultDataList;
   }

   public void setIndividualShotResultDataList (List<IndividualShotResultData> individualShotResultDataList)
   {
      this.individualShotResultDataList = individualShotResultDataList;
   }

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

}
