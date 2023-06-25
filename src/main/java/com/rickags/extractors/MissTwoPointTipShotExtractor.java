package com.rickags.extractors;

import org.springframework.stereotype.Component;

import com.rickags.facade.PbpFacade;
import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

@Component
public class MissTwoPointTipShotExtractor extends PbpFacade implements PlayTypeInformationExtractor
{
   private static String MISS_2PT_TIP_SHOT = " missed Two Point Tip Shot";

   public MissTwoPointTipShotExtractor ()
   {
      super();
   }

   public MissTwoPointTipShotExtractor (EspnPlayByPlay pbp)
   {
      super(pbp);
   }

   @Override
   public boolean supports (String gameDetail)
   {
      if (gameDetail.contains(MISS_2PT_TIP_SHOT)) {
         return true;
      }

      return false;
   }

   @Override
   public PlayTypeInformationExtractor generateExtractor (EspnPlayByPlay pbp)
   {
      return new MissTwoPointTipShotExtractor(pbp);
   }

   @Override
   public String getPlayType ()
   {
      return "MISS2PTCLOSE";
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
         return super.getGameDetail().substring(0, super.getGameDetail().indexOf(MISS_2PT_TIP_SHOT));
      }

      return null;
   }

   @Override
   public String getPriorPlayType ()
   {
      return "MISS2PTCLOSE";
   }

   @Override
   public String getBeginningOrEnding ()
   {
      return "END";
   }
}
