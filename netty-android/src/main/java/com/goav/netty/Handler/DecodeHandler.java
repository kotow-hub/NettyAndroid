/*
 * Copyright (c) 2016.
 * chinaume@163.com
 */

package com.goav.netty.Handler;


import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * 接收解析器
 *
 * @time: 16/10/8 12:14.<br/>
 * @author: Created by moo<br/>
 */

public class DecodeHandler extends ByteToMessageDecoder {


    /**
     * @param ctx
     * @param in
     * @param out
     * @throws Exception
     * @see EncodeHandler#encode(ChannelHandlerContext, MessageSuper, ByteBuf)
     * @see ChannelHandlerSuper#channelRead(ChannelHandlerContext, Object)
     * @see ResponseChannelHandler#channelRead(ChannelHandlerContext, Object)
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        try {

            if (in.isReadable()) {

                if (in.readableBytes() > 4) {

                    in.markReaderIndex();
                    int dataLength = in.readInt();
                    if (dataLength <= 0 || in.readableBytes() < dataLength) {
                        in.resetReaderIndex();
                        return;
                    }

                    byte[] bytes = new byte[dataLength];
                    in.readBytes(bytes);
                    out.add(bytes);
                }
            }
        } catch (Exception e) {

        }
    }
}
