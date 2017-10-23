package com.goav.netty.Impl

import com.goav.netty.message.MessageBasic

/**
 * Copyright (c) 2017.
 * chinaume@163.com
 */
interface ClientImpl {

    fun connect()

    fun request(message: MessageBasic)

    fun destroy()

}