package tree.src;

import tree.src.api.IBsTree;

import java.util.List;

/**
 * @author LDZ
 * @date 2019-07-27 15:11
 */
public class BsTree<T> extends Tree implements IBsTree<T> {

    private Node<T> root;

    private int Height(Node<T> node) {
        return null == node ? -1 : node.getHeight();
    }


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

    @Override
    public Node<T> insert(Node<T> bsNode, T t) {

        if (null == bsNode) {
            return new Node<>(t);
        }

        Node<T> current = new Node<>(t);

        if (bsNode.compareTo(current) < 0) {
            // 插进来的大
            bsNode.setRightChild(insert(bsNode.getRightChild(), t));
        } else if (bsNode.compareTo(current) > 0) {
            // 插进来的小
            bsNode.setLeftChild(insert(bsNode.getLeftChild(), t));
        }
        bsNode.setHeight(Integer.max(Height(bsNode.getRightChild()), Height(bsNode.getLeftChild())) + 1);
        return bsNode;
    }
}
