package hi.core.discount;

import hi.core.AppConfig;
import hi.core.member.Grade;
import hi.core.member.Member;
import hi.core.member.MemberService;
import hi.core.member.MemberServiceImpl;
import hi.core.order.Order;
import hi.core.order.OrderService;
import hi.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

   // DiscountPolicy discountPolicy = new RateDiscountPolicy();

    DiscountPolicy discountPolicy;

    @BeforeEach
    public void beforeEach(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        discountPolicy = ac.getBean("discountPolicy", DiscountPolicy.class);
    }
    @Test
    void vip_O() {

        //given
        Member member = new Member(1L,"남아연",Grade.VIP);

        //when
        int discount = discountPolicy.discount(member, 1200000);

        //then
        assertThat(discount).isEqualTo(120000);

    }


    @Test
    void vip_X(){

        //given
        Member member = new Member(1L,"남아연",Grade.BASIC);

        //when
        int discount = discountPolicy.discount(member, 12000000);
        //then
        assertThat(discount).isEqualTo(0);

    }

    //내가 짠 코드


//    MemberService memberService = new MemberServiceImpl();
//    OrderService orderService = new OrderServiceImpl();
//    DiscountPolicy discountPolicy = new RateDiscountPolicy();
//    @Test
//    void DiscountPolicy_vip고객일때(){
//
//        Long memberId = 2L;
//        Member member = new Member(memberId,"남아연", Grade.VIP);
//        memberService.join(member);
//
//        Order order = orderService.createOrder(2L, "부정적인말에휘둘리지마", 1200000);
//        discountPolicy.discount(member,1200000);
//
//        System.out.println("order = " + order);
//        System.out.println("order = " + order.getDiscountPrice());
//        System.out.println("order = " + order.calculatePrice());
//
//    }




}