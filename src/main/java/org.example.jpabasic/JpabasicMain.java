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

            Address address = new Address("city", "street", "zipcode");

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setHomeAddress(address); // *
            em.persist(member1);

//            // 임베디드 타입 같은 값 타입을 여러 엔티티에서 공유하면 위험하다 =====
//            Member member2 = new Member();
//            member2.setUsername("member2");
//            member2.setHomeAddress(address); // *
//            em.persist(member2);
//
//            member1.getHomeAddress().setCity("newCity"); // update문 *2 (사이드 이펙트 발생)


//            // 대신 값(인스턴스)를 복사해서 사용해야 한다. =====
//            Address copyAddress = new Address(
//                    address.getCity(), address.getStreet(), address.getZipcode());
//
//            Member member3 = new Member();
//            member3.setUsername("member3");
//            member3.setHomeAddress(copyAddress);
//            em.persist(member3);
//
//            member1.getHomeAddress().setCity("newCity"); // update문 *1

            // 불변객체 =====
            Address copyAddress = new Address(
                    address.getCity(), address.getStreet(), address.getZipcode());
            member1.setHomeAddress(copyAddress);

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