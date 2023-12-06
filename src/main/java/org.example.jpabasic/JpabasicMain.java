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

            // 값타입 저장 ==========
            Member member = new Member();
            member.setUsername("member");
            member.setHomeAddress(new Address("home", "street", "zipcode"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("보쌈");

//            member.getAddressHistory().add(new Address("old1", "street", "zipcode"));
//            member.getAddressHistory().add(new Address("old2", "street", "zipcode"));
            // 일대다로 변경 >> update문 (연관관계 참고)
            member.getAddressHistory().add(new AddressEntity("old1", "street", "zipcode"));
            member.getAddressHistory().add(new AddressEntity("old2", "street", "zipcode"));

            em.persist(member); // insert문 *6 (값타입은 모두 member에 의존)

            em.flush();
            em.clear();


            // 값타입 조회 ==========
            Member findMember = em.find(Member.class, member.getId()); // select member > 지연로딩
//            List<Address> addressHistory = findMember.getAddressHistory(); // select address > 지연로딩
//            for (Address address : addressHistory) {
//                System.out.println("address.city = " + address.getCity());
//            }
//
//
//            // 값타입 수정 ==========
////            findMember.getHomeAddress().setCity("newCity"); // X
//            Address a = findMember.getHomeAddress();
//            findMember.setHomeAddress(new Address("new", a.getStreet(), a.getZipcode()));
//
//            // 값 타입 컬렉션은 영속성 전이와 고아 객체 제거 기능을 필수로 가진다
//            findMember.getFavoriteFoods().remove("치킨"); // delete
//            findMember.getFavoriteFoods().add("한식"); // insert
//
//            // equals 기준 (컬렉션 다룰때는 꼭 오버라이딩 해야한다.)
//            findMember.getAddressHistory().remove(new Address("old1", "street", "zipcode")); // delete from ADDRESS where MEMBER_ID=?
//            findMember.getAddressHistory().add(new Address("old3", "street", "zipcode")); // insert *2
//            // 주인 엔티티와 연관된 모든 데이터를 삭제하고, 값 타입 컬렉션에 있는 현재 값을 모두 다시 저장한다.\
//            // >> 실무에서는 일대다를 고려하자

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