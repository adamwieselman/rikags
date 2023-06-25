package com.rickags.models.json.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import org.junit.Test;

public class PossessionResultDataTest
{
   @Test
   public void testPossessionResultData()
   {
      PossessionResultData possessionResultData = new PossessionResultData();
      possessionResultData.setOverallPossessionResult("result");
      possessionResultData.setOccurrences(new BigInteger("12"));
      possessionResultData.setOccurrencePercentage(new BigDecimal("56.87"));
      possessionResultData.setTempoAverage(new BigDecimal("97.25"));
      possessionResultData.setTempoStDev(new BigDecimal("24.67"));
      possessionResultData.setTempos(new ArrayList<>());
      possessionResultData.getTempos().add(new BigInteger("5"));
      possessionResultData.setIndividualPossessionResultDataList(new ArrayList<>());
      possessionResultData.getIndividualPossessionResultDataList().add(new IndividualPossessionResultData());

      assertEquals("result", possessionResultData.getOverallPossessionResult());
      assertEquals(new BigInteger("12"), possessionResultData.getOccurrences());
      assertEquals(new BigDecimal("56.87"), possessionResultData.getOccurrencePercentage());
      assertEquals(new BigDecimal("97.25"), possessionResultData.getTempoAverage());
      assertEquals(new BigDecimal("24.67"), possessionResultData.getTempoStDev());
      assertTrue(possessionResultData.getTempos().get(0) instanceof BigInteger);
      assertTrue(possessionResultData.getIndividualPossessionResultDataList().get(0) instanceof IndividualPossessionResultData);

   }
}
