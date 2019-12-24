package com.it.niotest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author wang_zy
 * @Date 2019/10/23 15:41
 */
public class NIOServer {

    private static ServerSocketChannel server;

    private int port = 8088;

    //注册器 多路复用核心
    private static Selector selector;

    ByteBuffer receiveBuffer = ByteBuffer.allocate(1024);

    ByteBuffer sendBuffer = ByteBuffer.allocate(1024);

    //事件标签集合
    Map<SelectionKey,String> sessionMsg =new HashMap<>();

    public NIOServer(int port) throws IOException {
        this.port=port;
        server=ServerSocketChannel.open();
        server.socket().bind(new InetSocketAddress(port));
        server.configureBlocking(false);
        selector=Selector.open();
        server.register(selector,SelectionKey.OP_ACCEPT);
    }

    public static void main(String[] args) throws IOException {
//        NIOServer nioServer = new NIOServer(8088);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        List<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(4);
        list2.add(6);
        list2.add(8);
        list.removeAll(list2);
        list.forEach(System.out::println);
    }

}
