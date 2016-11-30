/*
 * Copyright (c) 2016.
 * chinaume@163.com
 */

package com.goav.netty.Handler;

import android.text.TextUtils;

import com.goav.netty.Impl.ResponseListener;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/**
 * Live$.<br/>
 *
 * @time: 16/10/8 15:36.<br/>
 * @author: Created by moo<br/>
 */

public class ResponseChannelHandler extends ChannelHandlerSuper {

    protected static final InternalLogger logger = InternalLoggerFactory.getInstance(ResponseChannelHandler.class);
    private ResponseListener<? super JsonElement> ResponseHandler;

    protected ResponseChannelHandler() {
        ResponseHandler = new privateResponseListener();
    }

    public ResponseChannelHandler addListener(ResponseListener<? super JsonElement> ResponseHandler) {
        this.ResponseHandler = ResponseHandler;
        return this;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        self:
        if (msg != null) {
            try {
                String response = new String((byte[]) msg);
                logger.info("socket " + response);
                if (TextUtils.isEmpty(response))
                    break self;
                JsonElement element = new JsonParser().parse(response);
                if (ObservableNotify(ctx, element)) return;
            } catch (Exception ignored) {
            }
        }
        super.channelRead(ctx, msg);
        logger.info("socket 消息向下传递");
    }

    protected boolean ObservableNotify(ChannelHandlerContext ctx, JsonElement msg) throws Exception {
        return ResponseHandler.channelRead(ctx, msg);
    }

    private class privateResponseListener implements ResponseListener<JsonElement> {
        @Override
        public boolean channelRead(ChannelHandlerContext ctx, JsonElement msg) throws Exception {
            return false;
        }
    }

}
