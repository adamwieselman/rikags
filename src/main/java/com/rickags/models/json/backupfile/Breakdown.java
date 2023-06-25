package com.rickags.models.json.backupfile;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Breakdown
{
   private LocalDate startDate;
   private LocalDate endDate;
   private String gameTimeFrame;
   private String possessionStart;
   private BigInteger possessionScoreDifference;
   private List<PossessionResultBreakdown> possessionResultBreakdowns;

   public Breakdown ()
   {
      possessionResultBreakdowns = new ArrayList<>();
   }

   public LocalDate getStartDate ()
   {
      return startDate;
   }

   public void setStartDate (LocalDate startDate)
   {
      this.startDate = startDate;
   }

   public LocalDate getEndDate ()
   {
      return endDate;
   }

   public void setEndDate (LocalDate endDate)
   {
      this.endDate = endDate;
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

   public BigInteger getPossessionScoreDifference ()
   {
      return possessionScoreDifference;
   }

   public void setPossessionScoreDifference (BigInteger possessionScoreDifference)
   {
      this.possessionScoreDifference = possessionScoreDifference;
   }

   public List<PossessionResultBreakdown> getPossessionResultBreakdowns ()
   {
      return possessionResultBreakdowns;
   }

   public void setPossessionResultBreakdowns (List<PossessionResultBreakdown> possessionResultBreakdowns)
   {
      this.possessionResultBreakdowns = possessionResultBreakdowns;
   }
}
