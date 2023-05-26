package hello.hellospring2.repository;

import hello.hellospring2.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    //Member, Member의 pk 데이터타입

    //스프링 데이터 jpa가 JpaRepository를 받고 있으면 구현체를 자동으로 만들어준다.
    //그리고 자동으로 스프링 빈을 등록한다.
    //우리는 가져다 쓰면 된다.

    //JPQL : select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
