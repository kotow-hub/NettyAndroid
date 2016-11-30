/*
 * Copyright (c) 2016.
 * chinaume@163.com
 */

package com.goav.netty.Impl;


import com.goav.netty.message.MessageResponse;

import io.netty.channel.ChannelHandlerContext;

/**
 * Live$.<br/>
 *
 * @time: 16/10/8 15:30.<br/>
 * @author: Created by moo<br/>
 */

public interface ResponseListener<T> {

    /**
     * 消息接受类型处理
     *
     * @param ctx
     * @param msg {@link MessageResponse}
     * @throws Exception
     */
    boolean channelRead(final ChannelHandlerContext ctx, T msg) throws Exception;

}
