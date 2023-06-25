package com.rickags.extractors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;


public class MissTwoPointLayupExtractorTest
{
   private static String MISS_LAYUP = " missed Layup";

   @Test
   public void testMissTwoPointLayupExtractor(){
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setTimeStamp("15:35");
      pbp.setGameDetail("Jimmy Greek missed Layup");
      pbp.setGameHalf("1");
      pbp.setTeamLogo("/55.png");
      pbp.setCombinedScore("65-64");

      MissTwoPointLayupExtractor missTwoPointLayupExtractor = new MissTwoPointLayupExtractor(pbp);
      MissTwoPointLayupExtractor missTwoPointLayupExtractor1 = new MissTwoPointLayupExtractor();
      assertTrue( missTwoPointLayupExtractor.generateExtractor(pbp) instanceof MissTwoPointLayupExtractor);
      assertTrue( missTwoPointLayupExtractor1 instanceof MissTwoPointLayupExtractor);
      assertEquals("END", missTwoPointLayupExtractor.getBeginningOrEnding());
      assertEquals("MISS2PTCLOSE", missTwoPointLayupExtractor.getPriorPlayType());
      assertEquals("Jimmy Greek", missTwoPointLayupExtractor.getPlayPlayer());
      assertEquals("2PTCLOSEATTEMPT", missTwoPointLayupExtractor.getOverallPlayType());
      assertEquals("MISS2PTCLOSE", missTwoPointLayupExtractor.getPlayType());
      assertTrue(missTwoPointLayupExtractor.supports("Jimmy Greek missed Layup"));
      assertFalse(missTwoPointLayupExtractor.supports("Jimmy Greek Steal"));

      pbp.setGameDetail(null);
      assertNull(missTwoPointLayupExtractor.getPlayPlayer());
   }
}
