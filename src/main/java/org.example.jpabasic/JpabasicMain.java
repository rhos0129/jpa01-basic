package org.example.jpabasic;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class JpabasicMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabasic");
        EntityManager em = emf.createEntityManager();

        // JPA의 모든 데이터 변경은 트랜잭션 안에서 일어나야한다.
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setUsername("aaa");
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("bbb");
            em.persist(member2);

            em.flush();
            em.clear();

//            // ==========
//            // select문 출력
//            Member findMember = em.find(Member.class, member.getId());
//
//            // id값은 가지고 있으므로 select문 출력 안함
//            Member referenceMember = em.getReference(Member.class, member.getId());
//            System.out.println("referenceMember = " + referenceMember.getClass()); // class org.example.jpabasic.Member$HibernateProxy$fw0TcFAE (가짜엔티티 프록시)
//            System.out.println("referenceMember.id = " + referenceMember.getId());
//
//            // findMember를 실제 사용하는 시점에 영속성컨텍스트에게 초기화 요청 후 select문 출력함
//            System.out.println("referenceMember.username = " + referenceMember.getUsername());
//
//            // 초기화되어 있으므로 select문 출력 안함
//            System.out.println("referenceMember.username = " + referenceMember.getUsername());


//            // ==========
//            // 프록시 객체는 원본 엔티티를 상속받으므로 타입 체크시 주의
//            // == 비교 실패, instance of 사용
//
//            Member findMember = em.find(Member.class, member.getId());
//            Member referenceMember2 = em.getReference(Member.class, member2.getId());
//
//            logic(findMember, referenceMember2);


//            // ==========
//            // 영속성 컨텍스트에 찾는 엔티티가 이미 있으면 em.getReference()를 호출해도 실제 엔티티 반환
//            Member findMember = em.find(Member.class, member.getId());
//            System.out.println("findMember = " + findMember.getClass()); // class org.example.jpabasic.Member (원본 엔티티)
//
//            Member referenceMember = em.getReference(Member.class, 2.getId());
//            System.out.println("referenceMember = " + referenceMember.getClass()); // class org.example.jpabasic.Member (원본 엔티티)
//
//            // JPA는 동일성을 보장해야하기 때문
//            System.out.println("findMember == referenceMember : " + (findMember == referenceMember )); // true


//            // ==========
//            // 반대도 마찬가지임
//            Member referenceMember = em.getReference(Member.class, member.getId());
//            System.out.println("referenceMember = " + referenceMember.getClass()); // 프록시
//
//            Member findMember = em.find(Member.class, member.getId());
//            System.out.println("findMember = " + findMember.getClass()); // 원본 엔티티가 아님 프록시
//
//            // 동일성 보장됨
//            System.out.println("referenceMember == findMember : " + (referenceMember == findMember )); // true


//            // ==========
//            // 영속성 컨텍스트의 도움을 받을 수 없는 상태일 때 프록시를 초기화하면 문제 발생
//            Member referenceMember = em.getReference(Member.class, member.getId());
//            System.out.println("referenceMember = " + referenceMember.getClass()); // 프록시
//
////            em.detach(referenceMember); // 준영속
//            em.clear();
//
//            System.out.println("referenceMember.username = " + referenceMember.getUsername());
//            // >> 에러 발생 : org.hibernate.LazyInitializationException: could not initialize proxy [org.example.jpabasic.Member#43] - no Session


            // ==========
            Member referenceMember = em.getReference(Member.class, member.getId());
            System.out.println("referenceMember = " + referenceMember.getClass()); // 프록시
//            System.out.println("referenceMember.username = " + referenceMember.getUsername()); // 프록시 강제 초기화

            // 프록시 인스턴스의 초기화 여부 확인
            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(referenceMember));

            // 프록시 클래스 확인 방법
            System.out.println("name = " + referenceMember.getClass().getName());

            // 프록시 강제 초기화 (JPA 표준은 강제 초기화 없음)
            Hibernate.initialize(referenceMember);


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