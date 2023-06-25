package com.rickags.services;

import com.rickags.beans.RequestContext;
import com.rickags.beans.ResponseContext;

public interface PlayByPlayDataUpdateService
{
   boolean supports(String eventType);

   void pullAllPlayByPlayDataInformation (RequestContext request,
                                          ResponseContext response)
      throws Exception;
}
