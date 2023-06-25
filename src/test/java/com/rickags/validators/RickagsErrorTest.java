package com.rickags.validators;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RickagsErrorTest
{
   @Test
   public void testRickAgsError()
   {
      RickagsError rickagsError = new RickagsError("Error");
      assertEquals("Error", rickagsError.getErrorMessage());
      rickagsError.setErrorMessage("Different Error");
      assertEquals("Different Error", rickagsError.getErrorMessage());
   }
}
