package com.hj.study.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author hutao
 * @className NettyServerHandler
 * @description
 *  1. 自定义一个Handler，需要继承netty, 绑定好的某个HandlerAdapter
 *  2. 我们自定义一个Handler, 才能称为一个Handler
 * @date 2021/2/26 2:27 下午
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取实际数据
     * 1. ChannelHandlerContext ctx:上下文对象，管道pipeline, 通道channel, 地址
     * 2. Object msg: 客户端发送的数据，默认Object格式
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("获取到的数据ctx===>" + ctx);
        // 将msg 转成一个byteBuf
        // ByteBuf 是Netty提供的，不是NIO的ByteBuffer
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("客户端发送的数据===>"+ buf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址===>"+ ctx.channel().remoteAddress());
    }

    //读取数据完毕
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将数据写入到缓存，并刷新
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello 客户端", CharsetUtil.UTF_8));
    }

    /**
     * 异常处理: 关闭通道
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
