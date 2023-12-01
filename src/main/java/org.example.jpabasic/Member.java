package org.example.jpabasic;

import javax.persistence.*;

@Entity
public class Member extends BaseEntity {


    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩 LAZY >> join없이 Team을 프록시로 조회
//    @ManyToOne(fetch = FetchType.EAGER) // 즉시 로딩 EAGER >> join해서 Team도 원본 엔티티로 조회
    @JoinColumn(name = "TEAM_ID")
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