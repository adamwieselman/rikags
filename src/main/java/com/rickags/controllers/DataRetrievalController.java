package com.rickags.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rickags.beans.RequestContext;
import com.rickags.beans.ResponseContext;
import com.rickags.directors.DataRetrievalDirector;

@RestController
public class DataRetrievalController
{
    @Autowired
    private DataRetrievalDirector dataRetrievalDirector;

    public static final String APPLICATION_JSON = "application/json";

    @PostMapping(path = "v1.0/update/schedule/", produces = APPLICATION_JSON )
    public ResponseEntity<ResponseContext> updateScheduleData(@RequestBody RequestContext request) throws Exception {

        ResponseContext response = dataRetrievalDirector.processScheduleRequest(request);

        if(response.hasResponseErrors()){
            return new ResponseEntity<ResponseContext>(response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ResponseContext> (response, HttpStatus.OK);
    }

    @PostMapping(path = "v1.0/update/playbyplay/", produces = APPLICATION_JSON )
    public ResponseEntity<ResponseContext> updatePlayByPlayData(@RequestBody RequestContext request) throws Exception {

        ResponseContext response = dataRetrievalDirector.processPlayByPlayRequest(request);

        if(response.hasResponseErrors()){
            return new ResponseEntity<ResponseContext>(response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ResponseContext> (response, HttpStatus.OK);
    }
}

