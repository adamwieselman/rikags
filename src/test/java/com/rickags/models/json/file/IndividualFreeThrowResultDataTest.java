package com.rickags.models.json.file;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

import org.junit.Test;

public class IndividualFreeThrowResultDataTest
{
   @Test
   public void testIndividualFreeThrowResultData()
   {
      LocalDate startDate = LocalDate.now();

      IndividualFreeThrowResultData individualFreeThrowResultData = new IndividualFreeThrowResultData();
      individualFreeThrowResultData.setIndividualSuccesses(new BigInteger("18"));
      individualFreeThrowResultData.setPlayPlayer("Jimmy Greek");
      individualFreeThrowResultData.setIndividualOccurrences(new BigInteger("12"));
      individualFreeThrowResultData.setIndividualSuccessPercentage(new BigDecimal("25.78"));

      assertEquals(new BigDecimal("25.78"), individualFreeThrowResultData.getIndividualSuccessPercentage());
      assertEquals(new BigInteger("12"), individualFreeThrowResultData.getIndividualOccurrences());
      assertEquals(new BigInteger("18"), individualFreeThrowResultData.getIndividualSuccesses());
      assertEquals("Jimmy Greek", individualFreeThrowResultData.getPlayPlayer());

   }
}
