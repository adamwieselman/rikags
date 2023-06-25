package com.rickags.extractors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;


public class MadeTwoPointLayupExtractorTest
{
   private static String MADE_LAYUP = " made Layup";

   @Test
   public void testMadeTwoPointLayupExtractor(){
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setTimeStamp("15:35");
      pbp.setGameDetail("Jimmy Greek made Layup");
      pbp.setGameHalf("1");
      pbp.setTeamLogo("/55.png");
      pbp.setCombinedScore("65-64");

      MadeTwoPointLayupExtractor madeTwoPointLayupExtractor = new MadeTwoPointLayupExtractor(pbp);
      MadeTwoPointLayupExtractor madeTwoPointLayupExtractor1 = new MadeTwoPointLayupExtractor();
      assertTrue( madeTwoPointLayupExtractor.generateExtractor(pbp) instanceof MadeTwoPointLayupExtractor);
      assertTrue( madeTwoPointLayupExtractor1 instanceof MadeTwoPointLayupExtractor);
      assertEquals("ENDBEGIN", madeTwoPointLayupExtractor.getBeginningOrEnding());
      assertEquals("DEADBALL", madeTwoPointLayupExtractor.getPriorPlayType());
      assertEquals("Jimmy Greek", madeTwoPointLayupExtractor.getPlayPlayer());
      assertEquals("2PTCLOSEATTEMPT", madeTwoPointLayupExtractor.getOverallPlayType());
      assertEquals("MADE2PTCLOSE", madeTwoPointLayupExtractor.getPlayType());
      assertTrue(madeTwoPointLayupExtractor.supports("Jimmy Greek made Layup"));
      assertFalse(madeTwoPointLayupExtractor.supports("Jimmy Greek Steal"));

      pbp.setGameDetail(null);
      assertNull(madeTwoPointLayupExtractor.getPlayPlayer());
   }
}
