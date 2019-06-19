package com.YinglishZhi.LintCode_3;

import lombok.extern.slf4j.Slf4j;

/**
 * 计算数字k在0到n中的出现的次数，k可能是0~9的一个值
 *
 * @author LDZ
 * @date 2019-01-15
 **/
@Slf4j
public class Solution {


    /**
     * 方法
     *
     * @param k
     * @param n
     * @return
     */
    private static Integer digitCounts(int k, int n) {
        Integer result = 0;

        if (0 == k && 0 == n) {
            return 1;
        }

        for (int base = 1; n / base > 0; base *= 10) {
            int current = (n / base) % 10;
            int low = n - (n / base) * base;
            int high = n / (base * 10);

            int different = Integer.compare(current, k);

            switch (different) {
                case -1:
                    // current < k
                    result += high * base;
                    break;
                case 0:
                    // current == k
                    result += high * base + low + 1;
                    break;
                case 1:
                default:
                    // current > k
                    result += (0 == k) ? high + ((high == 0) ? 1 : 0) : ((high + 1) * base);
                    break;
            }
            log.info("第{}位 是{} 低位{} 高位{}", base, current, low, high);

        }
        return result;
    }


    public static void main(String[] args) {
        log.info("我爱杨惠！！");
        digitCounts(0, 19);
    }
}
