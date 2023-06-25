package com.rickags.models.json.file;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PossessionResultBreakdown
{
   private String gameTimeFrame;
   private String possessionStart;
   private String offensiveGroupingName;
   private String defensiveGroupingName;
   private BigInteger possessionDifference;
   private BigInteger pointsDifference;
   private BigInteger totalOccurrences;
   private List<PossessionResultData> possessionResultDataList;

   public PossessionResultBreakdown ()
   {
      possessionResultDataList = new ArrayList<>();
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

   public String getOffensiveGroupingName ()
   {
      return offensiveGroupingName;
   }

   public void setOffensiveGroupingName (String offensiveGroupingName)
   {
      this.offensiveGroupingName = offensiveGroupingName;
   }

   public String getDefensiveGroupingName ()
   {
      return defensiveGroupingName;
   }

   public void setDefensiveGroupingName (String defensiveGroupingName)
   {
      this.defensiveGroupingName = defensiveGroupingName;
   }

   public BigInteger getTotalOccurrences ()
   {
      return totalOccurrences;
   }

   public void setTotalOccurrences (BigInteger totalOccurrences)
   {
      this.totalOccurrences = totalOccurrences;
   }

   public BigInteger getPossessionDifference ()
   {
      return possessionDifference;
   }

   public void setPossessionDifference (BigInteger possessionDifference)
   {
      this.possessionDifference = possessionDifference;
   }

   public BigInteger getPointsDifference ()
   {
      return pointsDifference;
   }

   public void setPointsDifference (BigInteger pointsDifference)
   {
      this.pointsDifference = pointsDifference;
   }

   public List<PossessionResultData> getPossessionResultDataList ()
   {
      return possessionResultDataList;
   }

   public void setPossessionResultDataList (List<PossessionResultData> possessionResultDataList)
   {
      this.possessionResultDataList = possessionResultDataList;
   }
}
