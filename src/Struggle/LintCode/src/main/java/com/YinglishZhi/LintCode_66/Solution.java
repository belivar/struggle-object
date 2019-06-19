package com.YinglishZhi.LintCode_66;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出一棵二叉树，返回其节点值的前序遍历。
 *
 * @author LDZ
 * @date 2019-01-17
 */
@Slf4j
public class Solution {


    private static List<Integer> result = new ArrayList<>();

    /**
     * 递归实现
     *
     * @param root
     * @return
     */
    public static List<Integer> preorderTraversal(TreeNode root) {

        if (null != root) {
            result.add(root.val);
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }
        return result;
    }

    public static void main(String[] args) {
        log.info("我爱杨惠！！");
        TreeNode root = new TreeNode(1);
        preorderTraversal(root);
    }

    static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }
}
