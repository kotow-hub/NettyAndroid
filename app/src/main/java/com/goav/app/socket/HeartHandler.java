/*
 * Copyright (c) 2016.
 * chinaume@163.com
 */

package com.goav.app.socket;

import com.goav.netty.Impl.ResponseListener;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.netty.channel.ChannelHandlerContext;

/**
 * Live$.<br/>
 *
 * @time: 16/10/8 18:13.<br/>
 * @author: Created by moo<br/>
 * @see LoginHandler#ObservableNotify(ChannelHandlerContext, JsonElement)
 * @deprecated
 */

@Deprecated
public class HeartHandler implements ResponseListener<JsonObject> {


    @Override
    public boolean channelRead(ChannelHandlerContext ctx, JsonObject msg) throws Exception {
//        if (response.getType() == Constants.TYPE.REQUEST.PING) {
//            ctx.executor().schedule(() ->
//                    ctx.writeAndFlush(MessageHeart.create("android", MyState.get().getIdentify())), 4, TimeUnit.SECONDS);
//            return true;
//        }

        return false;
    }
}
