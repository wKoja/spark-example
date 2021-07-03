package com.cbtest.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import spark.QueryParamsMap;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonUtil {

    public static <T> String listToJson(List<T> list) throws IOException {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        final ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(output, list);

        return output.toString();
    }

    public static <T> String objectToJson(T object) throws IOException{

        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        final ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(output, object);

        return output.toString();
    }

    public static <T> T jsonToClass(String jsonString, Class<T> type) throws IOException{
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(jsonString, type);
    }

    //trying some stuff out, didn't actually put any work into this
    public static String hashMapToJson(Map<?,?> map) throws IOException{
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        final ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(output, map);

        return output.toString();
    }

    public static <T> String getMapJson(QueryParamsMap queryMap) throws IOException{
        Map<String, String[]> map = queryMap.toMap();
        List<Map<String, String[]>> mapList = new ArrayList<>();

        mapList.add(map);

        return listToJson(mapList);
    }

}
