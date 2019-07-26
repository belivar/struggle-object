package com.YinglishZhi.LeetCode_94;

import com.YinglishZhi.struct.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的中序遍历
 *
 * @author LDZ
 * @date 2019-07-26 13:14
 */
public class Solution {

    private static final TreeNode TREE_NODE_4 = new TreeNode(4);
    private static final TreeNode TREE_NODE_5 = new TreeNode(5);
    private static final TreeNode TREE_NODE_6 = new TreeNode(6);
    private static final TreeNode TREE_NODE_7 = new TreeNode(7);
    private static final TreeNode TREE_NODE_2 = new TreeNode(2, TREE_NODE_4, TREE_NODE_5);
    private static final TreeNode TREE_NODE_3 = new TreeNode(3, TREE_NODE_6, TREE_NODE_7);
    private static final TreeNode ROOT = new TreeNode(1, TREE_NODE_2, TREE_NODE_3);


    public static List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> res = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();

        TreeNode p = root;

        while (null != p || !stack.isEmpty()) {
            while (null != p) {
                stack.push(p);
                p = p.left;
            }

            if (!stack.isEmpty()) {
                p = stack.pop();
                res.add(p.val);
                p = p.right;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<Integer> res = inorderTraversal(ROOT);
        System.out.println(res);
    }
}
