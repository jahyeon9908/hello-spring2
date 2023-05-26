package hello.hellospring2.service;

import hello.hellospring2.domain.Member;
import hello.hellospring2.repository.MemberRepository;
import hello.hellospring2.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional //jpa를 쓰려면 항상 있어야 한다//data를 저장하고 변경할 때 필요
public class MemberService {

    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /* 회원 가입 */
    public Long join (Member member){

            validateDuplicateMember(member); //중복회원 검증
            memberRepository.save(member);
            return member.getId();

       //같은 이름이 있는 중복 회원x

       //ifPresent - 값이 있으면 실행하고 아니면 넘어감
       //findByName 해서 result에 값을 넣었는데 만약 찾아진 이름이 있다면 이미 가입한 회원이란것
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*전체 회원 조회 */
    public List<Member> findMembers(){

            return memberRepository.findAll();
    };

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
