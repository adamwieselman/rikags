package com.rickags.services;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;

@Component
public class UnirestApiServiceHelper{

    public GetRequest generateGetRequest(String url, Map<String, String> headerData, Map<String, Object> queryStringData) {

        if(url == null){
            return null;
        }

        GetRequest getRequest = Unirest.get(url);

        if(headerData != null){
            getRequest.headers(headerData);
        }

        if(queryStringData != null){
            getRequest.queryString(queryStringData);
        }

        return getRequest;
    }
}
