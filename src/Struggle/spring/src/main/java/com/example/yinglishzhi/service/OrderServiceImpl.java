package com.example.yinglishzhi.service;


import com.example.yinglishzhi.service.api.IOrderService;
import com.example.yinglishzhi.vo.CommonPlaceOrderContextVO;
import com.example.yinglishzhi.vo.PlaceOrderRequestVO;
import com.example.yinglishzhi.vo.PlaceOrderResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * order service impl
 *
 * @author YinglishZhi
 * @date 2019/5/23 下午11:02
 **/
//@Service("orderService")
@Slf4j
public class OrderServiceImpl implements IOrderService {


    @Autowired
    ApplicationContext context;

    @Override
    @SuppressWarnings(value = "unchecked")
    public PlaceOrderResultVO placeOrder(PlaceOrderRequestVO placeOrderRequestVO) {
        log.info("下单");

        ApplicationContext context1 = new ClassPathXmlApplicationContext("bean.xml");

        ClassPathResource classPathResource = new ClassPathResource("bean.xml");

        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(defaultListableBeanFactory);

        reader.loadBeanDefinitions(classPathResource);


        AbstractPlaceOrderFactory placeOrderFactory = context.getBean(placeOrderRequestVO.getOrderType(), AbstractPlaceOrderFactory.class);
        CommonPlaceOrderContextVO c = placeOrderFactory.newConfig();
        placeOrderFactory.initProperties(placeOrderRequestVO, c);
        return placeOrderFactory.placeOrderChain(c);


    }

}
