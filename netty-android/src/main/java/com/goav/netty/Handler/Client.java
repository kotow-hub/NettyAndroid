/*
 * Copyright (c) 2016.
 * chinaume@163.com
 */

package com.goav.netty.Handler;

import com.goav.netty.Impl.ResponseListener;
import com.google.gson.JsonElement;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/**
 * 长链接<p>
 * <p>
 * 后期替换推送 in future.
 * </p>
 *
 * @time: 16/10/8 12:41.<br/>
 * @author: Created by moo<br/>
 */

class Client extends ClientImpl {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance(Client.class);

    private SocketChannel socketChannel;
    private ScheduledExecutorService service;
    private ResponseChannelHandler handler;
    private BlockingDeque<Object> messageSupers;
    private boolean onDestrOY = false;
    private String host;
    private int port;

    @Override
    public void onConnectChange(boolean isConnect) {
        if (!getConnectState() && isConnect) {
            reset2Connect();
        }
    }

    private static class ClientHelper {
        final static Client CLIENT = new Client();
    }

    private Client() {
        service = Executors.newSingleThreadScheduledExecutor();
        handler = new ResponseChannelHandler();
        messageSupers = new LinkedBlockingDeque<>();
        request();
    }

    @Override
    public void Initialization() {
        reset2Connect();
    }

    public void InitializationWithWorkThread() {
        if (socketChannel != null && socketChannel.isOpen() && socketChannel.isActive()) {
            logger.info("socket 已经建立了链接");
            return;
        }


        onDestrOY = false;

//        Object host = Constants.Address.valueOf(Constants.SOCKET.HOST);
//        Object port = Constants.Address.valueOf(Constants.SOCKET.PORT);

        if (host == null || port == 0) {
            return;
        }

        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            bootstrap
                    .channel(NioSocketChannel.class)
                    .group(eventLoopGroup)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .remoteAddress(host, port)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new IdleStateHandler(10, 10, 10));
                            ch.pipeline().addLast(new EncodeHandler());
                            ch.pipeline().addLast(new DecodeHandler());
                            ch.pipeline().addLast(new CloseChannelHandler(Client.this));
                            ch.pipeline().addLast(handler);
                        }
                    });
            ChannelFuture future = bootstrap.connect().sync();
            if (future.isSuccess()) {
                socketChannel = (SocketChannel) future.channel();
                logger.info("socketChannel连接成功");
            } else {
                throw new InterruptedException("connection fail.");
            }
        } catch (InterruptedException e) {
            logger.info("socketChannel连接失败");
            logger.info(e);
            eventLoopGroup.shutdownGracefully();
            onDestroy();
            reset2Connect();
            //重置,重启
        }
    }

    private void request() {
        new Thread("Socket_Push_Message") {
            @Override
            public void run() {
                while (!onDestrOY) {
                    Object message = null;
                    try {
                        message = messageSupers.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (message != null && getConnectState()) {
                        socketChannel.writeAndFlush(message);
                    }
                }
            }
        }.start();
    }

    @Override
    public void request(Object message) {
        try {
            messageSupers.put(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean getConnectState() {
        return socketChannel != null && socketChannel.isActive();
    }

    @Override
    protected void restart(boolean restart) {
        reset();
        if (!getConnectState() && restart && !onDestrOY) {
            reset2Connect();
        }
    }

    @Override
    protected void close() {
        socketChannel = null;
    }

    @Override
    public void onDestroy() {
        onDestrOY = true;
        messageSupers.clear();
        reset();
    }

    @Override
    public ClientImpl addResponseListener(ResponseListener<? super JsonElement> response) {
        handler.addListener(response);
        return this;
    }

    @Override
    public ClientImpl addResponseListener(ResponseChannelHandler response) {
        handler = response;
        return this;
    }

    @Override
    public ClientImpl bind(String host, int port) {
        this.host = host;
        this.port = port;
        return this;
    }

    private void reset() {
        if (socketChannel != null) {
            socketChannel.close();
        }
    }


    private void reset2Connect() {
        logger.info("socket 连接2s后建立");
        service.schedule(new Runnable() {
            @Override
            public void run() {
                InitializationWithWorkThread();
            }
        }, 2, TimeUnit.SECONDS);
    }

    public static ClientImpl newInstances() {
        return ClientHelper.CLIENT;
    }

}
