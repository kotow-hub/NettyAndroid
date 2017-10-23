package com.goav.netty.message

import com.google.gson.GsonBuilder

import java.lang.reflect.Modifier

/**
 * Created by moo on 16/10/8.
 */
object GsonHelper {

    private var gson: com.google.gson.Gson? = null

    init {
        gson = GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.PROTECTED) //@protected 修饰的过滤，比如自动增长的id
                .create()
    }


    fun newInstances(): com.google.gson.Gson {
        return gson!!
    }

}
