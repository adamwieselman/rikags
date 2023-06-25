package com.rickags.models.json.file;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

import org.junit.Test;

public class IndividualPossessionResultDataTest
{
   @Test
   public void testIndividualPossessionResultData()
   {
      LocalDate startDate = LocalDate.now();

      IndividualPossessionResultData individualPossessionResultData = new IndividualPossessionResultData();
      individualPossessionResultData.setIndividualOccurrences(new BigInteger("11"));
      individualPossessionResultData.setPlayPlayer("Jimmy Greek");
      individualPossessionResultData.setIndividualOccurrencePercentage(new BigDecimal("16.33"));

      assertEquals("Jimmy Greek", individualPossessionResultData.getPlayPlayer());
      assertEquals(new BigDecimal("16.33"), individualPossessionResultData.getIndividualOccurrencePercentage());
      assertEquals(new BigInteger("11"), individualPossessionResultData.getIndividualOccurrences());
   }
}
