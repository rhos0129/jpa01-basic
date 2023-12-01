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

            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            em.flush();
            em.clear();

            // 영속성 전이
            em.persist(parent);
            em.persist(child1);
            em.persist(child2);

            // 고아객체
            Parent findParent = em.find(Parent.class, parent.getId());
            findParent.getChildren().remove(child1); // child1도 같이 삭제
            em.remove(findParent); // 모든 child 삭제

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