package com.example.yinglishzhi.service;

import com.example.yinglishzhi.service.api.IConfigurable;
import com.example.yinglishzhi.service.api.IPlaceOrderService;
import com.example.yinglishzhi.vo.CommonPlaceOrderContextVO;
import com.example.yinglishzhi.vo.CommonPlaceOrderResultVO;
import com.example.yinglishzhi.vo.PlaceOrderRequestVO;
import com.example.yinglishzhi.vo.PlaceOrderResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * 下订单抽象接口
 *
 * @author YinglishZhi
 * @date 2019/5/23 下午10:27
 **/
@Slf4j
public abstract class AbstractPlaceOrderFactory<T extends CommonPlaceOrderContextVO, R extends CommonPlaceOrderResultVO> implements IPlaceOrderService<T, R>, IConfigurable<T> {


    private Class<T> configClass;


    protected AbstractPlaceOrderFactory(Class<T> configClass) {
        this.configClass = configClass;
    }


    @Override
    public Class<T> getConfigClass() {
        return configClass;
    }

    @Override
    public T newConfig() {
        return BeanUtils.instantiateClass(this.configClass);
    }


    @Override
    public PlaceOrderResultVO placeOrderChain(T t) {
        verifyOrder(t);
        generateOrder(t);
        accountOrder(t);
        stockOrder(t);
        addOrder(t);
        return getPlaceOrderResult(t);
    }

    @Override
    public R generateOrderChain(T t) {
        verifyOrder(t);
        generateOrder(t);
        accountOrder(t);
        return convertGenerateOrderResult(t);
    }

    /**
     * 初始化 context
     *
     * @param placeOrderRequestVO 下单请求
     * @param t                   context
     */
    protected void initProperties(PlaceOrderRequestVO placeOrderRequestVO, T t) {
        t.setUid(placeOrderRequestVO.getUid());
    }

    /**
     * 校验订单
     *
     * @param t context
     */
    protected abstract void verifyOrder(T t);

    /**
     * 生成订单数据
     *
     * @param t context
     */
    protected abstract void generateOrder(T t);

    /**
     * 核算订单费用
     *
     * @param t context
     */
    protected abstract void accountOrder(T t);

    /**
     * 库存验证
     *
     * @param t context
     */
    protected abstract void stockOrder(T t);

    /**
     * 生成订单数据
     *
     * @param t context
     */
    protected abstract void addOrder(T t);

    /**
     * 订单生成页返回数据
     *
     * @param t context
     * @return 订单生成页数据
     */
    protected abstract R convertGenerateOrderResult(T t);

    /**
     * 获取下单返回数据
     *
     * @param t context
     * @return 下单返回数据
     */
    private PlaceOrderResultVO getPlaceOrderResult(T t) {
        log.info("返回下单数据");
        PlaceOrderResultVO placeOrderResultVO = new PlaceOrderResultVO();
        placeOrderResultVO.setOid(t.getOid());
        return placeOrderResultVO;
    }

}
