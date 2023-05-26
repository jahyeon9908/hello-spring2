package hello.hellospring2.repository;

import hello.hellospring2.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member(); //member라는 객체를 만들어서
        member.setName("spring"); //spring이라는 이름을 줌

        repository.save(member); //member를 save에 넣어주면 id가 자동으로 생성됨

        //result라는 Member 객체를 만들어서 저장된 member의 id를 넣어준다.
        Member result = repository.findById(member.getId()).get();
        Assertions.assertThat(member).isEqualTo(result);
        //Assertions.assertThat(member).isEqualTo(null);


        //그리고 둘이 비교해서 같으면 true, 다르면 false가 출력되게 한다.
        //System.out.println("result = "+ (result == member));
    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        Assertions.assertThat(result).isEqualTo(member1);
    }
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);

    }
}