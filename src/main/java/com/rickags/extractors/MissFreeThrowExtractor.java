package com.rickags.extractors;

import org.springframework.stereotype.Component;

import com.rickags.facade.PbpFacade;
import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

@Component
public class MissFreeThrowExtractor  extends PbpFacade implements PlayTypeInformationExtractor
{
   private static String MISS_FREE = " missed Free Throw";

   public MissFreeThrowExtractor ()
   {
      super();
   }

   public MissFreeThrowExtractor (EspnPlayByPlay pbp)
   {
      super(pbp);
   }

   @Override
   public boolean supports (String gameDetail)
   {
      if (gameDetail.contains(MISS_FREE)) {
         return true;
      }

      return false;
   }

   @Override
   public PlayTypeInformationExtractor generateExtractor (EspnPlayByPlay pbp)
   {
      return new MissFreeThrowExtractor(pbp);
   }

   @Override
   public String getPlayType ()
   {
      return "MISSFREE";
   }

   @Override
   public String getOverallPlayType ()
   {
      return "FREETHROW";
   }

   @Override
   public String getPlayPlayer ()
   {
      if (super.getGameDetail() != null) {
         return super.getGameDetail().substring(0, super.getGameDetail().indexOf(MISS_FREE));
      }

      return null;
   }

   @Override
   public String getPriorPlayType ()
   {
      return "FOUL";
   }

   @Override
   public String getBeginningOrEnding ()
   {
      return "END";
   }
}
