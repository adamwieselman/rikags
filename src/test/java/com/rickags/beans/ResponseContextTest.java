package com.rickags.beans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.rickags.validators.RickagsError;

public class ResponseContextTest
{

   @Rule
   public MockitoRule rule = MockitoJUnit.rule();

   @InjectMocks
   public ResponseContext responseContext = new ResponseContext();

   @Test
   public void testResponseContextBean(){
      LocalDate startDate = LocalDate.now();
      ResponseContext responseContext = new ResponseContext();
      responseContext.setStartDate(startDate);
      List<RickagsError> errors = new ArrayList<>();
      responseContext.setErrors(errors);
      responseContext.getErrors().add(new RickagsError("error"));

      assertEquals("error", responseContext.getErrors().get(0).getErrorMessage());
      assertEquals(startDate, responseContext.getStartDate());
      assertTrue(responseContext.hasResponseErrors());

   }
}


