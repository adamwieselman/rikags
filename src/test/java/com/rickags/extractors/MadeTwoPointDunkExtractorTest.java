package com.rickags.extractors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

public class MadeTwoPointDunkExtractorTest
{
   private static String MADE_DUNK = " made Dunk";

   @Test
   public void testMadeTwoPointDunkExtractor(){
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setTimeStamp("15:35");
      pbp.setGameDetail("Jimmy Greek made Dunk");
      pbp.setGameHalf("1");
      pbp.setTeamLogo("/55.png");
      pbp.setCombinedScore("65-64");

      MadeTwoPointDunkExtractor madeTwoPointDunkExtractor = new MadeTwoPointDunkExtractor(pbp);
      MadeTwoPointDunkExtractor madeTwoPointDunkExtractor1 = new MadeTwoPointDunkExtractor();
      assertTrue( madeTwoPointDunkExtractor.generateExtractor(pbp) instanceof MadeTwoPointDunkExtractor);
      assertTrue( madeTwoPointDunkExtractor1 instanceof MadeTwoPointDunkExtractor);
      assertEquals("ENDBEGIN", madeTwoPointDunkExtractor.getBeginningOrEnding());
      assertEquals("DEADBALL", madeTwoPointDunkExtractor.getPriorPlayType());
      assertEquals("Jimmy Greek", madeTwoPointDunkExtractor.getPlayPlayer());
      assertEquals("2PTCLOSEATTEMPT", madeTwoPointDunkExtractor.getOverallPlayType());
      assertEquals("MADE2PTCLOSE", madeTwoPointDunkExtractor.getPlayType());
      assertTrue(madeTwoPointDunkExtractor.supports("Jimmy Greek made Dunk"));
      assertFalse(madeTwoPointDunkExtractor.supports("Jimmy Greek Steal"));

      pbp.setGameDetail(null);
      assertNull(madeTwoPointDunkExtractor.getPlayPlayer());
   }

}
