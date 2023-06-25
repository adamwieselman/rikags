package com.rickags.models.json.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import org.junit.Test;

public class ShotResultBreakdownTest
{
   @Test
   public void testShotResultBreakdown()
   {
      ShotResultBreakdown shotResultBreakdown = new ShotResultBreakdown();
      shotResultBreakdown.setGameTimeFrame("Game Time Frame");
      shotResultBreakdown.setPossessionStart("start");
      shotResultBreakdown.setOverallPossessionResult("possResult");
      shotResultBreakdown.setOffensiveGroupingName("offGroupname");
      shotResultBreakdown.setDefensiveGroupingName("defGroupName");
      shotResultBreakdown.setTotalOccurrences(new BigInteger("2"));
      shotResultBreakdown.setTotalSuccesses(new BigInteger("3"));
      shotResultBreakdown.setSuccessPercentage(new BigDecimal("4.2"));
      shotResultBreakdown.setIndividualShotResultDataList(new ArrayList<>());
      shotResultBreakdown.getIndividualShotResultDataList().add(new IndividualShotResultData());

      assertEquals("Game Time Frame", shotResultBreakdown.getGameTimeFrame());
      assertEquals("start", shotResultBreakdown.getPossessionStart());
      assertEquals("possResult", shotResultBreakdown.getOverallPossessionResult());
      assertEquals("offGroupname", shotResultBreakdown.getOffensiveGroupingName());
      assertEquals("defGroupName", shotResultBreakdown.getDefensiveGroupingName());
      assertEquals(new BigInteger("2"), shotResultBreakdown.getTotalOccurrences());
      assertEquals(new BigInteger("3"), shotResultBreakdown.getTotalSuccesses());
      assertEquals(new BigDecimal("4.2"), shotResultBreakdown.getSuccessPercentage());
      assertTrue(shotResultBreakdown.getIndividualShotResultDataList().get(0) instanceof IndividualShotResultData);

   }
}
