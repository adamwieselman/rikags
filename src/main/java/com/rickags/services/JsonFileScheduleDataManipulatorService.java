package com.rickags.services;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.rickags.facade.EspnEventFacade;
import com.rickags.models.json.file.GameResult;
import com.rickags.models.json.file.NcaaTeam;
import com.rickags.models.json.file.YearSummary;

@Component
public class JsonFileScheduleDataManipulatorService
{

   public YearSummary prepareYearSummary (NcaaTeam ncaaTeam,
                                          String year,
                                          EspnEventFacade espnEventFacade,
                                          int eventCompetitorNumber)
   {
      for(YearSummary yearSummary : ncaaTeam.getYearSummaries()){
         if(yearSummary.getYear().equals(year)){
            return yearSummary;
         }
      }

      YearSummary newYearSummary = new YearSummary();
      newYearSummary.setYear(year);
      if (eventCompetitorNumber == 1) {
         newYearSummary.setConferenceId(espnEventFacade.getTeam1Conference());
      } else {
         newYearSummary.setConferenceId(espnEventFacade.getTeam2Conference());
      }
      ncaaTeam.getYearSummaries().add(newYearSummary);

      return newYearSummary;
   }

   public void prepareGameResult (YearSummary yearSummary,
                                  LocalDate startDate,
                                  EspnEventFacade espnEventFacade,
                                  int eventCompetitorNumber)
   {
      for(GameResult gameResult : yearSummary.getGameResults()){
         if(gameResult.getGameId().equals(espnEventFacade.getEventId())){
            return;
         }
      }

      createAndAddGameResult(yearSummary, startDate, espnEventFacade, eventCompetitorNumber);
   }

   public void createAndAddGameResult (YearSummary yearSummary,
                                       LocalDate startDate,
                                       EspnEventFacade espnEventFacade,
                                       int eventCompetitorNumber)
   {
      GameResult gameResult = new GameResult();

      gameResult.setGameDate(startDate);
      gameResult.setGameId(espnEventFacade.getEventId());
      gameResult.setVenueId(espnEventFacade.getEventVenueId());
      gameResult.setConferenceGame(espnEventFacade.isConferenceGame());

      if(eventCompetitorNumber==1) {
         gameResult.setOpponentId(espnEventFacade.getTeam2Id());
         gameResult.setOpponentName(espnEventFacade.getTeam2Name());
         gameResult.setOpponentConferenceId(espnEventFacade.getTeam2Conference());
         gameResult.setDesignatedHost(true);
      }else{
         gameResult.setOpponentId(espnEventFacade.getTeam1Id());
         gameResult.setOpponentName(espnEventFacade.getTeam1Name());
         gameResult.setOpponentConferenceId(espnEventFacade.getTeam1Conference());
         gameResult.setDesignatedHost(false);
      }

      yearSummary.getGameResults().add(gameResult);
   }
}
