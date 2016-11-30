/*
 * Copyright (c) 2016.
 * chinaume@163.com
 */

package com.goav.netty.Impl;

/**
 * Live$.<br/>
 *
 * @time: 16/10/8 11:19.<br/>
 * @author: Created by moo<br/>
 */

public interface PushCallBack<T> {

    void Response(T t);
}
