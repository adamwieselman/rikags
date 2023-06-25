package com.rickags.extractors;

import org.springframework.stereotype.Component;

import com.rickags.facade.PbpFacade;
import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

@Component
public class MissTwoPointDunkExtractor  extends PbpFacade implements PlayTypeInformationExtractor
{
   private static String MISS_DUNK = " missed Dunk";

   public MissTwoPointDunkExtractor ()
   {
      super();
   }

   public MissTwoPointDunkExtractor (EspnPlayByPlay pbp)
   {
      super(pbp);
   }

   @Override
   public boolean supports (String gameDetail)
   {
      if (gameDetail.contains(MISS_DUNK)) {
         return true;
      }

      return false;
   }

   @Override
   public PlayTypeInformationExtractor generateExtractor (EspnPlayByPlay pbp)
   {
      return new MissTwoPointDunkExtractor(pbp);
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
         return super.getGameDetail().substring(0, super.getGameDetail().indexOf(MISS_DUNK));
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
