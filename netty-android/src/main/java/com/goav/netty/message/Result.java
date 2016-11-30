/*
 * Copyright (c) 2016.
 * chinaume@163.com
 */

package com.goav.netty.message;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

/**
 * @date: 16/11/1 15:38.<br/>
 * @author: Created by moo<br/>
 */

public class Result {

    public static <T> T getTValue(String key, JsonObject object, Class<T> t) throws JsonSyntaxException {
        JsonElement element = object.get(key);
        return Gson.newInstances().fromJson(element, t);
    }


    public static String getStringValue(String key, JsonObject object) throws JsonSyntaxException {
        return object.get(key).getAsString();
    }

    public static int getIntValue(String key, JsonObject object) throws JsonSyntaxException {
        return object.get(key).getAsInt();
    }

    public static byte getByteValue(String key, JsonObject object) throws JsonSyntaxException {
        return object.get(key).getAsByte();
    }

    public static JsonObject getJsonObject(Object obj) {
        return new JsonParser().parse(Gson.newInstances().toJson(obj)).getAsJsonObject();
    }
}
