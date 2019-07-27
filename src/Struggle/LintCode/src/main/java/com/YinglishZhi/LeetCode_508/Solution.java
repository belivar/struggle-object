package com.YinglishZhi.LeetCode_508;

import com.YinglishZhi.struct.TreeNode;

import java.util.*;

/**
 * 出现次数最多的子树元素和
 *
 * @author LDZ
 * @date 2019-07-26 14:54
 */
public class Solution {
    private static final TreeNode TREE_NODE_4 = new TreeNode(4);
    private static final TreeNode TREE_NODE_5 = new TreeNode(5);
    private static final TreeNode TREE_NODE_6 = new TreeNode(6);
    private static final TreeNode TREE_NODE_7 = new TreeNode(7);
    private static final TreeNode TREE_NODE_2 = new TreeNode(2, TREE_NODE_4, TREE_NODE_5);
    private static final TreeNode TREE_NODE_3 = new TreeNode(3, TREE_NODE_6, TREE_NODE_7);
    private static final TreeNode ROOT = new TreeNode(1, TREE_NODE_2, TREE_NODE_3);


    private static final TreeNode TREE_NODE1_2 = new TreeNode(2);
    private static final TreeNode TREE_NODE1_3 = new TreeNode(-5);
    private static final TreeNode ROOT1 = new TreeNode(5, TREE_NODE1_2, TREE_NODE1_3);


    private static int[] findFrequentTreeSum(TreeNode root) {

        Map<TreeNode, Integer> map = new HashMap<>();
        Map<Integer, Integer> count = new HashMap<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode p = root;

        TreeNode pre = null;
        int max = 1;
        while (null != p || !stack.isEmpty()) {
            while (null != p) {
                stack.push(p);
                p = p.left;
            }

            p = stack.peek();

            if (null == p.right || pre == p.right) {
                p = stack.pop();
                int left = p.left == null ? 0 : map.get(p.left);
                int right = p.right == null ? 0 : map.get(p.right);
                int num = p.val + left + right;
                map.put(p, num);
                if (null == count.get(num)) {
                    count.put(num, 1);
                } else {
                    count.put(num, count.get(num) + 1);
                    max = Math.max(count.get(num), max);
                }
                pre = p;
                p = null;
            } else {
                p = p.right;
            }
        }

        List<Integer> res = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (entry.getValue() == max) {
                res.add(entry.getKey());
            }
        }
        int[] result = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            result[i] = res.get(i);
        }
        return result;
    }


    public static void main(String[] args) {
        findFrequentTreeSum(ROOT1);
    }

}
