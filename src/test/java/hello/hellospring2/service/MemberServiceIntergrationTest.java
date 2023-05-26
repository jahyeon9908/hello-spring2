package hello.hellospring2.service;

import hello.hellospring2.domain.Member;
import hello.hellospring2.repository.MemberRepository;
import hello.hellospring2.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntergrationTest {

    @Autowired //필드 인젝션
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    //현재 스프링에서 등록되어 있는 구현체, SpringConfig한 데서 올라올 거다.

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
        member2.setName("spring1");

        //when
        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> memberService.join(member1));
    }
}