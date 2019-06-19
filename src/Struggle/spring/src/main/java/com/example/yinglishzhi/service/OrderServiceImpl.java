package com.example.yinglishzhi.service;


import com.example.yinglishzhi.service.api.IOrderService;
import com.example.yinglishzhi.vo.CommonPlaceOrderContextVO;
import com.example.yinglishzhi.vo.PlaceOrderRequestVO;
import com.example.yinglishzhi.vo.PlaceOrderResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * order service impl
 *
 * @author YinglishZhi
 * @date 2019/5/23 下午11:02
 **/
@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {


    @Autowired
    ApplicationContext context;

    @Override
    @SuppressWarnings(value = "unchecked")
    public PlaceOrderResultVO placeOrder(PlaceOrderRequestVO placeOrderRequestVO) {
        log.info("下单");
        AbstractPlaceOrderFactory placeOrderFactory = context.getBean(placeOrderRequestVO.getOrderType(), AbstractPlaceOrderFactory.class);
        CommonPlaceOrderContextVO c = placeOrderFactory.newConfig();
        placeOrderFactory.initProperties(placeOrderRequestVO, c);
        return placeOrderFactory.placeOrderChain(c);
    }
}
