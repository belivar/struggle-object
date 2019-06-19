package com.example.yinglishzhi.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 订单类型
 *
 * @author YinglishZhi
 * @date 2019/5/23 下午11:00
 **/
@AllArgsConstructor
@Getter
@ToString
public enum OrderTypeEnum {

    /**
     * 优惠券订单
     */
    COUPON_ORDER(1),

    /**
     * 售卖订单
     */
    SALE_ORDER(2);

    /**
     *
     */
    private Integer orderType;
}
