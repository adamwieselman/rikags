package com.rickags.services;

import static com.rickags.constants.RickAgsConstants.EVENTTYPE_NCAABB;
import static com.rickags.constants.RickAgsConstants.FILE_LOCATION_NCAABBDATA_PREFIX;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rickags.beans.RequestContext;
import com.rickags.beans.ResponseContext;
import com.rickags.beans.TeamEspnPlayByPlay;
import com.rickags.factories.BasicObjectFactory;
import com.rickags.mappers.JsonMapper;
import com.rickags.models.json.espn.ncabb.EspnGamePlayByPlay;
import com.rickags.models.json.file.GameResult;
import com.rickags.models.json.file.NcaaTeam;
import com.rickags.models.json.file.YearSummary;

@Component
public class NcaaBasketballPlayByPlayDataUpdateService implements PlayByPlayDataUpdateService
{
   @Autowired
   protected NcaaBasketballTeamDataService ncaaBasketballTeamDataService;

   @Autowired
   protected BasicObjectFactory basicObjectFactory;

   @Autowired
   protected NcaaBasketballGameDataService ncaaBasketballGameDataService;

   @Autowired
   protected HtmlExtractionService htmlExtractionService;

   @Autowired
   protected JsonMapper jsonMapper;


   @Override
   public boolean supports (String eventType)
   {
      if(eventType.equals(EVENTTYPE_NCAABB)){
         return true;
      }

      return false;
   }

