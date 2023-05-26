package hello.hellospring2.repository;

import hello.hellospring2.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em; //jpa는 entityManager라는 것으로 모든 것을 동작시킨다.
                                    //build.gradle에서 data-jpa를 받으면 스프링 부트가 EntityManager를 생성해준다
                                    //application.properties의 정보들과 dbconnection 정보를 다 합쳐서 스프링부트가 자동으로 EntityManager를 만들어준다.
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); //조회할 타입과 pk
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {

        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

}
