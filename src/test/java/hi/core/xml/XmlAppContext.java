package hi.core.xml;

import hi.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

public class XmlAppContext {

    GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");

    @Test
    public void XmlAppContext(){

        MemberService bean = ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(bean).isInstanceOf(MemberService.class);
    }


}
