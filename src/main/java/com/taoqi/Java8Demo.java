package com.taoqi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Created by ChangFeng on 2017/11/24.
 */
public class Java8Demo {

    public static void main(String[] args) {
        methodRef1();
    }

    private static void methodRef1() {
        List<Integer> weights = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(map(weights, Apple::new));
    }

    public static List<Apple> map(List<Integer> weights, Function<Integer, Apple> function) {
        List<Apple> apples = new ArrayList<>();
        weights.forEach(weight -> {
            apples.add(function.apply(weight));
        });
        return apples;
    }

}

class Apple {
    private Integer weight;

    public Apple(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                '}';
    }
}
