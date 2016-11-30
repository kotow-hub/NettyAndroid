/*
 * Copyright (c) 2016.
 * chinaume@163.com
 */

package com.goav.netty.Handler;

import android.content.Context;

/**
 * @date: 16/11/16 20:16.<br/>
 * @author: Created by moo<br/>
 */

public abstract class ClientNetWorkIml {

    /**
     * recycle
     */
    public abstract void onDestroy();

    /**
     * start listener netWork
     *
     * @param context
     */
    public abstract void startListener(Context context);

    public static ClientNetWorkIml newInstance() {
        return ClientNetWork.newInstance();
    }

}
