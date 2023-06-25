package com.rickags.beans;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class RequestContextTest
{

   @Rule
   public MockitoRule rule = MockitoJUnit.rule();

   @InjectMocks
   public RequestContext requestContext = new RequestContext();

   @Test
   public void testRequestContextBean(){
      LocalDate startDate = LocalDate.now();
      RequestContext requestContext = new RequestContext();
      requestContext.setEventType("dumbstuff");
      requestContext.setStartDate(startDate);

      assertEquals(startDate, requestContext.getStartDate());
      assertEquals("dumbstuff", requestContext.getEventType());
   }

}
