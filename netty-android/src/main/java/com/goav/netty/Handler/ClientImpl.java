/*
 * Copyright (c) 2016.
 * chinaume@163.com
 */

package com.goav.netty.Handler;

import com.goav.netty.Impl.ConnectCallBack;
import com.goav.netty.Impl.ResponseListener;
import com.goav.netty.message.MessageSuper;
import com.google.gson.JsonElement;

/**
 * @time: 16/10/9 09:55.<br/>
 * @author: Created by moo<br/>
 */
public abstract class ClientImpl implements ConnectCallBack {

    public abstract void Initialization();

    /**
     * push message to server.
     *
     * @param message {@link MessageSuper}
     */
    public abstract void request(Object message);

    /**
     * @return Connected state about Client 2 Server.
     */
    public abstract boolean getConnectState();

    /**
     * restart connected or false.
     *
     * @param restart will restart or false.
     */
    protected abstract void restart(boolean restart);


    protected abstract void close();

    /**
     * over & recycle
     */
    public abstract void onDestroy();

    /**
     * use before {@link #Initialization()},after {@link #addResponseListener(ResponseChannelHandler)}
     *
     * @param response
     * @return ClientImpl
     */
    public abstract ClientImpl addResponseListener(ResponseListener<? super JsonElement> response);

    /**
     * use before {@link #Initialization()}
     *
     * @param response
     * @return ClientImpl
     */
    public abstract ClientImpl addResponseListener(ResponseChannelHandler response);


    /**
     * {@inheritDoc}
     */
    public abstract ClientImpl bind(String host, int port);

    /**
     * @return reference the instance of {@link ClientImpl}
     */
    public static ClientImpl newInstances() {
        return Client.newInstances();
    }

}
