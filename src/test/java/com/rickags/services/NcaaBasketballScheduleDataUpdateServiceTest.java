package com.rickags.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.rickags.beans.RequestContext;
import com.rickags.beans.ResponseContext;
import com.rickags.facade.EspnEventFacade;
import com.rickags.factories.BasicObjectFactory;
import com.rickags.models.json.espn.ncabb.EspnContent;
import com.rickags.models.json.espn.ncabb.EspnEvent;
import com.rickags.models.json.espn.ncabb.EspnSbData;
import com.rickags.models.json.espn.ncabb.EspnScoreboard;
import com.rickags.models.json.file.NcaaTeam;

public class NcaaBasketballScheduleDataUpdateServiceTest
{

   @Rule
   public MockitoRule rule = MockitoJUnit.rule();

   @Mock
   UnirestApiService unirestApiService;

   @Mock
   UrlGeneratorService urlGeneratorService;

   @Mock
   BasicObjectFactory basicObjectFactory;

   @Mock
   NcaaBasketballTeamDataService ncaaBasketballTeamDataService;

   @InjectMocks
   public NcaaBasketballScheduleDataUpdateService ncaaBasketballScheduleDataUpdateService = new NcaaBasketballScheduleDataUpdateService();


   @Test
   public void testsupports_eventTypeMatches ()
   {
      String eventType = "NCAABB";
      assertTrue(ncaaBasketballScheduleDataUpdateService.supports(eventType));
   }

   @Test
   public void testsupports_eventTypeNoMatch ()
   {
      String eventType = "OTHER";
      assertFalse(ncaaBasketballScheduleDataUpdateService.supports(eventType));
   }

   @Test
   public void testPullAllScheduleDataInformation_eventNotFinal ()
      throws UnirestException, IOException
   {
      RequestContext request = new RequestContext();
      ResponseContext response = mock(ResponseContext.class);
      EspnScoreboard espnScoreboard = new EspnScoreboard();
      espnScoreboard.setEspnContent(new EspnContent());
      espnScoreboard.getEspnContent().setEspnSbData(new EspnSbData());
      espnScoreboard.getEspnContent().getEspnSbData().setEspnEvents(new ArrayList<>());
      EspnEvent espnEvent = new EspnEvent();
      espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().add(espnEvent);
      EspnEventFacade espnEventFacade = mock(EspnEventFacade.class);

      LocalDate currentDate = LocalDate.now();
      LocalDate startDate = LocalDate.now().minusDays(1);
      request.setStartDate(startDate);

      given(urlGeneratorService.generateNcaaBasketballScoreboardUrl(startDate)).willReturn("SCOREBOARDURL");
      given(unirestApiService.getWebsiteDataObject("SCOREBOARDURL", null, null, EspnScoreboard.class)).willReturn(espnScoreboard);
      given(basicObjectFactory.createEspnEventFacade(espnEvent)).willReturn(espnEventFacade);
      given(espnEventFacade.getEventStatus()).willReturn("STATUS_CANCELLED");
      ncaaBasketballScheduleDataUpdateService.pullAllScheduleDataInformation (request, response);

      verifyZeroInteractions(ncaaBasketballTeamDataService);
   }

   @Test
   public void testPullAllScheduleDataInformation_eventNotDiv1Game ()
      throws UnirestException, IOException
   {
      RequestContext request = new RequestContext();
      ResponseContext response = mock(ResponseContext.class);
      EspnScoreboard espnScoreboard = new EspnScoreboard();
      espnScoreboard.setEspnContent(new EspnContent());
      espnScoreboard.getEspnContent().setEspnSbData(new EspnSbData());
      espnScoreboard.getEspnContent().getEspnSbData().setEspnEvents(new ArrayList<>());
      EspnEvent espnEvent = new EspnEvent();
      espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().add(espnEvent);
      EspnEventFacade espnEventFacade = mock(EspnEventFacade.class);

      LocalDate currentDate = LocalDate.now();
      LocalDate startDate = LocalDate.now().minusDays(1);
      request.setStartDate(startDate);

      given(urlGeneratorService.generateNcaaBasketballScoreboardUrl(startDate)).willReturn("SCOREBOARDURL");
      given(unirestApiService.getWebsiteDataObject("SCOREBOARDURL", null, null, EspnScoreboard.class)).willReturn(espnScoreboard);
      given(basicObjectFactory.createEspnEventFacade(espnEvent)).willReturn(espnEventFacade);
      given(espnEventFacade.getEventStatus()).willReturn("STATUS_FINAL");
      given(espnEventFacade.isDivisionOneGame()).willReturn(false);
      ncaaBasketballScheduleDataUpdateService.pullAllScheduleDataInformation (request, response);

      verifyZeroInteractions(ncaaBasketballTeamDataService);
   }

