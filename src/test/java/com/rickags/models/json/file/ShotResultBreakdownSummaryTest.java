package com.rickags.models.json.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import org.junit.Test;

public class ShotResultBreakdownSummaryTest
{
   @Test
   public void testShotResultBreakdownSummary()
   {
      ShotResultBreakdownSummary shotResultBreakdownSummary = new ShotResultBreakdownSummary();
      shotResultBreakdownSummary.setGameTimeFrame("Game Time Frame");
      shotResultBreakdownSummary.setPossessionStart("start");
      shotResultBreakdownSummary.setOverallPossessionResult("possResult");
      shotResultBreakdownSummary.setOffensiveGroupingName("offGroupname");
      shotResultBreakdownSummary.setDefensiveGroupingName("defGroupName");
      shotResultBreakdownSummary.setTotalOccurrences(new BigInteger("2"));
      shotResultBreakdownSummary.setTotalSuccesses(new BigInteger("3"));
      shotResultBreakdownSummary.setTotalSuccessPercentage(new BigDecimal("4.2"));
      shotResultBreakdownSummary.setTotalSuccessPercentageStrength(new BigDecimal("5.0"));
      shotResultBreakdownSummary.setGameSuccessPercentageAverage(new BigDecimal("6.8"));
      shotResultBreakdownSummary.setGameSuccessPercentageStDev(new BigDecimal("5.78"));
      shotResultBreakdownSummary.setGameSuccessPercentageStrength(new BigDecimal("7.82"));
      shotResultBreakdownSummary.setIndividualShotResultDataSummaryList(new ArrayList<>());
      shotResultBreakdownSummary.getIndividualShotResultDataSummaryList().add(new IndividualShotResultDataSummary());
      shotResultBreakdownSummary.setWeightedGameSuccessPercentageAverage(new BigDecimal("6.9"));
      shotResultBreakdownSummary.setWeightedGameSuccessPercentageStDev(new BigDecimal("15.78"));
      shotResultBreakdownSummary.setWeightedGameSuccessPercentageStrength(new BigDecimal("17.82"));


      assertEquals("Game Time Frame", shotResultBreakdownSummary.getGameTimeFrame());
      assertEquals("start", shotResultBreakdownSummary.getPossessionStart());
      assertEquals("possResult", shotResultBreakdownSummary.getOverallPossessionResult());
      assertEquals("offGroupname", shotResultBreakdownSummary.getOffensiveGroupingName());
      assertEquals("defGroupName", shotResultBreakdownSummary.getDefensiveGroupingName());
      assertEquals(new BigInteger("2"), shotResultBreakdownSummary.getTotalOccurrences());
      assertEquals(new BigInteger("3"), shotResultBreakdownSummary.getTotalSuccesses());
      assertEquals(new BigDecimal("4.2"), shotResultBreakdownSummary.getTotalSuccessPercentage());
      assertEquals(new BigDecimal("5.0"), shotResultBreakdownSummary.getTotalSuccessPercentageStrength());
      assertEquals(new BigDecimal("6.8"), shotResultBreakdownSummary.getGameSuccessPercentageAverage());
      assertEquals(new BigDecimal("5.78"), shotResultBreakdownSummary.getGameSuccessPercentageStDev());
      assertEquals(new BigDecimal("7.82"), shotResultBreakdownSummary.getGameSuccessPercentageStrength());
      assertEquals(new BigDecimal("6.9"), shotResultBreakdownSummary.getWeightedGameSuccessPercentageAverage());
      assertEquals(new BigDecimal("15.78"), shotResultBreakdownSummary.getWeightedGameSuccessPercentageStDev());
      assertEquals(new BigDecimal("17.82"), shotResultBreakdownSummary.getWeightedGameSuccessPercentageStrength());
      assertTrue(shotResultBreakdownSummary.getIndividualShotResultDataSummaryList().get(0) instanceof IndividualShotResultDataSummary);

   }
}
