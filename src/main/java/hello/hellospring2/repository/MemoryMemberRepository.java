package hello.hellospring2.repository;

import hello.hellospring2.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) { //member의 이름이 넘어왔다.
        member.setId(++sequence); //store로 가기 전에 id를 등록한다.
        store.put(member.getId(), member); //멤버의 id와 이름 등록
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //결과가 없으면 null일 텐데 그러면 optional에 감싸서 보냄
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        //store에 있는 value 즉 멤버들만 모아다가 리스트로 반환함
    }

    public void clearStore(){
        store.clear();
    }
}
