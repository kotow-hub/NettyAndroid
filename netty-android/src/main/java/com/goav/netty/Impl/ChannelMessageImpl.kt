package com.goav.netty.Impl

import java.io.Serializable

/**
 * Copyright (c) 2017.
 * chinaume@163.com
 */

/**
 * for data
 */
interface ChannelMessageImpl {


    fun read(): ByteArray


    fun write(message: ByteArray): Serializable


}