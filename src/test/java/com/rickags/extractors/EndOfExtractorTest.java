package com.rickags.extractors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;


public class EndOfExtractorTest
{
   private static String ENDOF = "End of ";


   @Test
   public void testEndOfExtractor(){
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setTimeStamp("15:35");
      pbp.setGameDetail("End of 2nd Half");
      pbp.setGameHalf("1");
      pbp.setTeamLogo("/55.png");
      pbp.setCombinedScore("65-64");

      EndOfExtractor endOfExtractor = new EndOfExtractor(pbp);
      EndOfExtractor endOfExtractor1 = new EndOfExtractor();
      assertTrue( endOfExtractor.generateExtractor(pbp) instanceof EndOfExtractor);
      assertTrue( endOfExtractor1 instanceof EndOfExtractor);
      assertEquals("STOPPAGE", endOfExtractor.getBeginningOrEnding());
      assertEquals("DEADBALL", endOfExtractor.getPriorPlayType());
      assertEquals(null, endOfExtractor.getPlayPlayer());
      assertEquals("ENDOF", endOfExtractor.getOverallPlayType());
      assertEquals("ENDOF", endOfExtractor.getPlayType());
      assertTrue(endOfExtractor.supports("End of 2nd Quarter"));
      assertFalse(endOfExtractor.supports("Jimmy Greek Steal"));

      pbp.setGameDetail(null);
      assertNull(endOfExtractor.getPlayPlayer());
   }
}
