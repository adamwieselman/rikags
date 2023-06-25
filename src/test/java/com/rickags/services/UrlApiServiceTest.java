package com.rickags.services;

import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.rickags.mappers.JsonMapper;

public class UrlApiServiceTest
{

   @Rule
   public MockitoRule rule = MockitoJUnit.rule();

   @Mock
   public UnirestApiServiceHelper unirestApiServiceHelper;

   @Mock
   public GetRequest getRequest;

   @Mock
   public JsonMapper jsonMapperFake;

   @Mock
   public HttpResponse<String> stringHttpResponse;

   @InjectMocks
   public UnirestApiService unirestApiService = new UnirestApiService();

   @Test
   public void getWebsiteDataString ()
      throws UnirestException
   {

      Mockito.when(unirestApiServiceHelper.generateGetRequest("url", null, null)).thenReturn(getRequest);
      Mockito.when(getRequest.asString()).thenReturn(stringHttpResponse);
      unirestApiService.getWebsiteDataString("url", null, null);
      Mockito.verify(unirestApiServiceHelper).generateGetRequest("url", null, null);

   }

   @Test
   public void getWebsiteDataStringNull ()
      throws UnirestException
   {

      Mockito.when(unirestApiServiceHelper.generateGetRequest("url", null, null)).thenReturn(null);
      String result = unirestApiService.getWebsiteDataString("url", null, null);
      Mockito.verify(unirestApiServiceHelper).generateGetRequest("url", null, null);
      assertNull(result);
   }

   @Test
   public void getWebsiteDataObject ()
      throws UnirestException
   {

      Mockito.when(unirestApiServiceHelper.generateGetRequest("url", null, null)).thenReturn(getRequest);
      Mockito.when(getRequest.asObject(String.class)).thenReturn(stringHttpResponse);
      String result = unirestApiService.getWebsiteDataObject("url", null, null, String.class);
      Mockito.verify(unirestApiServiceHelper).generateGetRequest("url", null, null);
   }

   @Test
   public void getWebsiteDataObjectNull ()
      throws UnirestException
   {

      Mockito.when(unirestApiServiceHelper.generateGetRequest("url", null, null)).thenReturn(null);
      Mockito.when(getRequest.asObject(String.class)).thenReturn(stringHttpResponse);
      String result = unirestApiService.getWebsiteDataObject("url", null, null, String.class);
      Mockito.verify(unirestApiServiceHelper).generateGetRequest("url", null, null);
      assertNull(result);
   }

   @Test
   public void startupShutdown()
      throws IOException
   {
      unirestApiService.jsonMapper = jsonMapperFake;
      unirestApiService.setUpUnirest();
      unirestApiService.shutdownUnirest();
   }
}
