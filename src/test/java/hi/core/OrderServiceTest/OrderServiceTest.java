package hi.core.OrderServiceTest;

import hi.core.AppConfig;
import hi.core.member.*;
import hi.core.order.Order;
import hi.core.order.OrderService;
import hi.core.order.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class OrderServiceTest {




     MemberService memberService;
    OrderService orderService ;

    @BeforeEach
    public void beforeEach(){

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        memberService = ac.getBean("memberService", MemberService.class);
         orderService = ac.getBean("orderService", OrderService.class);
//        AppConfig appConfig = new AppConfig();
//         memberService = appConfig.memberService();
//         orderService = appConfig.orderService();
    }


    @Test
    void createOrder_멤버등급이VIP일때(){
        //given
        Member member = new Member(1L,"남아연", Grade.VIP);
        memberService.join(member);
        //when
        Order order = orderService.createOrder(1L, "snack", 10000);
        int cal = order.calculatePrice();
        //then
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
        assertThat(cal).isEqualTo(9000);
        System.out.println("order= " + order);
        System.out.println("cal = " + cal);

    }

    @Test
    void createOrder_멤버등급이BASIC일때(){

        //GIVEN
        Long memberId = 2L;
         Member member = new Member(memberId,"김형준",Grade.BASIC);
         memberService.join(member);

        //WHEN
        Order order = orderService.createOrder(memberId, "너랑결혼할꺼임", 120000);

        //THEN
        assertThat(order.getDiscountPrice()).isEqualTo(0);
        assertThat(order.calculatePrice()).isEqualTo(120000);
        System.out.println("order = " + order);
    }



}
