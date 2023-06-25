package com.rickags.models.json.backupfile;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PlayerBreakdown
{
   String playerName;
   List<BigInteger> tempos;
   private BigInteger occurances;

   public PlayerBreakdown ()
   {
      tempos = new ArrayList<>();
   }

   public String getPlayerName ()
   {
      return playerName;
   }

   public void setPlayerName (String playerName)
   {
      this.playerName = playerName;
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
}
