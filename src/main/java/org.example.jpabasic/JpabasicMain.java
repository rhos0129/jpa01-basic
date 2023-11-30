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
//            // 비영속
//            Member member = new Member();
//            member.setId(100L);
//            member.setId(101L);
//            member.setName("HelloJPA");
//
//            // 영속
//            System.out.println("=== BEFORE ===");
//            em.persist(member); // DB에 쿼리가 바로 날라가는 것이 아니다.
//            System.out.println("=== AFTER === ");

//            // 1차캐시에서 조회 >> insert문 전에 출력된다.
//            Member findMember = em.find(Member.class, 101L);
//            System.out.println("findMember.id = " +findMember.getId());
//            System.out.println("findMember.name = " +findMember.getName());
//
//            // 1차캐시에 없다면 DB에서 조회 >> select 문 후에 출력
//            Member findMember2 = em.find(Member.class, 1L);
//            System.out.println("findMember2.id = " +findMember2.getId());
//            System.out.println("findMember2.name = " +findMember2.getName());

//            // 동일성보장
//            Member findMember3 = em.find(Member.class, 101L);
//            Member findMember4 = em.find(Member.class, 101L);
//            System.out.println("result = " + (findMember3 == findMember4)); //true

//            // 트랜잭션을 지원하는 쓰기 지연 >> 구분선 후에 insert문 출력
//            Member member1 = new Member(102L, "A");
//            Member member2 = new Member(103L, "B");
//            em.persist(member1);
//            em.persist(member2);
//            System.out.println("===========");

            // 변경감지 >> em.persist(findMember5) 안해도 update문 출력된다.
            Member findMember5 = em.find(Member.class, 102L);
            findMember5.setName("Z");
            System.out.println("===========");

            tx.commit();

        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
