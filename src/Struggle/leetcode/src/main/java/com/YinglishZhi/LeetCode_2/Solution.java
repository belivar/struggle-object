package com.YinglishZhi.LeetCode_2;

import com.YinglishZhi.struct.ListNode;

/**
 * 两数相加
 *
 * @author LDZ
 * @date 2019-07-30 10:18
 */
public class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        int flag = 0;
        ListNode listNode = result;
        int x;
        while (l1 != null || l2 != null) {
            x = flag;
            if (l1 != null) {
                x += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                x += l2.val;
                l2 = l2.next;
            }
            if (x > 9) {
                flag = 1;
                x = x - 10;
            } else {
                flag = 0;
            }
            result.next = new ListNode(x);
            result = result.next;
        }
        if (flag != 0)
            result.next = new ListNode(1);
        return listNode.next;
    }
}
