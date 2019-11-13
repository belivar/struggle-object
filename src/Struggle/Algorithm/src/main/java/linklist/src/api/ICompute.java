package linklist.src.api;

import linklist.src.LinkNode;

/**
 * @author LDZ
 * @date 2019-11-13 10:50
 */
public interface ICompute {

    LinkNode reverse(LinkNode linkNode);


    boolean hasCycle(LinkNode linkNode);

    LinkNode detectCycle(LinkNode head);

    LinkNode getIntersectionNode(LinkNode headA, LinkNode headB);


    LinkNode middleNode(LinkNode linkNode);

    LinkNode mergeTwoLists(LinkNode linkNode, LinkNode l2);

}
