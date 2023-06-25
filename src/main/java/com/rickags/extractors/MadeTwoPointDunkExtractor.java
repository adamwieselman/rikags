package com.rickags.extractors;

import org.springframework.stereotype.Component;

import com.rickags.facade.PbpFacade;
import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

@Component
public class MadeTwoPointDunkExtractor extends PbpFacade implements PlayTypeInformationExtractor
{
   private static String MADE_DUNK = " made Dunk";

   public MadeTwoPointDunkExtractor ()
   {
      super();
   }

   public MadeTwoPointDunkExtractor (EspnPlayByPlay pbp)
   {
      super(pbp);
   }

   @Override
   public boolean supports (String gameDetail)
   {
      if (gameDetail.contains(MADE_DUNK)) {
         return true;
      }

      return false;
   }

   @Override
   public PlayTypeInformationExtractor generateExtractor (EspnPlayByPlay pbp)
   {
      return new MadeTwoPointDunkExtractor(pbp);
   }

   @Override
   public String getPlayType ()
   {
      return "MADE2PTCLOSE";
   }

   @Override
   public String getOverallPlayType ()
   {
      return "2PTCLOSEATTEMPT";
   }

   @Override
   public String getPlayPlayer ()
   {
      if (super.getGameDetail() != null) {
         return super.getGameDetail().substring(0, super.getGameDetail().indexOf(MADE_DUNK));
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
