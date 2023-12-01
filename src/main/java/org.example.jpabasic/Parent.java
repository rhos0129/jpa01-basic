package org.example.jpabasic;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "parent",
//            cascade = CascadeType.ALL, // 영속성전이 : parent만 저장해도 관련 child들도 다 저장
            orphanRemoval = true // 고아객체 : 다른 곳에서 참조하지 않으면 삭제
    )
    private List<Child> children = new ArrayList<>();

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

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public void addChild(Child child) {
        children.add(child);
        child.setParent(this);

    }
}
