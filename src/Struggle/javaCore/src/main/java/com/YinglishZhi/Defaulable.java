package com.YinglishZhi;

/**
 * @author LDZ
 * @date 2019-12-18 21:47
 */
public interface Defaulable {
    default String notRequired() {
        return "Default implementation";

    }
}
