package hi.core.autowired;

import hi.core.member.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {


ApplicationContext ac =
        new AnnotationConfigApplicationContext(TestConfig.class);



@Test
public void callBean(){
    Assertions.assertThrows(NoSuchBeanDefinitionException.class,
            ()->ac.getBean(Member.class));
}



@Configuration
@ComponentScan
static class TestConfig{

    @Autowired(required = false)
    public void setNoBean1(Member member){
        System.out.println("setNoBean1 = " + member);
    }

    @Autowired
    public void setNoBean2(@Nullable Member member){
        System.out.println("setNoBean2 = " + member);

    }

    @Autowired
    public void setNoBean3(Optional<Member> member){
        System.out.println("setNoBean3 = " + member);
    }
}
}
