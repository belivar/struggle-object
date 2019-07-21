package sort.impl;

import sort.ISort;

/**
 * 排序
 *
 * @author LDZ
 * @date 2019-07-21 13:47
 */
public class Sort implements ISort {

    private int[] nums;

    private Sort(int[] nums) {
        this.nums = nums;
    }

    /**
     * 冒泡排序
     * O(n^2)
     *
     * @return
     */
    @Override
    public void bubbleSort() {
        if (0 == nums.length || 1 == nums.length) {
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] < nums[j]) {
                    nums[i] = nums[i] ^ nums[j];
                    nums[j] = nums[i] ^ nums[j];
                    nums[i] = nums[i] ^ nums[j];
                }
            }
        }
    }

    /**
     * 插入排序
     * O(n^2)
     *
     * @return
     */
    @Override
    public void insertSort() {
        if (0 == nums.length || 1 == nums.length) {
            return;
        }
        int current;
        for (int i = 0; i < nums.length - 1; i++) {
            current = nums[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && current > nums[preIndex]) {
                nums[preIndex + 1] = nums[preIndex];
                preIndex--;
            }
            nums[preIndex + 1] = current;
        }
    }

    /**
     * 选择排序
     * O(n^2)
     *
     * @return
     */
    @Override
    public void selectSort() {
        if (0 == nums.length || 1 == nums.length) {
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            int maxIndex = i;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] > nums[maxIndex]) {
                    maxIndex = j;
                }
            }
            if (nums[i] != nums[maxIndex]) {
                nums[i] = nums[i] ^ nums[maxIndex];
                nums[maxIndex] = nums[i] ^ nums[maxIndex];
                nums[i] = nums[i] ^ nums[maxIndex];
            }
        }
    }

    /**
     * 归并排序
     * O(nlogn)
     *
     * @return
     */
    @Override
    public void mergeSort() {
        if (0 == nums.length || 1 == nums.length) {
            return;
        }
        nums = mergeSort(nums);
    }

    private static int[] mergeSort(int[] nums) {
        if (0 == nums.length || 1 == nums.length) {
            return nums;
        }
        int mid = nums.length / 2;
        int[] left = new int[mid];
        int[] right = new int[nums.length - mid];
        System.arraycopy(nums, 0, left, 0, mid);
        System.arraycopy(nums, mid, right, 0, nums.length - mid);
        return merge(mergeSort(left), mergeSort(right));
    }

    /**
     * @param left
     * @param right
     * @return
     */
    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (i >= left.length) {
                result[index] = right[j++];
                continue;
            }
            if (j >= right.length) {
                result[index] = left[i++];
                continue;
            }
            if (left[i] > right[j]) {
                result[index] = left[i++];
            } else {
                result[index] = right[j++];
            }
        }
        return result;
    }

    @Override
    public void quickSort() {
        if (0 == nums.length || 1 == nums.length) {
            return;
        }
        quickSort(nums, 0, nums.length - 1);
    }

    private static void quickSort(int[] nums, int start, int end) {
        if (start < end) {
            int pivot = partition(nums, start, end);
            quickSort(nums, start, pivot - 1);
            quickSort(nums, pivot + 1, end);
        }
    }

    private static int partition(int[] nums, int start, int end) {
        int key = nums[start];
        while (start < end) {
            while (start < end && nums[end] <= key) {
                end--;
            }
            nums[start] = nums[end];
            while (start < end && nums[start] >= key) {
                start++;
            }
            nums[end] = nums[start];
        }
        nums[start] = key;
        return start;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i : nums) {
            sb.append(i).append('>');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] nums = {3, 8, 5, 2, 10, 4};
        ISort sort = new Sort(nums);

        sort.quickSort();

        System.out.println(sort.toString());
    }
}
