package com.YinglishZhi.LeetCode_985;

public class Solution {


    public static int[] sumEvenAfterQueries(int[] A, int[][] queries) {

        int sumEven = resultOfEven(A);

        int[] res = new int[A.length];

        for (int i = 0; i < queries.length; i++) {

            int[] query = queries[i];

            int tempA = A[query[1]];

            A[query[1]] += query[0];

            boolean tempAEven = tempA % 2 == 0;

            boolean query0Even = query[0] % 2 == 0;
String
            if (tempAEven && query0Even) {
                // 都是偶数 安排上 query0
                sumEven = sumEven + query[0];
            } else if (tempAEven) {
                // 原位置 偶数 加个奇数 去掉原来的数
                sumEven = sumEven - tempA;
            } else if (!query0Even) {
                sumEven = sumEven + query[0] + tempA;
            }

            res[i] = sumEven;

        }
        return res;
    }


    private static int resultOfEven(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res += (num % 2 == 0 ? num : 0);
        }
        return res;
    }


    public static void main(String[] args) {
        int[] A = {1, 2, 3, 4};

        int[][] queries = {{1, 0}, {-3, 1}, {-4, 0}, {2, 3}};

        int[] res = sumEvenAfterQueries(A, queries);

        for (int i : res) {
            System.out.println(i);
        }
    }
}
