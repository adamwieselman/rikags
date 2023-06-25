package com.rickags.extractors;

import org.springframework.stereotype.Component;

import com.rickags.facade.PbpFacade;
import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

@Component
public class MadeFreeThrowExtractor extends PbpFacade implements PlayTypeInformationExtractor
{
   private static String MADE_FREE = " made Free Throw";

   public MadeFreeThrowExtractor ()
   {
      super();
   }

   public MadeFreeThrowExtractor (EspnPlayByPlay pbp)
   {
      super(pbp);
   }

   @Override
   public boolean supports (String gameDetail)
   {
      if (gameDetail.contains(MADE_FREE)) {
         return true;
      }

      return false;
   }

   @Override
   public PlayTypeInformationExtractor generateExtractor (EspnPlayByPlay pbp)
   {
      return new MadeFreeThrowExtractor(pbp);
   }

   @Override
   public String getPlayType ()
   {
      return "MADEFREE";
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
         return super.getGameDetail().substring(0, super.getGameDetail().indexOf(MADE_FREE));
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
