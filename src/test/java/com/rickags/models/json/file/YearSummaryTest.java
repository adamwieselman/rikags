package com.rickags.models.json.file;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class YearSummaryTest
{
   @Test
   public void testYearSummary()
   {
      YearSummary yearSummary = new YearSummary();
      yearSummary.setYear("year");
      yearSummary.setConferenceId("confId");
      yearSummary.setHomeArenaId("homeArenaId");
      yearSummary.setGameResults(new ArrayList<>());
      yearSummary.getGameResults().add(new GameResult());

      assertEquals("year", yearSummary.getYear());
      assertEquals("confId", yearSummary.getConferenceId());
      assertEquals("homeArenaId", yearSummary.getHomeArenaId());
      assertEquals(1, yearSummary.getGameResults().size());
   }
}
