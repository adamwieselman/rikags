package com.rickags.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.stereotype.Component;

import com.rickags.facade.EspnEventFacade;
import com.rickags.models.json.file.GameResult;
import com.rickags.models.json.file.NcaaTeam;
import com.rickags.models.json.file.YearSummary;

@Component
public class JsonFileScheduleDataManipulatorServiceTest
{
   @Rule
   public MockitoRule rule = MockitoJUnit.rule();

   @InjectMocks
   public JsonFileScheduleDataManipulatorService jsonFileScheduleDataManipulatorService = new JsonFileScheduleDataManipulatorService();


   @Test
   public void testPrepareYearSummary_summaryExists()
   {
      EspnEventFacade espnEventFacade = mock(EspnEventFacade.class);
      NcaaTeam ncaaTeam = new NcaaTeam();
      YearSummary yearSummary = new YearSummary();
      yearSummary.setYear("2021");
      yearSummary.setConferenceId("testConf");
      ncaaTeam.getYearSummaries().add(yearSummary);


      YearSummary testYearSummary = jsonFileScheduleDataManipulatorService.prepareYearSummary(ncaaTeam, "2021", espnEventFacade, 1);
      assertEquals("2021", testYearSummary.getYear());
      assertEquals("testConf", testYearSummary.getConferenceId());
   }

   @Test
   public void testPrepareYearSummary_summaryDoesntExist()
   {
      EspnEventFacade espnEventFacade = mock(EspnEventFacade.class);
      NcaaTeam ncaaTeam = new NcaaTeam();
      given(espnEventFacade.getTeam1Conference()).willReturn("22");
      given(espnEventFacade.getTeam2Conference()).willReturn("11");

      YearSummary testYearSummary = jsonFileScheduleDataManipulatorService.prepareYearSummary(ncaaTeam, "2023", espnEventFacade, 1);
      assertEquals("2023", testYearSummary.getYear());
      assertEquals("22", testYearSummary.getConferenceId());

      testYearSummary = jsonFileScheduleDataManipulatorService.prepareYearSummary(ncaaTeam, "2020", espnEventFacade, 2);
      assertEquals("2020", testYearSummary.getYear());
      assertEquals("11", testYearSummary.getConferenceId());
   }

   @Test
   public void testPrepareGameResult_gameResultDoesntExist()
   {

      EspnEventFacade espnEventFacade = mock(EspnEventFacade.class);
      LocalDate startDate = LocalDate.now();
      NcaaTeam ncaaTeam = new NcaaTeam();
      YearSummary yearSummary = new YearSummary();
      GameResult gameResult = new GameResult();
      gameResult.setGameId("556677");
      yearSummary.getGameResults().add(gameResult);
      given(espnEventFacade.getEventId()).willReturn("556678");
      given(espnEventFacade.getEventVenueId()).willReturn("VenueId");
      given(espnEventFacade.isConferenceGame()).willReturn(true);
      given(espnEventFacade.getTeam1Id()).willReturn("234");
      given(espnEventFacade.getTeam1Conference()).willReturn("22");
      given(espnEventFacade.getTeam1Name()).willReturn("team1Name");
      given(espnEventFacade.getTeam2Conference()).willReturn("11");
      given(espnEventFacade.getTeam2Id()).willReturn("432");
      given(espnEventFacade.getTeam2Name()).willReturn("team2Name");

      jsonFileScheduleDataManipulatorService.prepareGameResult(yearSummary, startDate, espnEventFacade, 1);

      assertEquals(2, yearSummary.getGameResults().size());
      assertEquals("team2Name", yearSummary.getGameResults().get(1).getOpponentName());

      yearSummary.getGameResults().remove(1);

      jsonFileScheduleDataManipulatorService.prepareGameResult(yearSummary, startDate, espnEventFacade, 2);
      assertEquals(2, yearSummary.getGameResults().size());
      assertEquals("team1Name", yearSummary.getGameResults().get(1).getOpponentName());

   }

   @Test
   public void testPrepareGameResult_gameResultExists()
   {

      EspnEventFacade espnEventFacade = mock(EspnEventFacade.class);
      LocalDate startDate = LocalDate.now();
      NcaaTeam ncaaTeam = new NcaaTeam();
      YearSummary yearSummary = new YearSummary();
      GameResult gameResult = new GameResult();
      gameResult.setGameId("556677");
      yearSummary.getGameResults().add(gameResult);
      given(espnEventFacade.getEventId()).willReturn("556677");

      jsonFileScheduleDataManipulatorService.prepareGameResult(yearSummary, startDate, espnEventFacade, 1);

      assertEquals(1, yearSummary.getGameResults().size());
      assertEquals("556677", yearSummary.getGameResults().get(0).getGameId());

   }
}
