/*
 * Copyright (c) 2016.
 * chinaume@163.com
 */

package com.goav.app.socket;

import android.util.Log;

import com.goav.netty.Handler.ClientImpl;
import com.goav.netty.Handler.ResponseChannelHandler;
import com.goav.netty.Impl.ResponseListener;
import com.goav.netty.message.MessageResponse;
import com.goav.netty.message.MessageSuper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import io.netty.channel.ChannelHandlerContext;


/**
 * <p>
 * 处理自身长链接返回消息体<br/>
 * <ul>
 * <li>{@link ResponseChannelHandler#channelRead(ChannelHandlerContext, Object) 消息中转中心[ResponseChannelHandler]}</li>
 * <li>{@link MessageSuper 消息体[Message]}</li>
 * </ul>
 * </p>
 *
 * @time: 16/10/8 18:00.<br/>
 * @author: Created by moo<br/>
 */
public class ResponseHandler implements ResponseListener<JsonElement> {

    @Override
    public boolean channelRead(ChannelHandlerContext ctx, JsonElement msgConfig) throws Exception {
        if (msgConfig.isJsonObject()) {
            JsonObject object = (JsonObject) msgConfig;
            int type = object.get(Constants.PARAMS.RESPONSE.TYPE).getAsInt();

            if (type == Constants.TYPE.RESPONSE.RESPONSE) {
                int Reply = object.get(Constants.PARAMS.RESPONSE.REPLY).getAsInt();
                if (Reply == (byte) 1) {
                    //// FIXME: 16/10/14 reply
//                    msgConfig.addProperty(Constants.PARAMS.RESPONSE.TYPE, Constants.TYPE.RESPONSE.ANSWER);
//                    msgConfig.addProperty(Constants.PARAMS.RESPONSE.REPLY, 0);
//                    ctx.writeAndFlush(msgConfig);
                    ctx.writeAndFlush(flush(object));
//                    ClientImpl.newInstances().request(flush(object));
                }

                try {
                    final JsonObject clone = object;
                    eventWhenMessageToClient(clone);
                } catch (Exception ea) {
                    ea.printStackTrace();
                }
                return true;
            } else if (type == Constants.TYPE.RESPONSE.LOGOUT) {
                ClientImpl.newInstances().onDestroy();
            }
        }
        return false;
    }

    /**
     * int type = back.getIntValue("type");
     * int code = back.getIntValue("code");
     * String msg_id = back.getString("id");
     *
     * @param service service Data
     * @return
     */
    JsonObject flush(JsonObject service) {
        JsonObject msgConfig = new JsonObject();
        msgConfig.addProperty(Constants.PARAMS.RESPONSE.TYPE, Constants.TYPE.RESPONSE.ANSWER);
        try {
            msgConfig.addProperty(Constants.PARAMS.RESPONSE.CODE, service.get(Constants.PARAMS.RESPONSE.CODE).getAsInt());
        } catch (Exception e) {

        }
        try {
            msgConfig.addProperty(Constants.PARAMS.RESPONSE.ID, service.get(Constants.PARAMS.RESPONSE.ID).getAsString());
        } catch (Exception e) {

        }
//        msgConfig.addProperty(Constants.PARAMS.RESPONSE.REPLY, 0);
        return msgConfig;
    }

    /**
     * 处理本地消息
     *
     * @param clone {@link MessageResponse}-><{@code ? super} {@link MessageResponse}>
     * @throws Exception <ul>
     *                   <li>{@link JsonSyntaxException JsonSyntaxException} 解析数据本地失败,数据格式出问题</li>
     *                   <li>{@link NullPointerException NullPointerException}
     *                   <li>{@link IllegalArgumentException IllegalArgumentException}
     *                   <li>Other Exception</li>
     *                   </ul>
     */
    private void eventWhenMessageToClient(final JsonObject clone) throws Exception {
//        String action = clone.get("action").getAsString();
//        action.equals(action2) &&
        try {
            Log.d("Message-Response", clone.toString());
        } catch (Exception e) {

        }
    }
}
