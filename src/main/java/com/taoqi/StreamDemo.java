package com.taoqi;

import lombok.Data;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.LongStream;

/**
 * Created by TQ-G153 on 2017/12/1.
 */
public class StreamDemo {

    public static void main(String[] args) throws Exception {
        List<Tran> trans = new ArrayList<>();
        trans.add(new Tran(2010, "上海", 100));
        trans.add(new Tran(2011, "深圳", 200));
        trans.add(new Tran(2009, "北京", 150));
        trans.add(new Tran(2013, "上海", 300));
        trans.add(new Tran(2012, "北京", 400));

        trans.add(new Tran(2009, "深圳", 400));
        trans.add(new Tran(2008, "上海", 300));
        trans.add(new Tran(2007, "深圳", 100));
        // System.out.println(trans.stream().filter(tran->tran.getYear()<2011).sorted(Comparator.comparingInt(Tran::getYear)).collect(toList()));
        // System.out.println(trans.stream().map(Tran::getCity).distinct().collect(toList()));
        // System.out.println(trans.stream().map(Tran::getCity).sorted(Comparator.naturalOrder()).collect(toList()));
        // System.out.println(trans.stream().allMatch(tran -> tran.getCity().equals("BeiJing")));
        // trans.stream().filter(tran -> tran.getCity().equals("BeiJing")).map(Tran::getCity).forEach(System.out::println);
        // trans.stream().map(tran -> tran.getYear()).sorted(Comparator.reverseOrder()).limit(1).forEach(System.out::print);
        // String str = trans.stream().map(tran -> tran.getCity()).distinct().sorted().reduce(" ", (v1, v2) -> v1 + " " + v2);
        // System.out.println(trans.stream().mapToInt(Tran::getCount).sum());
        // OptionalInt optionalInt = trans.stream().mapToInt(Tran::getCount).max();
//        IntStream.rangeClosed(0,100).forEach(i-> System.out.print(i));
//        System.out.println();
//        IntStream.range(0,100).forEach(i-> System.out.print(i));
//        Optional<Tran> collect = trans.stream().collect(maxBy(Comparator.comparingInt(Tran::getCount)));
//        if(collect.isPresent()){
//            System.out.println(collect.get());
//        }
//        Integer collect = trans.stream().collect(summingInt(Tran::getCount));
//        Double collect = trans.stream().collect(averagingInt(Tran::getCount));
//        String collect = trans.stream().map(Tran::getCity).collect(joining(","));
//        Map<Integer, Map<String, List<Tran>>> collect = trans.stream().collect(groupingBy(Tran::getYear, groupingBy(Tran::getCity)));
//        Map<Integer, Long> collect = trans.stream().collect(groupingBy(Tran::getYear, counting()));
//        Map<Integer, Optional<Tran>> collect = trans.stream().collect(groupingBy(Tran::getYear, maxBy(Comparator.comparingInt(Tran::getCount))));
//        Map<Integer, Tran> collect = trans.stream().collect(groupingBy(Tran::getYear, collectingAndThen(maxBy(Comparator.comparingInt(Tran::getCount)), Optional::get)));
//        Map<Integer, Integer> collect = trans.stream().collect(groupingBy(Tran::getYear, summingInt(Tran::getCount)));
//        List<Integer> years = Arrays.asList(2008, 2007, 2009);
//        boolean match = trans.stream().anyMatch((tran) -> {
//            return years.contains(tran.getYear());
//        });
//        Map<Integer, List<Integer>> collect = trans.stream().collect(Collectors.groupingBy(Tran::getYear, Collectors.mapping(Tran::getCount, Collectors.toList())));
////        Map<Boolean, List<Tran>> collect = trans.stream().collect(Collectors.partitioningBy(tran -> tran.getYear() >= 2010));
//        Predicate<Tran> predicate = new Predicate<Tran>() {
//            @Override
//            public boolean test(Tran tran) {
//                return tran.getYear() >= 2010;
//            }
//        };
//        Map<Boolean, Tran> collect = trans.stream().collect(
//                Collectors.partitioningBy(predicate,
//                        Collectors.collectingAndThen(
//                                Collectors.maxBy(Comparator.comparingInt(Tran::getCount)),
//                                Optional::get)));
        //System.out.println(collect);
        long l1 = measureSumPerf(StreamDemo::rangedSum, 100_000_000);
        long l2 = measureSumPerf(StreamDemo::parallelRangedSum, 100_000_000);
        System.out.println(l1 + " msecs");
        System.out.println(l2 + " msecs");
        System.out.println(timeFrom("1522131539000"));
    }

    private static ZonedDateTime timeFrom(String milliSec) {
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(Long.valueOf(milliSec)), ZoneId.systemDefault());
        return zonedDateTime;
    }

    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }

    public static long rangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(0L, Long::sum);
    }

    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long::sum);
    }

}

@Data
class Tran {
    private Integer year;
    private String city;
    private Integer count;

    public Tran(Integer year, String city, Integer count) {
        this.year = year;
        this.city = city;
        this.count = count;
    }
}
