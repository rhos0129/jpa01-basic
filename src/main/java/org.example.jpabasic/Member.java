package org.example.jpabasic;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {


    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne // 일대다 양방향
    @JoinColumn(insertable = false, updatable = false) // 억지로 읽기전용으로 만든다.
    private Team team;

    @OneToOne // 일대일 단방향 - 주테이블에 외래키
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

//    @ManyToMany // 다대다 단방향
//    @JoinTable(name = "MEMBER_PRODUCT") // 중간테이블 create문
//    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "member") // 다대다 한계 극복
    private List<MemberProduct> memberProducts = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Locker getLocker() {
        return locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }

//    public List<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<Product> products) {
//        this.products = products;
//    }


    public List<MemberProduct> getMemberProducts() {
        return memberProducts;
    }

    public void setMemberProducts(List<MemberProduct> memberProducts) {
        this.memberProducts = memberProducts;
    }
}