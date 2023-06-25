package com.rickags.models.json.file;

import java.math.BigDecimal;
import java.math.BigInteger;

public class IndividualShotResultDataSummary
{
   private String playPlayer;
   private BigInteger totalIndividualOccurrences;
   private BigInteger totalIndividualSuccesses;
   private BigDecimal totalIndividualSuccessPercentage;
   private BigDecimal totalIndividualSuccessPercentageStrength;
   private BigDecimal gameIndividualSuccessPercentageAverage;
   private BigDecimal gameIndividualSuccessPercentageStDev;
   private BigDecimal gameIndividualSuccessPercentageStrength;
   private BigDecimal weightedGameIndividualSuccessPercentageAverage;
   private BigDecimal weightedGameIndividualSuccessPercentageStDev;
   private BigDecimal weightedGameIndividualSuccessPercentageStrength;

   public String getPlayPlayer ()
   {
      return playPlayer;
   }

   public void setPlayPlayer (String playPlayer)
   {
      this.playPlayer = playPlayer;
   }

   public BigInteger getTotalIndividualOccurrences ()
   {
      return totalIndividualOccurrences;
   }

   public void setTotalIndividualOccurrences (BigInteger totalIndividualOccurrences)
   {
      this.totalIndividualOccurrences = totalIndividualOccurrences;
   }

   public BigInteger getTotalIndividualSuccesses ()
   {
      return totalIndividualSuccesses;
   }

   public void setTotalIndividualSuccesses (BigInteger totalIndividualSuccesses)
   {
      this.totalIndividualSuccesses = totalIndividualSuccesses;
   }

   public BigDecimal getTotalIndividualSuccessPercentage ()
   {
      return totalIndividualSuccessPercentage;
   }

   public void setTotalIndividualSuccessPercentage (BigDecimal totalIndividualSuccessPercentage)
   {
      this.totalIndividualSuccessPercentage = totalIndividualSuccessPercentage;
   }

   public BigDecimal getTotalIndividualSuccessPercentageStrength ()
   {
      return totalIndividualSuccessPercentageStrength;
   }

   public void setTotalIndividualSuccessPercentageStrength (BigDecimal totalIndividualSuccessPercentageStrength)
   {
      this.totalIndividualSuccessPercentageStrength = totalIndividualSuccessPercentageStrength;
   }

   public BigDecimal getGameIndividualSuccessPercentageAverage ()
   {
      return gameIndividualSuccessPercentageAverage;
   }

   public void setGameIndividualSuccessPercentageAverage (BigDecimal gameIndividualSuccessPercentageAverage)
   {
      this.gameIndividualSuccessPercentageAverage = gameIndividualSuccessPercentageAverage;
   }

   public BigDecimal getGameIndividualSuccessPercentageStDev ()
   {
      return gameIndividualSuccessPercentageStDev;
   }

   public void setGameIndividualSuccessPercentageStDev (BigDecimal gameIndividualSuccessPercentageStDev)
   {
      this.gameIndividualSuccessPercentageStDev = gameIndividualSuccessPercentageStDev;
   }

   public BigDecimal getGameIndividualSuccessPercentageStrength ()
   {
      return gameIndividualSuccessPercentageStrength;
   }

   public void setGameIndividualSuccessPercentageStrength (BigDecimal gameIndividualSuccessPercentageStrength)
   {
      this.gameIndividualSuccessPercentageStrength = gameIndividualSuccessPercentageStrength;
   }

   public BigDecimal getWeightedGameIndividualSuccessPercentageAverage ()
   {
      return weightedGameIndividualSuccessPercentageAverage;
   }

   public void setWeightedGameIndividualSuccessPercentageAverage (BigDecimal weightedGameIndividualSuccessPercentageAverage)
   {
      this.weightedGameIndividualSuccessPercentageAverage = weightedGameIndividualSuccessPercentageAverage;
   }

   public BigDecimal getWeightedGameIndividualSuccessPercentageStDev ()
   {
      return weightedGameIndividualSuccessPercentageStDev;
   }

   public void setWeightedGameIndividualSuccessPercentageStDev (BigDecimal weightedGameIndividualSuccessPercentageStDev)
   {
      this.weightedGameIndividualSuccessPercentageStDev = weightedGameIndividualSuccessPercentageStDev;
   }

   public BigDecimal getWeightedGameIndividualSuccessPercentageStrength ()
   {
      return weightedGameIndividualSuccessPercentageStrength;
   }

   public void setWeightedGameIndividualSuccessPercentageStrength (BigDecimal weightedGameIndividualSuccessPercentageStrength)
   {
      this.weightedGameIndividualSuccessPercentageStrength = weightedGameIndividualSuccessPercentageStrength;
   }
}
