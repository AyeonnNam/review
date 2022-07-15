package hi.core.BeanFind;

import hi.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {


    //모든 빈 출력하기
    //내가 등록한 빈만 출력하기

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("object = " + bean + ", name = " + beanDefinitionName);

        }




    }

    @Test
    @DisplayName("내가 등록한 빈만 출력하기")
    void findMyBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("object = " + bean + ", name = " + beanDefinitionName );
            }

        }
    }























//    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
//
//
//    @Test
//    @DisplayName("모든 빈 출력하기")
//    void findAllBean(){
//        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
//        for (String beanDefinitionName : beanDefinitionNames) {
//            Object bean = ac.getBean(beanDefinitionName);
//            System.out.println("name = " + beanDefinitionName +", object = " + bean );
//        //스프링 내부 기반 빈 + 내가 등록한 빈
//
//        }
//
//    }
//
//
//    @Test
//    @DisplayName("등록한 빈 출력하기")
//    void findApplicationBean(){
//        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
//        for (String beanDefinitionName : beanDefinitionNames) {
//            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
//            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
//                Object bean = ac.getBean(beanDefinitionName);
//                System.out.println("object = " + bean + ", name = " + beanDefinitionName);
//
//            }
//        }
//
//    }





        }
