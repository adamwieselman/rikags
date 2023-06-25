package com.rickags.services;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

import com.mashape.unirest.http.exceptions.UnirestException;

public class DataUpdateServiceIntegrationTest
{
   @Test
   @Ignore
   public void testPullAllScheduleDataInformation ()
      throws IOException, UnirestException
   {
//      RequestContext requestContext = new RequestContext();
//      requestContext.setStartDate(LocalDate.of(2021, 11, 1));
//      ResponseContext responseContext = new ResponseContext();
//      UnirestApiServiceHelper unirestApiServiceHelper = new UnirestApiServiceHelper();
//      JsonMapper jsonMapper = new JsonMapper();
//   //   DataUpdateServiceHelper dataUpdateServiceHelper = new DataUpdateServiceHelper();
//      EspnPlayByPlayExtractionService espnPlayByPlayExtractionService = new EspnPlayByPlayExtractionService();
//      HtmlExtractionService htmlExtractionService = new HtmlExtractionService();
//      UnirestApiService unirestApiService = new UnirestApiService();
//      unirestApiService.unirestApiServiceHelper = unirestApiServiceHelper;
//      unirestApiService.jsonMapper = jsonMapper;
//      NcaaBasketballPlayByPlayDataUpdateService dataUpdateService = new NcaaBasketballPlayByPlayDataUpdateService();
//      dataUpdateService.unirestApiService = unirestApiService;
//  //    dataUpdateService.dataUpdateServiceHelper = dataUpdateServiceHelper;
//      dataUpdateService.jsonMapper = jsonMapper;
//      htmlExtractionService.unirestApiService = unirestApiService;
//      htmlExtractionService.jsonMapper = jsonMapper;
//      dataUpdateService.htmlExtractionService = htmlExtractionService;
//      dataUpdateService.espnPlayByPlayExtractionService = espnPlayByPlayExtractionService;
//      List<PlayTypeInformationExtractor> playTypeInformationExtractorList = new ArrayList<>();
//      playTypeInformationExtractorList.add(new SkipExtractor());
//      playTypeInformationExtractorList.add(new DefensiveReboundExtractor());
//      playTypeInformationExtractorList.add(new ExtraPartsExtractor());
//      playTypeInformationExtractorList.add(new FoulExtractor());
//      playTypeInformationExtractorList.add(new MadeFreeThrowExtractor());
//      playTypeInformationExtractorList.add(new MadeThreePointJumperExtractor());
//      playTypeInformationExtractorList.add(new MadeTwoPointDunkExtractor());
//      playTypeInformationExtractorList.add(new MadeTwoPointJumperExtractor());
//      playTypeInformationExtractorList.add(new MadeTwoPointLayupExtractor());
//      playTypeInformationExtractorList.add(new MadeTwoPointTipShotExtractor());
//      playTypeInformationExtractorList.add(new MissFreeThrowExtractor());
//      playTypeInformationExtractorList.add(new MadeThreePointJumperExtractor());
//      playTypeInformationExtractorList.add(new MissTwoPointDunkExtractor());
//      playTypeInformationExtractorList.add(new MissTwoPointJumperExtractor());
//      playTypeInformationExtractorList.add(new MissTwoPointLayupExtractor());
//      playTypeInformationExtractorList.add(new MissTwoPointTipShotExtractor());
//      playTypeInformationExtractorList.add(new OffensiveReboundExtractor());
//      playTypeInformationExtractorList.add(new StealExtractor());
//      playTypeInformationExtractorList.add(new TurnoverExtractor());
//
//      espnPlayByPlayExtractionService.playTypeInformationExtractorList = playTypeInformationExtractorList;
//
//    //  dataUpdateService.pullAllScheduleDataInformation(requestContext, responseContext);
//

   }

   @Test
   @Ignore
   public void testPullAllPlayByPlayDataInformation ()
      throws Exception
   {
//      RequestContext requestContext = new RequestContext();
//      requestContext.setStartDate(LocalDate.of(2022, 2, 13));
//      ResponseContext responseContext = new ResponseContext();
//      UnirestApiServiceHelper unirestApiServiceHelper = new UnirestApiServiceHelper();
//      JsonMapper jsonMapper = new JsonMapper();
//      //DataUpdateServiceHelper dataUpdateServiceHelper = new DataUpdateServiceHelper();
//      EspnPlayByPlayExtractionService espnPlayByPlayExtractionService = new EspnPlayByPlayExtractionService();
//      HtmlExtractionService htmlExtractionService = new HtmlExtractionService();
//      UnirestApiService unirestApiService = new UnirestApiService();
//      unirestApiService.unirestApiServiceHelper = unirestApiServiceHelper;
//      unirestApiService.jsonMapper = jsonMapper;
//      NcaaBasketballPlayByPlayDataUpdateService dataUpdateService = new NcaaBasketballPlayByPlayDataUpdateService();
//      dataUpdateService.unirestApiService = unirestApiService;
//     // dataUpdateService.dataUpdateServiceHelper = dataUpdateServiceHelper;
//      dataUpdateService.jsonMapper = jsonMapper;
//      htmlExtractionService.unirestApiService = unirestApiService;
//      htmlExtractionService.jsonMapper = jsonMapper;
//      dataUpdateService.htmlExtractionService = htmlExtractionService;
//   //   dataUpdateServiceHelper.espnPlayByPlayExtractionService = espnPlayByPlayExtractionService;
//
//      List<PlayTypeInformationExtractor> playTypeInformationExtractorList = new ArrayList<>();
//      playTypeInformationExtractorList.add(new SkipExtractor());
//      playTypeInformationExtractorList.add(new DeadBallTeamReboundExtractor());
//      playTypeInformationExtractorList.add(new BlockExtractor());
//      playTypeInformationExtractorList.add(new DefensiveReboundExtractor());
//      playTypeInformationExtractorList.add(new ExtraPartsExtractor());
//      playTypeInformationExtractorList.add(new FoulExtractor());
//      playTypeInformationExtractorList.add(new MadeFreeThrowExtractor());
//      playTypeInformationExtractorList.add(new MadeThreePointJumperExtractor());
//      playTypeInformationExtractorList.add(new MadeTwoPointDunkExtractor());
//      playTypeInformationExtractorList.add(new MadeTwoPointJumperExtractor());
//      playTypeInformationExtractorList.add(new MadeTwoPointLayupExtractor());
//      playTypeInformationExtractorList.add(new MadeTwoPointTipShotExtractor());
//      playTypeInformationExtractorList.add(new MissFreeThrowExtractor());
//      playTypeInformationExtractorList.add(new MissThreePointJumperExtractor());
//      playTypeInformationExtractorList.add(new MissTwoPointDunkExtractor());
//      playTypeInformationExtractorList.add(new MissTwoPointJumperExtractor());
//      playTypeInformationExtractorList.add(new MissTwoPointLayupExtractor());
//      playTypeInformationExtractorList.add(new MissTwoPointTipShotExtractor());
//      playTypeInformationExtractorList.add(new OffensiveReboundExtractor());
//      playTypeInformationExtractorList.add(new StealExtractor());
//      playTypeInformationExtractorList.add(new TurnoverExtractor());
//
//      espnPlayByPlayExtractionService.playTypeInformationExtractorList = playTypeInformationExtractorList;
//      dataUpdateService.espnPlayByPlayExtractionService = espnPlayByPlayExtractionService;
//
//      dataUpdateService.pullAllPlayByPlayDataInformation(requestContext, responseContext);

   }

