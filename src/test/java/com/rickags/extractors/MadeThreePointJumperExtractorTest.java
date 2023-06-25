package com.rickags.extractors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;


public class MadeThreePointJumperExtractorTest
{
   private static String MADE_3PT_JUMPER = " made Three Point Jumper";

   @Test
   public void testMadeThreePointJumperExtractor(){
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setTimeStamp("15:35");
      pbp.setGameDetail("Jimmy Greek made Three Point Jumper");
      pbp.setGameHalf("1");
      pbp.setTeamLogo("/55.png");
      pbp.setCombinedScore("65-64");

      MadeThreePointJumperExtractor threePointJumperExtractor = new MadeThreePointJumperExtractor(pbp);
      MadeThreePointJumperExtractor threePointJumperExtractor1 = new MadeThreePointJumperExtractor();
      assertTrue( threePointJumperExtractor.generateExtractor(pbp) instanceof MadeThreePointJumperExtractor);
      assertTrue( threePointJumperExtractor1 instanceof MadeThreePointJumperExtractor);

      assertEquals("ENDBEGIN", threePointJumperExtractor.getBeginningOrEnding());
      assertEquals("DEADBALL", threePointJumperExtractor.getPriorPlayType());
      assertEquals("Jimmy Greek", threePointJumperExtractor.getPlayPlayer());
      assertEquals("3PTATTEMPT", threePointJumperExtractor.getOverallPlayType());
      assertEquals("MADE3PTJUMP", threePointJumperExtractor.getPlayType());
      assertTrue(threePointJumperExtractor.supports("Jimmy Greek made Three Point Jumper"));
      assertFalse(threePointJumperExtractor.supports("Jimmy Greek Steal"));

      pbp.setGameDetail(null);
      assertNull(threePointJumperExtractor.getPlayPlayer());
   }
}
