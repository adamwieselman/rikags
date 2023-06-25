package com.rickags.models.json.file;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

public class IndividualShotResultDataSummaryTest
{
   @Test
   public void testIndividualShotResultDataSummary()
   {
      IndividualShotResultDataSummary individualShotResultDataSummary = new IndividualShotResultDataSummary();
      individualShotResultDataSummary.setPlayPlayer("Jimmy Greek");
      individualShotResultDataSummary.setTotalIndividualOccurrences(new BigInteger("2"));
      individualShotResultDataSummary.setTotalIndividualSuccesses(new BigInteger("4"));
      individualShotResultDataSummary.setTotalIndividualSuccessPercentage(new BigDecimal("45.56"));
      individualShotResultDataSummary.setTotalIndividualSuccessPercentageStrength(new BigDecimal("12.56"));
      individualShotResultDataSummary.setGameIndividualSuccessPercentageAverage(new BigDecimal("78.22"));
      individualShotResultDataSummary.setGameIndividualSuccessPercentageStDev(new BigDecimal("56.43"));
      individualShotResultDataSummary.setGameIndividualSuccessPercentageStrength(new BigDecimal("86.25"));
      individualShotResultDataSummary.setWeightedGameIndividualSuccessPercentageAverage(new BigDecimal("78.21"));
      individualShotResultDataSummary.setWeightedGameIndividualSuccessPercentageStDev(new BigDecimal("56.42"));
      individualShotResultDataSummary.setWeightedGameIndividualSuccessPercentageStrength(new BigDecimal("86.24"));

      assertEquals("Jimmy Greek", individualShotResultDataSummary.getPlayPlayer());
      assertEquals(new BigInteger("2"), individualShotResultDataSummary.getTotalIndividualOccurrences());
      assertEquals(new BigInteger("4"), individualShotResultDataSummary.getTotalIndividualSuccesses());
      assertEquals(new BigDecimal("45.56"), individualShotResultDataSummary.getTotalIndividualSuccessPercentage());
      assertEquals(new BigDecimal("12.56"), individualShotResultDataSummary.getTotalIndividualSuccessPercentageStrength());
      assertEquals(new BigDecimal("86.25"), individualShotResultDataSummary.getGameIndividualSuccessPercentageStrength());
      assertEquals(new BigDecimal("78.22"), individualShotResultDataSummary.getGameIndividualSuccessPercentageAverage());
      assertEquals(new BigDecimal("56.43"), individualShotResultDataSummary.getGameIndividualSuccessPercentageStDev());
      assertEquals(new BigDecimal("78.21"), individualShotResultDataSummary.getWeightedGameIndividualSuccessPercentageAverage());
      assertEquals(new BigDecimal("56.42"), individualShotResultDataSummary.getWeightedGameIndividualSuccessPercentageStDev());
      assertEquals(new BigDecimal("86.24"), individualShotResultDataSummary.getWeightedGameIndividualSuccessPercentageStrength());
   }
}
