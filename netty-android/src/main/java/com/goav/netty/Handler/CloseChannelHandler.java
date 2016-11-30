/*
 * Copyright (c) 2016.
 * chinaume@163.com
 */

package com.goav.netty.Handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

/**
 * @time: 16/10/14 11:16.<br/>
 * @author: Created by moo<br/>
 */

public class CloseChannelHandler extends ChannelHandlerSuper {

    private final ClientImpl client;

    public CloseChannelHandler(ClientImpl client) {
        this.client = client;
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        this.client.restart(true);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        this.client.restart(true);
    }

    @Override
    public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        super.close(ctx, promise);
        this.client.close();
    }
}
