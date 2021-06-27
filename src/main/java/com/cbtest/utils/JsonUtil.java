package com.cbtest.utils;

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
        final byte[] data = output.toByteArray();

        return new String(data);
    }

    public static String hashMapToJson(Map<?,?> map) throws IOException{
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        final ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(output, map);
        final byte[] data = output.toByteArray();

        return new String(data);
    }

    public static <T> String getMapJson(QueryParamsMap queryMap) throws IOException{
        Map<String, String[]> map = queryMap.toMap();
        List<Map<String, String[]>> mapList = new ArrayList<Map<String, String[]>>();

        mapList.add(map);

        String mapJson = listToJson(mapList);

        return mapJson;
    }

}
