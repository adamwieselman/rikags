package com.rickags.services;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class DateEvaluationServiceTest
{

   @Rule
   public MockitoRule rule = MockitoJUnit.rule();

   @InjectMocks
   public DateEvaluationService dateEvaluationService = new DateEvaluationService();

   @Test
   public void testReturnSeasonYear_LessThanFive()
   {
      LocalDate startDate = LocalDate.of(2020, 3, 10);
      assertEquals("2019", dateEvaluationService.returnSeasonYear(startDate));
   }

   @Test
   public void testReturnSeasonYear_GreaterThanFive()
   {
      LocalDate startDate = LocalDate.of(2020, 8, 10);
      assertEquals("2020", dateEvaluationService.returnSeasonYear(startDate));
   }
}
