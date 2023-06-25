package com.rickags.extractors;

import org.springframework.stereotype.Component;

import com.rickags.facade.PbpFacade;
import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

@Component
public class TimeoutExtractor extends PbpFacade implements PlayTypeInformationExtractor
{
   private static String TIMEOUT = " Timeout";

   public TimeoutExtractor ()
   {
      super();
   }

   public TimeoutExtractor (EspnPlayByPlay pbp)
   {
      super(pbp);
   }

   @Override
   public boolean supports (String gameDetail)
   {
      if (gameDetail.contains(TIMEOUT)){
         return true;
      }

      return false;
   }

   @Override
   public PlayTypeInformationExtractor generateExtractor (EspnPlayByPlay pbp)
   {
      return new TimeoutExtractor(pbp);
   }

   @Override
   public String getPlayType ()
   {
      return "TIMEOUT";
   }

   @Override
   public String getOverallPlayType ()
   {
      return "TIMEOUT";
   }

   @Override
   public String getPlayPlayer ()
   {
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
      return "STOPPAGE";
   }
}
