package hello.hellospring2.service;

import hello.hellospring2.domain.Member;

import hello.hellospring2.repository.MemberRepository;
import hello.hellospring2.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given - 뭔가가 주어졌을 때
        Member member = new Member();
        member.setName("hello");

        //when - 이것을 실행하면
        Long saveId = memberService.join(member);

        //then - 결과가 이게 나와야 한다.
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //두번째 인자인 () -> memberService.join(member2) 이 로직을 실행시켜서
        //첫번째 인자와 같은 타입의 예외가 발생하는지 검사한다.

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}