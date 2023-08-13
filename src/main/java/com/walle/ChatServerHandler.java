package com.walle;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {
    static final List<Channel> channels = new ArrayList<Channel>();

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
      log.info("Client joined - " , ctx);
        channels.add(ctx.channel());
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
              log.info("Server received - " , msg);

        for (Channel c : channels) {
            c.writeAndFlush("-> " + msg + '\n');
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.info("Closing connection for client - " , ctx);
        ctx.close();
    }
}
