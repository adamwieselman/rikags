package com.rickags.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RequestContext implements Serializable
{
   private LocalDate startDate;
   private String eventType;
   List<TeamEspnPlayByPlay> teamEspnPlayByPlayList;

   public RequestContext(){
      this.teamEspnPlayByPlayList = new ArrayList<>();
   }

   public String getEventType ()
   {
      return eventType;
   }

   public void setEventType (String eventType)
   {
      this.eventType = eventType;
   }


   public LocalDate getStartDate ()
   {
      return startDate;
   }

   public void setStartDate (LocalDate startDate)
   {
      this.startDate = startDate;
   }

}
