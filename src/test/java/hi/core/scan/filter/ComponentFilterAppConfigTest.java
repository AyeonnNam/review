package hi.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ComponentFilterAppConfigTest {


    @Test
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
}
