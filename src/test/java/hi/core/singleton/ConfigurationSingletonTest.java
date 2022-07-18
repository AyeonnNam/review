package hi.core.singleton;

import hi.core.AppConfig;
import hi.core.MemberRepository;
import hi.core.member.MemberService;
import hi.core.member.MemberServiceImpl;
import hi.core.order.OrderService;
import hi.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);


        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        MemberRepository memberRepository = ac.getBean(MemberRepository.class);

        System.out.println("memberRepository = " + memberRepository);
        System.out.println("memberService = " + memberService.getMemberRepository());
        System.out.println("orderService = " + orderService.getMemberRepository());

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);

    }

    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);
        System.out.println(bean.getClass());
    }
}
