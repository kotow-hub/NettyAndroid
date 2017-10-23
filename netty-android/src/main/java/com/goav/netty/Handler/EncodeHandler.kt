/*
 * Copyright (c) 2016.
 * chinaume@163.com
 */

package com.goav.netty.Handler


import com.goav.netty.message.MessageBasic
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder

/**
 * 发送编码器
 *
 * @time: 16/10/8 12:14.<br></br>
 * @author: Created by moo<br></br>
 */

internal class EncodeHandler : MessageToByteEncoder<MessageBasic>() {
    @Throws(Exception::class)
    override fun encode(ctx: ChannelHandlerContext, msg: MessageBasic, out: ByteBuf) {
        try {
            val bytes = msg.read();
            val size = bytes.size
            out.writeInt(size + 4)//body长度
            out.writeBytes(bytes)
            System.out.println("size=" + size)
        } catch (e: Exception) {
            System.out.println(e.message)
        }
    }

}
