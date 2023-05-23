package org.j2os.common;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonProvider {
    private JsonProvider(){}
    public  static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static String getJson(Object o){
        try {
            return OBJECT_MAPPER.writeValueAsString(o);
        } catch (Exception e) {
            throw new RuntimeException("");
        }
    }
}
