package com.rickags.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.rickags.extractors.BlockExtractor;
import com.rickags.extractors.DeadBallTeamReboundExtractor;
import com.rickags.extractors.DefensiveReboundExtractor;
import com.rickags.extractors.ErrorExtractor;
import com.rickags.extractors.FoulExtractor;
import com.rickags.extractors.JumpballExtractor;
import com.rickags.extractors.MadeFreeThrowExtractor;
import com.rickags.extractors.MadeThreePointJumperExtractor;
import com.rickags.extractors.MadeTwoPointJumperExtractor;
import com.rickags.extractors.MissFreeThrowExtractor;
import com.rickags.extractors.MissTwoPointJumperExtractor;
import com.rickags.extractors.OffensiveReboundExtractor;
import com.rickags.extractors.PlayTypeInformationExtractor;
import com.rickags.extractors.StealExtractor;
import com.rickags.extractors.TimeoutExtractor;
import com.rickags.extractors.TurnoverExtractor;
import com.rickags.factories.BasicObjectFactory;
import com.rickags.models.json.espn.ncabb.EspnGamePlayByPlay;
import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;
import com.rickags.models.json.file.Possession;


public class NcaaBasketballGameDataServiceTest
{

   @Rule
   public MockitoRule rule = MockitoJUnit.rule();

   @Mock
   EspnPlayByPlayExtractionService espnPlayByPlayExtractionService;

   @Mock
   BasicObjectFactory basicObjectFactory;

   @Mock
   NcaaBasketballGameDataServiceHelper ncaaBasketballGameDataServiceHelper;

   @InjectMocks
   public NcaaBasketballGameDataService ncaaBasketballGameDataService = new NcaaBasketballGameDataService();


   @Test
   public void testGatherPossessions_Error(){
      Possession possession = new Possession();
      EspnGamePlayByPlay espnGamePlayByPlay = new EspnGamePlayByPlay();
      espnGamePlayByPlay.setEspnPlayByPlays(new ArrayList<>());
      espnGamePlayByPlay.getEspnPlayByPlays().add(mock(EspnPlayByPlay.class));
      given(espnPlayByPlayExtractionService.extractPlayByPlayDetail(Mockito.any(EspnPlayByPlay.class))).willReturn(new ErrorExtractor());
      given(basicObjectFactory.createPossession()).willReturn(possession);

      List<Possession> possessions = ncaaBasketballGameDataService.gatherPossessions("20", espnGamePlayByPlay);
      assertEquals(0, possessions.size());
   }

   @Test
   public void testGatherPossessions_End(){
      List<PlayTypeInformationExtractor> playTypeInformationExtractorList = new ArrayList<>();
      playTypeInformationExtractorList.add(new JumpballExtractor());
      playTypeInformationExtractorList.add(new MissTwoPointJumperExtractor());
      playTypeInformationExtractorList.add(new ErrorExtractor());
      Possession possession = new Possession();
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setGameHalf("1");
      pbp.setGameDetail("Jonny Boy missed Jumper");
      pbp.setTimeStamp("16:25");
      pbp.setTeamLogo("150.png");
      EspnGamePlayByPlay espnGamePlayByPlay = new EspnGamePlayByPlay();
      espnGamePlayByPlay.setEspnPlayByPlays(new ArrayList<>());
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp);
      given(basicObjectFactory.createPossession()).willReturn(possession);
      ncaaBasketballGameDataService.espnPlayByPlayExtractionService = new EspnPlayByPlayExtractionService();
      ncaaBasketballGameDataService.espnPlayByPlayExtractionService.playTypeInformationExtractorList = playTypeInformationExtractorList;
      ncaaBasketballGameDataService.ncaaBasketballGameDataServiceHelper = new NcaaBasketballGameDataServiceHelper();
      ncaaBasketballGameDataService.ncaaBasketballGameDataServiceHelper.basicObjectFactory = new BasicObjectFactory();

