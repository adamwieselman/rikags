package com.rickags.extractors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

public class MissTwoPointJumperExtractorTest
{
   private static String MISS_JUMPER = " missed Jumper";

   @Test
   public void testMissTwoPointJumperExtractor(){
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setTimeStamp("15:35");
      pbp.setGameDetail("Jimmy Greek missed Jumper");
      pbp.setGameHalf("1");
      pbp.setTeamLogo("/55.png");
      pbp.setCombinedScore("65-64");

      MissTwoPointJumperExtractor missTwoPointJumperExtractor = new MissTwoPointJumperExtractor(pbp);
      MissTwoPointJumperExtractor missTwoPointJumperExtractor1 = new MissTwoPointJumperExtractor();
      assertTrue( missTwoPointJumperExtractor.generateExtractor(pbp) instanceof MissTwoPointJumperExtractor);
      assertTrue( missTwoPointJumperExtractor1 instanceof MissTwoPointJumperExtractor);
      assertEquals("END", missTwoPointJumperExtractor.getBeginningOrEnding());
      assertEquals("MISS2PTJUMP", missTwoPointJumperExtractor.getPriorPlayType());
      assertEquals("Jimmy Greek", missTwoPointJumperExtractor.getPlayPlayer());
      assertEquals("2PTJUMPATTEMPT", missTwoPointJumperExtractor.getOverallPlayType());
      assertEquals("MISS2PTJUMP", missTwoPointJumperExtractor.getPlayType());
      assertTrue(missTwoPointJumperExtractor.supports("Jimmy Greek missed Jumper"));
      assertFalse(missTwoPointJumperExtractor.supports("Jimmy Greek Steal"));

      pbp.setGameDetail(null);
      assertNull(missTwoPointJumperExtractor.getPlayPlayer());
   }
}
