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
//            // 특정 엔티티만 준영속 >>  select문만 출력되고 update문은 출력되지 않는다.
//            Member member = em.find(Member.class, 200L);
//            member.setName("member222");
//            em.detach(member); // 더이상 JPA가 관리하지 않음

            // 모든 엔티티 준영속 >> select문 두번 출력
            Member member = em.find(Member.class, 200L); // select문 첫번째
            member.setName("member222");
            em.clear();
            Member member2 = em.find(Member.class, 200L); // select문 두번째

            System.out.println("==============");

            tx.commit();

        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
