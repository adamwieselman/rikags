package com.rickags.models.json.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import org.junit.Test;

public class ReboundResultBreakdownTest
{
   @Test
   public void testReboundResultBreakdown()
   {
      ReboundResultBreakdown reboundResultBreakdown = new ReboundResultBreakdown();
      reboundResultBreakdown.setGameTimeFrame("Game Time Frame");
      reboundResultBreakdown.setPossessionStart("start");
      reboundResultBreakdown.setOverallPossessionResult("possResult");
      reboundResultBreakdown.setOffensiveGroupingName("offGroupname");
      reboundResultBreakdown.setDefensiveGroupingName("defGroupName");
      reboundResultBreakdown.setTotalOccurrences(new BigInteger("2"));
      reboundResultBreakdown.setTotalRebounds(new BigInteger("3"));
      reboundResultBreakdown.setReboundPercentage(new BigDecimal("4.2"));
      reboundResultBreakdown.setTempoAverage(new BigDecimal("5.0"));
      reboundResultBreakdown.setTempoStDev(new BigDecimal("6.8"));
      reboundResultBreakdown.setTempos(new ArrayList<>());
      reboundResultBreakdown.getTempos().add(new BigInteger("9"));
      reboundResultBreakdown.setIndividualReboundResultDataList(new ArrayList<>());
      reboundResultBreakdown.getIndividualReboundResultDataList().add(new IndividualReboundResultData());

      assertEquals("Game Time Frame", reboundResultBreakdown.getGameTimeFrame());
      assertEquals("start", reboundResultBreakdown.getPossessionStart());
      assertEquals("possResult", reboundResultBreakdown.getOverallPossessionResult());
      assertEquals("offGroupname", reboundResultBreakdown.getOffensiveGroupingName());
      assertEquals("defGroupName", reboundResultBreakdown.getDefensiveGroupingName());
      assertEquals(new BigInteger("2"), reboundResultBreakdown.getTotalOccurrences());
      assertEquals(new BigInteger("3"), reboundResultBreakdown.getTotalRebounds());
      assertEquals(new BigDecimal("4.2"), reboundResultBreakdown.getReboundPercentage());
      assertEquals(new BigDecimal("5.0"), reboundResultBreakdown.getTempoAverage());
      assertEquals(new BigDecimal("6.8"), reboundResultBreakdown.getTempoStDev());
      assertTrue(reboundResultBreakdown.getTempos().get(0) instanceof BigInteger);
      assertTrue(reboundResultBreakdown.getIndividualReboundResultDataList().get(0) instanceof IndividualReboundResultData);

   }
}
