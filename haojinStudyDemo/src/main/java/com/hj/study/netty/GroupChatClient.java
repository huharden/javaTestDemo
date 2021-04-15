package com.hj.study.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author hutao
 * @className GroupChatClient
 * @description TODO
 * @date 2021/3/4 12:07 下午
 */
public class GroupChatClient {
    //定义相关属性
    private final String HOST = "127.0.0.1";

    //服务器端口
    private final int PORT = 6678;

    private Selector selector;

    private SocketChannel socketChannel;

    private String userName;

    //构造器,初始化
    public GroupChatClient() throws IOException {
        selector = Selector.open();
        socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", PORT));
        //设置非阻塞
        socketChannel.configureBlocking(false);
        //将channel 注册到selector
        socketChannel.register(selector, SelectionKey.OP_READ);
        //得到username
        userName = socketChannel.getLocalAddress().toString().substring(1);
        System.out.println(userName + "is ok...");
    }

    //向服务器发送消息
    public void sendInfo(String info){
        info = userName + "说: " + info;
        try {
            socketChannel.write(ByteBuffer.wrap(info.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //读取从服务器回复的消息
    public void readInfo(){
        try {
            int readChannels = selector.select(2000);
            //存在可用通道
            if(readChannels > 0){
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    if(key.isReadable()){
                        //得到相关通道
                        SocketChannel sc = (SocketChannel) key.channel();
                        //得到一个Buffer
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        //读取
                        sc.read(buffer);
                        //把读到的缓冲区数据转成字符串
                        String msg = new String(buffer.array());
                        System.out.println(msg.trim());

                    }
                }
                //删除当前selectionKey,反正重复操作
                iterator.remove();
            }
            else {
                //System.out.println("无可用通道");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            GroupChatClient chatClient = new GroupChatClient();
            //启动一个线程, 每隔3秒，读取从服务器发送的数据
            new Thread(){
                public void run(){
                    while (true){
                        chatClient.readInfo();
                        try {
                            Thread.currentThread().sleep(3000);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
            }.start();

            //发送数据给服务器
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()){
                String s = scanner.nextLine();
                chatClient.sendInfo(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
