package com.rickags.extractors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;


public class ErrorExtractorTest
{
   @Test
   public void testErrorExtractor(){
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setTimeStamp("15:35");
      pbp.setGameDetail("End of 2nd Half");
      pbp.setGameHalf("1");
      pbp.setTeamLogo("/55.png");
      pbp.setCombinedScore("65-64");

      ErrorExtractor errorExtractor = new ErrorExtractor(pbp);
      ErrorExtractor errorExtractor1 = new ErrorExtractor();
      assertTrue( errorExtractor.generateExtractor(pbp) instanceof ErrorExtractor);
      assertTrue( errorExtractor1 instanceof ErrorExtractor);
      assertEquals(null, errorExtractor.getBeginningOrEnding());
      assertEquals("DEADBALL", errorExtractor.getPriorPlayType());
      assertEquals(null, errorExtractor.getPlayPlayer());
      assertEquals("ERROR", errorExtractor.getOverallPlayType());
      assertEquals("ERROR", errorExtractor.getPlayType());
      assertFalse(errorExtractor.supports("End of 2nd Quarter"));
      assertFalse(errorExtractor.supports("Jimmy Greek Steal"));

      pbp.setGameDetail(null);
      assertNull(errorExtractor.getPlayPlayer());
   }
}
