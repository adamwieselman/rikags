package com.rickags.models.json.backupfile;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PossessionResultSummary
{
   private String overallPossessionResult;
   private BigInteger occurances;
   private BigDecimal occurancePercentage;
   private BigInteger successes;
   private BigDecimal successesPercentage;
   private BigInteger offensiveReboundingMissed;
   private BigDecimal offensiveReboundingMissedPercentage;
   private BigDecimal tempoAverage;
   private BigDecimal tempoStDev;
   private List<BigInteger> tempos;
   private List<BigInteger> offensiveReboundingMissedTempos;
   private BigDecimal offensiveReboundingMissedTempoAverage;
   private BigDecimal offensiveReboundingMissedTempoStDev;
   private List<IndividualPossessionResultSummary> individualPossessionResultSummeries;

   public PossessionResultSummary ()
   {
      tempos = new ArrayList<>();
      offensiveReboundingMissedTempos = new ArrayList<>();
      individualPossessionResultSummeries = new ArrayList<>();
   }

   public String getOverallPossessionResult ()
   {
      return overallPossessionResult;
   }

   public void setOverallPossessionResult (String overallPossessionResult)
   {
      this.overallPossessionResult = overallPossessionResult;
   }

   public BigInteger getOccurances ()
   {
      return occurances;
   }

   public void setOccurances (BigInteger occurances)
   {
      this.occurances = occurances;
   }

   public BigDecimal getOccurancePercentage ()
   {
      return occurancePercentage;
   }

   public void setOccurancePercentage (BigDecimal occurancePercentage)
   {
      this.occurancePercentage = occurancePercentage;
   }

   public BigInteger getSuccesses ()
   {
      return successes;
   }

   public void setSuccesses (BigInteger successes)
   {
      this.successes = successes;
   }

   public BigDecimal getSuccessesPercentage ()
   {
      return successesPercentage;
   }

   public void setSuccessesPercentage (BigDecimal successesPercentage)
   {
      this.successesPercentage = successesPercentage;
   }

   public BigInteger getOffensiveReboundingMissed ()
   {
      return offensiveReboundingMissed;
   }

   public void setOffensiveReboundingMissed (BigInteger offensiveReboundingMissed)
   {
      this.offensiveReboundingMissed = offensiveReboundingMissed;
   }

   public BigDecimal getOffensiveReboundingMissedPercentage ()
   {
      return offensiveReboundingMissedPercentage;
   }

   public void setOffensiveReboundingMissedPercentage (BigDecimal offensiveReboundingMissedPercentage)
   {
      this.offensiveReboundingMissedPercentage = offensiveReboundingMissedPercentage;
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

   public List<BigInteger> getOffensiveReboundingMissedTempos ()
   {
      return offensiveReboundingMissedTempos;
   }

   public void setOffensiveReboundingMissedTempos (List<BigInteger> offensiveReboundingMissedTempos)
   {
      this.offensiveReboundingMissedTempos = offensiveReboundingMissedTempos;
   }

   public BigDecimal getOffensiveReboundingMissedTempoAverage ()
   {
      return offensiveReboundingMissedTempoAverage;
   }

   public void setOffensiveReboundingMissedTempoAverage (BigDecimal offensiveReboundingMissedTempoAverage)
   {
      this.offensiveReboundingMissedTempoAverage = offensiveReboundingMissedTempoAverage;
   }

   public BigDecimal getOffensiveReboundingMissedTempoStDev ()
   {
      return offensiveReboundingMissedTempoStDev;
   }

   public void setOffensiveReboundingMissedTempoStDev (BigDecimal offensiveReboundingMissedTempoStDev)
   {
      this.offensiveReboundingMissedTempoStDev = offensiveReboundingMissedTempoStDev;
   }

   public List<IndividualPossessionResultSummary> getIndividualPossessionResultSummeries ()
   {
      return individualPossessionResultSummeries;
   }

   public void setIndividualPossessionResultSummeries (List<IndividualPossessionResultSummary> individualPossessionResultSummeries)
   {
      this.individualPossessionResultSummeries = individualPossessionResultSummeries;
   }
}
