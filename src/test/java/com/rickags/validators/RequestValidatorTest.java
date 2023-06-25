package com.rickags.validators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.rickags.beans.RequestContext;
import com.rickags.beans.ResponseContext;

public class RequestValidatorTest
{

   @Rule
   public MockitoRule rule = MockitoJUnit.rule();

   @InjectMocks
   public RequestValidator requestValidator = new RequestValidator();

   @Test
   public void testValidateScheduleRequest_error_StartDateIsNull()
      throws Exception
   {
      RequestContext request = new RequestContext();
      request.setStartDate(null);

      ResponseContext response = requestValidator.validateScheduleRequest(request);

      assertTrue(response.hasResponseErrors());
      assertEquals("Start date is null", response.getErrors().get(0).getErrorMessage());

   }

   @Test
   public void testValidateScheduleRequest_error_StartDateIsAfterCurrentDate()
      throws Exception
   {
      RequestContext request = new RequestContext();
      LocalDate localDate = LocalDate.now();
      LocalDate startDate = LocalDate.of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth()+1);
      request.setStartDate(startDate);

      ResponseContext response = requestValidator.validateScheduleRequest(request);

      assertTrue(response.hasResponseErrors());
      assertEquals("Start date is after current date", response.getErrors().get(0).getErrorMessage());

   }

   @Test
   public void testValidateScheduleRequest_error_EventTypeIsNull()
      throws Exception
   {
      RequestContext request = new RequestContext();
      request.setStartDate(LocalDate.now());
      request.setEventType(null);

      ResponseContext response = requestValidator.validateScheduleRequest(request);

      assertTrue(response.hasResponseErrors());
      assertEquals("Event type is null", response.getErrors().get(0).getErrorMessage());

   }

   public void testValidateScheduleRequest_happyPath()
      throws Exception
   {
      RequestContext request = new RequestContext();
      request.setStartDate(LocalDate.now());
      request.setEventType("NCAABB");

      ResponseContext response = requestValidator.validateScheduleRequest(request);

      assertFalse(response.hasResponseErrors());

   }

   @Test
   public void testValidatePlayByPlayRequest_error_StartDateIsNull()
      throws Exception
   {
      RequestContext request = new RequestContext();
      request.setStartDate(null);

      ResponseContext response = requestValidator.validatePlayByPlayRequest(request);

      assertTrue(response.hasResponseErrors());
      assertEquals("Start date is null", response.getErrors().get(0).getErrorMessage());

   }

   @Test
   public void testValidatePlayByPlayRequest_error_StartDateIsAfterCurrentDate()
      throws Exception
   {
      RequestContext request = new RequestContext();
      LocalDate localDate = LocalDate.now();
      LocalDate startDate = LocalDate.of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth()+1);
      request.setStartDate(startDate);

      ResponseContext response = requestValidator.validatePlayByPlayRequest(request);

      assertTrue(response.hasResponseErrors());
      assertEquals("Start date is after current date", response.getErrors().get(0).getErrorMessage());

   }

   @Test
   public void testValidatePlayByPlayRequest_error_EventTypeIsNull()
      throws Exception
   {
      RequestContext request = new RequestContext();
      request.setStartDate(LocalDate.now());
      request.setEventType(null);

      ResponseContext response = requestValidator.validatePlayByPlayRequest(request);

      assertTrue(response.hasResponseErrors());
      assertEquals("Event type is null", response.getErrors().get(0).getErrorMessage());

   }

   public void testValidatePlayByPlayRequest_happyPath()
      throws Exception
   {
      RequestContext request = new RequestContext();
      request.setStartDate(LocalDate.now());
      request.setEventType("NCAABB");

      ResponseContext response = requestValidator.validatePlayByPlayRequest(request);

      assertFalse(response.hasResponseErrors());

   }
}
