package com.rickags.models.json.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.ArrayList;

import org.junit.Test;

public class PossessionResultBreakdownTest
{
   @Test
   public void testPossessionResultBreakdown()
   {
      PossessionResultBreakdown possessionResultBreakdown = new PossessionResultBreakdown();
      possessionResultBreakdown.setGameTimeFrame("Game Time Frame");
      possessionResultBreakdown.setPossessionStart("Possession Start");
      possessionResultBreakdown.setOffensiveGroupingName("Off Group Name");
      possessionResultBreakdown.setDefensiveGroupingName("Def Group Name");
      possessionResultBreakdown.setPossessionDifference(new BigInteger("3"));
      possessionResultBreakdown.setPointsDifference(new BigInteger("9"));
      possessionResultBreakdown.setTotalOccurrences(new BigInteger("45"));
      possessionResultBreakdown.setPossessionResultDataList(new ArrayList<>());
      PossessionResultData possessionResultData = new PossessionResultData();
      possessionResultBreakdown.getPossessionResultDataList().add(possessionResultData);

      assertEquals("Game Time Frame", possessionResultBreakdown.getGameTimeFrame());
      assertEquals("Possession Start", possessionResultBreakdown.getPossessionStart());
      assertEquals("Off Group Name", possessionResultBreakdown.getOffensiveGroupingName());
      assertEquals("Def Group Name", possessionResultBreakdown.getDefensiveGroupingName());
      assertEquals(new BigInteger("3"), possessionResultBreakdown.getPossessionDifference());
      assertEquals(new BigInteger("9"), possessionResultBreakdown.getPointsDifference());
      assertEquals(new BigInteger("45"), possessionResultBreakdown.getTotalOccurrences());
      assertTrue(possessionResultBreakdown.getPossessionResultDataList().get(0) instanceof PossessionResultData);

 }
}
