package org.example.jpabasic;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpabasicMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabasic");
        EntityManager em = emf.createEntityManager();

        // JPA의 모든 데이터 변경은 트랜잭션 안에서 일어나야한다.
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("memberA");
            member.setTeam(team);
            em.persist(member);

            Team team2 = new Team();
            team2.setName("teamB");
            em.persist(team2);

            Member member2 = new Member();
            member2.setUsername("memberB");
            member2.setTeam(team2);
            em.persist(member2);

            em.flush();
            em.clear();

            // Lazy >> join없이 member만
            // Eager >> join으로 member, team 모두
            Member findMember = em.find(Member.class, member.getId());

            // Lazy >> 프록시
            // Eager >> 원본객체
            System.out.println("findMember.team" + findMember.getTeam().getClass());

            // Lazy >> team select문 출력
            // Eager >> 추가 select문 없음
            System.out.println("========");
            System.out.println("member.team.name = " + findMember.getTeam().getName());
            System.out.println("========");

            // Eager는 select문이 N+1번 출력되므로 실무에서 사용하지 말자
            // >> JPQL의 N+1 문제
            // >> select * from member *1
            // >> select * from team where team_id = ?  *N
            // Lazy는 select문이 한번만 출력
            List<Member> members = em.createQuery("select m from Member m", Member.class)
                    .getResultList();


            tx.commit();

        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }

    // 파라미터로 프록시가 넘어올지 원본 엔티티가 넘어올 지 알 수 없다
    private static void logic(Member member1, Member member2) {
        System.out.println("member == member2 : " + (member1.getClass() == member2.getClass())); // false

        System.out.println("member instance of Member : " + (member1 instanceof Member)); // true
        System.out.println("member2 instance of Member : " + (member2 instanceof Member)); // true
    }


}