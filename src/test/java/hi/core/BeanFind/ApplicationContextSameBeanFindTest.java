package hi.core.BeanFind;

import hi.core.AppConfig;
import hi.core.MemberRepository;
import hi.core.discount.DiscountPolicy;
import hi.core.discount.RateDiscountPolicy;
import hi.core.member.MemberService;
import hi.core.member.MemberServiceImpl;
import hi.core.member.MemoryMemberRepository;
import hi.core.order.OrderService;
import hi.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(sameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면 중복 오류가 발생한다.")
    void sameBean(){
        //MemberRepository memberRepository = ac.getBean(MemberRepository.class);
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,()->ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
    void findBeanByName(){
      //  Object memberRepository1 = ac.getBean("memberRepository1");
        MemberRepository memberRepository1 = ac.getBean("memberRepository1", MemberRepository.class);
        System.out.println("memberRepository1 = " + memberRepository1);
        assertThat(memberRepository1).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType (){
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
//            Object bean = ac.getBean(key);
//            System.out.println("object = " + bean + ", name = " + key);

          System.out.println("key = " + key+ ", value = " + ac.getBean(key));
          System.out.println("beansOfType = " + beansOfType);

          //  org.assertj.core.api.Assertions.assertThat(bean).isInstanceOf(MemberRepository.class);

            //size로도 검증 가능하다
            org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(2);
        }
    }



    //static : sameBean scope를 ApplicationContextSameBeanFindTest로 한정함
    @Configuration
    static class sameBeanConfig {
//다른 파라미터값으로 동일한 빈설정이 가능함

        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }

    }


}
