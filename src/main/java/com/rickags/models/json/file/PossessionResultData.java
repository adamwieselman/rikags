package com.rickags.models.json.file;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PossessionResultData
{
   private String overallPossessionResult;
   private BigInteger occurrences;
   private BigDecimal occurrencePercentage;
   private BigDecimal tempoAverage;
   private BigDecimal tempoStDev;
   private List<BigInteger> tempos;
   private List<IndividualPossessionResultData> individualPossessionResultDataList;

   public PossessionResultData ()
   {
      tempos = new ArrayList<>();
      individualPossessionResultDataList = new ArrayList<>();
   }

   public String getOverallPossessionResult ()
   {
      return overallPossessionResult;
   }

   public void setOverallPossessionResult (String overallPossessionResult)
   {
      this.overallPossessionResult = overallPossessionResult;
   }

   public BigInteger getOccurrences ()
   {
      return occurrences;
   }

   public void setOccurrences (BigInteger occurrences)
   {
      this.occurrences = occurrences;
   }

   public BigDecimal getOccurrencePercentage ()
   {
      return occurrencePercentage;
   }

   public void setOccurrencePercentage (BigDecimal occurrencePercentage)
   {
      this.occurrencePercentage = occurrencePercentage;
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

   public List<IndividualPossessionResultData> getIndividualPossessionResultDataList ()
   {
      return individualPossessionResultDataList;
   }

   public void setIndividualPossessionResultDataList (List<IndividualPossessionResultData> individualPossessionResultDataList)
   {
      this.individualPossessionResultDataList = individualPossessionResultDataList;
   }
}
