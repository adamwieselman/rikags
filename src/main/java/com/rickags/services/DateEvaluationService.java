package com.rickags.services;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class DateEvaluationService
{

   public String returnSeasonYear (LocalDate startDate)
   {
      if (startDate.getMonthValue() < 5) {
         return String.valueOf(startDate.getYear() - 1);
      } else {
         return String.valueOf(startDate.getYear());
      }
   }
}
