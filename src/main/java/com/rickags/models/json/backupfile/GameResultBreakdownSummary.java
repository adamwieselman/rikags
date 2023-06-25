package com.rickags.models.json.backupfile;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class GameResultBreakdownSummary
{
   private String gameTimeFrame;
   private BigInteger possessionDifference;
   private BigInteger possessionScoreDifference;
   private String possessionStart;
   private String groupingName;
   private BigInteger totalOccurances;
   private List<PossessionResultSummary> possessionResultSummaries;

   public GameResultBreakdownSummary ()
   {
      possessionResultSummaries = new ArrayList<>();
   }

   public String getGameTimeFrame ()
   {
      return gameTimeFrame;
   }

   public void setGameTimeFrame (String gameTimeFrame)
   {
      this.gameTimeFrame = gameTimeFrame;
   }

   public BigInteger getPossessionDifference ()
   {
      return possessionDifference;
   }

   public void setPossessionDifference (BigInteger possessionDifference)
   {
      this.possessionDifference = possessionDifference;
   }

   public BigInteger getPossessionScoreDifference ()
   {
      return possessionScoreDifference;
   }

   public void setPossessionScoreDifference (BigInteger possessionScoreDifference)
   {
      this.possessionScoreDifference = possessionScoreDifference;
   }

   public String getPossessionStart ()
   {
      return possessionStart;
   }

   public void setPossessionStart (String possessionStart)
   {
      this.possessionStart = possessionStart;
   }

   public String getGroupingName ()
   {
      return groupingName;
   }

   public void setGroupingName (String groupingName)
   {
      this.groupingName = groupingName;
   }

   public List<PossessionResultSummary> getPossessionResultSummaries ()
   {
      return possessionResultSummaries;
   }

   public void setPossessionResultSummaries (List<PossessionResultSummary> possessionResultSummaries)
   {
      this.possessionResultSummaries = possessionResultSummaries;
   }

   public BigInteger getTotalOccurances ()
   {
      return totalOccurances;
   }

   public void setTotalOccurances (BigInteger totalOccurances)
   {
      this.totalOccurances = totalOccurances;
   }
}
