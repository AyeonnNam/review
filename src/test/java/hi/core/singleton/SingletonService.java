package hi.core.singleton;

import hi.core.AppConfig;
import hi.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonService {

    //1.static 영역에 객체를 딱 1개만 생성한다.
    private static final SingletonService instance = new SingletonService();
    //생성 불가능

    //2. public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
    public static SingletonService getInstance(){
        return instance;
    }

    //3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    private SingletonService() {
    }


    public void logic(){
        System.out.println("싱글톤 객체 호출");
    }


    /* static 영역에 객체 instance를 미리 하나 생성해서 올려둔다..
    이 객체 인스턴스가 필요하면 오직 getInstance()메서드를 통해서만 조회할 수 있다. 이 메서드를 호출하면 항상 같은 인스턴스를 반환한다.
    딱 1개의 객체 인스턴스만 존재해야 하므로, 생성자를 private로 막아서 혹시라도 외부에서 new 키워드로 객체 인트선트가 생성되는것을 막는다.
    private로 new 키워드를 막아두웠다.
    호출할때 마다 같은 객체 인스턴스르 반환하는 것을 확인할 수 있다.
    싱글톤패턴을 구현하는 방법은 여러가지가 있으나, 여기서는 객체를 미리 생성해두는 가장 단순하고 안전한 방법을 선택했다.
    싱글톤 패턴을 적용하면 고객의 요청이 올 떄마다 객체를 생성하는 것이 아니라, 이미 만들어진 객체를 공유해서 효율적으로 사용할 수 있다.
    하지만 싱글톤 패턴은 다음과 같은 수 많은 문제점들을 가지고 있다.
      - 싱글톤 패턴을 적용을 구현하는 코드 자체가 많이 들어간다.
      - 의존 관계상 클라이언트가 구체 클래스에 의존한다. -> DIP를 위반한다. (getInstance())
      - 클라이언트가 구체 클래스에 의존해서 OCP원칙을 위반할 가능성이 높다
      - 테스트 하기 어렵다
      - 내부 속성을 변경하거나 초기화 하기 어렵다
      - private 생성자로 자식 클래스를 만들기 어렵다
      - 결론적으로 유연성이 없다
      - 안티패턴임

    * */

    //싱글톤 컨테이너
    /* 스프링 컨테이너는 싱글톤 패턴의 문제점을 해결하면서, 개게체 인스턴스를 싱글톤(1개만생성)으로 관리한다.
    * 우리가 학습한 스프링 빈이 바로 싱글톤으로 관리되는 빈이다.
    * 스프링 컨테이너는 싱글톤 패턴을 적용하지 않아도, 객체 인스턴스를 싱글톤으로 관리한다.
    * 스프링 컨테이너는 싱글톤 컨테이너 역할을 한다. 이렇게 싱글톤 객체를 생성하고 관리하는 기능을 싱글톤 레지스트리라 한다.
    * 스프링 컨테이너의 이런 기능 덕분에 싱글톤 패턴의 모든 단점을 해결하면서 객체를 싱글톤으로 유지할 수 있다.
    * 싱글톤 패턴을 위한 지저분한 코드가 들어가지 않아도 된다.
    * DIP, OCP, 테스트, private 생성자로부터 자유롭게 싱글톤 사용 가능하다.
    *
    * */




}


