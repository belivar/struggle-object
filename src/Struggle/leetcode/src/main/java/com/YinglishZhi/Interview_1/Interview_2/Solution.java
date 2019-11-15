package com.YinglishZhi.Interview_1.Interview_2;

import com.YinglishZhi.struct.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * // 未完待续。。。
 * 二叉树
 *
 * @author LDZ
 * @date 2019-11-15 11:40
 */
public class Solution {


    private int getNextValue(TreeNode root, int key) {
        if (null == root) {
            throw new IllegalArgumentException(" root can't be null");
        }
        TreeNode preNode = null;
        while (root != null) {
            if (key < root.val) {
                if (null == root.left) {
                    if (null != preNode && preNode.val < key) {
                        return root.val;
                    }
                }
                preNode = root;
                root = root.left;
            } else if (key > root.val) {
                if (null == root.right) {
                    if (null != preNode && preNode.val > key) {
                        return preNode.val;
                    }
                }
                preNode = root;
                root = root.right;
            } else {
                if (root.right != null) {
                    return root.right.val;
                }
                return Optional.ofNullable(preNode).map(p -> p.val).filter(val -> val > key).orElseThrow(() -> new IllegalArgumentException("no value"));
            }
        }

        if (preNode.val > key) {
            return preNode.val;
        }

        throw new IllegalArgumentException("root don't have this value");
    }


    private static final TreeNode l11 = new TreeNode(2);
    private static final TreeNode l12 = new TreeNode(6);
    private static final TreeNode r11 = new TreeNode(10);
    private static final TreeNode r12 = new TreeNode(14);
    private static final TreeNode l1 = new TreeNode(4, l11, l12);
    private static final TreeNode r1 = new TreeNode(12, r11, r12);
    private static final TreeNode root = new TreeNode(8, l1, r1);

    private static final Map<Integer, Integer> CASE_MAP = new HashMap<Integer, Integer>() {{
        put(1, 2);
        put(2, 4);
        put(3, 4);
        put(4, 6);
        put(5, 6);
        put(6, 8);
        put(7, 8);
        put(8, 10);
        put(9, 10);
        put(10, 12);
        put(11, 12);
        put(12, 14);
        put(13, 14);
    }};


    @Test

    public void testCase1() {

        for (Map.Entry<Integer, Integer> entry : CASE_MAP.entrySet()) {
            try {
                int result = getNextValue(root, entry.getKey());
                int expectResult = entry.getValue();
                assertEquals(expectResult, result);
            } catch (Exception e) {
                System.out.println(entry.getKey() + "--" + entry.getValue());
            }
        }


    }

    @Test
    public void testCase2() {
        for (int i = 1; i < 12; i++) {
            int result = getNextValue(root, i);
            System.out.println(i + "==" + String.valueOf(result));
        }
    }
}
