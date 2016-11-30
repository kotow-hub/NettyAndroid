/*
 * Copyright (c) 2016.
 * chinaume@163.com
 */

package com.goav.app.socket;

import android.util.Log;

import com.goav.netty.Handler.ClientImpl;
import com.goav.netty.Handler.ResponseChannelHandler;
import com.goav.netty.Impl.PushCallBack;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

/**
 * Live$.<br/>
 *
 * @time: 16/10/8 18:00.<br/>
 * @author: Created by moo<br/>
 */
@ChannelHandler.Sharable
public class LoginHandler extends ResponseChannelHandler {

    private ConcurrentLinkedQueue<Long> concurrentLinkedQueue;
    private final String tag;
    private PushCallBack<Boolean> callback;
    private int count;
    private boolean isHeartStart;

    public LoginHandler(String tag) {
        this.tag = tag;
        concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
        callback = connect -> Log.d("SOCKET_STATE", connect.toString());
    }

    public LoginHandler(String tag, PushCallBack<Boolean> callBack) {
        this(tag);
        this.callback = callBack;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        if (callback != null) {
            callback.Response(ClientImpl.newInstances().getConnectState());
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(MessageLogin.create(tag));
    }


    @Override
    protected boolean ObservableNotify(ChannelHandlerContext ctx, JsonElement msg) throws Exception {
        if (!isHeartStart && msg.isJsonObject()) {
            JsonObject object = (JsonObject) msg;
            int type = object.get(Constants.PARAMS.RESPONSE.TYPE).getAsInt();
            if (type == Constants.TYPE.REQUEST.LOGIN) {
                int code = object.get(Constants.PARAMS.RESPONSE.CODE).getAsInt();
                if (code == Constants.CODE.RESPONSE.SUCCESS) {
                    isHeartStart = true;
                    clear();
                    ctx.executor().scheduleAtFixedRate(() ->
                                    ctx.writeAndFlush(MessageHeart.create("android", tag)),
                            2,
                            4,
                            TimeUnit.SECONDS);
                } else {
                    isHeartStart = false;
                    // FIXME: 16/10/13 登录重试
                    ctx.executor().schedule(() -> {
                                if (count > 3) {// FIXME: 16/10/14 不用size(),耗时
                                    clear();
                                    ctx.close();
                                    return;
                                }
                                ++count;
                                ctx.writeAndFlush(MessageLogin.create(tag));
                                concurrentLinkedQueue.add(System.currentTimeMillis());
                            }
                            , 500, TimeUnit.MILLISECONDS);
                }
                return true;
            }
        }
        return super.ObservableNotify(ctx, msg);
    }


    public void clear() {
        concurrentLinkedQueue.clear();
        count = 0;
    }
}
