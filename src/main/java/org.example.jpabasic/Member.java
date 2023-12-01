package org.example.jpabasic;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends BaseEntity {


    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne // 일대다 양방향
    @JoinColumn(insertable = false, updatable = false) // 억지로 읽기전용으로 만든다.
    private Team team;

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

}