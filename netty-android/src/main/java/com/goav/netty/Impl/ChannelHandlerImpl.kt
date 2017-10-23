package com.goav.netty.Impl

import io.netty.channel.ChannelHandlerContext

/**
 * Copyright (c) 2017.
 * chinaume@163.com
 */
interface ChannelHandlerImpl {

    fun channelRead(ctx: ChannelHandlerContext, msg: ByteArray): Boolean

}