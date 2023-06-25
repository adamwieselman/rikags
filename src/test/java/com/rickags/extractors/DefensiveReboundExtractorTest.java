package com.rickags.extractors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

public class DefensiveReboundExtractorTest
{
   @Test
   public void testDefensiveReboundExtractor(){
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setTimeStamp("15:35");
      pbp.setGameDetail("Jimmy Greek Defensive Rebound");
      pbp.setGameHalf("1");
      pbp.setTeamLogo("/55.png");
      pbp.setCombinedScore("65-64");

      DefensiveReboundExtractor defensiveReboundExtractor = new DefensiveReboundExtractor(pbp);
      DefensiveReboundExtractor defensiveReboundExtractor1 = new DefensiveReboundExtractor();
      assertTrue( defensiveReboundExtractor1 instanceof DefensiveReboundExtractor);
      assertTrue( defensiveReboundExtractor.generateExtractor(pbp) instanceof DefensiveReboundExtractor);
      assertEquals("BEGIN", defensiveReboundExtractor.getBeginningOrEnding());
      assertEquals("DEFREB", defensiveReboundExtractor.getPriorPlayType());
      assertEquals("Jimmy Greek", defensiveReboundExtractor.getPlayPlayer());
      assertEquals("DEFREB", defensiveReboundExtractor.getOverallPlayType());
      assertEquals("DEFREB", defensiveReboundExtractor.getPlayType());
      assertTrue(defensiveReboundExtractor.supports("Jimmy Greek Defensive Rebound"));
      assertFalse(defensiveReboundExtractor.supports("Jimmy Greek Steal"));

      pbp.setGameDetail(null);
      assertNull(defensiveReboundExtractor.getPlayPlayer());
   }
}
