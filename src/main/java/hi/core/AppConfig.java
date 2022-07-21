package hi.core;

import hi.core.discount.DiscountPolicy;
import hi.core.discount.FixDiscountPolicy;
import hi.core.discount.RateDiscountPolicy;
import hi.core.member.MemberService;
import hi.core.member.MemberServiceImpl;
import hi.core.member.MemoryMemberRepository;
import hi.core.order.OrderService;
import hi.core.order.OrderServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*직접 만든 DI 컨테이너 */
@Configuration
public class AppConfig {

    //애플리케이션의 실제 동작에 필요한 구현 객체를 생성한다
    //생성한 객체 인스턴스의 참조를 생성자를 통해서 주입해준다


    //생성자 주입
    @Bean
    public MemberService memberService(){
        //System.out.println("call AppConfig.memberService");
//        return new MemberServiceImpl(memberRepository());
        return new MemberServiceImpl(memberRepository());
//        return null;

    }

    @Bean
    public OrderService orderService(){
       // System.out.println("call AppConfig.orderService");
       // return new OrderServiceImpl(memberRepository(), discountPolicy());
     return new OrderServiceImpl(memberRepository(),discountPolicy());
//        return null;
    }

    @Bean
    public MemberRepository memberRepository(){
        //System.out.println("call AppConfig.memberRepository");

        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
    }







}
