package com.rickags.extractors;

import org.springframework.stereotype.Component;

import com.rickags.facade.PbpFacade;
import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

@Component
public class EndOfExtractor extends PbpFacade implements PlayTypeInformationExtractor
{
   private static String TIMEOUT = " Timeout";
   private static String ENDOF = "End of ";
   private static String JUMPBALL = "Jump Ball ";
   private static String DEADBALL_TEAM_REBOUND = "Deadball Team Rebound";

   public EndOfExtractor ()
   {
      super();
   }

   public EndOfExtractor (EspnPlayByPlay pbp)
   {
      super(pbp);
   }

   @Override
   public boolean supports (String gameDetail)
   {
      if (gameDetail.contains(ENDOF)){
         return true;
      }

      return false;
   }

   @Override
   public PlayTypeInformationExtractor generateExtractor (EspnPlayByPlay pbp)
   {
      return new EndOfExtractor(pbp);
   }

   @Override
   public String getPlayType ()
   {
      return "ENDOF";
   }

   @Override
   public String getOverallPlayType ()
   {
      return "ENDOF";
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
