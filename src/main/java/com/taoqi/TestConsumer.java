package com.taoqi;

/**
 * Created by TQ-G153 on 2018/1/11.
 */
@FunctionalInterface
public interface TestConsumer<T1,T2> {

    void apply(T1 t1, T2 t2);

}
