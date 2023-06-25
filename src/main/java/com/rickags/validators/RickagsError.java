package com.rickags.validators;

import java.io.Serializable;

public class RickagsError implements Serializable
{
   private String errorMessage;

   public RickagsError (String errorMessage)
   {
      this.errorMessage = errorMessage;
   }

   public String getErrorMessage ()
   {
      return errorMessage;
   }

   public void setErrorMessage (String errorMessage)
   {
      this.errorMessage = errorMessage;
   }
}
