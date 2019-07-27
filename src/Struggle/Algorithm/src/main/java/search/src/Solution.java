package search.src;

import search.src.api.ISearch;

/**
 * @author LDZ
 * @date 2019-07-27 11:50
 */
public class Solution {


    private static final int[] NUMS = {1, 2, 3, 4, 5, 6};
    private static final int KEY = 3;


    public static void main(String[] args) {
        ISearch search = new Search();

       int res = search.binarySearch(NUMS, KEY);

        System.out.println(res);
    }
}
