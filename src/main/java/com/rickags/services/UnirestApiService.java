package com.rickags.services;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.rickags.mappers.JsonMapper;

@Service
public class UnirestApiService {

    @Autowired
    public UnirestApiServiceHelper unirestApiServiceHelper;

    @Autowired
    public JsonMapper jsonMapper;

    public String getWebsiteDataString(String url, Map<String, String> headerData, Map<String, Object> queryStringData) throws UnirestException {

        GetRequest getRequest = unirestApiServiceHelper.generateGetRequest(url, headerData, queryStringData);
        if (getRequest != null) {
            return getRequest.asString().getBody();
        }

        return null;

    }

    public <T> T getWebsiteDataObject(String url, Map<String, String> headerData, Map<String, Object> queryStringData, Class<T> resultClass) throws UnirestException {

        GetRequest getRequest = unirestApiServiceHelper.generateGetRequest(url, headerData, queryStringData);
        if (getRequest != null) {
            return getRequest.asObject(resultClass).getBody();
        }

        return null;
    }

    public void setUpUnirest(){
        Unirest.setObjectMapper(jsonMapper);
    }

    public void shutdownUnirest() throws IOException {
        Unirest.shutdown();
    }
}