package com.taoqi.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * @description:
 * @author: ChangFeng
 * @create: 2018-07-18 10:38
 **/
public class DatagramChannelDemo {

    public static void main(String[] args) throws Exception {
        // open a DatagramChannel
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.socket().bind(new InetSocketAddress(9999));
        // receiving data
        ByteBuffer byteBuffer = ByteBuffer.allocate(48);
        byteBuffer.clear();
        // If the received packet contains more data than the Buffer can contain,
        // the remaining data is discarded silently.
        datagramChannel.receive(byteBuffer);
        // sending data
        String newData = "New String to write to file..."
                + System.currentTimeMillis();
        ByteBuffer sendBuf = ByteBuffer.allocate(48);
        sendBuf.put(newData.getBytes());
        sendBuf.flip();
        int bytesSent = datagramChannel.send(byteBuffer, new InetSocketAddress(9999));
        // Connecting to a Specific Address
        // you can only send and receive data packets from one specific address.
        datagramChannel.connect(new InetSocketAddress(9999));
    }

}