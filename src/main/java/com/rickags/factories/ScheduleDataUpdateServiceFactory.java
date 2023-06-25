package com.rickags.factories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rickags.services.ScheduleDataUpdateService;

@Component
public class ScheduleDataUpdateServiceFactory
{
   @Autowired
   protected List<ScheduleDataUpdateService> scheduleDataUpdateServices;

   public ScheduleDataUpdateService createScheduleDataUpdateService (String eventType)
   {
      for(ScheduleDataUpdateService scheduleDataUpdateService: scheduleDataUpdateServices){
         if(scheduleDataUpdateService.supports(eventType)){
            return scheduleDataUpdateService;
         }
      }

      return null;
   }
}
