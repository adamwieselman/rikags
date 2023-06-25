package com.rickags.models.json.backupfile;

import java.math.BigInteger;

import com.rickags.extractors.PlayTypeInformationExtractor;
import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

public class PlayTracker
{
   Integer playNumber;
   String playDetail;
   String playNextDetail;
   String playNextNextDetail;
   String playPlayer;
   String playNextPlayer;
   String playNextNextPlayer;
   String overallPlayType;
   String overallPlayTypeNext;
   String overallPlayTypeNextNext;
   boolean isOpponent;
   String altPlayType;
   String priorTimeFrameType;
   String priorPlayType;
   String playTypeInformationPast;
   String groupingName;
   String groupingName1;
   BigInteger priorPossessionDifference;
   BigInteger priorPointsDifference;
   BigInteger tempo;
   String overallPlayResult;
   String overallPlayResultIndiv;


   public PlayTracker (int initialX,
                       EspnPlayByPlay pbp,
                       EspnPlayByPlay pbpNext,
                       EspnPlayByPlay pbpNextNext,
                       PlayTypeInformationExtractor playTypeInformation,
                       PlayTypeInformationExtractor playTypeInformationNext,
                       PlayTypeInformationExtractor playTypeInformationNextNext,
                       boolean isOpponent,
                       String altPlayType,
                       String priorTimeFrameType,
                       String priorPlayType,
                       String groupingName,
                       String groupingName1,
                       BigInteger priorPossessionDifference,
                       BigInteger priorPointsDifference,
                       BigInteger tempo)
   {
      this.playNumber = initialX;
      this.playDetail = pbp.getGameDetail();
      this.playPlayer = playTypeInformation.getPlayPlayer();
      this.overallPlayType = playTypeInformation.getOverallPlayType();
      if(playTypeInformationNext != null) {
         this.playNextDetail = pbpNext.getGameDetail();
         this.playNextPlayer = playTypeInformationNext.getPlayPlayer();
         this.overallPlayTypeNext = playTypeInformationNext.getOverallPlayType();
      }
      if(playTypeInformationNextNext != null) {
         this.playNextNextDetail = pbpNextNext.getGameDetail();
         this.playNextNextPlayer = playTypeInformationNextNext.getPlayPlayer();
         this.overallPlayTypeNextNext = playTypeInformationNextNext.getOverallPlayType();
      }
      this.isOpponent = isOpponent;
      this.altPlayType = altPlayType;
      this.priorTimeFrameType = priorTimeFrameType;
      this.priorPlayType = priorPlayType;
      this.groupingName = groupingName;
      this.groupingName1 = groupingName1;
      this.priorPossessionDifference = priorPossessionDifference;
      this.priorPointsDifference = priorPointsDifference;
      this.tempo = tempo;
   }

   public Integer getPlayNumber ()
   {
      return playNumber;
   }

   public void setPlayNumber (Integer playNumber)
   {
      this.playNumber = playNumber;
   }

   public String getPlayDetail ()
   {
      return playDetail;
   }

   public void setPlayDetail (String playDetail)
   {
      this.playDetail = playDetail;
   }

   public String getPlayNextDetail ()
   {
      return playNextDetail;
   }

   public void setPlayNextDetail (String playNextDetail)
   {
      this.playNextDetail = playNextDetail;
   }

   public String getPlayNextNextDetail ()
   {
      return playNextNextDetail;
   }

   public void setPlayNextNextDetail (String playNextNextDetail)
   {
      this.playNextNextDetail = playNextNextDetail;
   }

   public String getPlayPlayer ()
   {
      return playPlayer;
   }

   public void setPlayPlayer (String playPlayer)
   {
      this.playPlayer = playPlayer;
   }

   public String getPlayNextPlayer ()
   {
      return playNextPlayer;
   }

   public void setPlayNextPlayer (String playNextPlayer)
   {
      this.playNextPlayer = playNextPlayer;
   }

   public String getPlayNextNextPlayer ()
   {
      return playNextNextPlayer;
   }

   public void setPlayNextNextPlayer (String playNextNextPlayer)
   {
      this.playNextNextPlayer = playNextNextPlayer;
   }

   public boolean isOpponent ()
   {
      return isOpponent;
   }

   public void setOpponent (boolean opponent)
   {
      isOpponent = opponent;
   }

   public String getAltPlayType ()
   {
      return altPlayType;
   }

   public void setAltPlayType (String altPlayType)
   {
      this.altPlayType = altPlayType;
   }

   public String getPriorTimeFrameType ()
   {
      return priorTimeFrameType;
   }

   public void setPriorTimeFrameType (String priorTimeFrameType)
   {
      this.priorTimeFrameType = priorTimeFrameType;
   }

   public String getPriorPlayType ()
   {
      return priorPlayType;
   }

   public void setPriorPlayType (String priorPlayType)
   {
      this.priorPlayType = priorPlayType;
   }

   public String getGroupingName ()
   {
      return groupingName;
   }

   public void setGroupingName (String groupingName)
   {
      this.groupingName = groupingName;
   }

   public String getGroupingName1 ()
   {
      return groupingName1;
   }

   public void setGroupingName1 (String groupingName1)
   {
      this.groupingName1 = groupingName1;
   }

   public BigInteger getPriorPossessionDifference ()
   {
      return priorPossessionDifference;
   }

   public void setPriorPossessionDifference (BigInteger priorPossessionDifference)
   {
      this.priorPossessionDifference = priorPossessionDifference;
   }

   public BigInteger getPriorPointsDifference ()
   {
      return priorPointsDifference;
   }

   public void setPriorPointsDifference (BigInteger priorPointsDifference)
   {
      this.priorPointsDifference = priorPointsDifference;
   }

   public BigInteger getTempo ()
   {
      return tempo;
   }

   public void setTempo (BigInteger tempo)
   {
      this.tempo = tempo;
   }

   public String getOverallPlayType ()
   {
      return overallPlayType;
   }

   public void setOverallPlayType (String overallPlayType)
   {
      this.overallPlayType = overallPlayType;
   }

   public String getOverallPlayTypeNext ()
   {
      return overallPlayTypeNext;
   }

   public void setOverallPlayTypeNext (String overallPlayTypeNext)
   {
      this.overallPlayTypeNext = overallPlayTypeNext;
   }

   public String getOverallPlayTypeNextNext ()
   {
      return overallPlayTypeNextNext;
   }

   public void setOverallPlayTypeNextNext (String overallPlayTypeNextNext)
   {
      this.overallPlayTypeNextNext = overallPlayTypeNextNext;
   }

   public String getOverallPlayResult ()
   {
      return overallPlayResult;
   }

   public void setOverallPlayResult (String overallPlayResult)
   {
      this.overallPlayResult = overallPlayResult;
   }

   public String getPlayTypeInformationPast ()
   {
      return playTypeInformationPast;
   }

   public void setPlayTypeInformationPast (String playTypeInformationPast)
   {
      this.playTypeInformationPast = playTypeInformationPast;
   }

   public String getOverallPlayResultIndiv ()
   {
      return overallPlayResultIndiv;
   }

   public void setOverallPlayResultIndiv (String overallPlayResultIndiv)
   {
      this.overallPlayResultIndiv = overallPlayResultIndiv;
   }
}
