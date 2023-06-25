package com.rickags.models.json.file;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

import org.junit.Test;

public class IndividualReboundResultDataTest
{
   @Test
   public void testIndividualPossessionResultData()
   {
      LocalDate startDate = LocalDate.now();

      IndividualReboundResultData individualReboundResultData = new IndividualReboundResultData();
      individualReboundResultData.setIndividualRebounds(new BigInteger("11"));
      individualReboundResultData.setPlayPlayer("Jimmy Greek");
      individualReboundResultData.setIndividualReboundPercentage(new BigDecimal("16.33"));

      assertEquals("Jimmy Greek", individualReboundResultData.getPlayPlayer());
      assertEquals(new BigDecimal("16.33"), individualReboundResultData.getIndividualReboundPercentage());
      assertEquals(new BigInteger("11"), individualReboundResultData.getIndividualRebounds());
   }
}
