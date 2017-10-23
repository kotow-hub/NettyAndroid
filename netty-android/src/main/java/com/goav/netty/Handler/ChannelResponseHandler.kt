package com.goav.netty.Handler

import com.goav.netty.Impl.ChannelHandlerImpl
import com.goav.netty.Impl.ChannelReleaseImpl
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import java.util.*

/**
 * Copyright (c) 2017.
 * chinaume@163.com
 */
public class ChannelResponseHandler : ChannelInboundHandlerAdapter(), ChannelReleaseImpl {

    private val mHandlerArray: ArrayList<ChannelHandlerImpl>;

    init {
        mHandlerArray = ArrayList<ChannelHandlerImpl>();
    }


    fun addChannelResponseHandler(channelHandlerImpl: ChannelHandlerImpl?) {
        if (channelHandlerImpl != null) {
            mHandlerArray.add(channelHandlerImpl)
        }
    }

    /**
     * dispath the message
     */
    override fun channelRead(ctx: ChannelHandlerContext, msg: Any?) {

        if (msg != null) {
            val response = msg as ByteArray
            if (mHandlerArray.any { it.channelRead(ctx, response) }) {
                return
            }
        }

        //nobody receiver
        super.channelRead(ctx, msg)
    }

    override fun destroy() {
        mHandlerArray.clear();
    }

}