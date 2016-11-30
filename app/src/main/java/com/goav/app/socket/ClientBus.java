/*
 * Copyright (c) 2016.
 * chinaume@163.com
 */

package com.goav.app.socket;

import android.content.Context;

import com.goav.netty.Handler.ClientImpl;
import com.goav.netty.Handler.ClientNetWorkIml;

/**
 * @time: 16/10/13 17:39.<br/>
 * @author: Created by moo<br/>
 */

public final class ClientBus {

    public static void Initialization(Context context, final String unique, String host, int port) {
        ClientImpl.newInstances()
                .bind(host, port)
                .addResponseListener(new LoginHandler(unique))
//                .addResponseListener(new HeartHandler())
                .addResponseListener(new ResponseHandler())
                .Initialization();

        ClientNetWorkIml.newInstance().startListener(context);

    }

    public static void request(Object messageSuper) {
        ClientImpl.newInstances().request(messageSuper);
    }

    public static void Recycle() {
        ClientNetWorkIml.newInstance().onDestroy();
        ClientImpl.newInstances().onDestroy();
    }

}
