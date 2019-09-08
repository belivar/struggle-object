package com.example.yinglishzhi.service.place;

import com.example.yinglishzhi.service.AbstractPlaceOrderFactory;
import com.example.yinglishzhi.vo.CommonPlaceOrderContextVO;
import com.example.yinglishzhi.vo.CommonPlaceOrderResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

/**
 * 优惠券订单
 *
 * @author YinglishZhi
 * @date 2019/5/23 下午10:51
 **/
@Service("COUPON")
@Slf4j
public class CouponOrderFactory extends AbstractPlaceOrderFactory<CouponOrderFactory.PlaceCouponOrderContext, CouponOrderFactory.PlaceCouponOrderResult> {


    protected CouponOrderFactory() {
        super(PlaceCouponOrderContext.class);
    }

    @Override
    protected void verifyOrder(PlaceCouponOrderContext placeCouponOrderContext) {
        ClassPathResource classPathResource = new ClassPathResource("bean.xml");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(classPathResource);

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        applicationContext.getBean()
    }

    @Override
    protected void generateOrder(PlaceCouponOrderContext placeCouponOrderContext) {

    }

    @Override
    protected void accountOrder(PlaceCouponOrderContext placeCouponOrderContext) {

    }

    @Override
    protected void stockOrder(PlaceCouponOrderContext placeCouponOrderContext) {

    }

    @Override
    protected void addOrder(PlaceCouponOrderContext placeCouponOrderContext) {

    }

    @Override
    protected PlaceCouponOrderResult convertGenerateOrderResult(PlaceCouponOrderContext placeCouponOrderContext) {
        return null;
    }

    @Override
    public boolean cancelOrder(Long oid, String cancelReason) {
        return false;
    }

    class PlaceCouponOrderContext extends CommonPlaceOrderContextVO {

    }

    class PlaceCouponOrderResult extends CommonPlaceOrderResultVO {

    }
}
