package com.rickags.services;

import java.io.IOException;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.rickags.beans.RequestContext;
import com.rickags.beans.ResponseContext;

public interface ScheduleDataUpdateService
{
   boolean supports(String eventType);

   void pullAllScheduleDataInformation (RequestContext request,
                                        ResponseContext response)
      throws IOException, UnirestException;
}
