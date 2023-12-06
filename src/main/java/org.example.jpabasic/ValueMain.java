package org.example.jpabasic;

import org.h2.util.IntArray;

public class ValueMain {

    public static void main(String[] args) {

        int a = 10;
        int b = 10;
        System.out.println("a == b : " + (a == b)); // true

        Address address1 = new Address("city", "street", "zipcode");
        Address address2 = new Address("city", "street", "zipcode");
        System.out.println("address1 == address2 : " + (address1 == address2)); // false 인스턴스의 참조 값을 비교
        System.out.println("address1 equals address2 : " + (address1.equals(address2))); // true 인스턴스의 값을 비교

    }
}
