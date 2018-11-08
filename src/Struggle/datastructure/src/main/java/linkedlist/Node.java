package linkedlist;

/**
 * 链表
 *
 * @author LDZ
 * @date
 */
public class Node {

    /**
     * 数据
     */
    private Object data;

    /**
     * 下个节点
     */
    private Node next;

    public Node(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
