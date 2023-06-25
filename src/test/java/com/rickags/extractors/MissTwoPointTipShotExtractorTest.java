package com.rickags.extractors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

public class MissTwoPointTipShotExtractorTest
{
   private static String MISS_2PT_TIP_SHOT = " missed Two Point Tip Shot";

   @Test
   public void testMissTwoPointTipShotExtractor(){
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setTimeStamp("15:35");
      pbp.setGameDetail("Jimmy Greek missed Two Point Tip Shot");
      pbp.setGameHalf("1");
      pbp.setTeamLogo("/55.png");
      pbp.setCombinedScore("65-64");

      MissTwoPointTipShotExtractor missTwoPointTipShotExtractor = new MissTwoPointTipShotExtractor(pbp);
      MissTwoPointTipShotExtractor missTwoPointTipShotExtractor1 = new MissTwoPointTipShotExtractor();
      assertTrue( missTwoPointTipShotExtractor.generateExtractor(pbp) instanceof MissTwoPointTipShotExtractor);
      assertTrue( missTwoPointTipShotExtractor1 instanceof MissTwoPointTipShotExtractor);
      assertEquals("END", missTwoPointTipShotExtractor.getBeginningOrEnding());
      assertEquals("MISS2PTCLOSE", missTwoPointTipShotExtractor.getPriorPlayType());
      assertEquals("Jimmy Greek", missTwoPointTipShotExtractor.getPlayPlayer());
      assertEquals("2PTCLOSEATTEMPT", missTwoPointTipShotExtractor.getOverallPlayType());
      assertEquals("MISS2PTCLOSE", missTwoPointTipShotExtractor.getPlayType());
      assertTrue(missTwoPointTipShotExtractor.supports("Jimmy Greek missed Two Point Tip Shot"));
      assertFalse(missTwoPointTipShotExtractor.supports("Jimmy Greek Steal"));

      pbp.setGameDetail(null);
      assertNull(missTwoPointTipShotExtractor.getPlayPlayer());
   }

}
