package com.rickags.extractors;

import org.springframework.stereotype.Component;

import com.rickags.facade.PbpFacade;
import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

@Component
public class DefensiveReboundExtractor extends PbpFacade implements PlayTypeInformationExtractor
{
   private static String DEF_REBOUND = " Defensive Rebound";

   public DefensiveReboundExtractor ()
   {
      super();
   }

   public DefensiveReboundExtractor (EspnPlayByPlay pbp)
   {
      super(pbp);
   }

   @Override
   public boolean supports (String gameDetail)
   {
      if (gameDetail.contains(DEF_REBOUND)) {
         return true;
      }

      return false;
   }

   @Override
   public PlayTypeInformationExtractor generateExtractor (EspnPlayByPlay pbp)
   {
      return new DefensiveReboundExtractor(pbp);
   }

   @Override
   public String getPlayType ()
   {
      return "DEFREB";
   }

   @Override
   public String getOverallPlayType ()
   {
      return "DEFREB";
   }

   @Override
   public String getPlayPlayer ()
   {
      if (super.getGameDetail() != null) {
         return super.getGameDetail().substring(0, super.getGameDetail().indexOf(DEF_REBOUND));
      }

      return null;
   }

   @Override
   public String getPriorPlayType ()
   {
      return "DEFREB";
   }

   @Override
   public String getBeginningOrEnding ()
   {
      return "BEGIN";
   }
}
