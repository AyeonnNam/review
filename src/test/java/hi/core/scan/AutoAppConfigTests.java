package hi.core.scan;

import hi.core.AutoAppConfig;
import hi.core.MemberRepository;
import hi.core.member.MemberService;
import hi.core.member.MemberServiceImpl;
import hi.core.order.Order;
import hi.core.order.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class AutoAppConfigTests {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);


    @Test
    void basicScan(){
        MemberService memberService = ac.getBean(MemberService.class);

        assertThat(memberService).isInstanceOf(MemberService.class);

        OrderService bean = ac.getBean(OrderService.class);

    }

    //0719 컴포넌트 스캔을 사용할때
    // 해당생성자에 @Autowired를 명시하지않아도 default로 지정이 되어있어 자동으로 의존관계주입이 가능하다.


}
