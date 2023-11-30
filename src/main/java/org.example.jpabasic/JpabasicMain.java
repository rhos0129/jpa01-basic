package org.example.jpabasic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpabasicMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabasic");
        EntityManager em = emf.createEntityManager();

        // JPA의 모든 데이터 변경은 트랜잭션 안에서 일어나야한다.
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // IDENTITY 전략은 예외적으로 구분선 전에 insert문이 출력된다.
            // SEQUENCE 전략의 allocationSize = 3 테스트
            // => create sequence member_seq start with 1 increment by 3 >> 시퀀스현재값 1
            Member member1 = new Member();
            member1.setUsername("A");
            em.persist(member1); // call next value for member_seq *2 >> 시퀀스현재값 4, 7

            Member member2 = new Member();
            member2.setUsername("B");
            em.persist(member2); // call next value for member_seq *0

            Member member3 = new Member();
            member3.setUsername("C");
            em.persist(member3); // call next value for member_seq *0

            Member member4 = new Member();
            member4.setUsername("D");
            em.persist(member4); // call next value for member_seq *1 >> 시퀀스현재값 11

            Member member5 = new Member();
            member5.setUsername("E");
            em.persist(member5); // call next value for member_seq *0

            System.out.println("========");

            tx.commit();

        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
