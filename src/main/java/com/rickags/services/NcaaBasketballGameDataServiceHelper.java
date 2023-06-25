package com.rickags.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rickags.extractors.PlayTypeInformationExtractor;
import com.rickags.factories.BasicObjectFactory;
import com.rickags.models.json.file.Possession;

@Component
public class NcaaBasketballGameDataServiceHelper
{
   @Autowired
   BasicObjectFactory basicObjectFactory;

   public void determinePossessionOwner(Possession possession, String teamId){
      List<PlayTypeInformationExtractor> possessionPlays = possession.getPossessionPlays();

      PlayTypeInformationExtractor firstPlayOfPossession = possessionPlays.get(0);
      if(firstPlayOfPossession.getBeginningOrEnding().equals("BEGIN") && (firstPlayOfPossession.getPlayType().equals("OFFREB") || firstPlayOfPossession.getPlayType().equals("DEFREB") ||firstPlayOfPossession.getPlayType().equals("TEAMREB"))){
         possession.setOpponentsPossession(firstPlayOfPossession.isOpponent(teamId));
         return;
      }

      for(int x = possessionPlays.size()-1; x > -1 ;--x){
         PlayTypeInformationExtractor playOfPossession = possessionPlays.get(x);
         if(playOfPossession.getOverallPlayType().equals("STEAL") || playOfPossession.getOverallPlayType().equals("FOUL") || playOfPossession.getOverallPlayType().equals("BLOCK")){
            possession.setOpponentsPossession(!playOfPossession.isOpponent(teamId));
            return;
         }

         if(playOfPossession.getOverallPlayType().equals("TURNOVER") || playOfPossession.getOverallPlayType().equals("2PTCLOSEATTEMPT") || playOfPossession.getOverallPlayType().equals("2PTJUMPATTEMPT")|| playOfPossession.getOverallPlayType().equals("3PTATTEMPT") || playOfPossession.getOverallPlayType().equals("FREETHROW")){
            possession.setOpponentsPossession(playOfPossession.isOpponent(teamId));
            return;

         }
      }
   }

   public void determinePossessionTempo(Possession possession){
      PlayTypeInformationExtractor firstPlayOfPossession = possession.getPossessionPlays().get(0);
      PlayTypeInformationExtractor lastPlayOfPossession = possession.getPossessionPlays().get(possession.getPossessionPlays().size()-1);
      possession.setPossessionTempo(firstPlayOfPossession.getTime().subtract(lastPlayOfPossession.getTime()));
   }


   public Possession setupPossession(PlayTypeInformationExtractor playTypeInformation, int possessionNumber){
      Possession possession = basicObjectFactory.createPossession();
      possession.getPossessionPlays().add(playTypeInformation);
      possession.setPossessionNumber(++possessionNumber);

      return possession;
   }

   public void determinePossessionError (Possession possession,
                                         String teamId)
   {
      for(PlayTypeInformationExtractor play : possession.getPossessionPlays()){
         if(play.isOpponent(teamId) != possession.isOpponentsPossession() && (!play.getPlayType().equals("FOUL") && !play.getPlayType().equals("STEAL") && !play.getPlayType().equals("BLOCK") && !play.getBeginningOrEnding().equals("STOPPAGE"))){
            possession.setPossessionError(true);
         }
      }
   }

