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
//            Member member = new Member();
//            member.setId(1L);
//            member.setUsername("A");
//            member.setRoleType(RoleType.USER);

            // RoleType에 GUEST 추가

//            Member member = new Member();
//            member.setId(2L);
//            member.setUsername("B");
//            member.setRoleType(RoleType.GUEST);

            Member member = new Member();
            member.setId(3L);
            member.setUsername("C");
            member.setRoleType(RoleType.USER);

            em.persist(member);

            tx.commit();

        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
