package com.rickags.models.json.backupfile;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class GameResultSubsetBreakdown
{
   String subsetPossessionResult;
   BigInteger occurances;
   List<BigInteger> tempos;
   List<PlayerBreakdown> playerBreakdowns;

   public GameResultSubsetBreakdown ()
   {
      tempos = new ArrayList<>();
      playerBreakdowns = new ArrayList<>();
   }

   public String getSubsetPossessionResult ()
   {
      return subsetPossessionResult;
   }

   public void setSubsetPossessionResult (String subsetPossessionResult)
   {
      this.subsetPossessionResult = subsetPossessionResult;
   }

   public BigInteger getOccurances ()
   {
      return occurances;
   }

   public void setOccurances (BigInteger occurances)
   {
      this.occurances = occurances;
   }

   public List<BigInteger> getTempos ()
   {
      return tempos;
   }

   public void setTempos (List<BigInteger> tempos)
   {
      this.tempos = tempos;
   }

   public List<PlayerBreakdown> getPlayerBreakdowns ()
   {
      return playerBreakdowns;
   }

   public void setPlayerBreakdowns (List<PlayerBreakdown> playerBreakdowns)
   {
      this.playerBreakdowns = playerBreakdowns;
   }
}
