package com.taoqi.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @description:
 * @author: ChangFeng
 * @create: 2018-07-06 10:30
 **/
public class ThreadPoolTest {

    public static void main(String[] args) {
        ExecutorService pollMsgThreadPool = Executors.newFixedThreadPool(3);
        ExecutorService consumeMsgThreadPool = new ThreadPoolExecutor(5, 5 * 2,
                5L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(2), new ThreadPoolExecutor.CallerRunsPolicy());
        List<LinkedBlockingQueue<Integer>> queueList = new ArrayList<>(3);
        queueList.add(new LinkedBlockingQueue<>());
        queueList.add(new LinkedBlockingQueue<>());
        queueList.add(new LinkedBlockingQueue<>());
        // 填充队列
        queueList.forEach(queue -> {
            IntStream.range(0, 10).boxed().forEach(n -> {
                Thread.currentThread().setName("producer thread");
                try {
                    sleep(5);
                    queue.add(n);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });


        // 消费队列
        queueList.forEach(queue -> {
            pollMsgThreadPool.execute(() -> {
                sleep(100);
                // poll
                while (queue.size() != 0) {
                    Integer poll = queue.poll();
                    System.out.println(Thread.currentThread().getName() + " polled " + poll);
                    consumeMsgThreadPool.execute(() -> {
                        // consume
                        System.out.println(Thread.currentThread().getName() + " consumed " + poll);
                        sleep(1000);
                    });
                }
            });
        });

    }

    public static void sleep(long millis) {
        try {
            //System.out.println(Thread.currentThread().getName()+"");
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}