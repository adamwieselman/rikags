package com.rickags.models;

import java.math.BigInteger;

public class GameIssue
{
   private BigInteger minutesTime;
   private BigInteger secondsTime;
   private String priorOverallPlayType;
   private String priorPlayType;
   private String priorTeam;
   private String currentOverallPlayType;
   private String currentPlayType;
   private String currentTeam;

   public BigInteger getMinutesTime ()
   {
      return minutesTime;
   }

   public void setMinutesTime (BigInteger minutesTime)
   {
      this.minutesTime = minutesTime;
   }

   public BigInteger getSecondsTime ()
   {
      return secondsTime;
   }

   public void setSecondsTime (BigInteger secondsTime)
   {
      this.secondsTime = secondsTime;
   }

   public String getPriorOverallPlayType ()
   {
      return priorOverallPlayType;
   }

   public void setPriorOverallPlayType (String priorOverallPlayType)
   {
      this.priorOverallPlayType = priorOverallPlayType;
   }

   public String getPriorPlayType ()
   {
      return priorPlayType;
   }

   public void setPriorPlayType (String priorPlayType)
   {
      this.priorPlayType = priorPlayType;
   }

   public String getPriorTeam ()
   {
      return priorTeam;
   }

   public void setPriorTeam (String priorTeam)
   {
      this.priorTeam = priorTeam;
   }

   public String getCurrentOverallPlayType ()
   {
      return currentOverallPlayType;
   }

   public void setCurrentOverallPlayType (String currentOverallPlayType)
   {
      this.currentOverallPlayType = currentOverallPlayType;
   }

   public String getCurrentPlayType ()
   {
      return currentPlayType;
   }

   public void setCurrentPlayType (String currentPlayType)
   {
      this.currentPlayType = currentPlayType;
   }

   public String getCurrentTeam ()
   {
      return currentTeam;
   }

   public void setCurrentTeam (String currentTeam)
   {
      this.currentTeam = currentTeam;
   }
}
