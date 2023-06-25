package com.rickags.services;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rickags.beans.ResponseContext;
import com.rickags.beans.TeamEspnPlayByPlay;
import com.rickags.extractors.PlayTypeInformationExtractor;
import com.rickags.factories.BasicObjectFactory;
import com.rickags.models.GameIssue;
import com.rickags.models.json.espn.ncabb.EspnGamePlayByPlay;
import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;
import com.rickags.models.json.file.Possession;

@Component
public class NcaaBasketballGameDataService
{
   @Autowired
   protected EspnPlayByPlayExtractionService espnPlayByPlayExtractionService;

   @Autowired
   protected NcaaBasketballGameDataServiceHelper ncaaBasketballGameDataServiceHelper;

   @Autowired
   protected BasicObjectFactory basicObjectFactory;

   public void sanitizePossessions (String teamId,
                                    String opponentId,
                                    String gameId,
                                    List<TeamEspnPlayByPlay> gamesIssuesList,
                                    EspnGamePlayByPlay espnGamePlayByPlay,
                                    ResponseContext response){

      Integer playByPlaySize = espnGamePlayByPlay.getEspnPlayByPlays().size();
      PlayTypeInformationExtractor priorPlayTypeInformation = null;
      TeamEspnPlayByPlay teamEspnPlayByPlay = new TeamEspnPlayByPlay();
      TeamEspnPlayByPlay gameIssues = new TeamEspnPlayByPlay();


      for (int x = 0; x < playByPlaySize; ++x) {
         PlayTypeInformationExtractor playTypeInformation = espnPlayByPlayExtractionService.extractPlayByPlayDetail(espnGamePlayByPlay.getEspnPlayByPlays().get(x));
         if(!ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformation, priorPlayTypeInformation, teamId)) {
            GameIssue gameIssue = new GameIssue();
            gameIssue.setMinutesTime(priorPlayTypeInformation.getTime().divide(new BigInteger("60")));
            gameIssue.setSecondsTime(priorPlayTypeInformation.getTime().remainder(new BigInteger("60")));
            gameIssue.setPriorOverallPlayType(priorPlayTypeInformation.getOverallPlayType());
            gameIssue.setPriorPlayType(priorPlayTypeInformation.getPlayType());
            gameIssue.setPriorTeam(priorPlayTypeInformation.getOpponent());
            gameIssue.setCurrentOverallPlayType(playTypeInformation.getOverallPlayType());
            gameIssue.setCurrentPlayType(playTypeInformation.getPlayType());
            gameIssue.setCurrentTeam(playTypeInformation.getOpponent());
            gameIssues.getIssueList().add(gameIssue);
         }
         priorPlayTypeInformation = playTypeInformation;
      }

