package com.YinglishZhi.LeetCode_965;

import com.YinglishZhi.struct.TreeNode;

import java.util.Stack;

/**
 * 单值二叉树
 *
 * @author LDZ
 * @date 2019-07-29 10:16
 */
public class Solution {

    public static boolean isUnivalTree(TreeNode root) {

        if (null == root) {
            return true;
        }
        int val = root.val;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }

            if (!stack.isEmpty()) {
                p = stack.pop();
                if (val != p.val) {
                    return false;
                }
                p = p.right;
            }
        }
        return true;
    }


    private boolean isUnivalTreeV2(TreeNode root) {
        if (null == root) {
            return true;
        }
        int val = root.val;
        Stack<TreeNode> stack = new Stack<>();

        TreeNode p = root;
        TreeNode pre = null;

        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            p = stack.peek();
            if (p.right == null || p.right == pre) {
                p = stack.pop();
                if (val != p.val) {
                    return false;
                }
                pre = p;
                p = null;
            } else {
                p = p.right;
            }
        }
        return true;
    }
}
