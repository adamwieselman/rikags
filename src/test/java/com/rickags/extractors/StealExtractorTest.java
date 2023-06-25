package com.rickags.extractors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

public class StealExtractorTest
{
   private static String STEAL = " Steal";

   @Test
   public void testStealExtractor()
   {
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setTimeStamp("15:35");
      pbp.setGameDetail("Jimmy Greek Steal");
      pbp.setGameHalf("1");
      pbp.setTeamLogo("/55.png");
      pbp.setCombinedScore("65-64");

      StealExtractor stealExtractor = new StealExtractor(pbp);
      StealExtractor stealExtractor1 = new StealExtractor();
      assertTrue(stealExtractor.generateExtractor(pbp) instanceof StealExtractor);
      assertTrue(stealExtractor1 instanceof StealExtractor);
      assertEquals("ENDBEGIN", stealExtractor.getBeginningOrEnding());
      assertEquals("STEAL", stealExtractor.getPriorPlayType());
      assertEquals("Jimmy Greek", stealExtractor.getPlayPlayer());
      assertEquals("STEAL", stealExtractor.getOverallPlayType());
      assertEquals("STEAL", stealExtractor.getPlayType());
      assertTrue(stealExtractor.supports("Jimmy Greek Steal"));
      assertFalse(stealExtractor.supports("Jimmy Greek Defensive Rebound"));

      pbp.setGameDetail(null);
      assertNull(stealExtractor.getPlayPlayer());
   }
}
