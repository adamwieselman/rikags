package com.rickags.extractors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;


public class TimeoutExtractorTest
{
   private static String TIMEOUT = " Timeout";

   @Test
   public void testTimeoutExtractor(){
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setTimeStamp("15:35");
      pbp.setGameDetail("Offical TV Timeout");
      pbp.setGameHalf("1");
      pbp.setTeamLogo("/55.png");
      pbp.setCombinedScore("65-64");

      TimeoutExtractor timeoutExtractor = new TimeoutExtractor(pbp);
      TimeoutExtractor timeoutExtractor1 = new TimeoutExtractor();
      assertTrue( timeoutExtractor.generateExtractor(pbp) instanceof TimeoutExtractor);
      assertTrue( timeoutExtractor1 instanceof TimeoutExtractor);
      assertEquals("STOPPAGE", timeoutExtractor.getBeginningOrEnding());
      assertEquals("DEADBALL", timeoutExtractor.getPriorPlayType());
      assertEquals(null, timeoutExtractor.getPlayPlayer());
      assertEquals("TIMEOUT", timeoutExtractor.getOverallPlayType());
      assertEquals("TIMEOUT", timeoutExtractor.getPlayType());
      assertTrue(timeoutExtractor.supports("Offical TV Timeout"));
      assertFalse(timeoutExtractor.supports("Jimmy Greek Steal"));

      pbp.setGameDetail(null);
      assertNull(timeoutExtractor.getPlayPlayer());
   }
}
