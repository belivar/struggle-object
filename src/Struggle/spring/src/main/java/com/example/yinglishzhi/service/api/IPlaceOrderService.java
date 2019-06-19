package com.example.yinglishzhi.service.api;


import com.example.yinglishzhi.vo.PlaceOrderResultVO;

/**
 * 订单接口
 *
 * @param <T> the type of the context to the order
 * @param <R> the type of the result to the order
 * @author YinglishZhi
 * @date 2019/5/23 下午10:11
 **/
public interface IPlaceOrderService<T, R> {

    /**
     * 生成订单页
     *
     * @param t 下单请求参数
     * @return 返回生成订单页所需要的数据
     */
    R generateOrderChain(T t);

    /**
     * 下单接口
     *
     * @param t context
     * @return 下单返回数据
     */
    PlaceOrderResultVO placeOrderChain(T t);

    /**
     * 取消原因
     *
     * @param oid          订单
     * @param cancelReason 取消原因
     * @return 取消结果
     */
    boolean cancelOrder(Long oid, String cancelReason);


}
