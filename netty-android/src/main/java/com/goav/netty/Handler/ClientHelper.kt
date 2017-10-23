package com.goav.netty.Handler

import com.goav.netty.Impl.ClientOptImpl

/**
 * Created by moo on 20/10/2017.
 */
object ClientHelper {

    /**
     * 得到对象自己管理
     */
    fun init(): ClientOptImpl = Client()

}