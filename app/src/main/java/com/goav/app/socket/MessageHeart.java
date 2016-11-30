/*
 * Copyright (c) 2016.
 * chinaume@163.com
 */

package com.goav.app.socket;


import com.goav.netty.message.MessageSuper;

/**
 * Live$.<br/>
 *
 * @time: 16/10/8 18:04.<br/>
 * @author: Created by moo<br/>
 */

public class MessageHeart extends MessageSuper {

    private final String tag;
    private final String ua;

    public MessageHeart(String tag, String ua) {
        this.tag = tag;
        this.ua = ua;
        setType(Constants.TYPE.REQUEST.PING);
    }

    public static MessageHeart create(String tag, String ua) {
        return new MessageHeart(tag, ua);
    }

}
