package com.rickags.extractors;

import org.springframework.stereotype.Component;

import com.rickags.facade.PbpFacade;
import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

@Component
public class MissThreePointJumperExtractor  extends PbpFacade implements PlayTypeInformationExtractor
{
   private static String MISS_3PT_JUMPER = " missed Three Point Jumper";

   public MissThreePointJumperExtractor ()
   {
      super();
   }

   public MissThreePointJumperExtractor (EspnPlayByPlay pbp)
   {
      super(pbp);
   }

   @Override
   public boolean supports (String gameDetail)
   {
      if (gameDetail.contains(MISS_3PT_JUMPER)) {
         return true;
      }

      return false;
   }

   @Override
   public PlayTypeInformationExtractor generateExtractor (EspnPlayByPlay pbp)
   {
      return new MissThreePointJumperExtractor(pbp);
   }

   @Override
   public String getPlayType ()
   {
      return "MISS3PTJUMP";
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
         return super.getGameDetail().substring(0, super.getGameDetail().indexOf(MISS_3PT_JUMPER));
      }

      return null;
   }

   @Override
   public String getPriorPlayType ()
   {
      return "MISS3PTJUMP";
   }

   @Override
   public String getBeginningOrEnding ()
   {
      return "END";
   }
}
