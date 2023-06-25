package com.rickags.services;

import static com.rickags.constants.RickAgsConstants.FILE_LOCATION_NCAABBDATA_PREFIX_TEST;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.rickags.beans.RequestContext;
import com.rickags.beans.ResponseContext;
import com.rickags.factories.BasicObjectFactory;
import com.rickags.mappers.JsonMapper;
import com.rickags.models.json.espn.ncabb.EspnGamePlayByPlay;
import com.rickags.models.json.file.GameResult;
import com.rickags.models.json.file.NcaaTeam;
import com.rickags.models.json.file.Possession;
import com.rickags.models.json.file.YearSummary;

public class NcaaBasketballPlayByPlayDataUpdateServiceTest
{

   @Rule
   public MockitoRule rule = MockitoJUnit.rule();

   @Mock
   BasicObjectFactory basicObjectFactory;

   @Mock
   NcaaBasketballTeamDataService ncaaBasketballTeamDataService;

   @Mock
   NcaaBasketballGameDataService ncaaBasketballGameDataService;

   @Mock
   JsonMapper jsonMapper;

   @Mock
   HtmlExtractionService htmlExtractionService;

   @InjectMocks
   public NcaaBasketballPlayByPlayDataUpdateService ncaaBasketballPlayByPlayDataUpdateService = new NcaaBasketballPlayByPlayDataUpdateService();

   @Test
   public void testSupports_True(){
      assertTrue(ncaaBasketballPlayByPlayDataUpdateService.supports("NCAABB"));
   }

   @Test
   public void testSupports_False(){
      assertFalse(ncaaBasketballPlayByPlayDataUpdateService.supports("Not_NCAABB"));
   }

   @Test
   public void testPullAllPlayByPlayDataInformation()
      throws Exception
   {
      File file = mock(File.class);
      NcaaTeam ncaaTeam = new NcaaTeam();
      ncaaTeam.setTeamId("2");
      FileOutputStream fileOutputStream = mock(FileOutputStream.class);
      List<Possession> possessions = new ArrayList<>();
      Possession possession = new Possession();
      possessions.add(possession);
      YearSummary yearSummary = new YearSummary();
      GameResult gameResult = new GameResult();
      EspnGamePlayByPlay espnGamePlayByPlay = new EspnGamePlayByPlay();
      gameResult.setGameId("1111");
      yearSummary.getGameResults().add(gameResult);
      ncaaTeam.getYearSummaries().add(yearSummary);
      given(basicObjectFactory.createFile(Mockito.anyString())).willReturn(new File(FILE_LOCATION_NCAABBDATA_PREFIX_TEST));
      given(basicObjectFactory.createFileOutputStream(Mockito.any(File.class))).willReturn(fileOutputStream);
      given(ncaaBasketballTeamDataService.retrieveData(Mockito.any(File.class))).willReturn(ncaaTeam);
      given(htmlExtractionService.extractNcaabbGameHtml("1111")).willReturn(espnGamePlayByPlay);
      given(jsonMapper.serializeResponseToJson(Mockito.any())).willReturn("String");
      //given(ncaaBasketballGameDataService.gatherPossessions("2", espnGamePlayByPlay)).willReturn(possessions);

      ncaaBasketballPlayByPlayDataUpdateService.pullAllPlayByPlayDataInformation(new RequestContext(), new ResponseContext());
   }
}
