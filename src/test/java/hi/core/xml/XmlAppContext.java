package hi.core.xml;

import hi.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.springframework.context.support.GenericXmlApplicationContext;

public class XmlAppContext {

    public void XmlAppContext(){

        GenericXmlApplicationContext ac = new GenericXmlApplicationContext();
        MemberService bean = ac.getBean(memberService, MemberService.class);
        Assertions.assertThat(bean).isInstanceOf(MemberService.class);
    }
}
