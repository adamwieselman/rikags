package com.rickags.extractors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;


public class MadeFreeThrowExtractorTest
{
   private static String MADE_FREE = " made Free Throw";

   @Test
   public void testMadeFreeThrowExtractor(){
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setTimeStamp("15:35");
      pbp.setGameDetail("Jimmy Greek made Free Throw");
      pbp.setGameHalf("1");
      pbp.setTeamLogo("/55.png");
      pbp.setCombinedScore("65-64");

      MadeFreeThrowExtractor madeFreeThrowExtractor = new MadeFreeThrowExtractor(pbp);
      MadeFreeThrowExtractor madeFreeThrowExtractor1 = new MadeFreeThrowExtractor();
      assertTrue( madeFreeThrowExtractor.generateExtractor(pbp) instanceof MadeFreeThrowExtractor);
      assertTrue( madeFreeThrowExtractor1 instanceof MadeFreeThrowExtractor);
      assertEquals("ENDBEGIN", madeFreeThrowExtractor.getBeginningOrEnding());
      assertEquals("DEADBALL", madeFreeThrowExtractor.getPriorPlayType());
      assertEquals("Jimmy Greek", madeFreeThrowExtractor.getPlayPlayer());
      assertEquals("FREETHROW", madeFreeThrowExtractor.getOverallPlayType());
      assertEquals("MADEFREE", madeFreeThrowExtractor.getPlayType());
      assertTrue(madeFreeThrowExtractor.supports("Jimmy Greek made Free Throw"));
      assertFalse(madeFreeThrowExtractor.supports("Jimmy Greek Steal"));

      pbp.setGameDetail(null);
      assertNull(madeFreeThrowExtractor.getPlayPlayer());
   }
}
