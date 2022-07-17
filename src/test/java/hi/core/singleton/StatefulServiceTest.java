package hi.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingleTon(){

        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);
        StatefulService statefulService3 = ac.getBean(StatefulService.class);

        //싱글톤을 사용하는 클래스(스프링빈)에 상태를 유지하게 하는 필드를 사용하게 되면
        statefulService1.order("userA", 100000);
        statefulService2.order("userB",200000);
        statefulService3.order("userC",300000);

        //주문결과 공유하게됨, 스프링 빈에는 지역변수를 사용하자
        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(300000);

    }



    @Configuration
    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}
