package hi.core.Member;

import hi.core.AppConfig;
import hi.core.member.Grade;
import hi.core.member.Member;
import hi.core.member.MemberService;
import hi.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberServiceTest {



   // MemberService memberService;

    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    MemberService memberService = ac.getBean("memberService", MemberService.class);

//    @BeforeEach
//    public void beforeEach() {
//
//        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
//       memberService = ac.getBean("memberService", MemberService.class);
//
//
//        //AppConfig appConfig = new AppConfig();
//     //memberService = appConfig.memberService();
//
//    }

    @Test
    void join(){

        //given
        Member member = new Member(1L,"남아연", Grade.BASIC);
        memberService.join(member);

        //when
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
        }


        @Test
        void findMember(){
            //given
            Member member = new Member(1L,"남아연", Grade.BASIC);
            memberService.join(member);
            //when
            Member findMember = memberService.findMember(1L);
            //then

            Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
            System.out.println("findMember = " + findMember);
            System.out.println("member = " + member);

        }
}
