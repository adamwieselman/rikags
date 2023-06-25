package com.rickags.models.json.file;

import java.math.BigDecimal;
import java.math.BigInteger;

public class IndividualPossessionResultData
{
   private String playPlayer;
   private BigInteger individualOccurrences;
   private BigDecimal individualOccurrencePercentage;

   public BigInteger getIndividualOccurrences ()
   {
      return individualOccurrences;
   }

   public void setIndividualOccurrences (BigInteger individualOccurrences)
   {
      this.individualOccurrences = individualOccurrences;
   }

   public BigDecimal getIndividualOccurrencePercentage ()
   {
      return individualOccurrencePercentage;
   }

   public void setIndividualOccurrencePercentage (BigDecimal individualOccurrencePercentage)
   {
      this.individualOccurrencePercentage = individualOccurrencePercentage;
   }

   public String getPlayPlayer ()
   {
      return playPlayer;
   }

   public void setPlayPlayer (String playPlayer)
   {
      this.playPlayer = playPlayer;
   }
}
