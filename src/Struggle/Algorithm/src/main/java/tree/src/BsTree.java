package tree.src;

import tree.src.api.IBsTree;

import java.util.List;

/**
 * @author LDZ
 * @date 2019-07-27 15:11
 */
public class BsTree<T> extends Tree implements IBsTree<T> {

    private Node<T> root;

    @Override
    public Node<T> createBsTree(List<T> list) {
        for (T t : list) {
            insert(t);
        }
        return root;
    }

    private void insert(T t) {
        insert(new Node<>(t));
    }

    @Override
    public void insert(Node<T> node) {
        if (null == root) {
            root = node;
            return;
        }
        Node<T> current = root;
        while (true) {
            if (node.compareTo(current) <= 0) {
                if (null == current.getLeftChild()) {
                    current.setLeftChild(node);
                    return;
                }
                current = current.getLeftChild();
            } else {
                if (null == current.getRightChild()) {
                    current.setRightChild(node);
                    return;
                }
                current = current.getRightChild();
            }
        }
    }
}
