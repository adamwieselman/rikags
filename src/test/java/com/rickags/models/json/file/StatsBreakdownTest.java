package com.rickags.models.json.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;

public class StatsBreakdownTest
{
   @Test
   public void testStatsBreakdown()
   {
      LocalDate date = LocalDate.now();
      StatsBreakdown statsBreakdown = new StatsBreakdown();
      statsBreakdown.setAsOfDate(date);
      statsBreakdown.setOverallGamesWeightedPct(new BigInteger("3"));
      statsBreakdown.setConferenceGamesWeightedPct(new BigInteger("50"));
      statsBreakdown.setNonConferenceGamesWeightedPct(new BigInteger("84"));
      statsBreakdown.setGamesOrTimeframe("gamesOrTimeframe");
      statsBreakdown.setLastOverallGamesOrDays(new BigInteger("21"));
      statsBreakdown.setLastConferenceGamesOrDays(new BigInteger("46"));
      statsBreakdown.setLastNonConferenceGamesOrDays(new BigInteger("7"));
      statsBreakdown.setOverallOffensivePossessionResultBreakdownList(new ArrayList<>());
      statsBreakdown.getOverallOffensivePossessionResultBreakdownList().add(new PossessionResultBreakdown());
      statsBreakdown.setOverallDefensivePossessionResultBreakdownList(new ArrayList<>());
      statsBreakdown.getOverallDefensivePossessionResultBreakdownList().add(new PossessionResultBreakdown());
      statsBreakdown.setOverallOffensiveShotResultBreakdownSummaryList(new ArrayList<>());
      statsBreakdown.getOverallOffensiveShotResultBreakdownSummaryList().add(new ShotResultBreakdownSummary());
      statsBreakdown.setOverallDefensiveShotResultBreakdownSummaryList(new ArrayList<>());
      statsBreakdown.getOverallDefensiveShotResultBreakdownSummaryList().add(new ShotResultBreakdownSummary());
      statsBreakdown.setOverallOffensiveReboundResultBreakdownSummaryList(new ArrayList<>());
      statsBreakdown.getOverallOffensiveReboundResultBreakdownSummaryList().add(new ReboundResultBreakdownSummary());
      statsBreakdown.setOverallDefensiveReboundResultBreakdownSummaryList(new ArrayList<>());
      statsBreakdown.getOverallDefensiveReboundResultBreakdownSummaryList().add(new ReboundResultBreakdownSummary());
      statsBreakdown.setOverallFreeThrowResultBreakdown(new FreeThrowResultBreakdown());


      assertEquals(date, statsBreakdown.getAsOfDate());
      assertEquals(new BigInteger("3"), statsBreakdown.getOverallGamesWeightedPct());
      assertEquals(new BigInteger("50"), statsBreakdown.getConferenceGamesWeightedPct());
      assertEquals(new BigInteger("84"), statsBreakdown.getNonConferenceGamesWeightedPct());
      assertEquals("gamesOrTimeframe", statsBreakdown.getGamesOrTimeframe());
      assertEquals(new BigInteger("21"), statsBreakdown.getLastOverallGamesOrDays());
      assertEquals(new BigInteger("46"), statsBreakdown.getLastConferenceGamesOrDays());
      assertEquals(new BigInteger("7"), statsBreakdown.getLastNonConferenceGamesOrDays());
      assertTrue(statsBreakdown.getOverallOffensivePossessionResultBreakdownList().get(0) instanceof PossessionResultBreakdown);
      assertTrue(statsBreakdown.getOverallDefensivePossessionResultBreakdownList().get(0) instanceof PossessionResultBreakdown);
      assertTrue(statsBreakdown.getOverallOffensiveShotResultBreakdownSummaryList().get(0) instanceof ShotResultBreakdownSummary);
      assertTrue(statsBreakdown.getOverallDefensiveShotResultBreakdownSummaryList().get(0) instanceof ShotResultBreakdownSummary);
      assertTrue(statsBreakdown.getOverallOffensiveReboundResultBreakdownSummaryList().get(0) instanceof ReboundResultBreakdownSummary);
      assertTrue(statsBreakdown.getOverallDefensiveReboundResultBreakdownSummaryList().get(0) instanceof ReboundResultBreakdownSummary);
      assertTrue(statsBreakdown.getOverallFreeThrowResultBreakdown() instanceof FreeThrowResultBreakdown);

   }
}
