package com.rickags.extractors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;


public class SkipExtractorTest
{
   private static String BADSTEAL = "Steal.";

   @Test
   public void testSkipExtractor()
   {
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setTimeStamp("15:35");
      pbp.setGameDetail("Jimmy Greek Steal.");
      pbp.setGameHalf("1");
      pbp.setTeamLogo("/55.png");
      pbp.setCombinedScore("65-64");

      SkipExtractor skipExtractor = new SkipExtractor(pbp);
      SkipExtractor skipExtractor1 = new SkipExtractor();
      assertTrue(skipExtractor.generateExtractor(pbp) instanceof SkipExtractor);
      assertTrue(skipExtractor1 instanceof SkipExtractor);
      assertEquals(null, skipExtractor.getBeginningOrEnding());
      assertEquals("SKIP", skipExtractor.getPriorPlayType());
      assertEquals(null, skipExtractor.getPlayPlayer());
      assertEquals("SKIP", skipExtractor.getOverallPlayType());
      assertEquals("SKIP", skipExtractor.getPlayType());
      assertTrue(skipExtractor.supports("Steal."));
      assertFalse(skipExtractor.supports("Jimmy Greek Defensive Rebound"));

      pbp.setGameDetail(null);
      assertNull(skipExtractor.getPlayPlayer());
   }
}
