package com.hq.study.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.channel.unix.DatagramSocketAddress;
import io.netty.channel.unix.DomainSocketAddress;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author hutao
 * @className NettyClient
 * @description netty 客户端
 * @date 2021/2/26 2:55 下午
 */
public class NettyClient {

    public static void main(String[] args) {

        //客户端需要的事件循环
        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();

            //设置相关参数
            bootstrap.group(eventExecutors)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //加入处理器
                            ch.pipeline().addLast(new NettyClientHandler());
                        }
                    });
            System.out.println("客户端初始化完成===>");

            //启动客户端连接服务器
            //ChannelFuture channelFuture = bootstrap.connect(domainSocketAddress).sync();
            URI websocketURI = new URI("ws://172.21.71.34:6679");

            ChannelFuture channelFuture = bootstrap.connect(websocketURI.getHost(),websocketURI.getPort()).sync();
            //关闭通道进行监听
            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            //优雅关闭
            eventExecutors.shutdownGracefully();
        }


    }


}
