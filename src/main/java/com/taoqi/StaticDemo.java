package com.taoqi;

/**
 * Created by TQ-G153 on 2018/3/13.
 */
public class StaticDemo {

    private static StaticDemo instance = new StaticDemo();
    public static int c1;
    public static int c2 = 2;

    private StaticDemo() {
        c1++;
        c2++;
        System.out.println(c1 + " " + c2);
    }

    public static StaticDemo getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        StaticDemo staticDemo = StaticDemo.getInstance();
        System.out.println(staticDemo.c1);
        System.out.println(staticDemo.c2);
    }

}