package com.rickags.models.json.file;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FreeThrowResultBreakdown
{
   private BigInteger totalOccurrences;
   private BigInteger totalSuccesses;
   private BigDecimal freeThrowPercentage;
   private List<IndividualFreeThrowResultData> individualFreeThrowResultDataList;


   public FreeThrowResultBreakdown ()
   {
      totalOccurrences = BigInteger.ZERO;
      totalSuccesses = BigInteger.ZERO;
      individualFreeThrowResultDataList = new ArrayList<>();
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

   public BigDecimal getFreeThrowPercentage ()
   {
      return freeThrowPercentage;
   }

   public void setFreeThrowPercentage (BigDecimal freeThrowPercentage)
   {
      this.freeThrowPercentage = freeThrowPercentage;
   }

   public List<IndividualFreeThrowResultData> getIndividualFreeThrowResultDataList ()
   {
      return individualFreeThrowResultDataList;
   }

   public void setIndividualFreeThrowResultDataList (List<IndividualFreeThrowResultData> individualFreeThrowResultDataList)
   {
      this.individualFreeThrowResultDataList = individualFreeThrowResultDataList;
   }
}
