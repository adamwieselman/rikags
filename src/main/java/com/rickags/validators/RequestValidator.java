package com.rickags.validators;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.rickags.beans.RequestContext;
import com.rickags.beans.ResponseContext;

@Component
public class RequestValidator
{
   public ResponseContext validateScheduleRequest (RequestContext request)
   {
      ResponseContext response = new ResponseContext();

      if(request.getStartDate()==null) {
         response.getErrors().add(new RickagsError("Start date is null"));
      }else{
         if(request.getStartDate().isAfter(LocalDate.now())) {
            response.getErrors().add(new RickagsError("Start date is after current date"));
         }
      }

      if(request.getEventType()==null){
         response.getErrors().add(new RickagsError("Event type is null"));
      }

      return response;
   }

   public ResponseContext validatePlayByPlayRequest (RequestContext request)
   {
      ResponseContext response = new ResponseContext();

      if(request.getStartDate()==null) {
         response.getErrors().add(new RickagsError("Start date is null"));
      }else{
         if(request.getStartDate().isAfter(LocalDate.now())) {
            response.getErrors().add(new RickagsError("Start date is after current date"));
         }
      }

      if(request.getEventType()==null){
         response.getErrors().add(new RickagsError("Event type is null"));
      }

      return response;
   }
}
