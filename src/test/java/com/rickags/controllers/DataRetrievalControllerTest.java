package com.rickags.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rickags.beans.RequestContext;
import com.rickags.beans.ResponseContext;
import com.rickags.directors.DataRetrievalDirector;
import com.rickags.validators.RickagsError;

public class DataRetrievalControllerTest
{

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    DataRetrievalDirector dataRetrievalDirector;

    @InjectMocks
    public DataRetrievalController dataRetrievalController = new DataRetrievalController();

    @Test
    public void testUpdateScheduleData_errors()
       throws Exception
    {

        RequestContext request = new RequestContext();
        request.setEventType("stuff");

        ResponseContext responseContext = new ResponseContext();
        responseContext.getErrors().add(new RickagsError("error message"));

        when(dataRetrievalDirector.processScheduleRequest(request)).thenReturn(responseContext);

        ResponseEntity<ResponseContext> response =  dataRetrievalController.updateScheduleData(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testUpdateScheduleData_happyPath()
       throws Exception
    {

        RequestContext request = new RequestContext();
        request.setEventType("stuff");

        ResponseContext responseContext = new ResponseContext();

        when(dataRetrievalDirector.processScheduleRequest(request)).thenReturn(responseContext);

        ResponseEntity<ResponseContext> response =  dataRetrievalController.updateScheduleData(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testUpdatePlayByPlayData_errors()
       throws Exception
    {

        RequestContext request = new RequestContext();
        request.setEventType("stuff");

        ResponseContext responseContext = new ResponseContext();
        responseContext.getErrors().add(new RickagsError("error message"));

        when(dataRetrievalDirector.processPlayByPlayRequest(request)).thenReturn(responseContext);

        ResponseEntity<ResponseContext> response =  dataRetrievalController.updatePlayByPlayData(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testUpdatePlayByPlayData_happyPath()
       throws Exception
    {

        RequestContext request = new RequestContext();
        request.setEventType("stuff");

        ResponseContext responseContext = new ResponseContext();

        when(dataRetrievalDirector.processPlayByPlayRequest(request)).thenReturn(responseContext);

        ResponseEntity<ResponseContext> response =  dataRetrievalController.updatePlayByPlayData(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}

