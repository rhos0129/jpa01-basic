package org.example.jpabasic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpabasicMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabasic");
        EntityManager em = emf.createEntityManager();

        // JPA의 모든 데이터 변경은 트랜잭션 안에서 일어나야한다.
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            // 일대다 단방향 단점
            // : 코드상으로는 insert문만 2번 나갈것 같은데 실행해보면 insert문 2번에 update문까지 추가로 나간다.
            // >> 직관적이지 않아 실무에서는 운영이 힘드므로 거의 사용하지 않는다.

            Member member = new Member();
            member.setUsername("memebr1");
            em.persist(member); // insert문

            Team team = new Team();
            team.setName("teamA");
            team.getMembers().add(member); // **
            em.persist(team); // insert문, update문(member)

            tx.commit();

        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}