package hi.core.autowired.allbean;

import hi.core.AutoAppConfig;
import hi.core.discount.DiscountPolicy;
import hi.core.discount.FixDiscountPolicy;
import hi.core.member.Grade;
import hi.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {

    @Test
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);


        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "남아연", Grade.VIP);
        int discountPrice =  discountService.discount(member,20000,"fixDiscountPolicy");
        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);
        System.out.println("discountPrice = " + discountPrice);

    }

   // @Component
    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> polices;

        //동적으로 코드를 선택해야 할때
        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> polices) {
            this.policyMap = policyMap;
            this.polices = polices;
            //System.out.println("policyMap = " + policyMap);
            //System.out.println("polices = " + polices);
        }

       public int discount(Member member, int price, String discountCode) {
           DiscountPolicy discountPolicy = policyMap.get(discountCode);
           return discountPolicy.discount(member, price);


       }
   }
}
