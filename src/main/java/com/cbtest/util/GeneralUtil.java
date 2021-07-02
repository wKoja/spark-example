package com.cbtest.util;

import java.lang.reflect.Field;

public class GeneralUtil {
    public static <T> boolean isEmpty(T object){
        for (Field field : object.getClass().getDeclaredFields()){
            try{
                field.setAccessible(true);
                if(field.get(object) != null){
                    return false;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return true;
    };

}
