package com.rickags.extractors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

public class FoulExtractorTest
{
   private static String FOUL = "Foul on ";

   @Test
   public void testFoulExtractor(){
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setTimeStamp("15:35");
      pbp.setGameDetail("Foul on Jimmy Greek");
      pbp.setGameHalf("1");
      pbp.setTeamLogo("/55.png");
      pbp.setCombinedScore("65-64");

      FoulExtractor foulExtractor = new FoulExtractor(pbp);
      FoulExtractor foulExtractor1 = new FoulExtractor();
      assertTrue( foulExtractor.generateExtractor(pbp) instanceof FoulExtractor);
      assertTrue( foulExtractor1 instanceof FoulExtractor);
      assertEquals("ENDBEGIN", foulExtractor.getBeginningOrEnding());
      assertEquals("DEADBALL", foulExtractor.getPriorPlayType());
      assertEquals("Jimmy Greek", foulExtractor.getPlayPlayer());
      assertEquals("FOUL", foulExtractor.getOverallPlayType());
      assertEquals("FOUL", foulExtractor.getPlayType());
      assertTrue(foulExtractor.supports("Foul on Jimmy Greek"));
      assertFalse(foulExtractor.supports("Jimmy Greek Steal"));

      pbp.setGameDetail(null);
      assertNull(foulExtractor.getPlayPlayer());
   }
}
