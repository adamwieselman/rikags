package com.rickags.extractors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.stereotype.Component;

import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

@Component
public class MissThreePointJumperExtractorTest
{
   private static String MISS_3PT_JUMPER = " missed Three Point Jumper";

   @Test
   public void testMissThreePointJumperExtractor(){
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setTimeStamp("15:35");
      pbp.setGameDetail("Jimmy Greek missed Three Point Jumper");
      pbp.setGameHalf("1");
      pbp.setTeamLogo("/55.png");
      pbp.setCombinedScore("65-64");

      MissThreePointJumperExtractor missThreePointJumperExtractor = new MissThreePointJumperExtractor(pbp);
      MissThreePointJumperExtractor missThreePointJumperExtractor1 = new MissThreePointJumperExtractor();
      assertTrue( missThreePointJumperExtractor.generateExtractor(pbp) instanceof MissThreePointJumperExtractor);
      assertTrue( missThreePointJumperExtractor1 instanceof MissThreePointJumperExtractor);
      assertEquals("END", missThreePointJumperExtractor.getBeginningOrEnding());
      assertEquals("MISS3PTJUMP", missThreePointJumperExtractor.getPriorPlayType());
      assertEquals("Jimmy Greek", missThreePointJumperExtractor.getPlayPlayer());
      assertEquals("3PTATTEMPT", missThreePointJumperExtractor.getOverallPlayType());
      assertEquals("MISS3PTJUMP", missThreePointJumperExtractor.getPlayType());
      assertTrue(missThreePointJumperExtractor.supports("Jimmy Greek missed Three Point Jumper"));
      assertFalse(missThreePointJumperExtractor.supports("Jimmy Greek Steal"));

      pbp.setGameDetail(null);
      assertNull(missThreePointJumperExtractor.getPlayPlayer());
   }
}
