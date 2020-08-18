package com.tom.spistudy.proxy.cglib;



/**
 * @author WangTao
 */
public class CglibBootstrap {

    public static void main(String[] args) {

        Customer customer = (Customer) new PersonCglibProxy().getInstance(Customer.class);
        customer.test();
    }
}

class Customer {

    public void test(){
        System.out.println("test");
    }
}
