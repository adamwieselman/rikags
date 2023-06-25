package com.rickags.models.json.backupfile;

import java.math.BigInteger;

public class PlayTrackerDisect
{
   BigInteger occurances;
   String playType;
   boolean isOpponent;
   String playTypeNext;
   boolean isOpponentNext;
   String firstInstanceFound;
  // String playTypeNextNext;
  // boolean isOpponentNextNext;


   public PlayTrackerDisect (BigInteger occurances,
                             String playType,
                             boolean isOpponent,
                             String playTypeNext,
                             boolean isOpponentNext,
                             String firstInstanceFound)
                             //String playTypeNextNext,
                            // boolean isOpponentNextNext)
   {
      this.occurances = occurances;
      this.playType = playType;
      this.isOpponent = isOpponent;
      this.playTypeNext = playTypeNext;
      this.isOpponentNext = isOpponentNext;
      this.firstInstanceFound = firstInstanceFound;
    //  this.playTypeNextNext = playTypeNextNext;
    //  this.isOpponentNextNext = isOpponentNextNext;
   }

   public BigInteger getOccurances ()
   {
      return occurances;
   }

   public void setOccurances (BigInteger occurances)
   {
      this.occurances = occurances;
   }

   public String getPlayType ()
   {
      return playType;
   }

   public void setPlayType (String playType)
   {
      this.playType = playType;
   }

   public boolean isOpponent ()
   {
      return isOpponent;
   }

   public void setOpponent (boolean opponent)
   {
      isOpponent = opponent;
   }

   public String getPlayTypeNext ()
   {
      return playTypeNext;
   }

   public void setPlayTypeNext (String playTypeNext)
   {
      this.playTypeNext = playTypeNext;
   }

   public boolean isOpponentNext ()
   {
      return isOpponentNext;
   }

   public void setOpponentNext (boolean opponentNext)
   {
      isOpponentNext = opponentNext;
   }

   public String getFirstInstanceFound ()
   {
      return firstInstanceFound;
   }

   public void setFirstInstanceFound (String firstInstanceFound)
   {
      this.firstInstanceFound = firstInstanceFound;
   }

   //   public String getPlayTypeNextNext ()
//   {
//      return playTypeNextNext;
//   }
//
//   public void setPlayTypeNextNext (String playTypeNextNext)
//   {
//      this.playTypeNextNext = playTypeNextNext;
//   }
//
//   public boolean isOpponentNextNext ()
//   {
//      return isOpponentNextNext;
//   }
//
//   public void setOpponentNextNext (boolean opponentNextNext)
//   {
//      isOpponentNextNext = opponentNextNext;
//   }
}
