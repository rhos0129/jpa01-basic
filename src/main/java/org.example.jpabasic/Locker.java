package org.example.jpabasic;

import javax.persistence.*;

@Entity
public class Locker {

    @Id @GeneratedValue
    @Column(name = "LOCK_ID")
    private Long id;

    private String name;

    @OneToOne(mappedBy = "locker") // 일대일 양방향 - 주테이블에 외래키
    private Member member;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
