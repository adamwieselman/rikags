package com.rickags.extractors;

import org.springframework.stereotype.Component;

import com.rickags.facade.PbpFacade;
import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

@Component
public class ErrorExtractor extends PbpFacade implements PlayTypeInformationExtractor
{
   private static String FOUL = "";

   public ErrorExtractor (EspnPlayByPlay pbp)
   {
      super(pbp);
   }

   public ErrorExtractor (){
      super();
   }

   @Override
   public boolean supports (String gameDetail)
   {
      return false;
   }

   @Override
   public PlayTypeInformationExtractor generateExtractor (EspnPlayByPlay pbp)
   {
      return new ErrorExtractor(pbp);
   }

   @Override
   public String getPlayType ()
   {
      return "ERROR";
   }

   @Override
   public String getOverallPlayType ()
   {
      return "ERROR";
   }

   @Override
   public String getPlayPlayer ()
   {
      return null;
   }

   @Override
   public String getPriorPlayType ()
   {
      return "DEADBALL";
   }

   @Override
   public String getBeginningOrEnding ()
   {
      return null;
   }
}
