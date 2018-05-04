package com.taoqi;

/**
 * Created by TQ-G153 on 2018/2/11.
 */
public class HashMapAnalysis {

    static final int MAXIMUM_CAPACITY = 1 << 30;

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        System.out.println(n >>> 1);
        System.out.println(n);
        System.out.println();
        n |= n >>> 2;
        System.out.println(n >>> 2);
        System.out.println(n);
        System.out.println();
        n |= n >>> 4;
        System.out.println(n >>> 4);
        System.out.println(n);
        System.out.println();
        n |= n >>> 8;
        System.out.println(n >>> 8);
        System.out.println(n);
        System.out.println();
        n |= n >>> 16;
        System.out.println(n >>> 16);
        System.out.println(n);
        System.out.println();
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public static void main(String[] args) {
        System.out.println(tableSizeFor(131071));
    }
}
