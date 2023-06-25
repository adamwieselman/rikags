package com.rickags.services;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.rickags.extractors.BlockExtractor;
import com.rickags.extractors.ErrorExtractor;
import com.rickags.extractors.PlayTypeInformationExtractor;
import com.rickags.extractors.TurnoverExtractor;
import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

public class EspnPlayByPlayExtractionServiceTest
{
   @Rule
   public MockitoRule rule = MockitoJUnit.rule();

   @InjectMocks
   public EspnPlayByPlayExtractionService espnPlayByPlayExtractionService = new EspnPlayByPlayExtractionService();


   @Test
   public void testExtractPlayByPlayDetail_noMatch(){

      List<PlayTypeInformationExtractor> playTypeInformationExtractorListfake = new ArrayList<>();
      playTypeInformationExtractorListfake.add(new TurnoverExtractor());
      espnPlayByPlayExtractionService.playTypeInformationExtractorList = playTypeInformationExtractorListfake;

      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setGameDetail("Test Detail");

      PlayTypeInformationExtractor playTypeInformationExtractor = espnPlayByPlayExtractionService.extractPlayByPlayDetail(pbp);
      assertTrue(playTypeInformationExtractor instanceof ErrorExtractor);

   }

   @Test
   public void testExtractPlayByPlayDetail_Match(){
      List<PlayTypeInformationExtractor> playTypeInformationExtractorListfake = new ArrayList<>();
      playTypeInformationExtractorListfake.add(new TurnoverExtractor());
      playTypeInformationExtractorListfake.add(new BlockExtractor());
      espnPlayByPlayExtractionService.playTypeInformationExtractorList = playTypeInformationExtractorListfake;

      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setGameDetail("Jimmy Greek Block");

      PlayTypeInformationExtractor playTypeInformationExtractor = espnPlayByPlayExtractionService.extractPlayByPlayDetail(pbp);
      assertTrue(playTypeInformationExtractor instanceof BlockExtractor);

   }
}
