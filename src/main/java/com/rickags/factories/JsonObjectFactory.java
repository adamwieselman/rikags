package com.rickags.factories;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class JsonObjectFactory {
    public JSONObject createJSONObject(String websiteDataString) throws Exception {
        return new JSONObject(websiteDataString);
    }

    public JSONObject createJSONObject() throws Exception {
        return new JSONObject();
    }

    public JSONArray createJSONArray(){
        return new JSONArray();
    }

}
