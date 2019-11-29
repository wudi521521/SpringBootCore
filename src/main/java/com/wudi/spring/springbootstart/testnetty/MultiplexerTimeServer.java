package com.wudi.spring.springbootstart.testnetty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Dillon Wu
 * @Title: MultiplexerTimeServer
 * @Description: 多路复用类
 * @date 2019/11/28 14:57
 */
public class MultiplexerTimeServer implements Runnable {
    //负责轮询多路复用器Selector
    private Selector selector;

    private ServerSocketChannel serverChannel;

    private volatile boolean stop;

    /**
     * 初始化多路复用器，绑定监听接口
     *
     * @param port
     */
    public MultiplexerTimeServer(int port) {
        try {
            //创建多路复用器
            selector = Selector.open();
            serverChannel = ServerSocketChannel.open();
            //对Channel和TCP参数进行配置,设置异步非阻塞
            serverChannel.configureBlocking(false);
            serverChannel.socket().bind(new InetSocketAddress(port), 1024);
            //系统资源初始化成功后，将ServerSocketChannel注册到Selector,监听SelectionKey.OP_ACCEPT操作
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The time server is start in port:" + port);
        } catch (Exception e) {

        }
    }

    public void stop() {
        this.stop = true;
    }

    /**
     * 循环遍历Selector，它的休眠时间为1s,
     */
    @Override
    public void run() {
        while (!stop) {
            try {
                //无论是否有读写等事件发生，selector每隔1s都被唤醒一次
                selector.select(1000);
                //当有处于就绪状态的Channel时，Selector将返回该channel的selectedKey集合
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                SelectionKey key = null;
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();
                    try {
                        //网络异步读写操作
                        handleInput(key);
                    } catch (Exception e) {
                        key.cancel();
                        if (key.channel() != null) {
                            key.channel().close();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //多路复用器关闭后，所有注册在上面的Channel和Pipe等资源都会被自动去注册并关闭，所以不需要重复释放资源
        if (selector != null) {
            try {
                selector.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 处理接入的请求信息
     * @param key
     * @throws Exception
     */
    private void handleInput(SelectionKey key) throws Exception {
        //根据SelectionKey的操作位进行判断即可获知网络事件类型
        if (key.isValid()) {
            //处理新接入的请求消息,
            if (key.isAcceptable()) {
                //Accept the new connection，ServerSocketChannel的accept接受客户端的连接
                //并创建SocketChannel实例，相当于完成了TCP三次握手,TCP物理链路正式建立
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                //Add the new connection to the selector
                sc.register(selector, SelectionKey.OP_READ);
            }
            //读取客户端的请求信息
            if (key.isReadable()) {
                //Read the data
                SocketChannel sc = (SocketChannel) key.channel();
                //创建缓冲区
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                //read读取请求码流，读取返回值，
                //大于0：读取到字节，对字节进行编解码，0:没有读取到字节，属于正常场景，忽略，-1:链路已经关闭，需要关闭SocketChannel
                int readBytes = sc.read(readBuffer);
                if (readBytes > 0) {
                    //readBuffer进行flip操作，它的作用是将缓冲区当前的limit设置为position，position设置为0
                    //用于后续缓冲去的读取操作
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    //调用ByteBuffers的get操作将缓冲区可读的字节数组复制到新创建的字节数组中
                    //最后调用字符串的构造函数创建请求消息体并打印
                    readBuffer.get(bytes);
                    String body = new String(bytes, "UTF-8");
                    System.out.println("The time server receive order:" + body);
                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ?
                            new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                    //调用方法，把数据返回客户端
                    doWrite(sc,currentTime);
                //链路关闭，需要关闭SocketChannel，释放资源
                }else if(readBytes <0){
                    //对端链路关闭
                    key.cancel();
                    sc.close();
                }
            }


        }
    }

    private void doWrite(SocketChannel channel, String response) throws IOException {
        if (response != null && response.trim().length() > 0){
            //将字符串编码成字节数组,根据字节数组的容量创建ByteBuffer
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            //调用ByteBuffer的put操作将字节数组复制到缓冲区，然后对缓冲区进行flip操作
            writeBuffer.put(bytes);
            writeBuffer.flip();
            //最后调用SocketChannel的write方法将缓冲区的字节数组发送出去
            channel.write(writeBuffer);
        }
    }
}
