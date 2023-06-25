package com.rickags.extractors;

import org.springframework.stereotype.Component;

import com.rickags.facade.PbpFacade;
import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

@Component
public class MadeTwoPointTipShotExtractor  extends PbpFacade implements PlayTypeInformationExtractor
{
   private static String MADE_2PT_TIP_SHOT = " made Two Point Tip Shot";

   public MadeTwoPointTipShotExtractor ()
   {
      super();
   }

   public MadeTwoPointTipShotExtractor (EspnPlayByPlay pbp)
   {
      super(pbp);
   }

   @Override
   public boolean supports (String gameDetail)
   {
      if (gameDetail.contains(MADE_2PT_TIP_SHOT)) {
         return true;
      }

      return false;
   }

   @Override
   public PlayTypeInformationExtractor generateExtractor (EspnPlayByPlay pbp)
   {
      return new MadeTwoPointTipShotExtractor(pbp);
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
         return super.getGameDetail().substring(0, super.getGameDetail().indexOf(MADE_2PT_TIP_SHOT));
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
