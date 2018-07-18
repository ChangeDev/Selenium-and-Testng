package com.taoqi.se;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description:
 * @author: ChangFeng
 * @create: 2018-07-15 21:01
 **/
public class ProxyDemo {
    public static void main(String[] args) {
        Men men = new Men() ;
        Person proxy = (Person) Proxy.newProxyInstance(men.getClass().getClassLoader(), men.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("before invoke");
                Object methodResult = method.invoke(men, args);
                System.out.println("after invoke");
                return null;
            }
        });
        proxy.say();
    }

}
interface  Person{
    void say();
}

class Men implements Person{
    @Override
    public void say() {
        System.out.println("I'm a men");
    }
}