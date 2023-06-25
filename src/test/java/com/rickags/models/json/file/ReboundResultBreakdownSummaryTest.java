package com.rickags.models.json.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import org.junit.Test;

public class ReboundResultBreakdownSummaryTest
{
   @Test
   public void testReboundResultBreakdownSummary()
   {
      ReboundResultBreakdownSummary reboundResultBreakdownSummary = new ReboundResultBreakdownSummary();
      reboundResultBreakdownSummary.setGameTimeFrame("Game Time Frame");
      reboundResultBreakdownSummary.setPossessionStart("start");
      reboundResultBreakdownSummary.setOverallPossessionResult("possResult");
      reboundResultBreakdownSummary.setOffensiveGroupingName("offGroupname");
      reboundResultBreakdownSummary.setDefensiveGroupingName("defGroupName");
      reboundResultBreakdownSummary.setTotalOccurrences(new BigInteger("2"));
      reboundResultBreakdownSummary.setTotalRebounds(new BigInteger("3"));
      reboundResultBreakdownSummary.setTotalReboundPercentage(new BigDecimal("4.2"));
      reboundResultBreakdownSummary.setTotalReboundPercentageStrength(new BigDecimal("5.0"));
      reboundResultBreakdownSummary.setGameReboundPercentageAverage(new BigDecimal("6.8"));
      reboundResultBreakdownSummary.setGameReboundPercentageStDev(new BigDecimal("5.78"));
      reboundResultBreakdownSummary.setGameReboundPercentageStrength(new BigDecimal("7.82"));
      reboundResultBreakdownSummary.setIndividualReboundResultDataSummaryList(new ArrayList<>());
      reboundResultBreakdownSummary.getIndividualReboundResultDataSummaryList().add(new IndividualReboundResultDataSummary());
      reboundResultBreakdownSummary.setWeightedGameReboundPercentageAverage(new BigDecimal("6.9"));
      reboundResultBreakdownSummary.setWeightedGameReboundPercentageStDev(new BigDecimal("15.78"));
      reboundResultBreakdownSummary.setWeightedGameReboundPercentageStrength(new BigDecimal("17.82"));


      assertEquals("Game Time Frame", reboundResultBreakdownSummary.getGameTimeFrame());
      assertEquals("start", reboundResultBreakdownSummary.getPossessionStart());
      assertEquals("possResult", reboundResultBreakdownSummary.getOverallPossessionResult());
      assertEquals("offGroupname", reboundResultBreakdownSummary.getOffensiveGroupingName());
      assertEquals("defGroupName", reboundResultBreakdownSummary.getDefensiveGroupingName());
      assertEquals(new BigInteger("2"), reboundResultBreakdownSummary.getTotalOccurrences());
      assertEquals(new BigInteger("3"), reboundResultBreakdownSummary.getTotalRebounds());
      assertEquals(new BigDecimal("4.2"), reboundResultBreakdownSummary.getTotalReboundPercentage());
      assertEquals(new BigDecimal("5.0"), reboundResultBreakdownSummary.getTotalReboundPercentageStrength());
      assertEquals(new BigDecimal("6.8"), reboundResultBreakdownSummary.getGameReboundPercentageAverage());
      assertEquals(new BigDecimal("5.78"), reboundResultBreakdownSummary.getGameReboundPercentageStDev());
      assertEquals(new BigDecimal("7.82"), reboundResultBreakdownSummary.getGameReboundPercentageStrength());
      assertEquals(new BigDecimal("6.9"), reboundResultBreakdownSummary.getWeightedGameReboundPercentageAverage());
      assertEquals(new BigDecimal("15.78"), reboundResultBreakdownSummary.getWeightedGameReboundPercentageStDev());
      assertEquals(new BigDecimal("17.82"), reboundResultBreakdownSummary.getWeightedGameReboundPercentageStrength());
      assertTrue(reboundResultBreakdownSummary.getIndividualReboundResultDataSummaryList().get(0) instanceof IndividualReboundResultDataSummary);

   }
}
