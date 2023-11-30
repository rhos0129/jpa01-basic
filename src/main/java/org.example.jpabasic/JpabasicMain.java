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
//            //저장
//            Member member = new Member();
////            member.setId(1L);
////            member.setName("HelloA");
//            member.setId(2L);
//            member.setName("HelloB");
//            em.persist(member);

//            // 조회
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.name = " + findMember.getName());
//
//            // 수정
//            findMember.setName("HelloJPA");
//            System.out.println("findMember.name = " + findMember.getName());

            // 삭제
//            em.remove(findMember);

            // 조회 - JPQL
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(1)
                    .getResultList();
            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }

            tx.commit();

        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
