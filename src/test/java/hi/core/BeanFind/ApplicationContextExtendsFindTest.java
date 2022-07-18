package hi.core.BeanFind;

import hi.core.discount.DiscountPolicy;
import hi.core.discount.FixDiscountPolicy;
import hi.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ApplicationContextExtendsFindTest {


    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);


    @Test
    @DisplayName("부모 타입으로 조회시 자식이 둘 이상있으면, 중복 오류가 발생한다")
    public void findBeanByParentDuplicate(){
        //DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,()->ac.getBean(DiscountPolicy.class) );
    }

    @Test
    @DisplayName("부모 타입으로 조회시 자식이 둘 이상 있으면, 빈 이름을 지정해서 호출하면 된다.")
    public void findBeanByParentTypeBeanName(){
        Object fixDiscountPolicy = ac.getBean("fixDiscountPolicy");
        assertThat(fixDiscountPolicy).isInstanceOf(FixDiscountPolicy.class);
    }

    @Test
    @DisplayName("특정하위타입으로 찾기")
    public void findBeanByPSubType(){
        FixDiscountPolicy bean = ac.getBean(FixDiscountPolicy.class);
        assertThat(bean).isInstanceOf(FixDiscountPolicy.class);
    }


    @Test
    @DisplayName("부모타입으로 모두 조회")
    void findAllBeanByParentType(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        for (String key : beansOfType.keySet()) {
           // Object bean = ac.getBean(key);
            System.out.println("key = " + key + ", value = " +ac.getBean(key));
            assertThat(beansOfType.size()).isEqualTo(2);

        }

    }



    @Test
    @DisplayName("부모타입으로 모두 조회하기 - OBJECT")
    public void findByObjectType(){
        Map<String, Object> beansOfType1 = ac.getBeansOfType(Object.class);
        for (String key : beansOfType1.keySet()) {
            System.out.println("key = " + key + ", value = " + ac.getBean(key));
            assertThat(beansOfType1.size()).isEqualTo(16);
        }
        System.out.println(beansOfType1.size());

    }




    @Configuration
    static class TestConfig {

        @Bean //역할과 구분을 쪼개놓음 타입과 메서드를 다르게 해놓은 이유!
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }
}
