package search.src;

import search.src.api.ISearch;

/**
 * @author LDZ
 * @date 2019-07-27 11:50
 */
public class Search implements ISearch {

    @Override
    public int binarySearch(int[] nums, int key) {
        int left = 0;
        int right = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] < key) {
                left = mid + 1;
            } else if (nums[mid] > key) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
