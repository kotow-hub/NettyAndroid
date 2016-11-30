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

public class MessageLogin extends MessageSuper {

    private final String alias;
    private String title;
    private String action; //用户打开app后的动作

    public MessageLogin(String tag) {
        this.alias = tag;
        setType(Constants.TYPE.REQUEST.LOGIN);
    }

    public static MessageLogin create(String tag) {
        return new MessageLogin(tag);
    }

}
