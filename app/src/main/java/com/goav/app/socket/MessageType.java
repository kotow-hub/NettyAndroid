/*
 * Copyright (c) 2016.
 * chinaume@163.com
 */

package com.goav.app.socket;


import com.goav.netty.message.MessageSuper;

/**
 * @time: 16/10/13 16:44.<br/>
 * @author: Created by moo<br/>
 */

public class MessageType extends MessageSuper {

    public MessageType(int type) {
        setType(type);
    }

    public static MessageType create(int type) {
        return new MessageType(type);
    }

}
