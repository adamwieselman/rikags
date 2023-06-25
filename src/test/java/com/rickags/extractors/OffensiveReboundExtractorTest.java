package com.rickags.extractors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

public class OffensiveReboundExtractorTest
{
   private static String OFF_REBOUND = " Offensive Rebound";

   @Test
   public void testOffensiveReboundExtractor(){
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setTimeStamp("15:35");
      pbp.setGameDetail("Jimmy Greek Offensive Rebound");
      pbp.setGameHalf("1");
      pbp.setTeamLogo("/55.png");
      pbp.setCombinedScore("65-64");

      OffensiveReboundExtractor offensiveReboundExtractor = new OffensiveReboundExtractor(pbp);
      OffensiveReboundExtractor offensiveReboundExtractor1 = new OffensiveReboundExtractor();
      assertTrue( offensiveReboundExtractor.generateExtractor(pbp) instanceof OffensiveReboundExtractor);
      assertTrue( offensiveReboundExtractor1 instanceof OffensiveReboundExtractor);
      assertEquals("BEGIN", offensiveReboundExtractor.getBeginningOrEnding());
      assertEquals("OFFREB", offensiveReboundExtractor.getPriorPlayType());
      assertEquals("Jimmy Greek", offensiveReboundExtractor.getPlayPlayer());
      assertEquals("OFFREB", offensiveReboundExtractor.getOverallPlayType());
      assertEquals("OFFREB", offensiveReboundExtractor.getPlayType());
      assertTrue(offensiveReboundExtractor.supports("Jimmy Greek Offensive Rebound"));
      assertFalse(offensiveReboundExtractor.supports("Jimmy Greek Steal"));

      pbp.setGameDetail(null);
      assertNull(offensiveReboundExtractor.getPlayPlayer());
   }
}