      List<Possession> possessions = ncaaBasketballGameDataService.gatherPossessions("20", espnGamePlayByPlay);
      assertEquals(1, possessions.size());
      assertEquals(2, possessions.get(0).getPossessionPlays().size());
   }

   @Test
   public void testGatherPossessions_End_ThenBegin(){
      List<PlayTypeInformationExtractor> playTypeInformationExtractorList = new ArrayList<>();
      playTypeInformationExtractorList.add(new JumpballExtractor());
      playTypeInformationExtractorList.add(new MissTwoPointJumperExtractor());
      playTypeInformationExtractorList.add(new OffensiveReboundExtractor());
      playTypeInformationExtractorList.add(new ErrorExtractor());
      Possession possession = new Possession();
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setGameHalf("1");
      pbp.setGameDetail("Jonny Boy missed Jumper");
      pbp.setTimeStamp("16:25");
      pbp.setTeamLogo("150.png");
      EspnPlayByPlay pbp2 = new EspnPlayByPlay();
      pbp2.setGameHalf("1");
      pbp2.setGameDetail("Jonny Boy Offensive Rebound");
      pbp2.setTimeStamp("16:10");
      pbp2.setTeamLogo("150.png");
      EspnGamePlayByPlay espnGamePlayByPlay = new EspnGamePlayByPlay();
      espnGamePlayByPlay.setEspnPlayByPlays(new ArrayList<>());
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp2);
      given(basicObjectFactory.createPossession()).willReturn(possession);
      ncaaBasketballGameDataService.espnPlayByPlayExtractionService = new EspnPlayByPlayExtractionService();
      ncaaBasketballGameDataService.espnPlayByPlayExtractionService.playTypeInformationExtractorList = playTypeInformationExtractorList;
      ncaaBasketballGameDataService.ncaaBasketballGameDataServiceHelper = new NcaaBasketballGameDataServiceHelper();
      ncaaBasketballGameDataService.ncaaBasketballGameDataServiceHelper.basicObjectFactory = new BasicObjectFactory();

      List<Possession> possessions = ncaaBasketballGameDataService.gatherPossessions("20", espnGamePlayByPlay);
      assertEquals(2, possessions.size());
      assertEquals(2, possessions.get(0).getPossessionPlays().size());
   }

   @Test
   public void testGatherPossessions_EndBegin(){
      List<PlayTypeInformationExtractor> playTypeInformationExtractorList = new ArrayList<>();
      playTypeInformationExtractorList.add(new JumpballExtractor());
      playTypeInformationExtractorList.add(new MadeTwoPointJumperExtractor());
      playTypeInformationExtractorList.add(new ErrorExtractor());
      Possession possession = new Possession();
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setGameHalf("1");
      pbp.setGameDetail("Jonny Boy made Jumper");
      pbp.setTimeStamp("16:25");
      pbp.setTeamLogo("150.png");
      EspnPlayByPlay pbp2 = new EspnPlayByPlay();
      pbp2.setGameHalf("1");
      pbp2.setGameDetail("Jonny Boy made Jumper");
      pbp2.setTimeStamp("16:10");
      pbp2.setTeamLogo("20.png");
      EspnGamePlayByPlay espnGamePlayByPlay = new EspnGamePlayByPlay();
      espnGamePlayByPlay.setEspnPlayByPlays(new ArrayList<>());
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp2);
      given(basicObjectFactory.createPossession()).willReturn(possession);
      ncaaBasketballGameDataService.espnPlayByPlayExtractionService = new EspnPlayByPlayExtractionService();
      ncaaBasketballGameDataService.espnPlayByPlayExtractionService.playTypeInformationExtractorList = playTypeInformationExtractorList;
      ncaaBasketballGameDataService.ncaaBasketballGameDataServiceHelper = new NcaaBasketballGameDataServiceHelper();
      ncaaBasketballGameDataService.ncaaBasketballGameDataServiceHelper.basicObjectFactory = new BasicObjectFactory();

      List<Possession> possessions = ncaaBasketballGameDataService.gatherPossessions("20", espnGamePlayByPlay);
      assertEquals(2, possessions.size());
      assertEquals(2, possessions.get(0).getPossessionPlays().size());
   }

   @Test
   public void testGatherPossessions_Stoppage(){
      List<PlayTypeInformationExtractor> playTypeInformationExtractorList = new ArrayList<>();
      playTypeInformationExtractorList.add(new JumpballExtractor());
      playTypeInformationExtractorList.add(new TimeoutExtractor());
      playTypeInformationExtractorList.add(new ErrorExtractor());
      Possession possession = new Possession();
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setGameHalf("1");
      pbp.setGameDetail("Boston University Timeout");
      pbp.setTimeStamp("16:25");
      pbp.setTeamLogo("150.png");
      EspnGamePlayByPlay espnGamePlayByPlay = new EspnGamePlayByPlay();
      espnGamePlayByPlay.setEspnPlayByPlays(new ArrayList<>());
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp);
      given(basicObjectFactory.createPossession()).willReturn(possession);
      ncaaBasketballGameDataService.espnPlayByPlayExtractionService = new EspnPlayByPlayExtractionService();
      ncaaBasketballGameDataService.espnPlayByPlayExtractionService.playTypeInformationExtractorList = playTypeInformationExtractorList;
      ncaaBasketballGameDataService.ncaaBasketballGameDataServiceHelper = new NcaaBasketballGameDataServiceHelper();
      ncaaBasketballGameDataService.ncaaBasketballGameDataServiceHelper.basicObjectFactory = new BasicObjectFactory();

      List<Possession> possessions = ncaaBasketballGameDataService.gatherPossessions("20", espnGamePlayByPlay);
      assertEquals(1, possessions.size());
      assertEquals(2, possessions.get(0).getPossessionPlays().size());
   }

   @Test
   public void testGatherPossessions_Stoppage2(){
      List<PlayTypeInformationExtractor> playTypeInformationExtractorList = new ArrayList<>();
      playTypeInformationExtractorList.add(new JumpballExtractor());
      playTypeInformationExtractorList.add(new TimeoutExtractor());
      playTypeInformationExtractorList.add(new MadeTwoPointJumperExtractor());
      playTypeInformationExtractorList.add(new ErrorExtractor());
      Possession possession = new Possession();
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setGameHalf("1");
      pbp.setGameDetail("Boston University Timeout");
      pbp.setTimeStamp("16:25");
      pbp.setTeamLogo("150.png");
      EspnPlayByPlay pbp2 = new EspnPlayByPlay();
      pbp2.setGameHalf("1");
      pbp2.setGameDetail("Jonny Cool made Jumper");
      pbp2.setTimeStamp("16:20");
      pbp2.setTeamLogo("150.png");
      EspnGamePlayByPlay espnGamePlayByPlay = new EspnGamePlayByPlay();
      espnGamePlayByPlay.setEspnPlayByPlays(new ArrayList<>());
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp2);
      given(basicObjectFactory.createPossession()).willReturn(possession);
      ncaaBasketballGameDataService.espnPlayByPlayExtractionService = new EspnPlayByPlayExtractionService();
      ncaaBasketballGameDataService.espnPlayByPlayExtractionService.playTypeInformationExtractorList = playTypeInformationExtractorList;
      ncaaBasketballGameDataService.ncaaBasketballGameDataServiceHelper = new NcaaBasketballGameDataServiceHelper();
      ncaaBasketballGameDataService.ncaaBasketballGameDataServiceHelper.basicObjectFactory = new BasicObjectFactory();

      List<Possession> possessions = ncaaBasketballGameDataService.gatherPossessions("20", espnGamePlayByPlay);
      assertEquals(2, possessions.size());
      assertEquals(2, possessions.get(0).getPossessionPlays().size());
   }

   @Test
   public void testGatherPossessions_Jumpball(){
      List<PlayTypeInformationExtractor> playTypeInformationExtractorList = new ArrayList<>();
      playTypeInformationExtractorList.add(new JumpballExtractor());
      playTypeInformationExtractorList.add(new TimeoutExtractor());
      playTypeInformationExtractorList.add(new ErrorExtractor());
      Possession possession = new Possession();
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setGameHalf("1");
      pbp.setGameDetail("Jump Ball Boston University");
      pbp.setTimeStamp("16:25");
      pbp.setTeamLogo("150.png");
      EspnGamePlayByPlay espnGamePlayByPlay = new EspnGamePlayByPlay();
      espnGamePlayByPlay.setEspnPlayByPlays(new ArrayList<>());
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp);
      given(basicObjectFactory.createPossession()).willReturn(possession);
      ncaaBasketballGameDataService.espnPlayByPlayExtractionService = new EspnPlayByPlayExtractionService();
      ncaaBasketballGameDataService.espnPlayByPlayExtractionService.playTypeInformationExtractorList = playTypeInformationExtractorList;
      ncaaBasketballGameDataService.ncaaBasketballGameDataServiceHelper = new NcaaBasketballGameDataServiceHelper();
      ncaaBasketballGameDataService.ncaaBasketballGameDataServiceHelper.basicObjectFactory = new BasicObjectFactory();

      List<Possession> possessions = ncaaBasketballGameDataService.gatherPossessions("20", espnGamePlayByPlay);
      assertEquals(1, possessions.size());
      assertEquals(1, possessions.get(0).getPossessionPlays().size());
   }

   @Test
   public void testGatherPossessions_End_ThenBegin_wSameTime(){
      List<PlayTypeInformationExtractor> playTypeInformationExtractorList = new ArrayList<>();
      playTypeInformationExtractorList.add(new JumpballExtractor());
      playTypeInformationExtractorList.add(new MissTwoPointJumperExtractor());
      playTypeInformationExtractorList.add(new OffensiveReboundExtractor());
      playTypeInformationExtractorList.add(new BlockExtractor());
      playTypeInformationExtractorList.add(new ErrorExtractor());
      Possession possession = new Possession();
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setGameHalf("1");
      pbp.setGameDetail("Jonny Boy missed Jumper");
      pbp.setTimeStamp("16:25");
      pbp.setTeamLogo("150.png");
      EspnPlayByPlay pbp2 = new EspnPlayByPlay();
      pbp2.setGameHalf("1");
      pbp2.setGameDetail("Big Boy Block");
      pbp2.setTimeStamp("16:25");
      pbp2.setTeamLogo("25.png");
      EspnPlayByPlay pbp3 = new EspnPlayByPlay();
      pbp3.setGameHalf("1");
      pbp3.setGameDetail("Jonny Boy Offensive Rebound");
      pbp3.setTimeStamp("16:10");
      pbp3.setTeamLogo("150.png");
      EspnGamePlayByPlay espnGamePlayByPlay = new EspnGamePlayByPlay();
      espnGamePlayByPlay.setEspnPlayByPlays(new ArrayList<>());
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp2);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp3);
      given(basicObjectFactory.createPossession()).willReturn(possession);
      ncaaBasketballGameDataService.espnPlayByPlayExtractionService = new EspnPlayByPlayExtractionService();
      ncaaBasketballGameDataService.espnPlayByPlayExtractionService.playTypeInformationExtractorList = playTypeInformationExtractorList;
      ncaaBasketballGameDataService.ncaaBasketballGameDataServiceHelper = new NcaaBasketballGameDataServiceHelper();
      ncaaBasketballGameDataService.ncaaBasketballGameDataServiceHelper.basicObjectFactory = new BasicObjectFactory();

      List<Possession> possessions = ncaaBasketballGameDataService.gatherPossessions("20", espnGamePlayByPlay);
      assertEquals(2, possessions.size());
      assertEquals(3, possessions.get(0).getPossessionPlays().size());
   }

   @Test
   public void testGatherPossessions_TwoBegins(){
      List<PlayTypeInformationExtractor> playTypeInformationExtractorList = new ArrayList<>();
      playTypeInformationExtractorList.add(new JumpballExtractor());
      playTypeInformationExtractorList.add(new MissTwoPointJumperExtractor());
      playTypeInformationExtractorList.add(new OffensiveReboundExtractor());
      playTypeInformationExtractorList.add(new BlockExtractor());
      playTypeInformationExtractorList.add(new ErrorExtractor());
      Possession possession = new Possession();
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setGameHalf("1");
      pbp.setGameDetail("Jonny Boy missed Jumper");
      pbp.setTimeStamp("16:25");
      pbp.setTeamLogo("150.png");
      EspnPlayByPlay pbp2 = new EspnPlayByPlay();
      pbp2.setGameHalf("1");
      pbp2.setGameDetail("Big Boy Block");
      pbp2.setTimeStamp("16:25");
      pbp2.setTeamLogo("25.png");
      EspnPlayByPlay pbp3 = new EspnPlayByPlay();
      pbp3.setGameHalf("1");
      pbp3.setGameDetail("Jonny Boy Offensive Rebound");
      pbp3.setTimeStamp("16:10");
      pbp3.setTeamLogo("150.png");
      EspnPlayByPlay pbp4 = new EspnPlayByPlay();
      pbp4.setGameHalf("1");
      pbp4.setGameDetail("Jonny Boy Offensive Rebound");
      pbp4.setTimeStamp("15:30");
      pbp4.setTeamLogo("150.png");
      EspnGamePlayByPlay espnGamePlayByPlay = new EspnGamePlayByPlay();
      espnGamePlayByPlay.setEspnPlayByPlays(new ArrayList<>());
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp2);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp3);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp4);
      given(basicObjectFactory.createPossession()).willReturn(possession);
      ncaaBasketballGameDataService.espnPlayByPlayExtractionService = new EspnPlayByPlayExtractionService();
      ncaaBasketballGameDataService.espnPlayByPlayExtractionService.playTypeInformationExtractorList = playTypeInformationExtractorList;
      ncaaBasketballGameDataService.ncaaBasketballGameDataServiceHelper = new NcaaBasketballGameDataServiceHelper();
      ncaaBasketballGameDataService.ncaaBasketballGameDataServiceHelper.basicObjectFactory = new BasicObjectFactory();

      List<Possession> possessions = ncaaBasketballGameDataService.gatherPossessions("20", espnGamePlayByPlay);
      assertEquals(3, possessions.size());
      assertEquals(3, possessions.get(0).getPossessionPlays().size());
   }

   @Test
   public void testGatherPossessions_TwoEndBegins(){
      List<PlayTypeInformationExtractor> playTypeInformationExtractorList = new ArrayList<>();
      playTypeInformationExtractorList.add(new JumpballExtractor());
      playTypeInformationExtractorList.add(new MissTwoPointJumperExtractor());
      playTypeInformationExtractorList.add(new MadeTwoPointJumperExtractor());
      playTypeInformationExtractorList.add(new OffensiveReboundExtractor());
      playTypeInformationExtractorList.add(new DefensiveReboundExtractor());
      playTypeInformationExtractorList.add(new BlockExtractor());
      playTypeInformationExtractorList.add(new ErrorExtractor());
      Possession possession = new Possession();
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setGameHalf("1");
      pbp.setGameDetail("Jonny Boy missed Jumper");
      pbp.setTimeStamp("16:25");
      pbp.setTeamLogo("150.png");
      EspnPlayByPlay pbp2 = new EspnPlayByPlay();
      pbp2.setGameHalf("1");
      pbp2.setGameDetail("Big Boy Block");
      pbp2.setTimeStamp("16:25");
      pbp2.setTeamLogo("25.png");
      EspnPlayByPlay pbp3 = new EspnPlayByPlay();
      pbp3.setGameHalf("1");
      pbp3.setGameDetail("Jonny Boy Offensive Rebound");
      pbp3.setTimeStamp("16:10");
      pbp3.setTeamLogo("150.png");
      EspnPlayByPlay pbp4 = new EspnPlayByPlay();
      pbp4.setGameHalf("1");
      pbp4.setGameDetail("Jonny Boy made Jumper");
      pbp4.setTimeStamp("15:30");
      pbp4.setTeamLogo("150.png");
      EspnPlayByPlay pbp5 = new EspnPlayByPlay();
      pbp5.setGameHalf("1");
      pbp5.setGameDetail("Jonny Boy made Jumper");
      pbp5.setTimeStamp("15:00");
      pbp5.setTeamLogo("150.png");
      EspnGamePlayByPlay espnGamePlayByPlay = new EspnGamePlayByPlay();
      espnGamePlayByPlay.setEspnPlayByPlays(new ArrayList<>());
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp2);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp3);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp4);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp5);
      given(basicObjectFactory.createPossession()).willReturn(possession);
      ncaaBasketballGameDataService.espnPlayByPlayExtractionService = new EspnPlayByPlayExtractionService();
      ncaaBasketballGameDataService.espnPlayByPlayExtractionService.playTypeInformationExtractorList = playTypeInformationExtractorList;
      ncaaBasketballGameDataService.ncaaBasketballGameDataServiceHelper = new NcaaBasketballGameDataServiceHelper();
      ncaaBasketballGameDataService.ncaaBasketballGameDataServiceHelper.basicObjectFactory = new BasicObjectFactory();

      List<Possession> possessions = ncaaBasketballGameDataService.gatherPossessions("20", espnGamePlayByPlay);
      assertEquals(3, possessions.size());
      assertEquals(3, possessions.get(0).getPossessionPlays().size());
   }

   @Test
   public void testGatherPossessions_TwoEndsOffensiveRebound(){
      List<PlayTypeInformationExtractor> playTypeInformationExtractorList = new ArrayList<>();
      playTypeInformationExtractorList.add(new JumpballExtractor());
      playTypeInformationExtractorList.add(new MissTwoPointJumperExtractor());
      playTypeInformationExtractorList.add(new MadeTwoPointJumperExtractor());
      playTypeInformationExtractorList.add(new OffensiveReboundExtractor());
      playTypeInformationExtractorList.add(new DefensiveReboundExtractor());
      playTypeInformationExtractorList.add(new BlockExtractor());
      playTypeInformationExtractorList.add(new ErrorExtractor());
      Possession possession = new Possession();
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setGameHalf("1");
      pbp.setGameDetail("Jonny Boy missed Jumper");
      pbp.setTimeStamp("16:25");
      pbp.setTeamLogo("150.png");
      EspnPlayByPlay pbp2 = new EspnPlayByPlay();
      pbp2.setGameHalf("1");
      pbp2.setGameDetail("Big Boy Block");
      pbp2.setTimeStamp("16:25");
      pbp2.setTeamLogo("25.png");
      EspnPlayByPlay pbp3 = new EspnPlayByPlay();
      pbp3.setGameHalf("1");
      pbp3.setGameDetail("Jonny Boy Offensive Rebound");
      pbp3.setTimeStamp("16:10");
      pbp3.setTeamLogo("150.png");
      EspnPlayByPlay pbp4 = new EspnPlayByPlay();
      pbp4.setGameHalf("1");
      pbp4.setGameDetail("Jonny Boy missed Jumper");
      pbp4.setTimeStamp("15:30");
      pbp4.setTeamLogo("150.png");
      EspnPlayByPlay pbp5 = new EspnPlayByPlay();
      pbp5.setGameHalf("1");
      pbp5.setGameDetail("Jonny Boy missed Jumper");
      pbp5.setTimeStamp("15:00");
      pbp5.setTeamLogo("150.png");
      EspnGamePlayByPlay espnGamePlayByPlay = new EspnGamePlayByPlay();
      espnGamePlayByPlay.setEspnPlayByPlays(new ArrayList<>());
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp2);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp3);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp4);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp5);
      given(basicObjectFactory.createPossession()).willReturn(possession);
      ncaaBasketballGameDataService.espnPlayByPlayExtractionService = new EspnPlayByPlayExtractionService();
      ncaaBasketballGameDataService.espnPlayByPlayExtractionService.playTypeInformationExtractorList = playTypeInformationExtractorList;
      ncaaBasketballGameDataService.ncaaBasketballGameDataServiceHelper = new NcaaBasketballGameDataServiceHelper();
      ncaaBasketballGameDataService.ncaaBasketballGameDataServiceHelper.basicObjectFactory = new BasicObjectFactory();

      List<Possession> possessions = ncaaBasketballGameDataService.gatherPossessions("20", espnGamePlayByPlay);
      assertEquals(3, possessions.size());
      assertEquals(3, possessions.get(0).getPossessionPlays().size());
   }

   @Test
   public void testGatherPossessions_TwoEndsDefensiveRebound(){
      List<PlayTypeInformationExtractor> playTypeInformationExtractorList = new ArrayList<>();
      playTypeInformationExtractorList.add(new JumpballExtractor());
      playTypeInformationExtractorList.add(new MissTwoPointJumperExtractor());
      playTypeInformationExtractorList.add(new MadeTwoPointJumperExtractor());
      playTypeInformationExtractorList.add(new OffensiveReboundExtractor());
      playTypeInformationExtractorList.add(new DefensiveReboundExtractor());
      playTypeInformationExtractorList.add(new BlockExtractor());
      playTypeInformationExtractorList.add(new ErrorExtractor());
      Possession possession = new Possession();
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setGameHalf("1");
      pbp.setGameDetail("Jonny Boy missed Jumper");
      pbp.setTimeStamp("16:25");
      pbp.setTeamLogo("150.png");
      EspnPlayByPlay pbp2 = new EspnPlayByPlay();
      pbp2.setGameHalf("1");
      pbp2.setGameDetail("Big Boy Block");
      pbp2.setTimeStamp("16:25");
      pbp2.setTeamLogo("20.png");
      EspnPlayByPlay pbp3 = new EspnPlayByPlay();
      pbp3.setGameHalf("1");
      pbp3.setGameDetail("Jonny Boy Offensive Rebound");
      pbp3.setTimeStamp("16:10");
      pbp3.setTeamLogo("150.png");
      EspnPlayByPlay pbp4 = new EspnPlayByPlay();
      pbp4.setGameHalf("1");
      pbp4.setGameDetail("Jonny Boy missed Jumper");
      pbp4.setTimeStamp("15:30");
      pbp4.setTeamLogo("150.png");
      EspnPlayByPlay pbp5 = new EspnPlayByPlay();
      pbp5.setGameHalf("1");
      pbp5.setGameDetail("Jonny Boy missed Jumper");
      pbp5.setTimeStamp("15:00");
      pbp5.setTeamLogo("20.png");
      EspnGamePlayByPlay espnGamePlayByPlay = new EspnGamePlayByPlay();
      espnGamePlayByPlay.setEspnPlayByPlays(new ArrayList<>());
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp2);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp3);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp4);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp5);
      given(basicObjectFactory.createPossession()).willReturn(possession);
      ncaaBasketballGameDataService.espnPlayByPlayExtractionService = new EspnPlayByPlayExtractionService();
      ncaaBasketballGameDataService.espnPlayByPlayExtractionService.playTypeInformationExtractorList = playTypeInformationExtractorList;
      ncaaBasketballGameDataService.ncaaBasketballGameDataServiceHelper = new NcaaBasketballGameDataServiceHelper();
      ncaaBasketballGameDataService.ncaaBasketballGameDataServiceHelper.basicObjectFactory = new BasicObjectFactory();

      List<Possession> possessions = ncaaBasketballGameDataService.gatherPossessions("20", espnGamePlayByPlay);
      assertEquals(3, possessions.size());
      assertEquals(3, possessions.get(0).getPossessionPlays().size());
   }

   @Test
   public void testGatherPossessions_FoulShots(){
      List<PlayTypeInformationExtractor> playTypeInformationExtractorList = new ArrayList<>();
      playTypeInformationExtractorList.add(new JumpballExtractor());
      playTypeInformationExtractorList.add(new MissTwoPointJumperExtractor());
      playTypeInformationExtractorList.add(new MadeTwoPointJumperExtractor());
      playTypeInformationExtractorList.add(new OffensiveReboundExtractor());
      playTypeInformationExtractorList.add(new DefensiveReboundExtractor());
      playTypeInformationExtractorList.add(new FoulExtractor());
      playTypeInformationExtractorList.add(new MissFreeThrowExtractor());
      playTypeInformationExtractorList.add(new MadeFreeThrowExtractor());
      playTypeInformationExtractorList.add(new DeadBallTeamReboundExtractor());
      playTypeInformationExtractorList.add(new BlockExtractor());
      playTypeInformationExtractorList.add(new ErrorExtractor());
      Possession possession = new Possession();
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setGameHalf("1");
      pbp.setGameDetail("Jonny Boy missed Jumper");
      pbp.setTimeStamp("16:25");
      pbp.setTeamLogo("150.png");
      EspnPlayByPlay pbp2 = new EspnPlayByPlay();
      pbp2.setGameHalf("1");
      pbp2.setGameDetail("Big Boy Block");
      pbp2.setTimeStamp("16:25");
      pbp2.setTeamLogo("25.png");
      EspnPlayByPlay pbp3 = new EspnPlayByPlay();
      pbp3.setGameHalf("1");
      pbp3.setGameDetail("Jonny Boy Offensive Rebound");
      pbp3.setTimeStamp("16:10");
      pbp3.setTeamLogo("150.png");
      EspnPlayByPlay pbp4 = new EspnPlayByPlay();
      pbp4.setGameHalf("1");
      pbp4.setGameDetail("Foul on Tony Boy");
      pbp4.setTimeStamp("15:30");
      pbp4.setTeamLogo("20.png");
      EspnPlayByPlay pbp5 = new EspnPlayByPlay();
      pbp5.setGameHalf("1");
      pbp5.setGameDetail("Jonny Boy missed Free Throw");
      pbp5.setTimeStamp("15:30");
      pbp5.setTeamLogo("150.png");
      EspnPlayByPlay pbp6 = new EspnPlayByPlay();
      pbp6.setGameHalf("1");
      pbp6.setGameDetail("Fake Deadball Team Rebound");
      pbp6.setTimeStamp("15:30");
      pbp6.setTeamLogo("150.png");
      EspnPlayByPlay pbp7 = new EspnPlayByPlay();
      pbp7.setGameHalf("1");
      pbp7.setGameDetail("Jonny Boy made Free Throw");
      pbp7.setTimeStamp("15:30");
      pbp7.setTeamLogo("150.png");
      EspnPlayByPlay pbp8 = new EspnPlayByPlay();
      pbp8.setGameHalf("1");
      pbp8.setGameDetail("Jonny Boy2 made Jumper");
      pbp8.setTimeStamp("15:00");
      pbp8.setTeamLogo("20.png");
      EspnGamePlayByPlay espnGamePlayByPlay = new EspnGamePlayByPlay();
      espnGamePlayByPlay.setEspnPlayByPlays(new ArrayList<>());
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp2);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp3);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp4);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp5);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp6);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp7);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp8);
      given(basicObjectFactory.createPossession()).willReturn(possession);
      ncaaBasketballGameDataService.espnPlayByPlayExtractionService = new EspnPlayByPlayExtractionService();
      ncaaBasketballGameDataService.espnPlayByPlayExtractionService.playTypeInformationExtractorList = playTypeInformationExtractorList;
      ncaaBasketballGameDataService.ncaaBasketballGameDataServiceHelper = new NcaaBasketballGameDataServiceHelper();
      ncaaBasketballGameDataService.ncaaBasketballGameDataServiceHelper.basicObjectFactory = new BasicObjectFactory();

      List<Possession> possessions = ncaaBasketballGameDataService.gatherPossessions("20", espnGamePlayByPlay);
      assertEquals(3, possessions.size());
      assertEquals(3, possessions.get(0).getPossessionPlays().size());
   }

   @Test
   public void testGatherPossessions_FoulAlone(){
      List<PlayTypeInformationExtractor> playTypeInformationExtractorList = new ArrayList<>();
      playTypeInformationExtractorList.add(new JumpballExtractor());
      playTypeInformationExtractorList.add(new MissTwoPointJumperExtractor());
      playTypeInformationExtractorList.add(new MadeTwoPointJumperExtractor());
      playTypeInformationExtractorList.add(new OffensiveReboundExtractor());
      playTypeInformationExtractorList.add(new DefensiveReboundExtractor());
      playTypeInformationExtractorList.add(new FoulExtractor());
      playTypeInformationExtractorList.add(new MissFreeThrowExtractor());
      playTypeInformationExtractorList.add(new MadeFreeThrowExtractor());
      playTypeInformationExtractorList.add(new DeadBallTeamReboundExtractor());
      playTypeInformationExtractorList.add(new BlockExtractor());
      playTypeInformationExtractorList.add(new ErrorExtractor());
      Possession possession = new Possession();
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setGameHalf("1");
      pbp.setGameDetail("Jonny Boy missed Jumper");
      pbp.setTimeStamp("16:25");
      pbp.setTeamLogo("150.png");
      EspnPlayByPlay pbp2 = new EspnPlayByPlay();
      pbp2.setGameHalf("1");
      pbp2.setGameDetail("Big Boy Block");
      pbp2.setTimeStamp("16:25");
      pbp2.setTeamLogo("25.png");
      EspnPlayByPlay pbp3 = new EspnPlayByPlay();
      pbp3.setGameHalf("1");
      pbp3.setGameDetail("Jonny Boy Offensive Rebound");
      pbp3.setTimeStamp("16:10");
      pbp3.setTeamLogo("150.png");
      EspnPlayByPlay pbp4 = new EspnPlayByPlay();
      pbp4.setGameHalf("1");
      pbp4.setGameDetail("Foul on Tony Boy");
      pbp4.setTimeStamp("15:30");
      pbp4.setTeamLogo("20.png");
      EspnPlayByPlay pbp5 = new EspnPlayByPlay();
      pbp5.setGameHalf("1");
      pbp5.setGameDetail("Jonny Boy made Jumper");
      pbp5.setTimeStamp("15:00");
      pbp5.setTeamLogo("150.png");
      EspnGamePlayByPlay espnGamePlayByPlay = new EspnGamePlayByPlay();
      espnGamePlayByPlay.setEspnPlayByPlays(new ArrayList<>());
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp2);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp3);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp4);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp5);
      given(basicObjectFactory.createPossession()).willReturn(possession);
      ncaaBasketballGameDataService.espnPlayByPlayExtractionService = new EspnPlayByPlayExtractionService();
      ncaaBasketballGameDataService.espnPlayByPlayExtractionService.playTypeInformationExtractorList = playTypeInformationExtractorList;
      ncaaBasketballGameDataService.ncaaBasketballGameDataServiceHelper = new NcaaBasketballGameDataServiceHelper();
      ncaaBasketballGameDataService.ncaaBasketballGameDataServiceHelper.basicObjectFactory = new BasicObjectFactory();

      List<Possession> possessions = ncaaBasketballGameDataService.gatherPossessions("20", espnGamePlayByPlay);
      assertEquals(3, possessions.size());
      assertEquals(3, possessions.get(0).getPossessionPlays().size());
   }

   @Test
   public void testGatherPossessions_FoulTurnover(){
      List<PlayTypeInformationExtractor> playTypeInformationExtractorList = new ArrayList<>();
      playTypeInformationExtractorList.add(new JumpballExtractor());
      playTypeInformationExtractorList.add(new MissTwoPointJumperExtractor());
      playTypeInformationExtractorList.add(new MadeTwoPointJumperExtractor());
      playTypeInformationExtractorList.add(new OffensiveReboundExtractor());
      playTypeInformationExtractorList.add(new DefensiveReboundExtractor());
      playTypeInformationExtractorList.add(new FoulExtractor());
      playTypeInformationExtractorList.add(new MissFreeThrowExtractor());
      playTypeInformationExtractorList.add(new MadeFreeThrowExtractor());
      playTypeInformationExtractorList.add(new DeadBallTeamReboundExtractor());
      playTypeInformationExtractorList.add(new BlockExtractor());
      playTypeInformationExtractorList.add(new ErrorExtractor());
      playTypeInformationExtractorList.add(new TurnoverExtractor());
      Possession possession = new Possession();
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setGameHalf("1");
      pbp.setGameDetail("Jonny Boy missed Jumper");
      pbp.setTimeStamp("16:25");
      pbp.setTeamLogo("150.png");
      EspnPlayByPlay pbp2 = new EspnPlayByPlay();
      pbp2.setGameHalf("1");
      pbp2.setGameDetail("Big Boy Block");
      pbp2.setTimeStamp("16:25");
      pbp2.setTeamLogo("25.png");
      EspnPlayByPlay pbp3 = new EspnPlayByPlay();
      pbp3.setGameHalf("1");
      pbp3.setGameDetail("Jonny Boy Offensive Rebound");
      pbp3.setTimeStamp("16:10");
      pbp3.setTeamLogo("150.png");
      EspnPlayByPlay pbp4 = new EspnPlayByPlay();
      pbp4.setGameHalf("1");
      pbp4.setGameDetail("Foul on Tony Boy");
      pbp4.setTimeStamp("15:30");
      pbp4.setTeamLogo("150.png");
      EspnPlayByPlay pbp5 = new EspnPlayByPlay();
      pbp5.setGameHalf("1");
      pbp5.setGameDetail("Tony Boy Turnover");
      pbp5.setTimeStamp("15:30");
      pbp5.setTeamLogo("150.png");
      EspnPlayByPlay pbp6 = new EspnPlayByPlay();
      pbp6.setGameHalf("1");
      pbp6.setGameDetail("Jonny Boy made Jumper");
      pbp6.setTimeStamp("15:00");
      pbp6.setTeamLogo("20.png");
      EspnGamePlayByPlay espnGamePlayByPlay = new EspnGamePlayByPlay();
      espnGamePlayByPlay.setEspnPlayByPlays(new ArrayList<>());
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp2);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp3);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp4);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp5);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp6);
      given(basicObjectFactory.createPossession()).willReturn(possession);
      ncaaBasketballGameDataService.espnPlayByPlayExtractionService = new EspnPlayByPlayExtractionService();
      ncaaBasketballGameDataService.espnPlayByPlayExtractionService.playTypeInformationExtractorList = playTypeInformationExtractorList;
      ncaaBasketballGameDataService.ncaaBasketballGameDataServiceHelper = new NcaaBasketballGameDataServiceHelper();
      ncaaBasketballGameDataService.ncaaBasketballGameDataServiceHelper.basicObjectFactory = new BasicObjectFactory();

      List<Possession> possessions = ncaaBasketballGameDataService.gatherPossessions("20", espnGamePlayByPlay);
      assertEquals(3, possessions.size());
      assertEquals(3, possessions.get(0).getPossessionPlays().size());
   }

   @Test
   public void testGatherPossessions_TurnoverSteal(){
      List<PlayTypeInformationExtractor> playTypeInformationExtractorList = new ArrayList<>();
      playTypeInformationExtractorList.add(new JumpballExtractor());
      playTypeInformationExtractorList.add(new MissTwoPointJumperExtractor());
      playTypeInformationExtractorList.add(new MadeTwoPointJumperExtractor());
      playTypeInformationExtractorList.add(new OffensiveReboundExtractor());
      playTypeInformationExtractorList.add(new DefensiveReboundExtractor());
      playTypeInformationExtractorList.add(new FoulExtractor());
      playTypeInformationExtractorList.add(new StealExtractor());
      playTypeInformationExtractorList.add(new MissFreeThrowExtractor());
      playTypeInformationExtractorList.add(new MadeFreeThrowExtractor());
      playTypeInformationExtractorList.add(new DeadBallTeamReboundExtractor());
      playTypeInformationExtractorList.add(new BlockExtractor());
      playTypeInformationExtractorList.add(new ErrorExtractor());
      playTypeInformationExtractorList.add(new TurnoverExtractor());
      Possession possession = new Possession();
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setGameHalf("1");
      pbp.setGameDetail("Jonny Boy missed Jumper");
      pbp.setTimeStamp("16:25");
      pbp.setTeamLogo("150.png");
      EspnPlayByPlay pbp2 = new EspnPlayByPlay();
      pbp2.setGameHalf("1");
      pbp2.setGameDetail("Big Boy Block");
      pbp2.setTimeStamp("16:25");
      pbp2.setTeamLogo("25.png");
      EspnPlayByPlay pbp3 = new EspnPlayByPlay();
      pbp3.setGameHalf("1");
      pbp3.setGameDetail("Jonny Boy Offensive Rebound");
      pbp3.setTimeStamp("16:10");
      pbp3.setTeamLogo("150.png");
      EspnPlayByPlay pbp4 = new EspnPlayByPlay();
      pbp4.setGameHalf("1");
      pbp4.setGameDetail("Tony Boy Turnover");
      pbp4.setTimeStamp("15:30");
      pbp4.setTeamLogo("150.png");
      EspnPlayByPlay pbp5 = new EspnPlayByPlay();
      pbp5.setGameHalf("1");
      pbp5.setGameDetail("Jonny Boy Steal");
      pbp5.setTimeStamp("15:30");
      pbp5.setTeamLogo("150.png");
      EspnPlayByPlay pbp6 = new EspnPlayByPlay();
      pbp6.setGameHalf("1");
      pbp6.setGameDetail("Jonny Boy made Jumper");
      pbp6.setTimeStamp("15:00");
      pbp6.setTeamLogo("20.png");
      EspnGamePlayByPlay espnGamePlayByPlay = new EspnGamePlayByPlay();
      espnGamePlayByPlay.setEspnPlayByPlays(new ArrayList<>());
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp2);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp3);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp4);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp5);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp6);
      given(basicObjectFactory.createPossession()).willReturn(possession);
      ncaaBasketballGameDataService.espnPlayByPlayExtractionService = new EspnPlayByPlayExtractionService();
      ncaaBasketballGameDataService.espnPlayByPlayExtractionService.playTypeInformationExtractorList = playTypeInformationExtractorList;
      ncaaBasketballGameDataService.ncaaBasketballGameDataServiceHelper = new NcaaBasketballGameDataServiceHelper();
      ncaaBasketballGameDataService.ncaaBasketballGameDataServiceHelper.basicObjectFactory = new BasicObjectFactory();

      List<Possession> possessions = ncaaBasketballGameDataService.gatherPossessions("20", espnGamePlayByPlay);
      assertEquals(3, possessions.size());
      assertEquals(3, possessions.get(0).getPossessionPlays().size());
   }


   @Test
   public void testGatherPossessions_FoulShots2(){
      List<PlayTypeInformationExtractor> playTypeInformationExtractorList = new ArrayList<>();
      playTypeInformationExtractorList.add(new JumpballExtractor());
      playTypeInformationExtractorList.add(new MissTwoPointJumperExtractor());
      playTypeInformationExtractorList.add(new MadeTwoPointJumperExtractor());
      playTypeInformationExtractorList.add(new OffensiveReboundExtractor());
      playTypeInformationExtractorList.add(new DefensiveReboundExtractor());
      playTypeInformationExtractorList.add(new FoulExtractor());
      playTypeInformationExtractorList.add(new MissFreeThrowExtractor());
      playTypeInformationExtractorList.add(new MadeFreeThrowExtractor());
      playTypeInformationExtractorList.add(new DeadBallTeamReboundExtractor());
      playTypeInformationExtractorList.add(new BlockExtractor());
      playTypeInformationExtractorList.add(new ErrorExtractor());
      Possession possession = new Possession();
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setGameHalf("1");
      pbp.setGameDetail("Jonny Boy missed Jumper");
      pbp.setTimeStamp("16:25");
      pbp.setTeamLogo("150.png");
      EspnPlayByPlay pbp2 = new EspnPlayByPlay();
      pbp2.setGameHalf("1");
      pbp2.setGameDetail("Big Boy Block");
      pbp2.setTimeStamp("16:25");
      pbp2.setTeamLogo("25.png");
      EspnPlayByPlay pbp3 = new EspnPlayByPlay();
      pbp3.setGameHalf("1");
      pbp3.setGameDetail("Jonny Boy Offensive Rebound");
      pbp3.setTimeStamp("16:10");
      pbp3.setTeamLogo("150.png");
      EspnPlayByPlay pbp4 = new EspnPlayByPlay();
      pbp4.setGameHalf("1");
      pbp4.setGameDetail("Foul on Tony Boy");
      pbp4.setTimeStamp("15:30");
      pbp4.setTeamLogo("20.png");
      EspnPlayByPlay pbp5 = new EspnPlayByPlay();
      pbp5.setGameHalf("1");
      pbp5.setGameDetail("Jonny Boy made Free Throw");
      pbp5.setTimeStamp("15:30");
      pbp5.setTeamLogo("150.png");
      EspnPlayByPlay pbp6 = new EspnPlayByPlay();
      pbp6.setGameHalf("1");
      pbp6.setGameDetail("Jonny Boy missed Free Throw");
      pbp6.setTimeStamp("15:30");
      pbp6.setTeamLogo("150.png");
      EspnPlayByPlay pbp7 = new EspnPlayByPlay();
      pbp7.setGameHalf("1");
      pbp7.setGameDetail("Jonny Boy Offensive Rebound");
      pbp7.setTimeStamp("15:25");
      pbp7.setTeamLogo("150.png");
      EspnGamePlayByPlay espnGamePlayByPlay = new EspnGamePlayByPlay();
      espnGamePlayByPlay.setEspnPlayByPlays(new ArrayList<>());
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp2);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp3);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp4);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp5);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp6);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp7);
      given(basicObjectFactory.createPossession()).willReturn(possession);
      ncaaBasketballGameDataService.espnPlayByPlayExtractionService = new EspnPlayByPlayExtractionService();
      ncaaBasketballGameDataService.espnPlayByPlayExtractionService.playTypeInformationExtractorList = playTypeInformationExtractorList;
      ncaaBasketballGameDataService.ncaaBasketballGameDataServiceHelper = new NcaaBasketballGameDataServiceHelper();
      ncaaBasketballGameDataService.ncaaBasketballGameDataServiceHelper.basicObjectFactory = new BasicObjectFactory();

      List<Possession> possessions = ncaaBasketballGameDataService.gatherPossessions("20", espnGamePlayByPlay);
      assertEquals(3, possessions.size());
      assertEquals(3, possessions.get(0).getPossessionPlays().size());
   }

   @Test
   public void testGatherPossessions_FoulShots3(){
      List<PlayTypeInformationExtractor> playTypeInformationExtractorList = new ArrayList<>();
      playTypeInformationExtractorList.add(new JumpballExtractor());
      playTypeInformationExtractorList.add(new MissTwoPointJumperExtractor());
      playTypeInformationExtractorList.add(new MadeTwoPointJumperExtractor());
      playTypeInformationExtractorList.add(new OffensiveReboundExtractor());
      playTypeInformationExtractorList.add(new DefensiveReboundExtractor());
      playTypeInformationExtractorList.add(new FoulExtractor());
      playTypeInformationExtractorList.add(new MissFreeThrowExtractor());
      playTypeInformationExtractorList.add(new MadeFreeThrowExtractor());
      playTypeInformationExtractorList.add(new DeadBallTeamReboundExtractor());
      playTypeInformationExtractorList.add(new BlockExtractor());
      playTypeInformationExtractorList.add(new ErrorExtractor());
      playTypeInformationExtractorList.add(new MadeThreePointJumperExtractor());
      playTypeInformationExtractorList.add(new TimeoutExtractor());

      Possession possession = new Possession();
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setGameHalf("1");
      pbp.setGameDetail("T.J. Weeks Jr. made Three Point Jumper. Assisted by Brandon Martin.");
      pbp.setTimeStamp("16:25");
      pbp.setTeamLogo("150.png");
      EspnPlayByPlay pbp2 = new EspnPlayByPlay();
      pbp2.setGameHalf("1");
      pbp2.setGameDetail("Foul on Brandon Martin.");
      pbp2.setTimeStamp("16:10");
      pbp2.setTeamLogo("150.png");
      EspnPlayByPlay pbp3 = new EspnPlayByPlay();
      pbp3.setGameHalf("1");
      pbp3.setGameDetail("Official TV Timeout");
      pbp3.setTimeStamp("16:10");
      pbp3.setTeamLogo("150.png");
      EspnPlayByPlay pbp4 = new EspnPlayByPlay();
      pbp4.setGameHalf("1");
      pbp4.setGameDetail("Jay Rodgers made Free Throw.");
      pbp4.setTimeStamp("16:10");
      pbp4.setTeamLogo("20.png");
      EspnPlayByPlay pbp5 = new EspnPlayByPlay();
      pbp5.setGameHalf("1");
      pbp5.setGameDetail("Jay Rodgers missed Free Throw.");
      pbp5.setTimeStamp("16:10");
      pbp5.setTeamLogo("20.png");
      EspnPlayByPlay pbp6 = new EspnPlayByPlay();
      pbp6.setGameHalf("1");
      pbp6.setGameDetail("T.J. Weeks Jr. Defensive Rebound.");
      pbp6.setTimeStamp("15:30");
      pbp6.setTeamLogo("150.png");
      EspnGamePlayByPlay espnGamePlayByPlay = new EspnGamePlayByPlay();
      espnGamePlayByPlay.setEspnPlayByPlays(new ArrayList<>());
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp2);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp3);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp4);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp5);
      espnGamePlayByPlay.getEspnPlayByPlays().add(pbp6);
      given(basicObjectFactory.createPossession()).willReturn(possession);
      ncaaBasketballGameDataService.espnPlayByPlayExtractionService = new EspnPlayByPlayExtractionService();
      ncaaBasketballGameDataService.espnPlayByPlayExtractionService.playTypeInformationExtractorList = playTypeInformationExtractorList;
      ncaaBasketballGameDataService.ncaaBasketballGameDataServiceHelper = new NcaaBasketballGameDataServiceHelper();
      ncaaBasketballGameDataService.ncaaBasketballGameDataServiceHelper.basicObjectFactory = new BasicObjectFactory();

      List<Possession> possessions = ncaaBasketballGameDataService.gatherPossessions("20", espnGamePlayByPlay);
      assertEquals(3, possessions.size());
      assertEquals(2, possessions.get(0).getPossessionPlays().size());
   }
}
