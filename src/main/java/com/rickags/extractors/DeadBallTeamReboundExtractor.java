package com.rickags.extractors;

import org.springframework.stereotype.Component;

import com.rickags.facade.PbpFacade;
import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

@Component
public class DeadBallTeamReboundExtractor extends PbpFacade implements PlayTypeInformationExtractor
{
   private static String DEADBALL_TEAM_REBOUND = " Deadball Team Rebound";

   public DeadBallTeamReboundExtractor ()
   {
      super();
   }

   public DeadBallTeamReboundExtractor (EspnPlayByPlay pbp)
   {
      super(pbp);
   }

   @Override
   public boolean supports (String gameDetail)
   {
      if (gameDetail.contains(DEADBALL_TEAM_REBOUND)) {
         return true;
      }

      return false;
   }

   @Override
   public PlayTypeInformationExtractor generateExtractor (EspnPlayByPlay pbp)
   {
      return new DeadBallTeamReboundExtractor(pbp);
   }

   @Override
   public String getPlayType ()
   {
      return "TEAMREB";
   }

   @Override
   public String getOverallPlayType ()
   {
      return "TEAMREB";
   }

   @Override
   public String getPlayPlayer ()
   {
      if (super.getGameDetail() != null) {
         return super.getGameDetail().substring(0, super.getGameDetail().indexOf(DEADBALL_TEAM_REBOUND));
      }

      return null;
   }

   @Override
   public String getPriorPlayType ()
   {
      return "DEADBALL";//"TEAMREB";
   }

   @Override
   public String getBeginningOrEnding ()
   {
      return "ENDBEGIN";
   }
}
