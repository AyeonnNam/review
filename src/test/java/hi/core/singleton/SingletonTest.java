package hi.core.singleton;

import hi.core.AppConfig;
import hi.core.member.MemberService;
import hi.core.order.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("pureContainerTest")
    public void pureContainer(){
        //순수한 컨테이너의 문제점
        AppConfig appConfig = new AppConfig();
    //호출할때마다 객체 생성
        OrderService orderService1 = appConfig.orderService();
        OrderService orderService2 = appConfig.orderService();

        Assertions.assertThat(orderService1).isNotSameAs(orderService2);
        System.out.println("orderService2 = " + orderService2);
        System.out.println("orderService1 = " + orderService1);


        //스프링 없는 순수한 DI 컨테이너인 AppConfig는 요청을 할 때마다 객체를 새로 생성한다.
        /* 고객 트래픽이 초당 100이 나오면 초당 100개씩 객체가 생성되고 소멸된다 -> 메모리 낭비
          해결 방안은 해당 객체가 딱 1개만 생성되고 공유하도록 설계하면 된다 -> 싱글톤 패턴

            싱글톤 패턴 :
            클래스의 인스터스가 딱 1개씩 생성되는 것을 보장하는 디자인 패턴이다
            그래서 객체 인스턴스를 2개 이상 생성하지 못하도록 막아야한다.
            private 생성자를 사용해서 외부에서 임의로 new 키워드를 사용하지 못하도록 막아야 한다.

         */

    }
    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    public void singletonServiceTest(){


        SingletonService instance = SingletonService.getInstance();
        SingletonService instance1 = SingletonService.getInstance();

        System.out.println("instance1 = " + instance1);
        System.out.println("instance = " + instance);

        assertThat(instance).isSameAs(instance1);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //1.조회: 호출할 때마다 같은 객체를 반환
        MemberService memberService1 = ac.getBean(MemberService.class);
        //2. 조회 호출 할때마다 같은 객체를 반환
        MemberService memberService2 = ac.getBean(MemberService.class);
        //3. 참조값이 같은 것을 확인
        System.out.println("memberService2 = " + memberService2);
        System.out.println("memberService1 = " + memberService1);
        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }


}
