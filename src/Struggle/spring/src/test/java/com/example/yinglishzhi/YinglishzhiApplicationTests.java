package com.example.yinglishzhi;

import com.example.yinglishzhi.entity.User;
import com.example.yinglishzhi.service.OrderServiceImpl;
import com.example.yinglishzhi.service.api.IOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;

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

//        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
//        User user = (User) context.getBean("helloWorld");
//        System.out.println(user.getBrand());
//        ClassPathResource classPathResource = new ClassPathResource("bean.xml");
        org.springframework.core.io.Resource resource = new DefaultResourceLoader().getResource("bean.xml");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(resource);

        User user = (User) factory.getBean("helloWorld");
        System.out.println(user.getBrand());


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
