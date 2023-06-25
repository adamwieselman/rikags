package com.rickags.extractors;

import org.springframework.stereotype.Component;

import com.rickags.facade.PbpFacade;
import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

@Component
public class TurnoverExtractor extends PbpFacade implements PlayTypeInformationExtractor
{
   private static String TURNOVER = " Turnover";

   public TurnoverExtractor ()
   {
      super();
   }

   public TurnoverExtractor (EspnPlayByPlay pbp)
   {
      super(pbp);
   }

   @Override
   public boolean supports (String gameDetail)
   {
      if (gameDetail.contains(TURNOVER)) {
         return true;
      }

      return false;
   }

   @Override
   public PlayTypeInformationExtractor generateExtractor (EspnPlayByPlay pbp)
   {
      return new TurnoverExtractor(pbp);
   }

   @Override
   public String getPlayType ()
   {
      return "TURNOVER";
   }

   @Override
   public String getOverallPlayType ()
   {
      return "TURNOVER";
   }

   @Override
   public String getPlayPlayer ()
   {
      if (super.getGameDetail() != null) {
         return super.getGameDetail().substring(0, super.getGameDetail().indexOf(TURNOVER));
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
