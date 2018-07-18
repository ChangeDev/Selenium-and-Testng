package com.taoqi.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Set;

/**
 * @description: nio demo
 * @author: ChangFeng
 * @create: 2018-07-13 10:26
 **/
public class NioDemo {

    public static void main(String[] args) throws Exception {
        //fileChannelWrite();
        //fileChannelRead();
        //transferTo();
        // transferFrom();
        // selector();
        //appendViaFileChannel();
        //serverSocketChannel();
        socketChannel();
    }

    public static void serverSocketChannel() throws Exception {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress("localhost", 8000));
        while (true) {
            SocketChannel accept = ssc.accept();
            ByteBuffer byteBuffer = ByteBuffer.allocate(48);
            byteBuffer.put("Hello socket channel".getBytes());
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                accept.write(byteBuffer);
            }
            accept.close();
        }

    }

    public static void socketChannel() throws Exception {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost", 8000));
        while (!sc.finishConnect()) {
        }
        ByteBuffer byteBuffer = ByteBuffer.allocate(48);
        sc.read(byteBuffer);
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()) {
            System.out.print((char) byteBuffer.get());
        }
    }

    public static void appendViaFileChannel() throws Exception {
        RandomAccessFile file = new RandomAccessFile("nio-data.txt", "rw");
        FileChannel fileChannel = file.getChannel();
        String txt = "New String to write to file..." + System.currentTimeMillis();
        ByteBuffer byteBuffer = ByteBuffer.allocate(48);
        byteBuffer.put(txt.getBytes());
        byteBuffer.flip();
        fileChannel.position(fileChannel.size());
        while (byteBuffer.hasRemaining()) {
            fileChannel.write(byteBuffer);
            fileChannel.force(false);
        }
        fileChannel.close();
    }

    private static void selector() throws IOException {
        // create a selector
        Selector selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // channel must be non-blocking mode to be used with a selector
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress("localhost", 8080));
        // register channel with the selector by SelectableChannel.register
        SelectionKey selectionKey = ssc.register(selector, SelectionKey.OP_ACCEPT);
        // the set of events you are interested in 'selecting'
        int interestOps = selectionKey.interestOps();
        boolean isInterestedInAccept = (interestOps & SelectionKey.OP_ACCEPT) != 0;
        System.out.println(isInterestedInAccept);
        // the set of operations the channel is ready for
        int readyOps = selectionKey.readyOps();
        // access Channel and Selector from the SelectionKey
        selectionKey.channel();
        selectionKey.selector();
        // you can attach an object to SelectorKey for recognizing or using
        selectionKey.attach(ByteBuffer.allocate(48));
        selectionKey.attachment();
        // you can call select() methods to find out how many channels are 'ready' for the events you are interested in
        // block until at least one channel is ready
        selector.select();
        // does the same as select() method except it blocks for a maximum of timeout milliseconds
        selector.select(1000);
        // does not block at all. return immediately with whatever channels are ready
        selector.selectNow();
        // once the select() method return value indicated  that one or more channels are ready
        // you can access the ready channels via the 'selected key set', by calling the selectedKeys() method.
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        // iterate selected key set to access the ready channels
        selectionKeys.forEach(tmpSelectionKey -> {
            if (tmpSelectionKey.isAcceptable()) {
                SelectableChannel channel = tmpSelectionKey.channel();
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) channel;
            }
        });
    }

    private static void transferFrom() throws IOException {
        RandomAccessFile srcFile = new RandomAccessFile("nio-data.txt", "rw");
        FileChannel srcFileChannel = srcFile.getChannel();
        File file = new File("nio-data1.txt");
        if (file.exists()) {
            System.out.println("deleted file " + file.getName());
            file.delete();
        }
        RandomAccessFile destFile = new RandomAccessFile(file, "rw");
        FileChannel destFileChannel = destFile.getChannel();
        destFileChannel.transferFrom(srcFileChannel, 0, srcFileChannel.size());
    }

    private static void transferTo() throws IOException {
        RandomAccessFile srcFile = new RandomAccessFile("nio-data.txt", "rw");
        FileChannel srcFileChannel = srcFile.getChannel();
        File file = new File("nio-data1.txt");
        if (file.exists()) {
            file.delete();
        }
        RandomAccessFile destFile = new RandomAccessFile(file, "rw");
        FileChannel destFileChannel = destFile.getChannel();
        srcFileChannel.transferTo(0, srcFileChannel.size(), destFileChannel);
    }

    private static void scatterRead(ScatteringByteChannel channel) throws IOException {
        // read data from a channel into more than one buffer
        ByteBuffer headBuf = ByteBuffer.allocate(128);
        ByteBuffer bodyBuf = ByteBuffer.allocate(1024);
        ByteBuffer[] byteBuffers = {headBuf, bodyBuf};
        channel.read(byteBuffers);
    }

    private static void gatherWrite(GatheringByteChannel channel) throws IOException {
        // write data from  more than one buffer into a single channel
        ByteBuffer headBuf = ByteBuffer.allocate(128);
        ByteBuffer bodyBuf = ByteBuffer.allocate(1024);
        ByteBuffer[] byteBuffers = {headBuf, bodyBuf};
        channel.write(byteBuffers);
    }

    public static void fileChannelWrite() throws Exception {
        RandomAccessFile file = new RandomAccessFile("nio-data.txt", "rw");
        FileChannel channel = file.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(48);
        // write mode
        buffer.put(new String("aabbccdd").getBytes());
        // write mode to read mode
        buffer.flip();
        // read data from buffer to channel
        channel.write(buffer);
        buffer.clear();
        buffer.put("eeffgg".getBytes());
        buffer.flip();
        channel.write(buffer);
        file.close();
    }

    public static void fileChannelRead() throws Exception {
        RandomAccessFile file = new RandomAccessFile("nio-data.txt", "rw");
        FileChannel channel = file.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(48);
        // write mode
        int l = channel.read(byteBuffer);
        while (l != -1) {
            System.out.println("read " + l);

            // write mode to read mode
            byteBuffer.flip();

            // read data
            while (byteBuffer.hasRemaining()) {
                System.out.print((char) byteBuffer.get());
            }
            byteBuffer.clear();
            l = channel.read(byteBuffer);
        }
        file.close();
    }
}