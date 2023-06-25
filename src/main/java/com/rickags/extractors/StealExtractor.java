package com.rickags.extractors;

import org.springframework.stereotype.Component;

import com.rickags.facade.PbpFacade;
import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

@Component
public class StealExtractor extends PbpFacade implements PlayTypeInformationExtractor
{
   private static String STEAL = " Steal";

   public StealExtractor ()
   {
      super();
   }

   public StealExtractor (EspnPlayByPlay pbp)
   {
      super(pbp);
   }

   @Override
   public boolean supports (String gameDetail)
   {
      if (gameDetail.contains(STEAL)) {
         return true;
      }

      return false;
   }

   @Override
   public PlayTypeInformationExtractor generateExtractor (EspnPlayByPlay pbp)
   {
      return new StealExtractor(pbp);
   }

   @Override
   public String getPlayType ()
   {
      return "STEAL";
   }

   @Override
   public String getOverallPlayType ()
   {
      return "STEAL";
   }

   @Override
   public String getPlayPlayer ()
   {
      if (super.getGameDetail() != null) {
         return super.getGameDetail().substring(0, super.getGameDetail().indexOf(STEAL));
      }

      return null;
   }

   @Override
   public String getPriorPlayType ()
   {
      return "STEAL";
   }

   @Override
   public String getBeginningOrEnding ()
   {
      return "ENDBEGIN";
   }
}
