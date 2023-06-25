package com.rickags.extractors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.stereotype.Component;

import com.rickags.models.json.espn.ncabb.EspnPlayByPlay;

@Component
public class MadeTwoPointTipShotExtractorTest
{
   private static String MADE_2PT_TIP_SHOT = " made Two Point Tip Shot";

   @Test
   public void testMadeTwoPointTipShotExtractor(){
      EspnPlayByPlay pbp = new EspnPlayByPlay();
      pbp.setTimeStamp("15:35");
      pbp.setGameDetail("Jimmy Greek made Two Point Tip Shot");
      pbp.setGameHalf("1");
      pbp.setTeamLogo("/55.png");
      pbp.setCombinedScore("65-64");

      MadeTwoPointTipShotExtractor madeTwoPointTipShotExtractor = new MadeTwoPointTipShotExtractor(pbp);
      MadeTwoPointTipShotExtractor madeTwoPointTipShotExtractor1 = new MadeTwoPointTipShotExtractor();
      assertTrue( madeTwoPointTipShotExtractor.generateExtractor(pbp) instanceof MadeTwoPointTipShotExtractor);
      assertTrue( madeTwoPointTipShotExtractor1 instanceof MadeTwoPointTipShotExtractor);
      assertEquals("ENDBEGIN", madeTwoPointTipShotExtractor.getBeginningOrEnding());
      assertEquals("DEADBALL", madeTwoPointTipShotExtractor.getPriorPlayType());
      assertEquals("Jimmy Greek", madeTwoPointTipShotExtractor.getPlayPlayer());
      assertEquals("2PTCLOSEATTEMPT", madeTwoPointTipShotExtractor.getOverallPlayType());
      assertEquals("MADE2PTCLOSE", madeTwoPointTipShotExtractor.getPlayType());
      assertTrue(madeTwoPointTipShotExtractor.supports("Jimmy Greek made Two Point Tip Shot"));
      assertFalse(madeTwoPointTipShotExtractor.supports("Jimmy Greek Steal"));

      pbp.setGameDetail(null);
      assertNull(madeTwoPointTipShotExtractor.getPlayPlayer());
   }

}
