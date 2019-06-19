package com.example.yinglishzhi.vo;

import lombok.Data;

import java.util.List;

/**
 * 下单请求
 *
 * @author YinglishZhi
 * @date 2019/5/23 下午10:54
 **/
@Data
public class PlaceOrderRequestVO {

    /**
     * 用户uid
     */
    private Long uid;

    /**
     * 设备 @see
     */
    private String device;

    /**
     * 版本
     */
    private String version;

    /**
     * 订单类型
     */
    private String orderType;

    /**
     * 下单物品
     */
    private List<Long> itemList;
}
