package com.goav.netty.message

import com.goav.netty.Impl.ChannelMessageImpl
import java.io.Serializable

/**
 * Copyright (c) 2017.
 * chinaume@163.com
 */


/**
 * super message for resule
 */

abstract class MessageBasic : Serializable, ChannelMessageImpl {


    override fun toString(): String = GsonHelper.newInstances().toString()

    override fun read(): ByteArray = GsonHelper.newInstances().toJson(this).toByteArray()

    override fun write(message:ByteArray): MessageBasic = GsonHelper.newInstances().fromJson(String(message),this.javaClass);


}