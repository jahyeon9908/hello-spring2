package hello.hellospring2.repository;

import hello.hellospring2.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //회원이 등록됨
    //Optional : 요즘은 null이 반환될 때 null 그 자체로 주기보다 optional로 감싸서 준다
    Optional<Member> findById(Long id); //등록된 회원의 id 찾기
    Optional<Member> findByName(String name); //등록된 회원의 name 찾기
    List<Member> findAll(); //지금까지 저장된 모든 회원의 리스트를 준다
}
