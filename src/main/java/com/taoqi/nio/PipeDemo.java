package com.taoqi.nio;

import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * @description: A Java NIO Pipe is a one-way data connection between two threads.
 * A Pipe has a source channel and a sink channel.
 * You write data to the sink channel.
 * This data can then be read from the source channel.
 * @author: ChangFeng
 * @create: 2018-07-18 11:31
 **/
public class PipeDemo {

    public static void main(String[] args) throws Exception {
        // creating a Pipe
        Pipe pipe = Pipe.open();
        // writing to a Pipe
        Pipe.SinkChannel sinkChannel = pipe.sink();
        ByteBuffer writeBuf = ByteBuffer.allocate(48);
        String newData = "New String to write to file..." + System.currentTimeMillis();
        writeBuf.put(newData.getBytes());
        writeBuf.flip();
        while (writeBuf.hasRemaining()) {
            sinkChannel.write(writeBuf);
        }
        // reading from a Pipe
        Pipe.SourceChannel sourceChannel = pipe.source();
        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = sourceChannel.read(buf);
    }
}