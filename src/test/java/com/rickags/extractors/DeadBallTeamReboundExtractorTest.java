package com.rickags.extractors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

public class DeadBallTeamReboundExtractorTest
{
   @Test
   public void testDeadBallTeamReboundExtractor(){
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setTimeStamp("15:35");
      pbp.setGameDetail("Boston Deadball Team Rebound");
      pbp.setGameHalf("1");
      pbp.setTeamLogo("/55.png");
      pbp.setCombinedScore("65-64");

      DeadBallTeamReboundExtractor deadBallTeamReboundExtractor = new DeadBallTeamReboundExtractor(pbp);
      DeadBallTeamReboundExtractor deadBallTeamReboundExtractor1 = new DeadBallTeamReboundExtractor();
      assertTrue( deadBallTeamReboundExtractor.generateExtractor(pbp) instanceof DeadBallTeamReboundExtractor);
      assertTrue( deadBallTeamReboundExtractor1 instanceof DeadBallTeamReboundExtractor);
      assertEquals("ENDBEGIN", deadBallTeamReboundExtractor.getBeginningOrEnding());
      assertEquals("DEADBALL", deadBallTeamReboundExtractor.getPriorPlayType());
      assertEquals("Boston", deadBallTeamReboundExtractor.getPlayPlayer());
      assertEquals("TEAMREB", deadBallTeamReboundExtractor.getOverallPlayType());
      assertEquals("TEAMREB", deadBallTeamReboundExtractor.getPlayType());
      assertTrue(deadBallTeamReboundExtractor.supports("Boston Deadball Team Rebound"));
      assertFalse(deadBallTeamReboundExtractor.supports("Jimmy Greek Steal"));

      pbp.setGameDetail(null);
      assertNull(deadBallTeamReboundExtractor.getPlayPlayer());
   }
}
