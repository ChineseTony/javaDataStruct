package com.tom.interview;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;

public class Parent<E> {

    public void eat(E e) {
        System.out.println("P:eat-->" + e);
    }
    public void eatAll(Collection<? extends E> c) {
        System.out.println("P:eatAll");
        Iterator<? extends E> iterator = c.iterator();
        while (iterator.hasNext()) {
            // 修改下面这行代码
//            eat(iterator.next());
//            new Parent<E>().eat(iterator.next());
            if (this instanceof Child){
                Child tmp = (Child)this;
                Method[]  methods = tmp.getClass().getSuperclass().getMethods();
                for (Method m:methods){
                    if ("eat".equals(m.getName())){
                        try {
                            m.setAccessible(true);
                            m.invoke(this,iterator.next());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
