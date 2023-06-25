package com.rickags.extractors;

import org.springframework.stereotype.Component;

import com.rickags.facade.PbpFacade;
import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

@Component
public class OffensiveReboundExtractor extends PbpFacade implements PlayTypeInformationExtractor
{
   private static String OFF_REBOUND = " Offensive Rebound";

   public OffensiveReboundExtractor ()
   {
      super();
   }

   public OffensiveReboundExtractor (EspnPlayByPlay pbp)
   {
      super(pbp);
   }

   public boolean supports (String gameDetail)
   {
      if (gameDetail.contains(OFF_REBOUND)) {
         return true;
      }

      return false;
   }

   public PlayTypeInformationExtractor generateExtractor (EspnPlayByPlay pbp)
   {
      return new OffensiveReboundExtractor(pbp);
   }

   public String getPlayType ()
   {
      return "OFFREB";
   }

   public String getOverallPlayType ()
   {
      return "OFFREB";
   }

   public String getPlayPlayer ()
   {
      if (super.getGameDetail() != null) {
         return super.getGameDetail().substring(0, super.getGameDetail().indexOf(OFF_REBOUND));
      }

      return null;
   }

   public String getPriorPlayType ()
   {
      return "OFFREB";
   }

   @Override
   public String getBeginningOrEnding ()
   {
      return "BEGIN";
   }
}
