package com.rickags.directors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rickags.beans.RequestContext;
import com.rickags.beans.ResponseContext;
import com.rickags.factories.PlayByPlayDataOrganizerServiceFactory;
import com.rickags.factories.ScheduleDataUpdateServiceFactory;
import com.rickags.services.PlayByPlayDataUpdateService;
import com.rickags.services.ScheduleDataUpdateService;
import com.rickags.validators.RequestValidator;

@Component
public class DataRetrievalDirector
{

    @Autowired
    private RequestValidator requestValidator;

    @Autowired
    private ScheduleDataUpdateServiceFactory scheduleDataUpdateServiceFactory;

    @Autowired
    private PlayByPlayDataOrganizerServiceFactory playByPlayDataOrganizerServiceFactory;

    public ResponseContext processScheduleRequest (RequestContext request)
       throws Exception
    {
        ResponseContext response = requestValidator.validateScheduleRequest(request);

        if (!response.hasResponseErrors()) {
            ScheduleDataUpdateService scheduleDataUpdateService = scheduleDataUpdateServiceFactory.createScheduleDataUpdateService(request.getEventType());
            scheduleDataUpdateService.pullAllScheduleDataInformation(request, response);
        }

        return response;
    }


    public ResponseContext processPlayByPlayRequest (RequestContext request)
       throws Exception
    {
        ResponseContext response = requestValidator.validatePlayByPlayRequest(request);

        if(!response.hasResponseErrors()) {
            PlayByPlayDataUpdateService playByPlayDataUpdateService = playByPlayDataOrganizerServiceFactory.createPlayByPlayDataOrganizerService(request.getEventType());
            playByPlayDataUpdateService.pullAllPlayByPlayDataInformation(request, response);
        }

        return response;
    }
}
