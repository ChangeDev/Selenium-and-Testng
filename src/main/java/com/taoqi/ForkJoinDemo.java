package com.taoqi;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * Created by TQ-G153 on 2018/3/28.
 */
public class ForkJoinDemo {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        long n =1000_000;
        long l = normalSum(n);
        System.out.println(l + " 耗时: " + (System.currentTimeMillis() - start));

        long start1 = System.currentTimeMillis();
        long l1 = forkJoinSum(n);
        System.out.println(l1 + " 耗时: " + (System.currentTimeMillis() - start1));
    }

    public static long normalSum(long n) {
        long[] longs = LongStream.range(0, n).toArray();
        long sum = 0;
        for (int i = 0; i < longs.length; i++) {
            sum += longs[i];
        }
        return sum;
    }

    public static long forkJoinSum(long n) {
        long[] longs = LongStream.range(0, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(longs);
        return new ForkJoinPool().invoke(task);
    }

}

class ForkJoinSumCalculator extends RecursiveTask<Long> {

    private long[] numbers;
    private int start;
    private int end;

    public static final long THRESHOLD = 1000_000;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            return this.computeSequentially();
        }
        ForkJoinSumCalculator left = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        left.fork();//异步执行
        ForkJoinSumCalculator right = new ForkJoinSumCalculator(numbers, start + length / 2, end);
        long rightResult = right.compute();// 同步执行
        long leftResult = left.join();//读取这个子任务的结果或者等待其结果
        return rightResult + leftResult;
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}