   public boolean isPlayOk (PlayTypeInformationExtractor playTypeInformation,
                            PlayTypeInformationExtractor priorPlayTypeInformation,
                            String teamNumber)
   {
      if(priorPlayTypeInformation == null) {
         return true;
      }

      if(priorPlayTypeInformation.getPlayType().equals("JUMPBALL")){
         if(playTypeInformation.getPlayType().equals("ENDOF") ||
            playTypeInformation.getPlayType().equals("FOUL") ||
            playTypeInformation.getPlayType().equals("JUMPBALL") ||
            playTypeInformation.getPlayType().equals("TIMEOUT") ||
            ((playTypeInformation.getOverallPlayType().equals("2PTCLOSEATTEMPT") ||
               playTypeInformation.getOverallPlayType().equals("2PTJUMPATTEMPT")||
               playTypeInformation.getOverallPlayType().equals("3PTATTEMPT") ||
               playTypeInformation.getPlayType().equals("TURNOVER"))
                  && (playTypeInformation.isOpponent(teamNumber)==priorPlayTypeInformation.isOpponent(teamNumber)))

         ){
            return true;
         }
      }

      if(priorPlayTypeInformation.getPlayType().equals("ENDOF")){
         if(playTypeInformation.getPlayType().equals("ENDOF") ||
            playTypeInformation.getPlayType().equals("FOUL") ||
            playTypeInformation.getPlayType().equals("JUMPBALL") ||
            playTypeInformation.getPlayType().equals("TIMEOUT") ||
            playTypeInformation.getPlayType().equals("TURNOVER") ||
            playTypeInformation.getOverallPlayType().equals("2PTCLOSEATTEMPT") ||
            playTypeInformation.getOverallPlayType().equals("2PTJUMPATTEMPT")||
            playTypeInformation.getOverallPlayType().equals("3PTATTEMPT")
         ){
            return true;
         }
      }

      if(priorPlayTypeInformation.getPlayType().equals("FOUL")){
         if(playTypeInformation.getPlayType().equals("ENDOF") ||
            playTypeInformation.getPlayType().equals("FOUL") ||
            playTypeInformation.getPlayType().equals("JUMPBALL") ||
            playTypeInformation.getPlayType().equals("TIMEOUT") ||
            playTypeInformation.getPlayType().equals("TURNOVER") ||
            ((playTypeInformation.getOverallPlayType().equals("2PTCLOSEATTEMPT") ||
               playTypeInformation.getOverallPlayType().equals("2PTJUMPATTEMPT")||
               playTypeInformation.getOverallPlayType().equals("3PTATTEMPT") ||
               playTypeInformation.getOverallPlayType().equals("FREETHROW"))
               && (playTypeInformation.isOpponent(teamNumber)!=priorPlayTypeInformation.isOpponent(teamNumber)))

         ){
            return true;
         }
      }

      if(priorPlayTypeInformation.getPlayType().equals("TURNOVER")){
         if(playTypeInformation.getPlayType().equals("ENDOF") ||
            playTypeInformation.getPlayType().equals("FOUL") ||
            playTypeInformation.getPlayType().equals("JUMPBALL") ||
            playTypeInformation.getPlayType().equals("TIMEOUT") ||
            ((playTypeInformation.getOverallPlayType().equals("2PTCLOSEATTEMPT") ||
               playTypeInformation.getOverallPlayType().equals("2PTJUMPATTEMPT")||
               playTypeInformation.getOverallPlayType().equals("3PTATTEMPT") ||
               playTypeInformation.getPlayType().equals("TURNOVER") ||
               playTypeInformation.getPlayType().equals("STEAL"))
               && (playTypeInformation.isOpponent(teamNumber)!=priorPlayTypeInformation.isOpponent(teamNumber)))

         ){
            return true;
         }
      }

      if(priorPlayTypeInformation.getPlayType().equals("STEAL")){
         if(playTypeInformation.getPlayType().equals("ENDOF") ||
            playTypeInformation.getPlayType().equals("FOUL") ||
            playTypeInformation.getPlayType().equals("JUMPBALL") ||
            playTypeInformation.getPlayType().equals("TIMEOUT") ||
            ((playTypeInformation.getOverallPlayType().equals("2PTCLOSEATTEMPT") ||
               playTypeInformation.getOverallPlayType().equals("2PTJUMPATTEMPT")||
               playTypeInformation.getOverallPlayType().equals("3PTATTEMPT") ||
               playTypeInformation.getPlayType().equals("TURNOVER"))
               && (playTypeInformation.isOpponent(teamNumber)==priorPlayTypeInformation.isOpponent(teamNumber)))

         ){
            return true;
         }
      }


      if(priorPlayTypeInformation.getPlayType().equals("TIMEOUT")){
         if(playTypeInformation.getPlayType().equals("ENDOF") ||
            playTypeInformation.getPlayType().equals("FOUL") ||
            playTypeInformation.getPlayType().equals("JUMPBALL") ||
            playTypeInformation.getPlayType().equals("TIMEOUT") ||
            playTypeInformation.getOverallPlayType().equals("2PTCLOSEATTEMPT") ||
            playTypeInformation.getOverallPlayType().equals("2PTJUMPATTEMPT")||
            playTypeInformation.getOverallPlayType().equals("3PTATTEMPT") ||
            playTypeInformation.getOverallPlayType().equals("FREETHROW") ||
            playTypeInformation.getPlayType().equals("TURNOVER")
         ){
            return true;
         }
      }

      if(priorPlayTypeInformation.getPlayType().equals("OFFREB")){
         if(playTypeInformation.getPlayType().equals("ENDOF") ||
            playTypeInformation.getPlayType().equals("FOUL") ||
            playTypeInformation.getPlayType().equals("JUMPBALL") ||
            playTypeInformation.getPlayType().equals("TIMEOUT") ||
            ((playTypeInformation.getOverallPlayType().equals("2PTCLOSEATTEMPT") ||
               playTypeInformation.getOverallPlayType().equals("2PTJUMPATTEMPT")||
               playTypeInformation.getOverallPlayType().equals("3PTATTEMPT") ||
               playTypeInformation.getOverallPlayType().equals("FREETHROW") ||
               playTypeInformation.getPlayType().equals("TURNOVER"))
               && (playTypeInformation.isOpponent(teamNumber)==priorPlayTypeInformation.isOpponent(teamNumber)))

         ){
            return true;
         }
      }

      if(priorPlayTypeInformation.getPlayType().equals("MISS3PTJUMP") ||
         priorPlayTypeInformation.getPlayType().equals("MISS2PTCLOSE") ||
         priorPlayTypeInformation.getPlayType().equals("MISS2PTJUMP")){

         if(playTypeInformation.getPlayType().equals("ENDOF") ||
            playTypeInformation.getPlayType().equals("TEAMREB") ||
            ((playTypeInformation.getOverallPlayType().equals("DEFREB") ||
               playTypeInformation.getPlayType().equals("BLOCK"))
               && (playTypeInformation.isOpponent(teamNumber)!=priorPlayTypeInformation.isOpponent(teamNumber))) ||
            ((playTypeInformation.getOverallPlayType().equals("OFFREB"))
               && (playTypeInformation.isOpponent(teamNumber)==priorPlayTypeInformation.isOpponent(teamNumber)))
         ){
            return true;
         }
      }

      if(priorPlayTypeInformation.getPlayType().equals("MISSFREE")){
         if(playTypeInformation.getPlayType().equals("ENDOF") ||
            playTypeInformation.getPlayType().equals("TEAMREB") ||
            playTypeInformation.getPlayType().equals("JUMPBALL") ||
            playTypeInformation.getPlayType().equals("TIMEOUT") ||
         ((playTypeInformation.getOverallPlayType().equals("DEFREB"))
               && (playTypeInformation.isOpponent(teamNumber)!=priorPlayTypeInformation.isOpponent(teamNumber))) ||
            ((playTypeInformation.getOverallPlayType().equals("OFFREB"))
               && (playTypeInformation.isOpponent(teamNumber)==priorPlayTypeInformation.isOpponent(teamNumber)))
         ){
            return true;
         }
      }

      if(priorPlayTypeInformation.getPlayType().equals("DEFREB")){
         if(playTypeInformation.getPlayType().equals("ENDOF") ||
            playTypeInformation.getPlayType().equals("FOUL") ||
            playTypeInformation.getPlayType().equals("JUMPBALL") ||
            playTypeInformation.getPlayType().equals("TIMEOUT") ||
            ((playTypeInformation.getOverallPlayType().equals("2PTCLOSEATTEMPT") ||
               playTypeInformation.getOverallPlayType().equals("2PTJUMPATTEMPT")||
               playTypeInformation.getOverallPlayType().equals("3PTATTEMPT") ||
               playTypeInformation.getPlayType().equals("TURNOVER"))
               && (playTypeInformation.isOpponent(teamNumber)==priorPlayTypeInformation.isOpponent(teamNumber)))

         ){
            return true;
         }
      }

      if(priorPlayTypeInformation.getPlayType().equals("BLOCK")){
         if(playTypeInformation.getPlayType().equals("ENDOF") ||
            playTypeInformation.getPlayType().equals("TEAMREB") ||
            ((playTypeInformation.getOverallPlayType().equals("DEFREB"))
               && (playTypeInformation.isOpponent(teamNumber)==priorPlayTypeInformation.isOpponent(teamNumber))) ||
            ((playTypeInformation.getOverallPlayType().equals("OFFREB"))
               && (playTypeInformation.isOpponent(teamNumber)!=priorPlayTypeInformation.isOpponent(teamNumber)))
         ){
            return true;
         }
      }

      if(priorPlayTypeInformation.getPlayType().equals("TEAMREB")){
         if(playTypeInformation.getPlayType().equals("ENDOF") ||
            playTypeInformation.getPlayType().equals("FOUL") ||
            playTypeInformation.getPlayType().equals("JUMPBALL") ||
            playTypeInformation.getPlayType().equals("TIMEOUT") ||
            ((playTypeInformation.getOverallPlayType().equals("2PTCLOSEATTEMPT") ||
               playTypeInformation.getOverallPlayType().equals("2PTJUMPATTEMPT")||
               playTypeInformation.getOverallPlayType().equals("3PTATTEMPT") ||
               playTypeInformation.getOverallPlayType().equals("FREETHROW") ||
               playTypeInformation.getPlayType().equals("TURNOVER"))
               && (playTypeInformation.isOpponent(teamNumber)==priorPlayTypeInformation.isOpponent(teamNumber)))
         ){
            return true;
         }
      }


      if(priorPlayTypeInformation.getPlayType().equals("MADE3PTJUMP") ||
         priorPlayTypeInformation.getPlayType().equals("MADE2PTCLOSE") ||
         priorPlayTypeInformation.getPlayType().equals("MADE2PTJUMP")){

         if(playTypeInformation.getPlayType().equals("ENDOF") ||
            playTypeInformation.getPlayType().equals("FOUL") ||
            playTypeInformation.getPlayType().equals("JUMPBALL") ||
            playTypeInformation.getPlayType().equals("TIMEOUT") ||
            ((playTypeInformation.getOverallPlayType().equals("2PTCLOSEATTEMPT") ||
               playTypeInformation.getOverallPlayType().equals("2PTJUMPATTEMPT")||
               playTypeInformation.getOverallPlayType().equals("3PTATTEMPT") ||
               playTypeInformation.getPlayType().equals("TURNOVER"))
               && (playTypeInformation.isOpponent(teamNumber)!=priorPlayTypeInformation.isOpponent(teamNumber)))

         ){
            return true;
         }
      }

      if(priorPlayTypeInformation.getPlayType().equals("MADEFREE")){
         if(playTypeInformation.getPlayType().equals("ENDOF") ||
            playTypeInformation.getPlayType().equals("FOUL") ||
            playTypeInformation.getPlayType().equals("JUMPBALL") ||
            playTypeInformation.getPlayType().equals("TIMEOUT") ||
            ((playTypeInformation.getOverallPlayType().equals("FREETHROW"))
               && (playTypeInformation.isOpponent(teamNumber)==priorPlayTypeInformation.isOpponent(teamNumber))) ||
            ((playTypeInformation.getOverallPlayType().equals("2PTCLOSEATTEMPT") ||
               playTypeInformation.getOverallPlayType().equals("2PTJUMPATTEMPT")||
               playTypeInformation.getOverallPlayType().equals("3PTATTEMPT") ||
               playTypeInformation.getPlayType().equals("TURNOVER"))
               && (playTypeInformation.isOpponent(teamNumber)!=priorPlayTypeInformation.isOpponent(teamNumber)))
         ){
            return true;
         }
      }

      return false;
   }
}
