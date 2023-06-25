package com.rickags.extractors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.stereotype.Component;

import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

@Component
public class MissTwoPointDunkExtractorTest
{
   private static String MISS_DUNK = " missed Dunk";

   @Test
   public void testMissTwoPointDunkExtractor(){
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setTimeStamp("15:35");
      pbp.setGameDetail("Jimmy Greek missed Dunk");
      pbp.setGameHalf("1");
      pbp.setTeamLogo("/55.png");
      pbp.setCombinedScore("65-64");

      MissTwoPointDunkExtractor missTwoPointDunkExtractor = new MissTwoPointDunkExtractor(pbp);
      MissTwoPointDunkExtractor missTwoPointDunkExtractor1 = new MissTwoPointDunkExtractor();
      assertTrue( missTwoPointDunkExtractor.generateExtractor(pbp) instanceof MissTwoPointDunkExtractor);
      assertTrue( missTwoPointDunkExtractor1 instanceof MissTwoPointDunkExtractor);
      assertEquals("END", missTwoPointDunkExtractor.getBeginningOrEnding());
      assertEquals("MISS2PTCLOSE", missTwoPointDunkExtractor.getPriorPlayType());
      assertEquals("Jimmy Greek", missTwoPointDunkExtractor.getPlayPlayer());
      assertEquals("2PTCLOSEATTEMPT", missTwoPointDunkExtractor.getOverallPlayType());
      assertEquals("MISS2PTCLOSE", missTwoPointDunkExtractor.getPlayType());
      assertTrue(missTwoPointDunkExtractor.supports("Jimmy Greek missed Dunk"));
      assertFalse(missTwoPointDunkExtractor.supports("Jimmy Greek Steal"));

      pbp.setGameDetail(null);
      assertNull(missTwoPointDunkExtractor.getPlayPlayer());
   }

}
