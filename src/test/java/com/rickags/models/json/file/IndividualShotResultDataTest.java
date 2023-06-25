package com.rickags.models.json.file;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

import org.junit.Test;

public class IndividualShotResultDataTest
{
   @Test
   public void testIndividualShotResultData()
   {
      LocalDate startDate = LocalDate.now();

      IndividualShotResultData individualShotResultData = new IndividualShotResultData();
      individualShotResultData.setIndividualOccurrences(new BigInteger("11"));
      individualShotResultData.setIndividualSuccesses(new BigInteger("16"));
      individualShotResultData.setPlayPlayer("Jimmy Greek");
      individualShotResultData.setIndividualSuccessPercentage(new BigDecimal("16.33"));

      assertEquals("Jimmy Greek", individualShotResultData.getPlayPlayer());
      assertEquals(new BigDecimal("16.33"), individualShotResultData.getIndividualSuccessPercentage());
      assertEquals(new BigInteger("11"), individualShotResultData.getIndividualOccurrences());
      assertEquals(new BigInteger("16"), individualShotResultData.getIndividualSuccesses());
   }
}
