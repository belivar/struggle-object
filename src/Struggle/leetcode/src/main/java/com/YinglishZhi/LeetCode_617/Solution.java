package com.YinglishZhi.LeetCode_617;

import com.YinglishZhi.struct.TreeNode;

import java.util.Stack;
import java.util.function.Function;

/**
 * 合并二叉树
 *
 * @author LDZ
 * @date 2019-08-24 13:14
 */
public class Solution {

    public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {

        if (null == t1 && null == t2) {
            return null;
        }
        if (null == t1) {
            return t2;
        }

        if (null == t2) {
            return t1;
        }


        TreeNode result = new TreeNode(t1.val + t2.val);
        Stack<TreeNode[]> stack = new Stack<>();
        TreeNode[] treeNodes = {t1, t2, result};
        stack.push(treeNodes);
        while (!stack.isEmpty()) {
            TreeNode[] treeNode = stack.pop();
            if ((treeNode[0] != null && null != treeNode[0].right) || (null != treeNode[1] && null != treeNode[1].right)) {
                treeNode[2].right = new TreeNode((null == treeNode[0] || null == treeNode[0].right ? 0 : treeNode[0].right.val) + (null == treeNode[1] || null == treeNode[1].right ? 0 : treeNode[1].right.val));
                TreeNode[] tmp = {treeNode[0] != null ? treeNode[0].right : null, treeNode[1] != null ? treeNode[1].right : null, treeNode[2].right};
                stack.push(tmp);
            }
            if ((null != treeNode[0] && null != treeNode[0].left) || (null != treeNode[1] && null != treeNode[1].left)) {
                treeNode[2].left = new TreeNode((null == treeNode[0] || null == treeNode[0].left ? 0 : treeNode[0].left.val) + (null == treeNode[1] || null == treeNode[1].left ? 0 : treeNode[1].left.val));
                TreeNode[] tmp = {treeNode[0] != null ? treeNode[0].left : null, treeNode[1] != null ? treeNode[1].left : null, treeNode[2].left};
                stack.push(tmp);
            }
        }
        return result;
    }


    public static TreeNode mergeTreesV2(TreeNode t1, TreeNode t2) {


        if (null == t1) {
            return t2;
        }

        if (null == t2) {
            return t1;
        }


        TreeNode result = new TreeNode(t1.val + t2.val);

        result.left = mergeTreesV2(t1.left, t2.left);
        result.right = mergeTreesV2(t1.right, t2.right);

        return result;


    }


    public static void main(String[] args) {
        TreeNode t1D = new TreeNode(5);
        TreeNode t1C = new TreeNode(2);
        TreeNode t1B = new TreeNode(3, t1D, null);
//        TreeNode t1 = new TreeNode(1, t1B, t1C);
        TreeNode t1 = null;

        TreeNode t2D = new TreeNode(7);
        TreeNode t2E = new TreeNode(4);
        TreeNode t2C = new TreeNode(3, null, t2D);
        TreeNode t2B = new TreeNode(1, null, t2E);
//        TreeNode t2 = new TreeNode(2, t2B, t2C);
        TreeNode t2 = new TreeNode(2);

        TreeNode treeNode = mergeTrees(t1, t2);
        System.out.println(treeNode);
    }
}
