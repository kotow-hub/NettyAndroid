/*
 * Copyright (c) 2016.
 * chinaume@163.com
 */

package com.goav.netty.message;

import java.io.Serializable;

/**
 * Live$.<br/>
 *
 * @time: 16/10/8 11:07.<br/>
 * @author: Created by moo<br/>
 */

public class MessageSuper implements Serializable {

    private int type = -1;
    private int code;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public byte[] read() {
        return Gson.newInstances().toJson(this).getBytes();
    }

    @Override
    public String toString() {
        return Gson.newInstances().toJson(this);
    }

    public MessageSuper write(byte[] bytes) {
        return Gson.newInstances().fromJson(new String(bytes), getClass());
    }


}
