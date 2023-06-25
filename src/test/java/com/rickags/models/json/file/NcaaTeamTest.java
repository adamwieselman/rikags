package com.rickags.models.json.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class NcaaTeamTest
{
   @Test
   public void testNcaaTeam()
   {
      NcaaTeam ncaaTeam = new NcaaTeam();
      ncaaTeam.setTeamName("team name");
      ncaaTeam.setTeamId("team id");
      ncaaTeam.setYearSummaries(new ArrayList<>());
      ncaaTeam.setStatsBreakdown(new ArrayList<>());
      ncaaTeam.getYearSummaries().add(new YearSummary());
      ncaaTeam.getStatsBreakdown().add(new StatsBreakdown());

      assertEquals("team name", ncaaTeam.getTeamName());
      assertEquals("team id", ncaaTeam.getTeamId());
      assertTrue(ncaaTeam.getYearSummaries().get(0) instanceof YearSummary);
      assertTrue(ncaaTeam.getStatsBreakdown().get(0) instanceof StatsBreakdown);
   }
}
