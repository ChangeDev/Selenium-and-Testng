package com.taoqi.java8;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by TQ-G153 on 2018/3/1.
 */
public class StreamDemo1 {

    public static void main(String[] args) throws Exception {
        //filesStream1();
        //infiniteStreamOfGenerate();
        //infiniteStreamOfIterate();
        infiniteStreamOfGenerate1();
    }

    public static void infiniteStreamOfGenerate1() {
        IntSupplier intSupplier = new IntSupplier() {

            int pre = 0;
            int current = 1;

            @Override
            public int getAsInt() {
                int oldPre = this.pre;
                int nextValue = oldPre + this.current;
                this.pre = this.current;
                this.current = nextValue;
                return oldPre;
            }
        };

        IntStream.generate(intSupplier).limit(10).forEach(System.out::println);

    }

    public static void infiniteStreamOfGenerate() {
        Stream.generate(Math::random).forEach(System.out::println);
    }

    public static void infiniteStreamOfIterate() {
        Stream.iterate(0, n -> n + 1).forEach(System.out::println);
    }

    public static void filesStream1() throws Exception {
        Files.lines(Paths.get("D:\\Users\\TQ-G153\\Desktop\\test.txt"))
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .distinct().forEach(System.out::println);

    }
}
