package hi.core.scan;

import hi.core.AutoAppConfig;
import hi.core.MemberRepository;
import hi.core.discount.DiscountPolicy;
import hi.core.discount.RateDiscountPolicy;
import hi.core.member.Grade;
import hi.core.member.Member;
import hi.core.member.MemberService;
import hi.core.member.MemberServiceImpl;
import hi.core.order.Order;
import hi.core.order.OrderService;
import hi.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class AutoAppConfigTests {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);


    @Test
    void basicScan(){
      //Overriding bean definition for bean 'memoryMemberRepository' with a different definition
//        MemberRepository memberRepository = ac.getBean(MemberRepository.class);
//       // System.out.println("memberRepository = " + memberRepository.getClass());
//        MemberService memberService = ac.getBean(MemberService.class);
//        MemberServiceImpl memberService1 = ac.getBean(MemberServiceImpl.class);
//
//        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);

        Member member = new Member(2L,"남아연", Grade.VIP);
        MemberService memberService = ac.getBean(MemberService.class);
        memberService.join(member);
        OrderService orderService = ac.getBean(OrderService.class);
        Order order = orderService.createOrder(2L, "과자", 200000);
        int discountPrice = order.getDiscountPrice();
        Assertions.assertThat(discountPrice).isEqualTo(1000);
        System.out.println("discountPrice = " + discountPrice);



//

//        MemberService memberService = ac.getBean(MemberService.class);
//
//        assertThat(memberService).isInstanceOf(MemberService.class);
//
//        OrderService bean = ac.getBean(OrderService.class);
//
//        OrderServiceImpl bean1 = ac.getBean(OrderServiceImpl.class);
//        MemberRepository memberRepository = bean1.getMemberRepository();
//        System.out.println("memberRepository = " + memberRepository);


    }

    //0719 컴포넌트 스캔을 사용할때
    // 해당생성자에 @Autowired를 명시하지않아도 default로 지정이 되어있어 자동으로 의존관계주입이 가능하다.


}
