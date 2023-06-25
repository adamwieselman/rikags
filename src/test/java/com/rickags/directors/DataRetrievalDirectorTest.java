package com.rickags.directors;

import static org.mockito.Mockito.mock;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.rickags.beans.RequestContext;
import com.rickags.beans.ResponseContext;
import com.rickags.factories.PlayByPlayDataOrganizerServiceFactory;
import com.rickags.factories.ScheduleDataUpdateServiceFactory;
import com.rickags.services.PlayByPlayDataUpdateService;
import com.rickags.services.ScheduleDataUpdateService;
import com.rickags.validators.RequestValidator;
import com.rickags.validators.RickagsError;

public class DataRetrievalDirectorTest
{
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private RequestValidator requestValidator;

    @Mock
    private ScheduleDataUpdateServiceFactory scheduleDataUpdateServiceFactory;

    @Mock
    private PlayByPlayDataOrganizerServiceFactory playByPlayDataOrganizerServiceFactory;

    @InjectMocks
    public DataRetrievalDirector dataRetrievalDirector = new DataRetrievalDirector();

    @Test
    public void testProcessScheduleRequest_hasErrors()
       throws Exception
    {
        ResponseContext response = new ResponseContext();
        response.getErrors().add(new RickagsError("error"));

        Mockito.when(requestValidator.validateScheduleRequest(Mockito.any(RequestContext.class))).thenReturn(response);
        dataRetrievalDirector.processScheduleRequest(new RequestContext());

        Mockito.verifyZeroInteractions(scheduleDataUpdateServiceFactory);
    }

    @Test
    public void testProcessScheduleRequest_happyPath()
       throws Exception
    {
        ResponseContext response = new ResponseContext();
        RequestContext request = new RequestContext();
        request.setEventType("NCAABB");
        ScheduleDataUpdateService scheduleDataUpdateService = mock(ScheduleDataUpdateService.class);

        Mockito.when(requestValidator.validateScheduleRequest(Mockito.any(RequestContext.class))).thenReturn(response);
        Mockito.when(scheduleDataUpdateServiceFactory.createScheduleDataUpdateService("NCAABB")).thenReturn(scheduleDataUpdateService);

        dataRetrievalDirector.processScheduleRequest(request);

        Mockito.verify(scheduleDataUpdateService).pullAllScheduleDataInformation(request, response);

    }


    @Test
    public void testProcessPlayByPlayRequest_hasErrors()
       throws Exception
    {
        ResponseContext response = new ResponseContext();
        response.getErrors().add(new RickagsError("error"));

        Mockito.when(requestValidator.validatePlayByPlayRequest(Mockito.any(RequestContext.class))).thenReturn(response);
        dataRetrievalDirector.processPlayByPlayRequest(new RequestContext());

        Mockito.verifyZeroInteractions(playByPlayDataOrganizerServiceFactory);
    }

    @Test
    public void testProcessPlayByPlayRequest_happyPath()
       throws Exception
    {
        ResponseContext response = new ResponseContext();
        RequestContext request = new RequestContext();
        request.setEventType("NCAABB");
        PlayByPlayDataUpdateService playByPlayDataUpdateService = mock(PlayByPlayDataUpdateService.class);

        Mockito.when(requestValidator.validatePlayByPlayRequest(Mockito.any(RequestContext.class))).thenReturn(response);
        Mockito.when(playByPlayDataOrganizerServiceFactory.createPlayByPlayDataOrganizerService("NCAABB")).thenReturn(playByPlayDataUpdateService);

        dataRetrievalDirector.processPlayByPlayRequest(request);

        Mockito.verify(playByPlayDataUpdateService).pullAllPlayByPlayDataInformation(request, response);

    }

}
