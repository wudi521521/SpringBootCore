package com.wudi.spring.springbootstart.testnetty;



import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Dillon Wu
 * @Title: TimeClientHandle
 * @Description: TODO
 * @date 2019/11/29 13:33
 */
public class TimeClientHandle implements Runnable {

    private String host;
    private int port;
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile boolean stop;

    public TimeClientHandle(String host, int port) {
        this.host = host == null ? "127.0.0.1" : host;
        this.port = port;
        try {
            //创建NIO的多路复用器和SocketChannel对象，设置为异步非阻塞模式
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
        } catch (Exception e) {
            e.printStackTrace();
            //0是正常退出程序,非0是非正常退出程序
            System.exit(1);
        }
    }

    @Override
    public void run() {
        try {
            //连接的实现
            doConnect();
        } catch (Exception e) {
            e.printStackTrace();
            //非正常退出
            System.exit(1);
        }
        while (!stop) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                SelectionKey key = null;
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();
                    try {
                        handleInput(key);
                    } catch (Exception e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                //非正常退出
                System.exit(1);
            }
        }
        if (selector != null) {
            //由于多路复用器上可能注册成千上万的Channel或者pipe，如果一一对着资源进行释放显然不合适
            //JDK底层会自动释放所有跟此路复用器关联的资源
            try {
                selector.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 有就绪的channel时，就调用此方法
     * @param key
     * @throws Exception
     */
    private void handleInput(SelectionKey key) throws Exception {
        if (key.isValid()) {
            //判断是否连接成功
            SocketChannel sc = (SocketChannel) key.channel();
            //如果是处于连接状态，说明服务端已经返回ACK应答消息
            if (key.isConnectable()) {
                //对连接结果进行判断，调用SocketChannel的finishConnect()方法
                if (sc.finishConnect()) {
                    //将SocketChannel注册到多路复用器上，注册SelectionKey.OP_READ操作位，监听网络操作，
                    //发送消息到服务器端
                    sc.register(selector, SelectionKey.OP_READ);
                    doWrite(sc);

                } else
                    //连接失败，进程退出
                    System.exit(1);
            }
            //客户端是如何读取服务器应答消息的
            if (key.isReadable()) {
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                //异步操作
                int readBytes = sc.read(readBuffer);
                if (readBytes > 0) {
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, "UTF-8");
                    System.out.println("Now is :" + body);
                    //执行后将stop置为true
                    this.stop = true;
                } else if (readBytes < 0) {
                    //对端链路关闭
                    key.cancel();
                    sc.close();
                } else
                    ;//读到0字节，忽略
            }
        }
    }

    private void doConnect() throws Exception {
        //如果直接连接成功，则注册到多路复用器上，发送请求消息，读应答,如果没有连接成功则说明服务器端没有返回TCP
        //握手应答消息，但是并不代表连接失败
        if (socketChannel.connect(new InetSocketAddress(host, port))) {
            socketChannel.register(selector, SelectionKey.OP_READ);
            doWrite(socketChannel);
        } else
            //需要将SocketChannel注册到多路复用器Selector上,注册SelectionKey.OP_CONNECT,当服务端返回
            //TCP syn-ack消息后,Selector就能够轮询到这个SocketChannel处于连接就绪状态
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
    }

    private void doWrite(SocketChannel sc) throws Exception {
        byte[] req = "QUERY TIME ORDER".getBytes();
        //发送缓存区，由于发送是异步的，所以会存在"半包写"问题
        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
        writeBuffer.put(req);
        writeBuffer.flip();
        sc.write(writeBuffer);
        //最后通过hasRemaining()方法对发送结果进行判断，如果缓存区中的消息全部发送完成打印数据
        if (!writeBuffer.hasRemaining()) {
            System.out.println("Send order 2 server succeed");
        }
    }
}
