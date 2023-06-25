package com.rickags.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.mashape.unirest.request.GetRequest;

public class UrlApiServiceHelperTest
{

   @Rule
   public MockitoRule rule = MockitoJUnit.rule();

   @InjectMocks
   public UnirestApiServiceHelper unirestApiServiceHelper = new UnirestApiServiceHelper();

   @Test
   public void testGetRequest_urlNull(){

      assertNull(unirestApiServiceHelper.generateGetRequest(null, null, null));
   }

   @Test
   public void testGetRequest_headerAndQueryNull(){
      GetRequest getRequest =  unirestApiServiceHelper.generateGetRequest("google.com", null, null);
      assertEquals(0, getRequest.getHeaders().size());
   }

   @Test
   public void testGetRequest_headerAndQueryPopulated(){
      Map<String, String> headerData = new HashMap<>();
      headerData.put("foo","bar");
      Map<String, Object> queryData = new HashMap<>();
      queryData.put("bar","foo");
      GetRequest getRequest =  unirestApiServiceHelper.generateGetRequest("google.com", headerData, queryData);
      assertEquals(1, getRequest.getHeaders().size());
      assertEquals("google.com?bar=foo", getRequest.getUrl());
      assertEquals("bar", getRequest.getHeaders().get("foo").get(0));
   }

}
