package Tree.src;

import lombok.Data;

/**
 * 树结构
 *
 * @author LDZ
 * @date 2019-07-25 23:03
 */
@Data
public class Node<T> {

    /**
     * 存储数据
     */
    private T data;

    /**
     * 左子树
     */
    private Node<T> leftChild;

    /**
     * 右子树
     */
    private Node<T> rightChild;

    public Node(T data) {
        this.data = data;
        this.leftChild = null;
        this.rightChild = null;
    }

    public Node(T data, Node<T> leftChild, Node<T> rightChild) {
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
