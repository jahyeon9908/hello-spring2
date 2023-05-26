package hello.hellospring2.domain;

import javax.persistence.*;

@Entity //jpa가 관리하는 entity가 되는 것이다.
public class Member {

    @Id //pk mapping
    @GeneratedValue(strategy = GenerationType.IDENTITY) //insert 하면 자동으로 id값 주는 기능 - identity 전략
    private Long id; //시스템이 정한다.

    private String name; //고객이 정한다.

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
}
