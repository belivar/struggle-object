package linkedlist;

import java.util.ArrayList;

/**
 * 单链表
 */
public class SingleLinkedList {

    /**
     * 长度
     */
    private int size;

    /**
     * 头节点
     */
    private Node head;

    /**
     * 链表头增加元素
     *
     * @param o
     * @return
     */
    public Node addHead(Object o) {
        Node newNode = new Node(o);

        if (0 == size) {
            head = newNode;
        } else {
            newNode.setNext(head);
            head = newNode;
        }
        size++;
        return head;
    }

    /**
     * 从链表头删除元素
     *
     * @return
     */
    public Object deleteHead() {
        Object o = head.getData();
        head = head.getNext();
        size--;
        return o;
    }

    /**
     * 查找指定元素
     *
     * @param o
     * @return
     */
    public Node find(Object o) {
        Node current = head;

        int tempSize = size;

        while (tempSize > 0) {
            if (current.getData().equals(o)) {
                return current;
            } else {
                current = current.getNext();
            }
            tempSize--;
        }

        return null;
    }

    /**
     * 删除节点
     *
     * @param o
     * @return
     */
    public boolean delete(Object o) {
        if (0 == size) {
            return false;
        }
        Node current = head;
        Node previous = head;
        while (!current.getData().equals(o)) {
            if (null == current.getNext()) {
                return false;
            } else {
                previous = current;
                current = current.getNext();
            }
        }
        if (current == head) {
            head = current.getNext();
        } else {
            previous.setNext(current.getNext());
        }
        size--;
        return true;
    }

    /**
     * 判空
     *
     * @return
     */
    public boolean isEmpty() {
        return 0 == size;
    }

    public void display(Node head) {
        if (size > 0) {
            Node node = head;
            int tempSize = size;
            if (size == 1) {
                System.out.println(node.getData());
                return;
            }

            while (tempSize > 0) {
                if (node.equals(head)) {
                    System.out.print("[" + node.getData() + "->");
                } else if (node.getData() == null) {
                    System.out.print(node.getData() + "]");
                } else {
                    System.out.print(node.getData() + "->");
                }

                node = node.getNext();
                tempSize--;
            }
            System.out.println();
        } else {
            System.out.println("[]");
        }
    }

    /**
     * 反转链表 递归
     *
     * @return
     */
    public Node reverseNodeV1(Node head) {
        if (head == null || head.getNext() == null) {
            return head;
        }
        Node reverseHead = reverseNodeV1(head.getNext());
        head.getNext().setNext(head);
        head.setNext(null);
        return reverseHead;
    }

    public Node reverseNodeV2(Node head) {
        if (head == null) {
            return head;
        }
        Node pre = head;
        Node current = head.getNext();
        Node temp;
        while (current != null) {
            temp = current.getNext();
            current.setNext(pre);
            pre = current;
            current = temp;
        }

        head.setNext(null);
        return pre;
    }

    public Node reverseNodeV3(Node head) {
        Node pre = null;

        while (head != null) {
            Node temp = head.getNext();
            head.setNext(pre);
            pre = head;
            head = temp;
        }

        return pre;
    }

    public Node findMiddle(Node head) {
        if (head == null) {
            return head;
        }

        Node slow = head;
        Node fast = head.getNext();

        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }


    public boolean isPalindrome(Node head) {
        if (head == null) {
            return true;
        }

        Node middle = findMiddle(head);
        middle.setNext(reverseNodeV1(middle.getNext()));
        Node p1 = head;
        Node p2 = middle.getNext();

        while (p1 != null && p2 != null && p1.getData().equals(p2.getData())) {
            p1 = p1.getNext();
            p2 = p2.getNext();
        }
        return p2 == null;
    }

    public static void main(String[] args) {
        SingleLinkedList singleList = new SingleLinkedList();
        singleList.addHead("A");
        singleList.addHead("B");
        singleList.addHead("C");
        singleList.addHead("D");
        //打印当前链表信息
        singleList.display(singleList.head);
//        //删除C
//        singleList.delete("C");
//        singleList.display();
//        //查找B
//        System.out.println(singleList.find("B"));

        Node head1 = singleList.reverseNodeV1(singleList.head);

        singleList.display(head1);

        Node head2 = singleList.reverseNodeV2(head1);

        singleList.display(head2);

        Node head3 = singleList.reverseNodeV3(head2);

        singleList.display(head3);
    }
}
