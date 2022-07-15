package hi.core;

import hi.core.member.Grade;
import hi.core.member.Member;
import hi.core.member.MemberService;
import hi.core.order.Order;
import hi.core.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderApp {

    public static void main(String[] args) {

        //AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        //ac.register(AppConfig.class);
        //ac.refresh();

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);

        //AppConfig appConfig = new AppConfig();

       // MemberService memberService = appConfig.memberService();
       // OrderService orderService = appConfig.orderService();

        Long memberId = 2L;
        Member member = new Member(memberId,"남", Grade.VIP);
        memberService.join(member);
        Order order = orderService.createOrder(2L, "SNACK", 28000000);

        System.out.println("order = " + order);
        System.out.println("order.calculatePrice = " + order.calculatePrice());

    }
}