   @Test
   @Ignore
   public void testDisectAllPlayByPlayDataInformation ()
      throws Exception
   {
//         RequestContext requestContext = new RequestContext();
//         requestContext.setStartDate(LocalDate.of(2022, 2, 13));
//         ResponseContext responseContext = new ResponseContext();
//         UnirestApiServiceHelper unirestApiServiceHelper = new UnirestApiServiceHelper();
//         JsonMapper jsonMapper = new JsonMapper();
//        // DataUpdateServiceHelper dataUpdateServiceHelper = new DataUpdateServiceHelper();
//         EspnPlayByPlayExtractionService espnPlayByPlayExtractionService = new EspnPlayByPlayExtractionService();
//         HtmlExtractionService htmlExtractionService = new HtmlExtractionService();
//         UnirestApiService unirestApiService = new UnirestApiService();
//         unirestApiService.unirestApiServiceHelper = unirestApiServiceHelper;
//         unirestApiService.jsonMapper = jsonMapper;
//         NcaaBasketballPlayByPlayDataUpdateService dataUpdateService = new NcaaBasketballPlayByPlayDataUpdateService();
//         dataUpdateService.unirestApiService = unirestApiService;
//      //   dataUpdateService.dataUpdateServiceHelper = dataUpdateServiceHelper;
//         dataUpdateService.jsonMapper = jsonMapper;
//         htmlExtractionService.unirestApiService = unirestApiService;
//         htmlExtractionService.jsonMapper = jsonMapper;
//         dataUpdateService.htmlExtractionService = htmlExtractionService;
//     //    dataUpdateServiceHelper.espnPlayByPlayExtractionService = espnPlayByPlayExtractionService;
//
//         List<PlayTypeInformationExtractor> playTypeInformationExtractorList = new ArrayList<>();
//         playTypeInformationExtractorList.add(new SkipExtractor());
//         playTypeInformationExtractorList.add(new DeadBallTeamReboundExtractor());
//         playTypeInformationExtractorList.add(new BlockExtractor());
//         playTypeInformationExtractorList.add(new DefensiveReboundExtractor());
//         playTypeInformationExtractorList.add(new ExtraPartsExtractor());
//         playTypeInformationExtractorList.add(new FoulExtractor());
//         playTypeInformationExtractorList.add(new MadeFreeThrowExtractor());
//         playTypeInformationExtractorList.add(new MadeThreePointJumperExtractor());
//         playTypeInformationExtractorList.add(new MadeTwoPointDunkExtractor());
//         playTypeInformationExtractorList.add(new MadeTwoPointJumperExtractor());
//         playTypeInformationExtractorList.add(new MadeTwoPointLayupExtractor());
//         playTypeInformationExtractorList.add(new MadeTwoPointTipShotExtractor());
//         playTypeInformationExtractorList.add(new MissFreeThrowExtractor());
//         playTypeInformationExtractorList.add(new MissThreePointJumperExtractor());
//         playTypeInformationExtractorList.add(new MissTwoPointDunkExtractor());
//         playTypeInformationExtractorList.add(new MissTwoPointJumperExtractor());
//         playTypeInformationExtractorList.add(new MissTwoPointLayupExtractor());
//         playTypeInformationExtractorList.add(new MissTwoPointTipShotExtractor());
//         playTypeInformationExtractorList.add(new OffensiveReboundExtractor());
//         playTypeInformationExtractorList.add(new StealExtractor());
//         playTypeInformationExtractorList.add(new TurnoverExtractor());
//
//         espnPlayByPlayExtractionService.playTypeInformationExtractorList = playTypeInformationExtractorList;
//         dataUpdateService.espnPlayByPlayExtractionService = espnPlayByPlayExtractionService;
//
//         dataUpdateService.pullAllPlayByPlayDataInformation(requestContext, responseContext);
   }
}
