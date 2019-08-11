package com.example.service.base;

import io.netty.buffer.ByteBuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles a server-side channel.
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter { // (1)

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        // Discard the received data silently.

        //System.out.println(msg);

        //((ByteBuf) msg).release(); // (3)

        ByteBuf in = (ByteBuf) msg;
        try {



            Map<String,Object> map=new HashMap<String,Object>();
            map.put("service","TestService");
            map.put("method","getMessage");

            ArrayList<Character> list=new ArrayList<Character>();


            while (in.isReadable()) { // (1)

                /*
                System.out.print((char) in.readByte());
                System.out.flush();
                */

                list.add((char) in.readByte());

            }


            char[] arr=new char[list.size()];
            for(int i=0;i<list.size();i++)
            {
                arr[i]=list.get(i);
            }


            String s=new String(arr);

            map.put("params",new Object[]{s});


            Object o=ServiceDo.getInstance().Handle(map,ctx);
            System.out.println("返回的======"+o.toString());




            /*
            while (in.isReadable()) { // (1)
                System.out.print((char) in.readByte());
                System.out.flush();
            }
            */

        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("报错了");
        }
        finally {
            ReferenceCountUtil.release(msg); // (2)
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}