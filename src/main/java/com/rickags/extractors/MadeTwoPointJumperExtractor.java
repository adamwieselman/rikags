package com.rickags.extractors;

import org.springframework.stereotype.Component;

import com.rickags.facade.PbpFacade;
import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

@Component
public class MadeTwoPointJumperExtractor  extends PbpFacade implements PlayTypeInformationExtractor
{
   private static String MADE_JUMPER = " made Jumper";

   public MadeTwoPointJumperExtractor ()
   {
      super();
   }

   public MadeTwoPointJumperExtractor (EspnPlayByPlay pbp)
   {
      super(pbp);
   }

   @Override
   public boolean supports (String gameDetail)
   {
      if (gameDetail.contains(MADE_JUMPER)) {
         return true;
      }

      return false;
   }

   @Override
   public PlayTypeInformationExtractor generateExtractor (EspnPlayByPlay pbp)
   {
      return new MadeTwoPointJumperExtractor(pbp);
   }

   @Override
   public String getPlayType ()
   {
      return "MADE2PTJUMP";
   }

   @Override
   public String getOverallPlayType ()
   {
      return "2PTJUMPATTEMPT";
   }

   @Override
   public String getPlayPlayer ()
   {
      if (super.getGameDetail() != null) {
         return super.getGameDetail().substring(0, super.getGameDetail().indexOf(MADE_JUMPER));
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
