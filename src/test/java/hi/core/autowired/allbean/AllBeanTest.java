package hi.core.autowired.allbean;

import hi.core.AutoAppConfig;
import hi.core.discount.DiscountPolicy;
import hi.core.discount.FixDiscountPolicy;
import hi.core.member.Grade;
import hi.core.member.Member;
import hi.core.member.MemberService;
import hi.core.order.Order;
import hi.core.order.OrderService;
import hi.core.order.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.support.descriptor.DirectorySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;



public class AllBeanTest {

    @Test
    public void discountService(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);
        MemberService memberService = ac.getBean(MemberService.class);

        Member member = new Member(2L, "남아연",Grade.VIP);
        memberService.join(member);

        int discountPrice = discountService.Discount(member, 3870000, 1);
        System.out.println("discountPrice = " + discountPrice);
     //   Assertions.assertThat(discountPrice).isEqualTo(387000);
        //index 0 = fixDiscountPolicy
        //index 1 = rateDiscountPolicy

    }


    @Component
      static class DiscountService{
          private final Map<String, DiscountPolicy> discountPolicyMap;
          private final List<DiscountPolicy> discountPolicies;

        public DiscountService(Map<String, DiscountPolicy> discountPolicyMap, List<DiscountPolicy> discountPolicies) {
            this.discountPolicyMap = discountPolicyMap;
            this.discountPolicies = discountPolicies;
        }


          public int Discount(Member member, int price, int discountCode) {
              DiscountPolicy discountPolicy = discountPolicies.get(discountCode);
              int discount = discountPolicy.discount(member,price);


              return discount;
          }
      }


}
