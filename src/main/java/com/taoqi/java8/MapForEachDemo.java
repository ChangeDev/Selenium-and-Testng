package com.taoqi.java8;

import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @description:
 * @author: ChangFeng
 * @create: 2018-05-24 17:23
 **/
public class MapForEachDemo {

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("initMap");
        Map<String, String> map = new HashMap<>(500000);
        IntStream.range(0, 500000).boxed().forEach(i -> {
            map.put(i + "", i + "");
        });
        System.out.println(map.size());
        stopWatch.stop();

        stopWatch.start("mapForeach");
        testMapForEach(map);
        stopWatch.stop();

        stopWatch.start("mapStream");
        testMapEntrySetStream(map);
        stopWatch.stop();


        System.out.println(stopWatch.prettyPrint());
        System.out.println(stopWatch.getTotalTimeMillis());
        System.out.println(stopWatch.getLastTaskName());
        System.out.println(stopWatch.getLastTaskInfo());
        System.out.println(stopWatch.getTaskCount());
    }

    public static void testMapForEach(Map<String, String> map) {
        map.forEach((k, v) -> {
            int i = Integer.valueOf(k) + Integer.valueOf(v);
        });
    }

    public static void testMapEntrySetStream(Map<String, String> map) {
        map.entrySet().stream().forEach(entry -> {
            String k = entry.getKey();
            String v = entry.getValue();
            int i = Integer.valueOf(k) + Integer.valueOf(v);
        });
    }
    void a(String a,String b){}
    void a(String... a){

    }

}