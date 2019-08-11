package com.example.service.base;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class StringHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String message)
            throws Exception {

        System.out.println("进入了read");
        System.out.println(message);
        System.out.println("出去了read");


    }

}
