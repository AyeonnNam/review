package hi.core.autowired.allbean;

import hi.core.AutoAppConfig;
import hi.core.discount.DiscountPolicy;
import hi.core.discount.FixDiscountPolicy;
import hi.core.member.Grade;
import hi.core.member.Member;
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
        public void AllBeanTest(){
        ApplicationContext ac =
                new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
            DiscountService discountService = ac.getBean(DiscountService.class);
            Member member = new Member(1L,"남아연",Grade.VIP);
            int discount = discountService.Discount(member, 20000, "rateDiscountPolicy");
            Assertions.assertThat(discount).isEqualTo(2000);
            System.out.println("discount = " + discount);


        }




    @Component
   public static class DiscountService{

    private final List<DiscountPolicy> discountPolicies;
    private final Map<String, DiscountPolicy> discountPolicyMap;

    @Autowired
        public DiscountService(List<DiscountPolicy> discountPolicies,
                               Map<String, DiscountPolicy> discountPolicyMap) {
            this.discountPolicies = discountPolicies;
            this.discountPolicyMap = discountPolicyMap;
        }

        public int Discount(Member member, int itemPrice, String discountName){
            DiscountPolicy discountPolicy = discountPolicyMap.get(discountName);
            System.out.println("discountPolicy = " + discountPolicy);
            return discountPolicy.discount(member,itemPrice);
        }
    }


}
