/*
 * Copyright (c) 2016.
 * chinaume@163.com
 */

package com.goav.netty.Handler


import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder

/**
 * 接收解析器
 *
 * @time: 16/10/8 12:14.<br></br>
 * @author: Created by moo<br></br>
 */

internal class DecodeHandler : ByteToMessageDecoder() {


    /**
     * @param ctx
     * @param in
     * @param out
     * @throws Exception
     * @see EncodeHandler.encode
     */
    @Throws(Exception::class)
    override fun decode(ctx: ChannelHandlerContext, `in`: ByteBuf, out: MutableList<Any>) {

        try {

            if (`in`.isReadable) {

                if (`in`.readableBytes() > 4) {

                    `in`.markReaderIndex()
                    val dataLength = `in`.readInt() - 4
                    if (dataLength <= 0 || `in`.readableBytes() < dataLength) {
                        `in`.resetReaderIndex()
                        return
                    }

                    val bytes = ByteArray(dataLength)
                    `in`.readBytes(bytes)
                    out.add(bytes)
                }
            }
        } catch (e: Exception) {
            System.out.println(e.message)
        }

    }
}
