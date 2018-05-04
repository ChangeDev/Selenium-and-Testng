package com.taoqi;

import java.util.*;

/**
 * Created by TQ-G153 on 2017/11/6.
 */
public class Test {

    public static void main(String[] args) throws Exception {
        List<Obj> audits = new ArrayList<>();
        Obj o1 = new Obj();
        o1.setId(1L);
        o1.setName("1");
        audits.add(o1);
        Obj o2 = new Obj();
        o2.setId(3L);
        o2.setName("3");
        audits.add(o2);
        Obj o3 = new Obj();
        o3.setId(4L);
        o3.setName("4");
        audits.add(o3);
        Obj o4 = new Obj();
        o4.setId(2L);
        o4.setName("1");
        audits.add(o4);
        Optional<Obj> first = audits.stream().sorted(Comparator.comparingLong(Obj::getId).reversed()).findFirst();
        System.out.println(first.get());
    }

}

class Obj {
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Obj{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}