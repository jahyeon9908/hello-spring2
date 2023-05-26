package hello.hellospring2;

import hello.hellospring2.aop.TimeTraceAop;
import hello.hellospring2.repository.*;
import hello.hellospring2.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//스프링이 뜰 때 @Configuration 읽고 @Bean을 보고 인식해서 memberService()와 memberRepository()를 스프링 빈에 등록하고
// 스프링 빈으로 등록되어 있는 memberRepository를 MemberService에 넣어준다.
public class SpringConfig {

  /*  private DataSource dataSource;
    //스프링이 application.properties를 보고 db와 연결할 수 있는 정보가 있는 dataSource를 만들어준다.

    @Autowired
    public SpringConfig(DataSource dataSource){ //스프링이 만들어준 dataSource를 주입해준다
        this.dataSource = dataSource;
    }
*/
    /*private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em){
        this.em = em;
    }
*/

    //이렇게 하면 spring data jpa가 구현체를 만들어놓은게 등록이 된다
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        //스프링 컨테이너에서 MemberRepository를 찾는데 등록해둔 게 없으나 SpringDataJpaMemberRepository가 있다.
        //인터페이스만 만들어두고 JpaRepository를 상속받으면 스프링 데이터 jpa가 구현체를 만들어내고 빈으로 등록한다
        //그래서 우리가 memberRepository로 인젝션 받을 수 잇다.
        this.memberRepository = memberRepository;
    }

    //멤버서비스에 의존관계 세팅
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

   /* @Bean
    public MemberRepository memberRepository(){
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);
        //return new JpaMemberRepository(em);
    }*/

}


