package com.rickags.services;

import static com.rickags.constants.RickAgsConstants.EVENTSTATUS_FINAL;
import static com.rickags.constants.RickAgsConstants.EVENTTYPE_NCAABB;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.rickags.beans.RequestContext;
import com.rickags.beans.ResponseContext;
import com.rickags.facade.EspnEventFacade;
import com.rickags.factories.BasicObjectFactory;
import com.rickags.mappers.JsonMapper;
import com.rickags.models.json.espn.ncabb.EspnEvent;
import com.rickags.models.json.espn.ncabb.EspnScoreboard;
import com.rickags.models.json.file.NcaaTeam;

@Component
public class NcaaBasketballScheduleDataUpdateService implements ScheduleDataUpdateService
{

   @Autowired
   protected UnirestApiService unirestApiService;

   @Autowired
   protected UrlGeneratorService urlGeneratorService;

   @Autowired
   protected JsonMapper jsonMapper;

   @Autowired
   protected BasicObjectFactory basicObjectFactory;

   @Autowired
   protected NcaaBasketballTeamDataService ncaaBasketballTeamDataService;

   @Override
   public boolean supports (String eventType)
   {
      if(eventType.equals(EVENTTYPE_NCAABB)){
         return true;
      }

      return false;
   }

   public void pullAllScheduleDataInformation (RequestContext request,
                                               ResponseContext response)
      throws IOException, UnirestException
   {
      LocalDate endDate = LocalDate.now();
      LocalDate startDate = request.getStartDate();

      while (!startDate.equals(endDate)) {
         unirestApiService.setUpUnirest();
         String scoreboardUrl = urlGeneratorService.generateNcaaBasketballScoreboardUrl(startDate);
         EspnScoreboard espnScoreboard = unirestApiService.getWebsiteDataObject(scoreboardUrl, null, null, EspnScoreboard.class);
         unirestApiService.shutdownUnirest();

         List<EspnEvent> espnEventsList = espnScoreboard.getEspnEvents();
         // if no games skip to next day
         if (espnEventsList.size() != 0) {
            for (EspnEvent espnEvent : espnEventsList) {
               EspnEventFacade espnEventFacade = basicObjectFactory.createEspnEventFacade(espnEvent);

               //collect data if status is final
               if (EVENTSTATUS_FINAL.equals(espnEventFacade.getEventStatus())){
                  if(espnEventFacade.isDivisionOneGame()) {
                     for(int x=1; x<=2;++x) {
                        NcaaTeam ncaaTeam = ncaaBasketballTeamDataService.retrieveData(espnEventFacade, x);
                        ncaaBasketballTeamDataService.createAndAddData(ncaaTeam, startDate, espnEventFacade, x);
                        ncaaBasketballTeamDataService.saveData(ncaaTeam, espnEventFacade, x);
                     }
                  }
               }
            }
         }
         startDate = startDate.plusDays(1);
      }
   }
}
