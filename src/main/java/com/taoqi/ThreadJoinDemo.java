package com.taoqi;

import java.util.concurrent.TimeUnit;

/**
 * Created by TQ-G153 on 2018/3/2.
 */
public class ThreadJoinDemo implements Runnable {

    public static void main(String[] args) throws InterruptedException{
        Thread subThread = new Thread(new ThreadJoinDemo());
        subThread.start();
        for (int i = 0; i < 10; i++) {
            if (i==7){
                subThread.join();
            }
            System.out.println(i);
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("subThread start ...");
            TimeUnit.SECONDS.sleep(4);
            System.out.println("subThread shutdown ...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
