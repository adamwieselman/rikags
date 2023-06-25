package com.rickags.extractors;

import org.springframework.stereotype.Component;

import com.rickags.facade.PbpFacade;
import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

@Component
public class BlockExtractor extends PbpFacade implements PlayTypeInformationExtractor
{
   private static String BLOCK = " Block";

   public BlockExtractor ()
   {
      super();
   }

   public BlockExtractor (EspnPlayByPlay pbp)
   {
      super(pbp);
   }

   @Override
   public boolean supports (String gameDetail)
   {
      if (gameDetail.contains(BLOCK)) {
         return true;
      }

      return false;
   }

   @Override
   public PlayTypeInformationExtractor generateExtractor (EspnPlayByPlay pbp)
   {
      return new BlockExtractor(pbp);
   }

   @Override
   public String getPlayType ()
   {
      return "BLOCK";
   }

   @Override
   public String getOverallPlayType ()
   {
      return "BLOCK";
   }

   @Override
   public String getPlayPlayer ()
   {

      if (super.getGameDetail() != null) {
            return super.getGameDetail().substring(0, super.getGameDetail().indexOf(BLOCK));
      }

      return null;
   }

   @Override
   public String getPriorPlayType ()
   {
      return "BLOCK";
   }

   @Override
   public String getBeginningOrEnding ()
   {
      return "END";
   }
}
