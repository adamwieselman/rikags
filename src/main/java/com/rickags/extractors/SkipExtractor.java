package com.rickags.extractors;

import org.springframework.stereotype.Component;

import com.rickags.facade.PbpFacade;
import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

@Component
public class SkipExtractor extends PbpFacade implements PlayTypeInformationExtractor
{
   private static String BADSTEAL = "Steal.";

   public SkipExtractor ()
   {
      super();
   }

   public SkipExtractor (EspnPlayByPlay pbp)
   {
      super(pbp);
   }

   @Override
   public boolean supports (String gameDetail)
   {
      if (gameDetail.startsWith(BADSTEAL)) {
         return true;
      }

      return false;
   }

   @Override
   public PlayTypeInformationExtractor generateExtractor (EspnPlayByPlay pbp)
   {
      return new SkipExtractor(pbp);
   }

   @Override
   public String getPlayType ()
   {
      return "SKIP";
   }

   @Override
   public String getOverallPlayType ()
   {
      return "SKIP";
   }

   @Override
   public String getPlayPlayer ()
   {
      return null;
   }

   @Override
   public String getPriorPlayType ()
   {
      return "SKIP";
   }

   @Override
   public String getBeginningOrEnding ()
   {
      return null;
   }
}
