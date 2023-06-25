package com.rickags.extractors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

public class TurnoverExtractorTest
{
   private static String TURNOVER = " Turnover";
   @Test
   public void testTurnoverExtractor(){
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setTimeStamp("15:35");
      pbp.setGameDetail("Jimmy Greek Turnover");
      pbp.setGameHalf("1");
      pbp.setTeamLogo("/55.png");
      pbp.setCombinedScore("65-64");

      TurnoverExtractor turnoverExtractor = new TurnoverExtractor(pbp);
      TurnoverExtractor turnoverExtractor1 = new TurnoverExtractor();
      assertTrue(turnoverExtractor1 instanceof TurnoverExtractor);
      assertTrue( turnoverExtractor.generateExtractor(pbp) instanceof TurnoverExtractor);
      assertEquals("ENDBEGIN", turnoverExtractor.getBeginningOrEnding());
      assertEquals("DEADBALL", turnoverExtractor.getPriorPlayType());
      assertEquals("Jimmy Greek", turnoverExtractor.getPlayPlayer());
      assertEquals("TURNOVER", turnoverExtractor.getOverallPlayType());
      assertEquals("TURNOVER", turnoverExtractor.getPlayType());
      assertTrue(turnoverExtractor.supports("Jimmy Greek Turnover"));
      assertFalse(turnoverExtractor.supports("Jimmy Greek Steal"));

      pbp.setGameDetail(null);
      assertNull(turnoverExtractor.getPlayPlayer());
   }
}
