package com.example.yinglishzhi.service.api;

import com.example.yinglishzhi.vo.PlaceOrderRequestVO;
import com.example.yinglishzhi.vo.PlaceOrderResultVO;

/**
 * order service
 *
 * @author YinglishZhi
 * @date 2019/5/23 下午10:53
 **/
public interface IOrderService {

    /**
     * 下单接口
     *
     * @param placeOrderRequestVO 下单请求
     * @return 下单返回 oid
     */
    PlaceOrderResultVO placeOrder(PlaceOrderRequestVO placeOrderRequestVO);
}
