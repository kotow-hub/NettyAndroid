package com.goav.netty.message;

import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

/**
 * Created by moo on 16/10/8.
 */
public class Gson {

    private static com.google.gson.Gson gson;

    static {
        gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.PROTECTED) //@protected 修饰的过滤，比如自动增长的id
                .create();
    }


    public static com.google.gson.Gson newInstances() {
        return gson;
    }

}
