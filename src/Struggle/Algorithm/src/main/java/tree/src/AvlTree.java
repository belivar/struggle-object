package tree.src;

import tree.src.api.IAvlTree;
import tree.src.api.IBsTree;

import java.util.function.BiFunction;

/**
 * avl
 *
 * @author LDZ
 * @date 2019-08-01 13:30
 */
public class AvlTree<T> extends Tree implements IAvlTree<T> {

    private Node<T> root;

    BiFunction<Node<T>, Node<T>, T> maxBiFunction = (a, b) -> a.compareTo(b) > 0 ? a.getData() : b.getData();

    private int Height(Node<T> node) {
        return null == node ? -1 : node.getHeight();
    }


    @Override
    public Node<T> insert(Node<T> avlNode, T t) {

        if (null == avlNode) {
            return new Node<>(t);
        }
        Node<T> current = new Node<>(t);
        if (avlNode.compareTo(current) < 0) {
            // 插进来的大 插入到右边 可能造成右倾斜
            avlNode.setRightChild(insert(avlNode.getRightChild(), t));
            if (Height(avlNode.getRightChild()) - Height(avlNode.getLeftChild()) == 2) {
                // 右倾斜 左旋
                if (current.compareTo(avlNode.getRightChild()) > 0) {
                    avlNode = rotateLeft(avlNode);
                } else {
                    avlNode = rotateRightAndLeft(avlNode);
                }
            }
        } else if (avlNode.compareTo(current) > 0) {
            // 插进来的小
            avlNode.setLeftChild(insert(avlNode.getLeftChild(), t));
            if (Height(avlNode.getLeftChild()) - Height(avlNode.getRightChild()) == 2) {
                if (current.compareTo(avlNode.getLeftChild()) < 0) {
                    avlNode = rotateRight(avlNode);
                } else {
                    avlNode = rotateLeftAndRight(avlNode);
                }
            }
        }
        avlNode.setHeight(Integer.max(Height(avlNode.getRightChild()), Height(avlNode.getLeftChild())) + 1);
        return avlNode;
    }

    @Override
    public Node<T> insert1(Node<T> avlNode, T t) {

        IBsTree<T> bsTree = new BsTree<>();
        avlNode = bsTree.insert(avlNode, t);


        // 平衡 树
        int heightNow = (Integer.max(Height(avlNode.getRightChild()), Height(avlNode.getLeftChild())) + 1);
        avlNode.setHeight(heightNow);

        int balanceFactor = getBalanceFactor(avlNode);

        if (balanceFactor > 1 && getBalanceFactor(avlNode.getLeftChild()) >= 0) {
            // 左倾斜 且 左子树偏左 右旋
            return rotateRight(avlNode);
        }
        if (balanceFactor > 1 && getBalanceFactor(avlNode.getLeftChild()) < 0) {
            // 左倾斜 且 左子树偏右 先左旋再右旋
            return rotateLeftAndRight(avlNode);
        }

        if (balanceFactor < -1 && getBalanceFactor(avlNode.getRightChild()) <= 0) {
            // 右倾斜 且 右子树偏右 左旋
            return rotateLeft(avlNode);
        }
        if (balanceFactor < -1 && getBalanceFactor(avlNode.getRightChild()) > 0) {
            // 右倾斜 且 右子树偏左 先右旋再左旋
            return rotateRightAndLeft(avlNode);
        }
        return avlNode;
    }

    private int getBalanceFactor(Node<T> node) {
        if (null == node) {
            return 0;
        }
        return Height(node.getLeftChild()) - Height(node.getRightChild());
    }

    /**
     * 右旋
     *
     * @param avlNode 根结点
     * @return 根结点
     */
    private Node<T> rotateRight(Node<T> avlNode) {
        Node<T> left = avlNode.getLeftChild();
        avlNode.setLeftChild(left.getRightChild());
        left.setRightChild(avlNode);
        // 更新高度
        avlNode.setHeight(Integer.max(Height(avlNode.getLeftChild()), Height(avlNode.getRightChild())) + 1);
        left.setHeight(Integer.max(Height(left.getRightChild()), Height(left.getLeftChild())) + 1);
        return left;
    }

    /**
     * 左旋
     *
     * @param avlNode 根结点
     * @return 根结点
     */
    private Node<T> rotateLeft(Node<T> avlNode) {
        Node<T> right = avlNode.getRightChild();
        avlNode.setRightChild(right.getLeftChild());
        right.setLeftChild(avlNode);
        // 更新高度
        avlNode.setHeight(Integer.max(Height(avlNode.getLeftChild()), Height(avlNode.getRightChild())) + 1);
        right.setHeight(Integer.max(Height(right.getRightChild()), Height(right.getLeftChild())) + 1);
        return right;
    }

    /**
     * 双旋转 先左后右
     *
     * @param avlNode 根结点
     * @return 根结点
     */
    private Node<T> rotateLeftAndRight(Node<T> avlNode) {
        avlNode.setLeftChild(rotateLeft(avlNode.getLeftChild()));
        return rotateRight(avlNode);
    }

    /**
     * 双旋转 先右后左
     *
     * @param avlNode 根结点
     * @return 根结点
     */
    private Node<T> rotateRightAndLeft(Node<T> avlNode) {
        avlNode.setRightChild(rotateRight(avlNode.getRightChild()));
        return rotateLeft(avlNode);
    }
}
