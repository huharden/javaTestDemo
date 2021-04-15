package com.hj.study.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author hutao
 * @className GroupChatServer
 * @description 群聊功能
 * @date 2021/3/4 10:46 上午
 */
public class GroupChatServer {

    //定义属性
    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int port = 6678;

    //构造器
    public GroupChatServer(){
       try {
           //得到选择器
           selector = Selector.open();
           listenChannel = ServerSocketChannel.open();
           //绑定端口
           listenChannel.socket().bind(new InetSocketAddress(port));
           //设置非阻塞模式
           listenChannel.configureBlocking(false);
           //将该listenChannel 注册到selector
           listenChannel.register(selector, SelectionKey.OP_ACCEPT);

       }catch (IOException e){
           e.getMessage();
       }
    }

    //监听器
    public void listen() {

        try {
            //循环处理监听
            while (true) {
                int count = selector.select(2000);
                //有事件处理
                if(count > 0){
                    //遍历得到取出selectionKey集合
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()){
                        //取出selectionKey
                        SelectionKey key = iterator.next();
                        //监听到accept
                        if(key.isAcceptable()){
                            SocketChannel sc = listenChannel.accept();
                            sc.configureBlocking(false);
                            //注册
                            sc.register(selector, SelectionKey.OP_READ);
                            //提示上线
                            System.out.println(sc.getRemoteAddress() + "上线");
                        }
                        //通道发生read事件，即通道可读状态
                        if(key.isReadable()){
                           //处理读操作
                            readData(key);
                        }

                        //删除当前的key,防止重复使用
                        iterator.remove();
                    }
                } else {
                    System.out.println("等待....");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {

        }

    }

    //读取客户端消息
    private void readData(SelectionKey key){
        //定义一个SocketChannel
        SocketChannel channel = null;

        try {
            //取到关联的channel
            channel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = channel.read(buffer);
            //根据count值做处理
            if(count > 0){
                //把缓存区的数据转为字符串
                String msg = new String(buffer.array());
                System.out.println("from客户端:" + msg);

                //向其他客户端转发消息
                sendInfoToOtherClient(msg, channel);

            }
        } catch (IOException e) {
            try {
                System.out.println(channel.getRemoteAddress() + "离线...");
                //取消注册
                key.cancel();
                //关闭通道
                channel.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            e.printStackTrace();
        }

    }

    //转发消息给其他客户(通道)
    private void sendInfoToOtherClient(String msg, SocketChannel self) {
        System.out.println("服务器转发消息中....");
        try {
            //遍历所有注册到selector 上的socketChannel, 并排除自己
            for (SelectionKey key : selector.keys()) {
                //通过key, 取出对应的SocketChannel
                Channel targetChannel = key.channel();
                //排除自己
                if (targetChannel instanceof SocketChannel && targetChannel != self) {

                    SocketChannel dest = (SocketChannel) targetChannel;
                    //将msg 存储到buffer
                    ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());

                    dest.write(byteBuffer);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        //创建一个服务器对象
        GroupChatServer groupChatServer = new GroupChatServer();

        groupChatServer.listen();

    }
}
