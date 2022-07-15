package hi.core;

import hi.core.member.Grade;
import hi.core.member.Member;
import hi.core.member.MemberService;
import hi.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        // AppConfig appConfig = new AppConfig();
       // MemberService memberService = appConfig.memberService();
        memberService.join(new Member(1L,"남아연", Grade.VIP));


        Member findMember = memberService.findMember(1L);



        System.out.println(findMember.getGrade());
        System.out.println(findMember.getId());
        System.out.println(findMember.getName());



    }
}
