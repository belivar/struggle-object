package com.example.yinglishzhi.service.api;

/**
 * 配置
 *
 * @author YinglishZhi
 * @date 2019/5/23 下午10:32
 **/
public interface IConfigurable<T> {
    /**
     * @return
     */
    T newConfig();

    /**
     * @return
     */
    Class<T> getConfigClass();
}
