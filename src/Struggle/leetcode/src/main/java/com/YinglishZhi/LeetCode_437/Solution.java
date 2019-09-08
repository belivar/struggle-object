package com.YinglishZhi.LeetCode_437;

import com.YinglishZhi.struct.TreeNode;

/**
 * @author LDZ
 * @date 2019-09-08 22:31
 */
public class Solution {
    private static int pathSum(TreeNode root, int sum) {
        if (null == root) {
            return 0;
        }
        return paths(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }


    private static int paths(TreeNode root, int sum) {

        if (null == root) {
            return 0;
        }

        int res = 0;

        if (root.val == sum) {
            res += 1;
        }

        res += paths(root.left, sum - root.val);
        res += paths(root.right, sum - root.val);

        return res;
    }

}
