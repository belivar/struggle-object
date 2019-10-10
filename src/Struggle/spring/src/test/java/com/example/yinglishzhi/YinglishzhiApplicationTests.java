package com.example.yinglishzhi;

import com.example.yinglishzhi.service.OrderServiceImpl;
import com.example.yinglishzhi.service.api.IOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import sun.rmi.rmic.iiop.ClassPathLoader;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YinglishzhiApplicationTests {

    @Resource
    ApplicationContext applicationContext;

    @Test
    public void contextLoads() {

        registerSpringBean("orderService", OrderServiceImpl.class.getName(), null);

        IOrderService orderService = (IOrderService) applicationContext.getBean("orderService");

        System.out.println(orderService.getClass().getClassLoader().getParent());
    }

    public void registerSpringBean(String beanId, String clazzName, Map propertyMap) {
        // beanDefinition
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazzName);
        if (propertyMap != null) {
            Iterator<?> entries = propertyMap.entrySet().iterator();
            Map.Entry<?, ?> entry;
            while (entries.hasNext()) {
                entry = (Map.Entry<?, ?>) entries.next();
                String key = (String) entry.getKey();
                Object value = entry.getValue();
                beanDefinitionBuilder.addPropertyValue(key, value);
            }
        }
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        // beanFactory
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;

        System.out.println("configurableApplicationContext name = " + configurableApplicationContext.getClass().getName());

        BeanDefinitionRegistry beanDefinitionRegistry = (BeanDefinitionRegistry) configurableApplicationContext.getBeanFactory();

        System.out.println("BeanDefinitionRegistry name = " + beanDefinitionRegistry.getClass().getName());
        beanDefinitionRegistry.registerBeanDefinition(beanId, beanDefinition);


    }

}
