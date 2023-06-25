package com.rickags.models.json.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;

public class FreeThrowResultBreakdownTest
{
   @Test
   public void testFreeThrowResultBreakdown()
   {
      LocalDate startDate = LocalDate.now();

      FreeThrowResultBreakdown freeThrowResultBreakdown = new FreeThrowResultBreakdown();
      freeThrowResultBreakdown.setFreeThrowPercentage(new BigDecimal("18.67"));
      freeThrowResultBreakdown.setTotalOccurrences(new BigInteger("14"));
      freeThrowResultBreakdown.setTotalSuccesses(new BigInteger("3"));
      freeThrowResultBreakdown.setIndividualFreeThrowResultDataList(new ArrayList<>());
      IndividualFreeThrowResultData individualFreeThrowResultData = new IndividualFreeThrowResultData();
      freeThrowResultBreakdown.getIndividualFreeThrowResultDataList().add(individualFreeThrowResultData);


      assertEquals(new BigDecimal("18.67"), freeThrowResultBreakdown.getFreeThrowPercentage());
      assertTrue(freeThrowResultBreakdown.getIndividualFreeThrowResultDataList().get(0) instanceof IndividualFreeThrowResultData);
      assertEquals(new BigInteger("14"), freeThrowResultBreakdown.getTotalOccurrences());
      assertEquals(new BigInteger("3"), freeThrowResultBreakdown.getTotalSuccesses());


   }
}
