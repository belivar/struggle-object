package binary;

import java.util.logging.Logger;

/**
 * 位运算 实现加减乘除
 *
 * @author LDZ
 * @date 2019-06-19 20:53
 */
public class solution {

    private static Logger logger = Logger.getLogger(solution.class.getName());

    /**
     * 位移计算加法
     *
     * @param a a
     * @param b b
     * @return 和
     */
    public static int addByBit(int a, int b) {

        if (0 == b) {
            return a;
        }
        int sum = a ^ b;
        int carry = (a & b) << 1;
        return addByBit(sum, carry);
    }

    public static int addByBitV2(int a, int b) {
        int sum = a ^ b;
        int carry = (a & b) << 1;
        while (0 != carry) {
            int at = sum;
            int bt = carry;
            sum = at ^ bt;
            carry = (at & bt) << 1;
        }
        return sum;
    }

    public static int substractByBit(int a, int b) {
        // 先求减数的补码 取反 +1
        int subtractor = addByBitV2(~b, 1);
        return addByBitV2(a, subtractor);
    }

    public static int multifyByBit(int a, int b) {
        int multiplicand = a < 0 ? addByBitV2(~a, 1) : a;
        int multiplier = b < 0 ? addByBitV2(~b, 1) : b;

        int product = 0;
        int count = 0;

        while (count < multiplier) {
            product = addByBitV2(product, multiplicand);
            count = addByBitV2(count, 1);
        }
        return (a ^ b) < 0 ? addByBitV2(~product, 1) : product;
    }

    public static int multifyByBitV2(int a, int b) {
        int multiplicand = a < 0 ? addByBitV2(~a, 1) : a;
        int multiplier = b < 0 ? addByBitV2(~b, 1) : b;

        int product = 0;
        while (multiplier > 0) {
            if ((multiplier & 0x1) > 0) {
                product = addByBitV2(product, multiplicand);
            }
            multiplicand = multiplicand << 1;
            multiplier = multiplier >> 1;
        }
        return (a ^ b) < 0 ? addByBitV2(~product, 1) : product;
    }

    public static int divideByBit(int a, int b) {
        // 除数
        int dividend = a < 0 ? addByBitV2(~a, 1) : a;
        // 被除数
        int divisor = b < 0 ? addByBitV2(~b, 1) : b;
        // 商
        int quotient = 0;
        // 余数
        int remainder = 0;
        while (dividend >= divisor) {
            quotient = addByBit(quotient, 1);
            dividend = substractByBit(dividend, divisor);
        }
        return (a ^ b) < 0 ? addByBitV2(~quotient, 1) : quotient;

    }

    public static int divideByBitV2(int a, int b) {
        // 除数
        int dividend = a < 0 ? addByBitV2(~a, 1) : a;
        // 被除数
        int divisor = b < 0 ? addByBitV2(~b, 1) : b;
        // 商
        int quotient = 0;
        // 余数
        int remainder = 0;
        for (int i = 31; i >= 0; i--) {
            if ((dividend >> i) >= divisor) {
                quotient = addByBitV2(quotient, 1 << i);
                dividend = substractByBit(dividend, divisor << i);
            }
        }
        return (a ^ b) < 0 ? addByBitV2(~quotient, 1) : quotient;
    }

    public static void main(String[] args) {
//        logger.info(String.valueOf(addByBit(71, 22)));
//        logger.info(String.valueOf(addByBitV2(43, 11)));
//        logger.info(String.valueOf(substractByBit(99, 22)));
//        logger.info(String.valueOf(multifyByBit(99, 22)));
//        logger.info(String.valueOf(multifyByBitV2(99, 22)));
//        logger.info(String.valueOf(divideByBit(10, 3)));
        logger.info(String.valueOf(divideByBitV2(-2147483648, -1)));
    }
}
