package org.example.jpabasic;

import org.h2.util.IntArray;

public class ValueMain {

    public static void main(String[] args) {

        // 자바의 기본 타입은 절대 공유X
        int a = 10;
        int b = a; // 기본 타입은 항상 값을 복사함

        a = 20;

        System.out.println("a = " + a); // 10
        System.out.println("b = " + b); // 20 (절대 공유X)


//         // Integer같은 래퍼 클래스나 String 같은 특수한 클래스는 공유는 가능하지만 값이 변경될 수 없다
//        Integer a1 = new Integer(10);
//        Integer b2 = a1;
//
//        a1 = new Integer(20); // 이거는 새로운 객체를 생성하는 것이지 값을 변경하는 것이 아니다.
//        a1 = a1.setValue(20); // 값 변경 메소드를 지원하지 않는다.
//
//        System.out.println("a1 = " + a1); // 20
//        System.out.println("b2 = " + b2); // 20 (만약 값 변경 메소드를 지원한다면 같은 참조값을 공유하게 된다. 사이드이펙트 발생.)

    }
}
