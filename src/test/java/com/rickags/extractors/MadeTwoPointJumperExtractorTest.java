package com.rickags.extractors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.stereotype.Component;

import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

@Component
public class MadeTwoPointJumperExtractorTest
{
   private static String MADE_JUMPER = " made Jumper";

   @Test
   public void testMadeTwoPointJumperExtractor(){
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setTimeStamp("15:35");
      pbp.setGameDetail("Jimmy Greek made Jumper");
      pbp.setGameHalf("1");
      pbp.setTeamLogo("/55.png");
      pbp.setCombinedScore("65-64");

      MadeTwoPointJumperExtractor madeTwoPointJumperExtractor = new MadeTwoPointJumperExtractor(pbp);
      MadeTwoPointJumperExtractor madeTwoPointJumperExtractor1 = new MadeTwoPointJumperExtractor();
      assertTrue( madeTwoPointJumperExtractor.generateExtractor(pbp) instanceof MadeTwoPointJumperExtractor);
      assertTrue( madeTwoPointJumperExtractor1 instanceof MadeTwoPointJumperExtractor);
      assertEquals("ENDBEGIN", madeTwoPointJumperExtractor.getBeginningOrEnding());
      assertEquals("DEADBALL", madeTwoPointJumperExtractor.getPriorPlayType());
      assertEquals("Jimmy Greek", madeTwoPointJumperExtractor.getPlayPlayer());
      assertEquals("2PTJUMPATTEMPT", madeTwoPointJumperExtractor.getOverallPlayType());
      assertEquals("MADE2PTJUMP", madeTwoPointJumperExtractor.getPlayType());
      assertTrue(madeTwoPointJumperExtractor.supports("Jimmy Greek made Jumper"));
      assertFalse(madeTwoPointJumperExtractor.supports("Jimmy Greek Steal"));

      pbp.setGameDetail(null);
      assertNull(madeTwoPointJumperExtractor.getPlayPlayer());
   }
}
