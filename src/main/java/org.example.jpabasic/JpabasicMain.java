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
//            // 저장 - 오류
//            Member member = new Member();
//            member.setUsername("member1");
//            em.persist(member);
//
//            Team team = new Team();
//            team.setName("TeamA");
//            team.getMembers().add(member); // 연관관계 주인이 아니므로 null이 된다. (읽기전용임)
//            em.persist(team);

            // 저장 - 정상
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
//            member.setTeam(team); // 연관관계 주인이므로 저장된다.
//            member.changeTeam(team); // ***
            em.persist(member);

//            team.getMembers().add(member); // 작동은 안해도 순수한 객체 관계를 고려하면 양쪽 다 입력해야한다.
            // >> 작성 안하고 이후 flush(), clear()도 주석처리하면 조회시 m이 출력되지 않는다..
            // >> 연관관계 편의 메소드 권장
            team.addMember(member); // 편의메소드는 둘중에 한곳에서만 설정

            // select문 조회하기 위해 추가
            em.flush();
            em.clear();

            // 조회 - 양방향
            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();
            for (Member m : members) {
                System.out.println("m = " + m.getUsername());
            }

//            // 무한루프 주의
//            findTeam.toString(); // 에러


            tx.commit();

        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}