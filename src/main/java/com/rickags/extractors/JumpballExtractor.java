package com.rickags.extractors;

import org.springframework.stereotype.Component;

import com.rickags.facade.PbpFacade;
import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

@Component
public class JumpballExtractor extends PbpFacade implements PlayTypeInformationExtractor
{

   private static String JUMPBALL = "Jump Ball ";

   public JumpballExtractor ()
   {
      super();
   }

   public JumpballExtractor (EspnPlayByPlay pbp)
   {
      super(pbp);
   }

   @Override
   public boolean supports (String gameDetail)
   {
      if (gameDetail.contains(JUMPBALL)){
         return true;
      }

      return false;
   }

   @Override
   public PlayTypeInformationExtractor generateExtractor (EspnPlayByPlay pbp)
   {
      return new JumpballExtractor(pbp);
   }

   @Override
   public String getPlayType ()
   {
         return "JUMPBALL";

   }

   @Override
   public String getOverallPlayType ()
   {
      return "JUMPBALL";
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
      return "BEGIN";
   }
}
