package linklist.src;

import linklist.src.api.ICompute;

/**
 * @author LDZ
 * @date 2019-11-13 10:53
 */
public class Compute implements ICompute {


    @Override
    public LinkNode reverse(LinkNode head) {

        if (null == head || null == head.next) {
            return head;
        }
        LinkNode p = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    @Override
    public boolean hasCycle(LinkNode head) {
        if (null == head) {
            return false;
        }
        LinkNode slow = head;
        LinkNode fast = head.next;

        while (null != fast && null != fast.next) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    @Override
    public LinkNode detectCycle(LinkNode head) {
        if (null == head) {
            return null;
        }
        LinkNode slow = head;
        LinkNode fast = head.next;

        while (null != fast && null != fast.next) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                LinkNode tmp = head;
                while (slow != tmp) {
                    slow = slow.next;
                    tmp = tmp.next;
                }
                return slow;
            }
        }
        return null;
    }

    @Override
    public LinkNode getIntersectionNode(LinkNode headA, LinkNode headB) {

        LinkNode p1 = headA;
        LinkNode p2 = headB;

        while (p1 != p2) {
            p1 = p1 != null ? p1.next : headB;
            p2 = p2 != null ? p2.next : headA;
        }

        return p1;
    }

    @Override
    public LinkNode middleNode(LinkNode head) {
        LinkNode slow = head;
        LinkNode fast = head.next;

        while (null != fast && null != fast.next) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    @Override
    public LinkNode mergeTwoLists(LinkNode l1, LinkNode l2) {

        if (null == l1) {
            return l2;
        }
        if (null == l2) {
            return l1;
        }
        LinkNode dummy = new LinkNode(-1, null);
        LinkNode p = dummy;

        while (l1 != null && l2 != null) {
            if (l1.value > l2.value) {
                p.next = l2;
                l2 = l2.next;
            } else {
                p.next = l1;
                l1 = l1.next;
            }
            p = p.next;
        }
        if (l1 != null) {
            p.next = l1;
        }
        if (l2 != null) {
            p.next = l2;
        }
        return dummy.next;
    }

    private static final LinkNode A5 = new LinkNode(9, null);
    private static final LinkNode A4 = new LinkNode(7, A5);
    private static final LinkNode A3 = new LinkNode(5, A4);
    private static final LinkNode A2 = new LinkNode(3, A3);
    private static final LinkNode A1 = new LinkNode(1, A2);

    private static final LinkNode B5 = new LinkNode(10, null);
    private static final LinkNode B4 = new LinkNode(8, B5);
    private static final LinkNode B3 = new LinkNode(6, B4);
    private static final LinkNode B2 = new LinkNode(4, B3);
    private static final LinkNode B1 = new LinkNode(2, B2);

    public static void main(String[] args) {
        ICompute c = new Compute();
        LinkNode r = c.mergeTwoLists(A1, B1);
        System.out.printf(r.toString());
    }
}
