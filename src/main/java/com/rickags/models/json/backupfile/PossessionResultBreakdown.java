package com.rickags.models.json.backupfile;

import java.math.BigDecimal;

public class PossessionResultBreakdown
{
   private String possessionResult;
   private BigDecimal overallPctAllOccurances;
   private BigDecimal strengthNumberAllOccurances;
   private BigDecimal avgPctByGame;
   private BigDecimal stdevByGame;
   private BigDecimal strengthNumberByGame;
   private BigDecimal avgPctByGameWeighted;
   private BigDecimal stdevByGameWeighted;
   private BigDecimal strengthNumberByGameWeighted;

   public String getPossessionResult ()
   {
      return possessionResult;
   }

   public void setPossessionResult (String possessionResult)
   {
      this.possessionResult = possessionResult;
   }

   public BigDecimal getOverallPctAllOccurances ()
   {
      return overallPctAllOccurances;
   }

   public void setOverallPctAllOccurances (BigDecimal overallPctAllOccurances)
   {
      this.overallPctAllOccurances = overallPctAllOccurances;
   }

   public BigDecimal getStrengthNumberAllOccurances ()
   {
      return strengthNumberAllOccurances;
   }

   public void setStrengthNumberAllOccurances (BigDecimal strengthNumberAllOccurances)
   {
      this.strengthNumberAllOccurances = strengthNumberAllOccurances;
   }

   public BigDecimal getAvgPctByGame ()
   {
      return avgPctByGame;
   }

   public void setAvgPctByGame (BigDecimal avgPctByGame)
   {
      this.avgPctByGame = avgPctByGame;
   }

   public BigDecimal getStdevByGame ()
   {
      return stdevByGame;
   }

   public void setStdevByGame (BigDecimal stdevByGame)
   {
      this.stdevByGame = stdevByGame;
   }

   public BigDecimal getStrengthNumberByGame ()
   {
      return strengthNumberByGame;
   }

   public void setStrengthNumberByGame (BigDecimal strengthNumberByGame)
   {
      this.strengthNumberByGame = strengthNumberByGame;
   }

   public BigDecimal getAvgPctByGameWeighted ()
   {
      return avgPctByGameWeighted;
   }

   public void setAvgPctByGameWeighted (BigDecimal avgPctByGameWeighted)
   {
      this.avgPctByGameWeighted = avgPctByGameWeighted;
   }

   public BigDecimal getStdevByGameWeighted ()
   {
      return stdevByGameWeighted;
   }

   public void setStdevByGameWeighted (BigDecimal stdevByGameWeighted)
   {
      this.stdevByGameWeighted = stdevByGameWeighted;
   }

   public BigDecimal getStrengthNumberByGameWeighted ()
   {
      return strengthNumberByGameWeighted;
   }

   public void setStrengthNumberByGameWeighted (BigDecimal strengthNumberByGameWeighted)
   {
      this.strengthNumberByGameWeighted = strengthNumberByGameWeighted;
   }
}
