package com.example.yinglishzhi.vo;

import lombok.Data;

/**
 * 下单基本上下文
 *
 * @author YinglishZhi
 * @date 2019/5/23 下午10:42
 **/
@Data
public class CommonPlaceOrderContextVO {


    /**
     * 用户uid
     */
    private Long uid;

    /**
     * 下单生成id
     */
    private Long oid;
}
