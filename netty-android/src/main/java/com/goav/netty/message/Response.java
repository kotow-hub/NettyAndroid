/*
 * Copyright (c) 2016.
 * chinaume@163.com
 */

package com.goav.netty.message;

import java.io.Serializable;

/**
 * @date: 16/11/1 15:50.<br/>
 * @author: Created by moo<br/>
 */

public class Response implements Serializable {
    private int type;
    private int code;
    private byte reply;


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

    public byte getReply() {
        return reply;
    }

    public void setReply(byte reply) {
        this.reply = reply;
    }
}
