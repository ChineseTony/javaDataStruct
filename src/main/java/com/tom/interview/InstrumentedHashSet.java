package com.tom.interview;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class InstrumentedHashSet<E> extends HashSet<E> {

    private int addCount = 0;

    public InstrumentedHashSet() {
    }

    public InstrumentedHashSet(int initCap, float loadFactor) {
        super(initCap, loadFactor);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
//        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }

    public static void main(String[] args) {
        InstrumentedHashSet<String> set = new InstrumentedHashSet<>();
        List<String> stringList = Arrays.asList("Snap", "Crackle", "Pop");
        InstrumentedHashSet<String> s = new InstrumentedHashSet<>();
        s.addAll(stringList);
        System.out.println(stringList.size());
        System.out.println(s.getAddCount());
        s.removeIf(next -> StringUtils.isNotBlank(next) && next.length() > 3);
    }
}