      if(gameIssues.getIssueList().size() != 0){
         teamEspnPlayByPlay.setEspnGamePlayByPlay(espnGamePlayByPlay);
         teamEspnPlayByPlay.getTeams().add(teamId);
         teamEspnPlayByPlay.getTeams().add(opponentId);
         teamEspnPlayByPlay.setGameId(gameId);
         gameIssues.getTeams().add(teamId);
         gameIssues.getTeams().add(opponentId);
         gameIssues.setGameId(gameId);
         response.getTeamEspnPlayByPlayList().add(teamEspnPlayByPlay);
         gamesIssuesList.add(gameIssues);
      }
   }


   public List<Possession> gatherPossessions (String teamId,
                                              EspnGamePlayByPlay espnGamePlayByPlay)
   {
      List<Possession> possessions = new ArrayList<>();

      Integer playByPlaySize = espnGamePlayByPlay.getEspnPlayByPlays().size();

      Possession possession = null;
      int possessionNumber = 1;
      boolean isPreviousPlayEND = false;
      boolean isPreviousPlayBEGIN = true;
      String previousPlayType = "";
      BigInteger time = new BigInteger("2400");
      possession = basicObjectFactory.createPossession();
      possession.setPossessionNumber(possessionNumber);
      boolean firstPlay = true;
      PlayTypeInformationExtractor priorPlayTypeInformation = null;

      for (int x = 0; x < playByPlaySize; ++x) {
         PlayTypeInformationExtractor playTypeInformation = espnPlayByPlayExtractionService.extractPlayByPlayDetail(espnGamePlayByPlay.getEspnPlayByPlays().get(x));

         if(!playTypeInformation.getPlayType().equals("ERROR")){

            if(firstPlay && !playTypeInformation.getBeginningOrEnding().equals("BEGIN")){
               EspnPlayByPlay newEndPlay = new EspnPlayByPlay();
               newEndPlay.setGameHalf(espnGamePlayByPlay.getEspnPlayByPlays().get(x).getGameHalf());
               newEndPlay.setTeamLogo(espnGamePlayByPlay.getEspnPlayByPlays().get(x).getTeamLogo());
               newEndPlay.setGameDetail("Jump Ball ");
               newEndPlay.setTimeStamp("24:00");
               newEndPlay.setCombinedScore("0-0");
               playTypeInformation = espnPlayByPlayExtractionService.extractPlayByPlayDetail(newEndPlay);
               --x;
            }

            if(!ncaaBasketballGameDataServiceHelper.isPlayOk(playTypeInformation, priorPlayTypeInformation, teamId)) {
               boolean playNotOk = true;

               if ((isPreviousPlayBEGIN && playTypeInformation.getBeginningOrEnding().equals("BEGIN") && !playTypeInformation.getPlayType().equals("JUMPBALL")) || (isPreviousPlayEND && playTypeInformation.getBeginningOrEnding().equals("END") && !playTypeInformation.getPlayType().equals("BLOCK"))) {

                  if (isPreviousPlayBEGIN && playTypeInformation.getBeginningOrEnding().equals("BEGIN")) {
                     EspnPlayByPlay newEndPlay = new EspnPlayByPlay();
                     newEndPlay.setGameHalf(espnGamePlayByPlay.getEspnPlayByPlays().get(x - 1).getGameHalf());
                     newEndPlay.setTeamLogo(espnGamePlayByPlay.getEspnPlayByPlays().get(x - 1).getTeamLogo());
                     newEndPlay.setGameDetail("Someone missed Jumper");
                     newEndPlay.setTimeStamp(espnGamePlayByPlay.getEspnPlayByPlays().get(x - 1).getTimeStamp());
                     newEndPlay.setCombinedScore(espnGamePlayByPlay.getEspnPlayByPlays().get(x - 1).getCombinedScore());
                     playTypeInformation = espnPlayByPlayExtractionService.extractPlayByPlayDetail(newEndPlay);
                     isPreviousPlayBEGIN = false;
                     isPreviousPlayEND = true;
                     --x;
                     playNotOk = false;
                  } else if (isPreviousPlayEND && playTypeInformation.getBeginningOrEnding().equals("END") && !playTypeInformation.getPlayType().equals("BLOCK")) {
                     EspnPlayByPlay newBeginPlay = new EspnPlayByPlay();
                     newBeginPlay.setGameHalf(espnGamePlayByPlay.getEspnPlayByPlays().get(x).getGameHalf());
                     newBeginPlay.setTeamLogo(espnGamePlayByPlay.getEspnPlayByPlays().get(x).getTeamLogo());
                     newBeginPlay.setTimeStamp(espnGamePlayByPlay.getEspnPlayByPlays().get(x-1).getTimeStamp());
                     newBeginPlay.setCombinedScore(espnGamePlayByPlay.getEspnPlayByPlays().get(x).getCombinedScore());

                     PlayTypeInformationExtractor playTypeInformationPrior = espnPlayByPlayExtractionService.extractPlayByPlayDetail(espnGamePlayByPlay.getEspnPlayByPlays().get(x - 1));
                     if (playTypeInformation.isOpponent(teamId) == playTypeInformationPrior.isOpponent(teamId)) {
                        newBeginPlay.setGameDetail("Someone Offensive Rebound");
                     } else {
                        newBeginPlay.setGameDetail("Someone Defensive Rebound");
                     }
                     playTypeInformation = espnPlayByPlayExtractionService.extractPlayByPlayDetail(newBeginPlay);
                     isPreviousPlayEND = false;
                     isPreviousPlayBEGIN = true;
                     --x;
                     playNotOk = false;
                  }
               }

               if(playNotOk){
                  System.out.println("Prior overall: " + priorPlayTypeInformation.getPriorPlayType() + "Prior play: " + priorPlayTypeInformation.getPlayType() + "Prior Time: " + playTypeInformation.getTime() + "Current overall: " + playTypeInformation.getOverallPlayType() + "Current play: " + playTypeInformation.getPlayType() + "Current Time: " + playTypeInformation.getTime());
               }
            }

            switch (playTypeInformation.getBeginningOrEnding()) {
               case "BEGIN":
                  if (firstPlay == true) {
                     possession.getPossessionPlays().add(playTypeInformation);
                     firstPlay = false;
                  } else {
                     possessions.add(possession);
                     possession = ncaaBasketballGameDataServiceHelper.setupPossession(playTypeInformation, possessionNumber);
                  }
                  isPreviousPlayBEGIN = true;
                  isPreviousPlayEND = false;
                  time = playTypeInformation.getTime();
                  break;
               case "END":
                  possession.getPossessionPlays().add(playTypeInformation);

                  ncaaBasketballGameDataServiceHelper.determinePossessionOwner(possession, teamId);
                  ncaaBasketballGameDataServiceHelper.determinePossessionTempo(possession);
                  ncaaBasketballGameDataServiceHelper.determinePossessionError(possession, teamId);

                  isPreviousPlayBEGIN = false;
                  isPreviousPlayEND = true;
                  time = playTypeInformation.getTime();
                  break;
               case "ENDBEGIN":
               case "STOPPAGE":
                  possession.getPossessionPlays().add(playTypeInformation);
                  if(x+1 <playByPlaySize){
                     PlayTypeInformationExtractor playTypeInformationFuture = espnPlayByPlayExtractionService.extractPlayByPlayDetail(espnGamePlayByPlay.getEspnPlayByPlays().get(x+1));
                     if(!playTypeInformationFuture.getTime().equals(playTypeInformation.getTime())){
                        ncaaBasketballGameDataServiceHelper.determinePossessionOwner(possession, teamId);
                        ncaaBasketballGameDataServiceHelper.determinePossessionTempo(possession);
                        possessions.add(possession);
                        possession = ncaaBasketballGameDataServiceHelper.setupPossession(playTypeInformation, possessionNumber);
                     }
                  }
                  isPreviousPlayBEGIN = true;
                  isPreviousPlayEND = false;
                  time = playTypeInformation.getTime();
                  break;
               default:
                  break;
            }

            priorPlayTypeInformation = playTypeInformation;
         }
      }
      // to handle the straggler possession at the end
      if(possession.getPossessionPlays().size() != 0) {
         ncaaBasketballGameDataServiceHelper.determinePossessionOwner(possession, teamId);
         ncaaBasketballGameDataServiceHelper.determinePossessionTempo(possession);
         possessions.add(possession);
      }
      return possessions;
   }

}
