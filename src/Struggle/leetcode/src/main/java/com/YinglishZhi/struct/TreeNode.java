package com.YinglishZhi.struct;

/**
 * 树
 *
 * @author LDZ
 * @date 2019-07-26 12:08
 */
public class TreeNode {

    /**
     * val
     */
    public int val;

    /**
     * left node
     */
    public TreeNode left;

    /**
     * right node
     */
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

}
