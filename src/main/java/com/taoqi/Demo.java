package com.taoqi;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @description:
 * @author: ChangFeng
 * @create: 2018-04-04 15:03
 **/
public class Demo {


    public static void main(String[] args) throws Exception {
        Demo demo = new Demo();
        Future<Double> priceAsync = demo.getPriceAsync("Mi Mix 2");
        System.out.println("get");
        System.out.println(priceAsync.get());
    }

    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            long start = System.currentTimeMillis();
            System.out.println("cll getPrice method");
            double price = this.getPrice(product);
            System.out.println("call getPrice method cost:" + (System.currentTimeMillis() - start) + "ms");
            futurePrice.complete(price);
        }).start();
        return futurePrice;
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    private double calculatePrice(String product) {
        delay();
        Random random = new Random();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }


}