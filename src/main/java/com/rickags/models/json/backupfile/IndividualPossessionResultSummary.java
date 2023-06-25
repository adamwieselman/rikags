package com.rickags.models.json.backupfile;

import java.math.BigDecimal;
import java.math.BigInteger;

public class IndividualPossessionResultSummary
{
   private String playerName;
   private BigInteger occurances;
   private BigDecimal occurancePercentage;
   private BigInteger successes;
   private BigDecimal successesPercentage;
   private BigInteger offensiveReboundingMissed;
   private BigDecimal offensiveReboundingMissedPercentage;

   public String getPlayerName ()
   {
      return playerName;
   }

   public void setPlayerName (String playerName)
   {
      this.playerName = playerName;
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
}
