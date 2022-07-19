package hi.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.test.autoconfigure.data.cassandra.DataCassandraTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

        //ComponentScan시
        //includeFilters 추가
        //@MyIncludeComponent 태그 붙일 시
        //BeanA beanA = ac.getBean(BeanA.class);
        //assertThat(beanA).isNotNull();


        //@ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE, classes = BeanA.class)
        assertThrows(NoSuchBeanDefinitionException.class,()-> ac.getBean(BeanA.class));

        // excludeFilters =@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class
        assertThrows(NoSuchBeanDefinitionException.class,()-> ac.getBean(BeanB.class));
    }




    @Configuration
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters ={@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class

            ), @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = BeanA.class)
            }  )
    static class ComponentFilterAppConfig {

    }




  /*  @Test
    void filterScan(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(MyComponentFilterScanConfig.class);
       BeanA bean = ac.getBean(BeanA.class);
      //  BeanB beanB = ac.getBean(BeanB.class);
        assertThat(bean).isNotNull();
        assertThrows(NoSuchBeanDefinitionException.class, ()-> ac.getBean(BeanB.class));

    }



    //type= FilterType.ANNOTATION : Default

    @ComponentScan(includeFilters = @ComponentScan.Filter(classes = MyIncludeComponent.class),
    excludeFilters = @ComponentScan.Filter(classes = MyExcludeComponent.class))
    @Configuration
    static class MyComponentFilterScanConfig{

    }
*/}
