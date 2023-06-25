package com.rickags.extractors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.stereotype.Component;

import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

@Component
public class MissFreeThrowExtractorTest
{
   private static String MISS_FREE = " missed Free Throw";

   @Test
   public void testMissFreeThrowExtractor(){
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setTimeStamp("15:35");
      pbp.setGameDetail("Jimmy Greek missed Free Throw");
      pbp.setGameHalf("1");
      pbp.setTeamLogo("/55.png");
      pbp.setCombinedScore("65-64");

      MissFreeThrowExtractor missFreeThrowExtractor = new MissFreeThrowExtractor(pbp);
      MissFreeThrowExtractor missFreeThrowExtractor1 = new MissFreeThrowExtractor();
      assertTrue( missFreeThrowExtractor1 instanceof MissFreeThrowExtractor);
      assertTrue( missFreeThrowExtractor.generateExtractor(pbp) instanceof MissFreeThrowExtractor);
      assertEquals("END", missFreeThrowExtractor.getBeginningOrEnding());
      assertEquals("FOUL", missFreeThrowExtractor.getPriorPlayType());
      assertEquals("Jimmy Greek", missFreeThrowExtractor.getPlayPlayer());
      assertEquals("FREETHROW", missFreeThrowExtractor.getOverallPlayType());
      assertEquals("MISSFREE", missFreeThrowExtractor.getPlayType());
      assertTrue(missFreeThrowExtractor.supports("Jimmy Greek missed Free Throw"));
      assertFalse(missFreeThrowExtractor.supports("Jimmy Greek Steal"));

      pbp.setGameDetail(null);
      assertNull(missFreeThrowExtractor.getPlayPlayer());
   }
}
