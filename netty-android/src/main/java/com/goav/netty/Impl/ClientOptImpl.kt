package com.goav.netty.Impl

import com.goav.netty.Handler.ReConnect

/**
 * Created by moo on 20/10/2017.
 */
interface ClientOptImpl {

    fun reConnect(reConnect: ReConnect): ClientOptImpl

    fun address(host: String, port: Int): ClientOptImpl

    fun addTimeOut(readerIdleTimeSeconds: Int, writerIdleTimeSeconds: Int, allIdleTimeSeconds: Int): ClientOptImpl

    fun addCallBack(callBack: ChannelConnectImpl): ClientOptImpl

    fun build(): ClientImpl

}