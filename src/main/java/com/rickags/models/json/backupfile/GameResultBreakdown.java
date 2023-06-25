package com.rickags.models.json.backupfile;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class GameResultBreakdown
{
   private String gameTimeFrame;
   private BigInteger possessionDifference;
   private BigInteger possessionScoreDifference;
   private String possessionStart;
   private String overallPossessionResult;
   private boolean offense;
   private String groupingName;
   private BigInteger occurances;
   List<BigInteger> tempos;
   List<GameResultSubsetBreakdown> gameResultSubsetBreakdowns;

   public GameResultBreakdown ()
   {
      tempos = new ArrayList<>();
      gameResultSubsetBreakdowns = new ArrayList<>();
   }

   public String getGameTimeFrame ()
   {
      return gameTimeFrame;
   }

   public void setGameTimeFrame (String gameTimeFrame)
   {
      this.gameTimeFrame = gameTimeFrame;
   }

   public BigInteger getPossessionScoreDifference ()
   {
      return possessionScoreDifference;
   }

   public BigInteger getPossessionDifference ()
   {
      return possessionDifference;
   }

   public void setPossessionDifference (BigInteger possessionDifference)
   {
      this.possessionDifference = possessionDifference;
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

   public String getOverallPossessionResult ()
   {
      return overallPossessionResult;
   }

   public void setOverallPossessionResult (String overallPossessionResult)
   {
      this.overallPossessionResult = overallPossessionResult;
   }

   public boolean isOffense ()
   {
      return offense;
   }

   public void setOffense (boolean offense)
   {
      this.offense = offense;
   }

   public List<BigInteger> getTempos ()
   {
      return tempos;
   }

   public void setTempos (List<BigInteger> tempos)
   {
      this.tempos = tempos;
   }

   public BigInteger getOccurances ()
   {
      return occurances;
   }

   public void setOccurances (BigInteger occurances)
   {
      this.occurances = occurances;
   }

   public String getGroupingName ()
   {
      return groupingName;
   }

   public void setGroupingName (String groupingName)
   {
      this.groupingName = groupingName;
   }

   public List<GameResultSubsetBreakdown> getGameResultSubsetBreakdowns ()
   {
      return gameResultSubsetBreakdowns;
   }

   public void setGameResultSubsetBreakdowns (List<GameResultSubsetBreakdown> gameResultSubsetBreakdowns)
   {
      this.gameResultSubsetBreakdowns = gameResultSubsetBreakdowns;
   }
}
