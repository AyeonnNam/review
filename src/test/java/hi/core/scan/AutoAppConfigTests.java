package hi.core.scan;

import hi.core.AutoAppConfig;
import hi.core.member.MemberService;
import hi.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class AutoAppConfigTests {

    ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);


    @Test
    void basicScan(){
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
