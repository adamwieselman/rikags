package com.rickags.extractors;

import org.springframework.stereotype.Component;

import com.rickags.facade.PbpFacade;
import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

@Component
public class FoulExtractor extends PbpFacade implements PlayTypeInformationExtractor
{
   private static String FOUL = "Foul on ";

   public FoulExtractor (EspnPlayByPlay pbp)
   {
      super(pbp);
   }

   public FoulExtractor(){
      super();
   }

   @Override
   public boolean supports (String gameDetail)
   {
      if (gameDetail.contains(FOUL)) {
         return true;
      }

      return false;
   }

   @Override
   public PlayTypeInformationExtractor generateExtractor (EspnPlayByPlay pbp)
   {
      return new FoulExtractor(pbp);
   }

   @Override
   public String getPlayType ()
   {
      return "FOUL";
   }

   @Override
   public String getOverallPlayType ()
   {
      return "FOUL";
   }

   @Override
   public String getPlayPlayer ()
   {
      if (super.getGameDetail() != null) {
         return super.getGameDetail().substring(8, super.getGameDetail().length());
      }

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
      return "ENDBEGIN";
   }
}
