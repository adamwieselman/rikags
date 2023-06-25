package com.rickags.mappers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.io.IOException;

import org.junit.Test;
import org.mockito.InjectMocks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rickags.testclasses.TestClass;

public class JsonMapperTest
{

    @InjectMocks
    JsonMapper jsonMapper = new JsonMapper();

    @Test
    public void testSerialize(){

        TestClass testClass = new TestClass();
        testClass.setGame("person");
        testClass.setPlace("place");
        String json = jsonMapper.serializeResponseToJson(testClass);

        assertEquals("{\"game\":\"person\",\"place\":\"place\"}", json);
    }

    @Test
    public void testSerialize_nullClass_shouldReturnNullString(){
        TestClass testClass = null;
        String json = jsonMapper.serializeResponseToJson(testClass);
        assertEquals("null", json);
    }

    @SuppressWarnings("serial")
    @Test
    public void testSerialize_throwsException_shouldThrowRuntimeException() throws Exception {
        ObjectMapper mapper = mock(ObjectMapper.class);
        TestClass testClass = mock(TestClass.class);

        given(mapper.writeValueAsString(testClass)).willThrow(new JsonProcessingException("test"){});

        try{
            jsonMapper.serializeResponseToJson(testClass);
            fail();
        }catch(RuntimeException e){
            assertTrue(e.getCause() instanceof JsonProcessingException   );
        }
    }

    @Test
    public void testDeserialize_badJson_shouldThrowIOException(){
        String json = "this won't work at all";

        try{
            jsonMapper.deserializeResponse(json, "", TestClass.class);
            fail();
        }catch(RuntimeException runtimeException){
            assertTrue(runtimeException.getCause() instanceof IOException);
        }
    }

    @Test
    public void testDeserialize_goodJson_shouldReturnGoodObjects(){
        String matchingJson = "{\"game\":\"this game\",\"place\":\"this name\"}";
        String notMatchingEverythingJson = "{\"game\":\"only game\"}";
        String moreThanMatchingEverythingJson = "{\"bigGame\":\"not just game\",\"crap\":\"crap value\",\"place\":\"not just name\"}";

        try {
            TestClass testClassMatching = jsonMapper.deserializeResponse(matchingJson, "", TestClass.class);
            TestClass testClassNotMatchingEverything = jsonMapper.deserializeResponse(notMatchingEverythingJson, "", TestClass.class);
            TestClass testClassMatchingMoreThanEverything = jsonMapper.deserializeResponse(moreThanMatchingEverythingJson, "", TestClass.class);

            assertEquals("this game", testClassMatching.getGame());
            assertEquals("this name", testClassMatching.getPlace());
            assertEquals("only game", testClassNotMatchingEverything.getGame());
            assertEquals(null, testClassNotMatchingEverything.getPlace());
            assertEquals("not just game", testClassMatchingMoreThanEverything.getGame());
            assertEquals("not just name", testClassMatchingMoreThanEverything.getPlace());
        }catch(RuntimeException re){
            fail();
        }
    }

    @Test
    public void testWriteValue(){
        TestClass testClass = new TestClass();
        testClass.setGame("game");
        testClass.setPlace("place");
        String objectString = jsonMapper.writeValue(testClass);
        assertEquals("{\"game\":\"game\",\"place\":\"place\"}", objectString);
    }

    @Test
    public void testReadValue(){
        String setup = "{\"game\":\"game\",\"place\":\"place\"}";
        TestClass testClass = jsonMapper.readValue(setup, TestClass.class);
        assertEquals("game", testClass.getGame());
        assertEquals("place", testClass.getPlace());
    }

    @Test
    public void testReadValueWithLists(){
        String setup = "{\"game\":\"game\",\"place\":\"place\",\"events\":[{\"id\":\"1\"},{\"id\":\"3\"}]}";
        TestClass testClass = jsonMapper.readValue(setup, TestClass.class);
        assertEquals("game", testClass.getGame());
        assertEquals("place", testClass.getPlace());
    }
}
