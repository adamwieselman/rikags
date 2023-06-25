package com.rickags.models.json.file;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ReboundResultBreakdown
{
   private String gameTimeFrame;
   private String possessionStart;
   private String overallPossessionResult;
   private String offensiveGroupingName;
   private String defensiveGroupingName;
   private BigInteger totalOccurrences;
   private BigInteger totalRebounds;
   private BigDecimal reboundPercentage;
   private BigDecimal tempoAverage;
   private BigDecimal tempoStDev;
   private List<BigInteger> tempos;
   private List<IndividualReboundResultData> individualReboundResultDataList;

   public ReboundResultBreakdown ()
   {
      tempos = new ArrayList<>();
      individualReboundResultDataList = new ArrayList<>();
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

   public BigDecimal getReboundPercentage ()
   {
      return reboundPercentage;
   }

   public void setReboundPercentage (BigDecimal reboundPercentage)
   {
      this.reboundPercentage = reboundPercentage;
   }

   public List<IndividualReboundResultData> getIndividualReboundResultDataList ()
   {
      return individualReboundResultDataList;
   }

   public void setIndividualReboundResultDataList (List<IndividualReboundResultData> individualReboundResultDataList)
   {
      this.individualReboundResultDataList = individualReboundResultDataList;
   }

   public BigDecimal getTempoAverage ()
   {
      return tempoAverage;
   }

   public void setTempoAverage (BigDecimal tempoAverage)
   {
      this.tempoAverage = tempoAverage;
   }

   public BigDecimal getTempoStDev ()
   {
      return tempoStDev;
   }

   public void setTempoStDev (BigDecimal tempoStDev)
   {
      this.tempoStDev = tempoStDev;
   }

   public List<BigInteger> getTempos ()
   {
      return tempos;
   }

   public void setTempos (List<BigInteger> tempos)
   {
      this.tempos = tempos;
   }
}
