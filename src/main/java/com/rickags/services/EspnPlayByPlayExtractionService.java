package com.rickags.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rickags.extractors.ErrorExtractor;
import com.rickags.extractors.PlayTypeInformationExtractor;
import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

@Component
public class EspnPlayByPlayExtractionService
{
   @Autowired
   protected List<PlayTypeInformationExtractor> playTypeInformationExtractorList;

   public PlayTypeInformationExtractor extractPlayByPlayDetail (EspnPlayByPlay pbp)
   {
      for(PlayTypeInformationExtractor playTypeInformationExtractor : playTypeInformationExtractorList){
          if(playTypeInformationExtractor.supports(pbp.getGameDetail())){
               return playTypeInformationExtractor.generateExtractor(pbp);
          }
      }

      PlayTypeInformationExtractor playTypeInformationExtractor = new ErrorExtractor(pbp);

      return playTypeInformationExtractor;
   }
}