   @Test
   public void testPullAllScheduleDataInformation_espnEventsListSize_0 ()
      throws UnirestException, IOException
   {
      RequestContext request = new RequestContext();
      ResponseContext response = mock(ResponseContext.class);
      EspnScoreboard espnScoreboard = new EspnScoreboard();
      espnScoreboard.setEspnContent(new EspnContent());
      espnScoreboard.getEspnContent().setEspnSbData(new EspnSbData());
      espnScoreboard.getEspnContent().getEspnSbData().setEspnEvents(new ArrayList<>());

      LocalDate currentDate = LocalDate.now();
      LocalDate startDate = LocalDate.now().minusDays(1);
      request.setStartDate(startDate);

      given(urlGeneratorService.generateNcaaBasketballScoreboardUrl(startDate)).willReturn("SCOREBOARDURL");
      given(unirestApiService.getWebsiteDataObject("SCOREBOARDURL", null, null, EspnScoreboard.class)).willReturn(espnScoreboard);
      given(basicObjectFactory.createEspnEventFacade(Mockito.any(EspnEvent.class))).willReturn(new EspnEventFacade(new EspnEvent()));

      ncaaBasketballScheduleDataUpdateService.pullAllScheduleDataInformation (request, response);

      verifyZeroInteractions(basicObjectFactory);
   }
   
   @Test
   public void testPullAllScheduleDataInformation_happyPath ()
      throws UnirestException, IOException
   {
      RequestContext request = new RequestContext();
      ResponseContext response = mock(ResponseContext.class);
      EspnScoreboard espnScoreboard = new EspnScoreboard();
      espnScoreboard.setEspnContent(new EspnContent());
      espnScoreboard.getEspnContent().setEspnSbData(new EspnSbData());
      espnScoreboard.getEspnContent().getEspnSbData().setEspnEvents(new ArrayList<>());
      EspnEvent espnEvent = new EspnEvent();
      espnScoreboard.getEspnContent().getEspnSbData().getEspnEvents().add(espnEvent);
      EspnEventFacade espnEventFacade = mock(EspnEventFacade.class);
      NcaaTeam ncaaTeam = mock(NcaaTeam.class);

      LocalDate currentDate = LocalDate.now();
      LocalDate startDate = LocalDate.now().minusDays(1);
      request.setStartDate(startDate);


      given(urlGeneratorService.generateNcaaBasketballScoreboardUrl(startDate)).willReturn("SCOREBOARDURL");
      given(unirestApiService.getWebsiteDataObject("SCOREBOARDURL", null, null, EspnScoreboard.class)).willReturn(espnScoreboard);
      given(basicObjectFactory.createEspnEventFacade(espnEvent)).willReturn(espnEventFacade);
      given(espnEventFacade.getEventStatus()).willReturn("STATUS_FINAL");
      given(espnEventFacade.isDivisionOneGame()).willReturn(true);
      given(ncaaBasketballTeamDataService.retrieveData(espnEventFacade, 1)).willReturn(ncaaTeam);
      given(ncaaBasketballTeamDataService.retrieveData(espnEventFacade, 2)).willReturn(ncaaTeam);

      ncaaBasketballScheduleDataUpdateService.pullAllScheduleDataInformation (request, response);

      Mockito.verify(ncaaBasketballTeamDataService, atLeastOnce()).createAndAddData(ncaaTeam, startDate, espnEventFacade, 1);
      Mockito.verify(ncaaBasketballTeamDataService, atLeastOnce()).saveData(ncaaTeam, espnEventFacade, 1);
      Mockito.verify(ncaaBasketballTeamDataService, atLeastOnce()).createAndAddData(ncaaTeam, startDate, espnEventFacade, 2);
      Mockito.verify(ncaaBasketballTeamDataService, atLeastOnce()).saveData(ncaaTeam, espnEventFacade, 2);


   }
}
