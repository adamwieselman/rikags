package com.rickags.extractors;

import org.springframework.stereotype.Component;

import com.rickags.facade.PbpFacade;
import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

@Component
public class MissTwoPointJumperExtractor extends PbpFacade implements PlayTypeInformationExtractor
{
   private static String MISS_JUMPER = " missed Jumper";

   public MissTwoPointJumperExtractor ()
   {
      super();
   }

   public MissTwoPointJumperExtractor (EspnPlayByPlay pbp)
   {
      super(pbp);
   }

   @Override
   public boolean supports (String gameDetail)
   {
      if (gameDetail.contains(MISS_JUMPER)) {
         return true;
      }

      return false;
   }

   @Override
   public PlayTypeInformationExtractor generateExtractor (EspnPlayByPlay pbp)
   {
      return new MissTwoPointJumperExtractor(pbp);
   }

   @Override
   public String getPlayType ()
   {
      return "MISS2PTJUMP";
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
         return super.getGameDetail().substring(0, super.getGameDetail().indexOf(MISS_JUMPER));
      }

      return null;
   }

   @Override
   public String getPriorPlayType ()
   {
      return "MISS2PTJUMP";
   }

   @Override
   public String getBeginningOrEnding ()
   {
      return "END";
   }
}
