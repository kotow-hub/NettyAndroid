/*
 * Copyright (c) 2016.
 * chinaume@163.com
 */

package com.goav.netty.Handler;


import com.goav.netty.message.Gson;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 发送编码器
 *
 * @time: 16/10/8 12:14.<br/>
 * @author: Created by moo<br/>
 */

public class EncodeHandler extends MessageToByteEncoder<Object> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        try {
            byte[] bytes = Gson.newInstances().toJson(msg).getBytes();
            out.writeInt(bytes.length);//body长度
            out.writeBytes(bytes);
        } catch (Exception e) {

        }
    }

}
