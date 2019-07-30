package com.YinglishZhi.LeetCode_404;

import com.YinglishZhi.struct.TreeNode;

import java.util.Stack;

/**
 * 左叶子之和
 *
 * @author LDZ
 * @date 2019-07-26 14:34
 */
public class Solution {

    private static final TreeNode TREE_NODE_4 = new TreeNode(4);
    private static final TreeNode TREE_NODE_5 = new TreeNode(5);
    //    private static final TreeNode TREE_NODE_6 = new TreeNode(15);
//    private static final TreeNode TREE_NODE_7 = new TreeNode(7);
    private static final TreeNode TREE_NODE_2 = new TreeNode(2, TREE_NODE_4, TREE_NODE_5);
    private static final TreeNode TREE_NODE_3 = new TreeNode(3);
    private static final TreeNode ROOT = new TreeNode(1, TREE_NODE_2, TREE_NODE_3);

    public static int sumOfLeftLeaves(TreeNode root) {
        int result = 0;
        Stack<TreeNode> stack = new Stack<>();

        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode p = stack.pop();

            if (null != p.right) {
                stack.push(p.right);
            }

            if (null != p.left) {
                stack.push(p.left);
                if (null == p.left.left && null == p.left.right) {
                    result = result + p.left.val;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(sumOfLeftLeaves(ROOT));
    }
}
