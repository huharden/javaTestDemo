package com.hj.study.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author hutao
 * @className NettyServer
 * @description netty 服务端
 * @date 2021/2/26 3:49 下午
 */
@Component
public class NettyWSServer {

    private static final Logger log = LoggerFactory.getLogger(NettyWSServer.class);

    @Value("${socket.port}")
    private Integer port;

    public void run() {
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
                    .childHandler(new NettyWSServerInitializer()); //给workGroup 的EventLoop设置一个管道进行处理
            //启动服务器，并绑定端口并且同步，生成一个ChannelFuture
            ChannelFuture cf = bootstrap.bind(port).sync();
            log.info("netty websocket server 启动完毕...");

            //监听关闭通道
            cf.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            throw new RuntimeException("ws启动异常", e);
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }


}
