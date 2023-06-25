package com.rickags.mappers;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Component
public class JsonMapper implements com.mashape.unirest.http.ObjectMapper   {

    private ObjectMapper jsonMapper;

    public JsonMapper (){
        jsonMapper = new ObjectMapper();
        jsonMapper.registerModule(new JavaTimeModule());
        jsonMapper.disable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES);
        jsonMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        jsonMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        jsonMapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
        jsonMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        jsonMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    public <T> String serializeResponseToJson(T t){
        try {
            return jsonMapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T deserializeResponse(String json, String path, Class<T> resultClass)  {
        try {
            return getNodeValue(json, path, resultClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T getNodeValue(String json, String path, Class<T> resultClass) throws IOException {
        return jsonMapper.readerFor(resultClass).readValue(jsonMapper.readTree(json).at(path));
    }

    public <T> T readValue(String value, Class<T> valueType){
            return deserializeResponse(value, "", valueType);
    }

    public String writeValue(Object value) {
        return serializeResponseToJson(value);
    }
}
