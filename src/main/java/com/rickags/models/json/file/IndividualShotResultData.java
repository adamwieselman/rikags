package com.rickags.models.json.file;

import java.math.BigDecimal;
import java.math.BigInteger;

public class IndividualShotResultData
{
   private String playPlayer;
   private BigInteger individualOccurrences;
   private BigInteger individualSuccesses;
   private BigDecimal individualSuccessPercentage;

   public String getPlayPlayer ()
   {
      return playPlayer;
   }

   public void setPlayPlayer (String playPlayer)
   {
      this.playPlayer = playPlayer;
   }

   public BigInteger getIndividualOccurrences ()
   {
      return individualOccurrences;
   }

   public void setIndividualOccurrences (BigInteger individualOccurrences)
   {
      this.individualOccurrences = individualOccurrences;
   }

   public BigInteger getIndividualSuccesses ()
   {
      return individualSuccesses;
   }

   public void setIndividualSuccesses (BigInteger individualSuccesses)
   {
      this.individualSuccesses = individualSuccesses;
   }

   public BigDecimal getIndividualSuccessPercentage ()
   {
      return individualSuccessPercentage;
   }

   public void setIndividualSuccessPercentage (BigDecimal individualSuccessPercentag)
   {
      this.individualSuccessPercentage = individualSuccessPercentag;
   }
}
