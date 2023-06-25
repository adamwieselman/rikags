package com.rickags.facade;

import java.math.BigInteger;

import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

public class PbpFacade
{
   EspnPlayByPlay pbp;

   public PbpFacade (EspnPlayByPlay pbp)
   {
      this.pbp = pbp;
   }

   public PbpFacade(){

   }

   public String getGameHalf()
   {
      return pbp.getGameHalf();
   }

   public String getGameDetail(){
      return pbp.getGameDetail();
   }

   public BigInteger getTime(){

      String[] timeparts = pbp.getTimeStamp().split(":");

      BigInteger timeSeconds = BigInteger.ZERO;

      if (pbp.getGameHalf().equals("1")) {
         timeSeconds = new BigInteger(timeparts[0]).add(new BigInteger("20")).multiply(new BigInteger("60")).add(new BigInteger(timeparts[1]));
      } else {
         timeSeconds = new BigInteger(timeparts[0]).multiply(new BigInteger("60")).add(new BigInteger(timeparts[1]));
      }

      return timeSeconds;
   }

   public boolean isOpponent (String teamNumber)
   {
      int pngSpot = pbp.getTeamLogo().indexOf(".png");
      int slashSpot = pbp.getTeamLogo().lastIndexOf("/", pngSpot);

      String playTeamNumber = pbp.getTeamLogo().substring(slashSpot + 1, pngSpot);

      if (playTeamNumber.equals(teamNumber)) {
         return false;
      }

      return true;
   }

   public String getOpponent()
   {
      int pngSpot = pbp.getTeamLogo().indexOf(".png");
      int slashSpot = pbp.getTeamLogo().lastIndexOf("/", pngSpot);

      String playTeamNumber = pbp.getTeamLogo().substring(slashSpot + 1, pngSpot);

      return playTeamNumber;
   }

   public BigInteger getPointsDifference()
   {
      String[] scorePoints = pbp.getCombinedScore().split("-");
      return new BigInteger(scorePoints[0].trim()).subtract(new BigInteger(scorePoints[1].trim()));
   }

   public BigInteger getPossessionDifference(){
      String[] scorePoints = pbp.getCombinedScore().split("-");
      BigInteger priorPointsDiff = new BigInteger(scorePoints[0].trim()).subtract(new BigInteger(scorePoints[1].trim()));
      BigInteger priorPossessionDiff = priorPointsDiff.divide(new BigInteger("3"));
      BigInteger priorPossessionDiffRem = priorPointsDiff.mod(new BigInteger("3"));

      int priorPossessionDiffCompare = priorPossessionDiff.compareTo(BigInteger.ZERO);

      if(!priorPossessionDiffRem.equals(BigInteger.ZERO)){
         if(priorPossessionDiffCompare < 0){
            priorPossessionDiff = priorPossessionDiff.subtract(BigInteger.ONE);
         }
         if(priorPossessionDiffCompare > 0){
            priorPossessionDiff = priorPossessionDiff.add(BigInteger.ONE);
         }
         if(priorPossessionDiffCompare == 0){
            if(priorPointsDiff.compareTo(BigInteger.ZERO)<0){
               priorPossessionDiff = priorPossessionDiff.subtract(BigInteger.ONE);
            }else{
               priorPossessionDiff = priorPossessionDiff.add(BigInteger.ONE);
            }
         }
      }

      if(priorPossessionDiff.compareTo(new BigInteger("4")) > 0){
         priorPossessionDiff = new BigInteger("4");
      }

      if(priorPossessionDiff.compareTo(new BigInteger("-4"))< 0){
         priorPossessionDiff = new BigInteger("-4");
      }

      return priorPossessionDiff;
   }
}
