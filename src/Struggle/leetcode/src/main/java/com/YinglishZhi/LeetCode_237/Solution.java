package com.YinglishZhi.LeetCode_237;

import com.YinglishZhi.struct.ListNode;

/**
 * @author LDZ
 * @date 2019-09-17 14:25
 */
public class Solution {

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

}
