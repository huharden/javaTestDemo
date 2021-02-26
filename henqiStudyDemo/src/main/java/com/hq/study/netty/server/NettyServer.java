package com.hq.study.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author hutao
 * @className NettyServer
 * @description netty测试
 * @date 2021/2/26 2:07 下午
 */
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {

        //创建两个线程组 bossGroup 和 workGroup
        // bossGroup 只处理连接请求，真正和客户端业务处理，交给workGroup
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {

            //创建服务器端的启动对象，配置对象
            ServerBootstrap bootstrap = new ServerBootstrap();

            // 使用NioSctpServerChannel 作为服务器通道实现
            bootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class) //设置NioSocketChannel 作为服务器的通道实现
                    .option(ChannelOption.SO_BACKLOG, 128) // 设置线程队列得到的连接数
                    .childOption(ChannelOption.SO_KEEPALIVE, true) //设置保持活动连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        //向pipeline设置处理器
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new NettyServerHandler());
                        }
                    }); //给workGroup 的EventLoop设置一个管道进行处理
            System.out.println("服务器初始化完成===>");

            //启动服务器，并绑定端口并且同步，生成一个ChannelFuture
            ChannelFuture cf = bootstrap.bind(6668).sync();
            //监听关闭通道
            cf.channel().closeFuture().sync();

        }
        //异常情况，优雅关闭
        finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }


    }
}
