package com.goav.netty.Impl

import io.netty.channel.ChannelPipeline
import io.netty.channel.socket.SocketChannel

/**
 * Copyright (c) 2017.
 * chinaume@163.com
 */
interface ChannelConnectImpl {

    fun addChannelHandler(pipeline: ChannelPipeline): ChannelPipeline

    fun onConnectCallBack(sc: SocketChannel?)


    object DEFAULT : ChannelConnectImpl {
        override fun onConnectCallBack(sc: SocketChannel?) {
            //        throw UnsupportedOperationException()
        }

        override fun addChannelHandler(pipeline: ChannelPipeline): ChannelPipeline = pipeline

    }

}

