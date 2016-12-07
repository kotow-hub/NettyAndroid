/*
 * Copyright (c) 2016.
 * chinaume@163.com
 */

package com.goav.netty.Handler;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.goav.netty.Impl.ConnectCallBack;

class ClientNetWork extends ClientNetWorkIml {


    ClientNetWork() {
    }

    private void initThread() {
        thread = new Thread("Client_Thread") {
            @Override
            public void run() {
                while (callBack != null && mContext != null) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pushNetWorkState();
                }
            }
        };
    }


    @Override
    public void onDestroy() {
        this.mContext = null;
        this.callBack = null;
    }

    @Override
    public void startListener(Context context) {
        this.mContext = context;
        this.callBack = ClientImpl.newInstances();
        initThread();
        startLocalListener();
    }

    private void startLocalListener() {
        if (!thread.isAlive() && mContext != null) {
            thread.start();
        }
    }


    private void pushNetWorkState() {
        boolean connect = isNetworkAvailable();
        if (connect != isConnect) {
            isConnect = connect;
            this.callBack.onConnectChange(isConnect);
        }
    }

    private boolean isNetworkAvailable() {
        if (mContext == null) {
            isConnect = false;
            return false;
        }
        ConnectivityManager connectivity = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }

    private static class ClientNetWorkHelper {
        final static ClientNetWork CLIENT_NET_WORK = new ClientNetWork();
    }


    private ConnectCallBack callBack;
    private boolean isConnect;
    private Context mContext;
    private Thread thread;


    public static ClientNetWork newInstance() {
        return ClientNetWorkHelper.CLIENT_NET_WORK;
    }

}