   public void pullAllPlayByPlayDataInformation (RequestContext request,
                                                 ResponseContext response)
      throws Exception
   {
      Collection<File> filesInFolder = FileUtils.listFiles(basicObjectFactory.createFile(FILE_LOCATION_NCAABBDATA_PREFIX), null, false);
      List<TeamEspnPlayByPlay> gameIssuesList = new ArrayList<>();

      if(response.getTeamEspnPlayByPlayList() == null) {
         for (File file : filesInFolder) {
            NcaaTeam ncaaTeam = ncaaBasketballTeamDataService.retrieveData(file);

            for (YearSummary yearSummary : ncaaTeam.getYearSummaries()) {
               for (GameResult gameResult : yearSummary.getGameResults()) {
                  EspnGamePlayByPlay espnGamePlayByPlay = htmlExtractionService.extractNcaabbGameHtml(gameResult.getGameId());
                  ncaaBasketballGameDataService.sanitizePossessions(ncaaTeam.getTeamId(), gameResult.getOpponentId(), gameResult.getGameId(), gameIssuesList, espnGamePlayByPlay, response);
               }
            }
         }
      }else{
         for(TeamEspnPlayByPlay teamEspnPlayByPlay : response.getTeamEspnPlayByPlayList()) {
            ncaaBasketballGameDataService.sanitizePossessions(teamEspnPlayByPlay.getTeams().get(0), teamEspnPlayByPlay.getTeams().get(1), teamEspnPlayByPlay.getGameId(), gameIssuesList, teamEspnPlayByPlay.getEspnGamePlayByPlay(), response);
         }
      }

      File errorBreakdownFile = basicObjectFactory.createFile("C:\\Users\\Owner\\OneDrive\\Documents\\Ncaabb Team Json\\Other\\errors.json");

      try (FileOutputStream output = basicObjectFactory.createFileOutputStream(errorBreakdownFile)){
         output.write(jsonMapper.serializeResponseToJson(gameIssuesList).getBytes(StandardCharsets.UTF_8));
         output.flush();
      }

      // ncaaBasketballTeamDataService.createAndAddData(ncaaTeam, possessions);
      // ncaaBasketballTeamDataService.saveData(ncaaTeam);



//         for (YearSummary yearSummary : ncaaTeam.getYearSummaries()) {
//            for (GameResult gameResult : yearSummary.getGameResults()) {
//               EspnGamePlayByPlay espnGamePlayByPlay = htmlExtractionService.extractNcaabbGameHtml(gameResult.getGameId());





//               PlayTypeInformationExtractor playTypeInformation = null;
//               Integer playByPlaySize = espnGamePlayByPlay.getEspnPlayByPlays().size();
//               boolean newPossession = true;
//               boolean createdFix = false;
//               Possession possession = null;
//               int possessionNumber = 0;
//               boolean isCurrentPossessionOpponent = false;
//               if(playByPlaySize != 0) {
//                  playTypeInformation = espnPlayByPlayExtractionService.extractPlayByPlayDetail(espnGamePlayByPlay.getEspnPlayByPlays().get(0));
//
//
//                  if (playTypeInformation.getPlayType().equals("FOUL")) {
//                     isCurrentPossessionOpponent = !playTypeInformation.isOpponent(teamNumber);
//                  } else {
//                     isCurrentPossessionOpponent = playTypeInformation.isOpponent(teamNumber);
//                  }
//               }
//
//               for (int x = 0; x < playByPlaySize; ++x) {
//                  if (newPossession) {
//                     possession = new Possession();
//                     possession.getPossessionPath().add("A");
//                     possession.setOpponentsPossession(isCurrentPossessionOpponent);
//                     possession.setPossessionNumber(++possessionNumber);
//                     newPossession = false;
//                  }
//
//                  //get the playType info from the pbp
//                  if(!createdFix){
//                     possession.getPossessionPath().add("aE");
//                     playTypeInformation = espnPlayByPlayExtractionService.extractPlayByPlayDetail(espnGamePlayByPlay.getEspnPlayByPlays().get(x));
//                  }else{
//                     possession.getPossessionPath().add("aF");
//                     createdFix = !createdFix;
//                  }
//
//                  //check to see that it's not null or shouldn't be there.
//                  if (playTypeInformation.getPlayType().equals("ERROR")) {
//                     errorSanitizingService.sanitizeErrorPossession(playTypeInformation, possession, possessionList, gameResult.getGameId(), isCurrentPossessionOpponent, possessionNumber, teamNumber, playByPlaySize, espnGamePlayByPlay.getEspnPlayByPlays());
//
//                  } else if (playTypeInformation.isOpponent(teamNumber) != isCurrentPossessionOpponent && !(playTypeInformation.getPlayType().equals("DEFREB") || playTypeInformation.getPlayType().equals("STEAL") || playTypeInformation.getPlayType().equals("BLOCK") || playTypeInformation.getPlayType().equals("FOUL") || playTypeInformation.getPlayType().equals("TIMEOUT") || playTypeInformation.getPlayType().equals("END OF") || playTypeInformation.getPlayType().equals("JUMPBALL"))) {
//                     possession.setContainsError(true);
//                     possession.getPossessionPath().add("D");
//                     possession.setGameNumber(gameResult.getGameId());
//                     possessionList.add(possession);
//
//                     possession = new Possession();
//                     possession.getPossessionPath().add("E");
//                     possession.setOpponentsPossession(isCurrentPossessionOpponent);
//                     possession.setPossessionNumber(++possessionNumber);
//                     possession.setContainsError(true);
//                     possession.setGameNumber(gameResult.getGameId());
//                     possession.getPossessionPlays().add(playTypeInformation);
//                     isCurrentPossessionOpponent = playTypeInformation.isOpponent(teamNumber);
//
//                  } else if (playTypeInformation.getPlayType().equals("MISS2PTCLOSE")) {
//                     missTwoPointCloseSanitizingService.sanitizeTwoPointClosePossession(playTypeInformation, possession, possessionList, gameResult.getGameId(), isCurrentPossessionOpponent, possessionNumber, teamNumber, playByPlaySize, espnGamePlayByPlay.getEspnPlayByPlays(), newPossession, x);
//
//                  } else if (playTypeInformation.getPlayType().equals("MISS2PTJUMP")) {
//                     missTwoPointJumperSanitizingService.sanitizeTwoPointJumperPossession(playTypeInformation, possession, possessionList, gameResult.getGameId(), isCurrentPossessionOpponent, possessionNumber, teamNumber, playByPlaySize, espnGamePlayByPlay.getEspnPlayByPlays(), newPossession, x);
//
//                  } else if (playTypeInformation.getPlayType().equals("MISS3PTJUMP")) {
//                     missThreePointJumperSanitizingService.sanitizeMissThreePointJumperPossession(playTypeInformation, possession, possessionList, gameResult.getGameId(), isCurrentPossessionOpponent, possessionNumber, teamNumber, playByPlaySize, espnGamePlayByPlay.getEspnPlayByPlays(), newPossession, x);
//
//                  } else if (playTypeInformation.getPlayType().equals("MISSFREE")) {
//                     missFreeThrowSanitizingService.sanitizeMissFreeThrowPossession(playTypeInformation, possession, possessionList, gameResult.getGameId(), isCurrentPossessionOpponent, possessionNumber, teamNumber, playByPlaySize, espnGamePlayByPlay.getEspnPlayByPlays(), newPossession, x);
//
//                  } else if (playTypeInformation.getPlayType().equals("MADE2PTCLOSE")) {
//                     madeTwoPointCloseSanitizingService.sanitizeMadeTwoPointClosePossession(playTypeInformation, possession, possessionList, gameResult.getGameId(), isCurrentPossessionOpponent, possessionNumber, teamNumber, playByPlaySize, espnGamePlayByPlay.getEspnPlayByPlays(), newPossession, x);
//
//                  } else if (playTypeInformation.getPlayType().equals("MADE2PTJUMP")) {
//                     madeTwoPointJumperSanitizingService.sanitizeMadeTwoPointJumperPossession(playTypeInformation, possession, possessionList, gameResult.getGameId(), isCurrentPossessionOpponent, possessionNumber, teamNumber, playByPlaySize, espnGamePlayByPlay.getEspnPlayByPlays(), newPossession, x);
//
//                  } else if (playTypeInformation.getPlayType().equals("MADE3PTJUMP")) {
//                     madeThreePointJumperSanitizingService.sanitizeMadeThreePointJumperPossession(playTypeInformation, possession, possessionList, gameResult.getGameId(), isCurrentPossessionOpponent, possessionNumber, teamNumber, playByPlaySize, espnGamePlayByPlay.getEspnPlayByPlays(), newPossession, x);
//
//                  } else if (playTypeInformation.getPlayType().equals("MADEFREE")) {
//                     madeFreeThrowSanitizingService.sanitizeMadeFreeThrowPossession(playTypeInformation, possession, possessionList, gameResult.getGameId(), isCurrentPossessionOpponent, possessionNumber, teamNumber, playByPlaySize, espnGamePlayByPlay.getEspnPlayByPlays(), newPossession, x);
//
//                  } else if (playTypeInformation.getPlayType().equals("END OF")) {
//                     endOfSanitizingService.sanitizeEndOfPossession(playTypeInformation, possession, possessionList, gameResult.getGameId(), isCurrentPossessionOpponent, possessionNumber, teamNumber, playByPlaySize, espnGamePlayByPlay.getEspnPlayByPlays(), newPossession, x);
//
//                  } else if (playTypeInformation.getPlayType().equals("JUMPBALL") && playTypeInformation.isOpponent(teamNumber) != isCurrentPossessionOpponent) {
//                     possession.getPossessionPath().add("W");
//                     possession.getPossessionPlays().add(playTypeInformation);
//                     isCurrentPossessionOpponent = playTypeInformation.isOpponent(teamNumber);
//                     newPossession = true;
//                     possessionList.add(possession);
//                  } else if (playTypeInformation.getPlayType().equals("TURNOVER")) {
//                     possession.getPossessionPath().add("X");
//                     possession.getPossessionPlays().add(playTypeInformation);
//                     isCurrentPossessionOpponent = !playTypeInformation.isOpponent(teamNumber);
//                     newPossession = true;
//                     possessionList.add(possession);
//                  } else if (playTypeInformation.getPlayType().equals("BLOCK")) {
//                     if (x + 1 < playByPlaySize) {
//                        possession.getPossessionPath().add("Y");
//                        PlayTypeInformationExtractor playTypeInformationNext = espnPlayByPlayExtractionService.extractPlayByPlayDetail(espnGamePlayByPlay.getEspnPlayByPlays().get(x + 1));
//
//                        if ((playTypeInformationNext.getPlayType().equals("OFFREB") || playTypeInformationNext.getPlayType().equals("TEAM REBOUND")) && playTypeInformationNext.isOpponent(teamNumber) == isCurrentPossessionOpponent) {
//                           possession.getPossessionPath().add("Z");
//                           possession.getPossessionPlays().add(playTypeInformation);
//                        } else {
//                           possession.getPossessionPath().add("aA");
//                           possession.getPossessionPlays().add(playTypeInformation);
//                           isCurrentPossessionOpponent = playTypeInformation.isOpponent(teamNumber);
//                           newPossession = true;
//                           possessionList.add(possession);
//                        }
//                     } else {
//                        possession.getPossessionPath().add("aB");
//                        possession.getPossessionPlays().add(playTypeInformation);
//                        isCurrentPossessionOpponent = playTypeInformation.isOpponent(teamNumber);
//                        newPossession = true;
//                        possessionList.add(possession);
//                     }
//                  }else {
//                     possession.getPossessionPath().add("aC");
//                     possession.getPossessionPlays().add(playTypeInformation);
//                  }
//               }
//               if (!newPossession){
//                  possession.getPossessionPath().add("aD");
//                  possessionList.add(possession);
//               }
//            }
//         }
//
//         List<Possession> errorPossessionList = new ArrayList<>();
//
//         for(Possession possession : possessionList){
//            if(possession.isContainsError()){
//                Possession errorPossession = new Possession();
//                errorPossession.setContainsError(possession.isContainsError());
//                errorPossession.setPossessionPath(possession.getPossessionPath());
//                errorPossession.setPossessionNumber(possession.getPossessionNumber());
//                errorPossession.setGameNumber(possession.getGameNumber());
//                errorPossession.setPossessionPlays(possession.getPossessionPlays());
//                errorPossessionList.add(errorPossession);
//            }
//         }
//
//         errorBreakdownFile = new File("C:\\Users\\Owner\\OneDrive\\Documents\\Ncaabb Team Json\\Other\\" + ncaaTeam.getTeamId() + "errors.json");
//         possessionListFile = new File("C:\\Users\\Owner\\OneDrive\\Documents\\Ncaabb Team Json\\Other\\" + ncaaTeam.getTeamId() + "sanitized.json");
//
//         try (FileOutputStream output = new FileOutputStream(possessionListFile)){
//            output.write(jsonMapper.serializeResponseToJson(possessionList).getBytes(StandardCharsets.UTF_8));
//            output.flush();
//         }
//
//         try (FileOutputStream output = new FileOutputStream(errorBreakdownFile)){
//            output.write(jsonMapper.serializeResponseToJson(errorPossessionList).getBytes(StandardCharsets.UTF_8));
//            output.flush();
//         }
   }
}

