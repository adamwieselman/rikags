package com.rickags.extractors;

import org.springframework.stereotype.Component;

import com.rickags.facade.PbpFacade;
import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

@Component
public class MadeThreePointJumperExtractor extends PbpFacade implements PlayTypeInformationExtractor
{
   private static String MADE_3PT_JUMPER = " made Three Point Jumper";

   public MadeThreePointJumperExtractor ()
   {
      super();
   }

   public MadeThreePointJumperExtractor  (EspnPlayByPlay pbp)
   {
      super(pbp);
   }

   @Override
   public boolean supports (String gameDetail)
   {
      if (gameDetail.contains(MADE_3PT_JUMPER)) {
         return true;
      }
      
      return false;
   }

   @Override
   public PlayTypeInformationExtractor generateExtractor (EspnPlayByPlay pbp)
   {
      return new MadeThreePointJumperExtractor(pbp);
   }

   @Override
   public String getPlayType ()
   {
      return "MADE3PTJUMP";
   }

   @Override
   public String getOverallPlayType ()
   {
      return "3PTATTEMPT";
   }

   @Override
   public String getPlayPlayer ()
   {
      if (super.getGameDetail() != null) {
         return super.getGameDetail().substring(0, super.getGameDetail().indexOf(MADE_3PT_JUMPER));
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
