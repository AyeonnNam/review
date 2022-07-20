package hi.core;

import hi.core.member.MemberService;
import hi.core.member.MemberServiceImpl;
import hi.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.FilterType.*;

@Configuration
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = ANNOTATION, classes = Configuration.class)}

)
public class AutoAppConfig {

    //@Component가 붙은 빈과 동일한 이름을 가진 빈을 수동등록하기
//    @Bean(name="memoryMemberRepository")
//    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//    }
}
