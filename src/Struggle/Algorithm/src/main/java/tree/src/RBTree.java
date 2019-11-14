package tree.src;

/**
 * @author LDZ
 * @date 2019-11-14 00:17
 */
public class RBTree<K extends Comparable<K>, V> {

    private RBTreeNode<K, V> root;

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException(" key can't be null");
        }
        return this.get(root, key);
    }

    public V get(RBTreeNode<K, V> node, K key) {
        while (node != null) {
            int compare = key.compareTo(node.getKey());
            if (compare > 0) {
                node = node.getLeft();
            } else if (compare < 0) {
                node = node.getRight();
            } else {
                return node.getValue();
            }
        }
        return null;
    }

    private RBTreeNode<K, V> put(RBTreeNode<K, V> node, K key, V value) {

        if (null == node) {
            return new RBTreeNode<>(key, value, RBTreeNode.Color.BLACK);
        }
        // 插
        int compare = key.compareTo(node.getKey());
        if (compare > 0) {
            node.setRight(put(node.getRight(), key, value));
        } else if (compare < 0) {
            node.setLeft(put(node.getLeft(), key, value));
        } else {
            node.setValue(value);
        }

        // 调整

        if (isRed(node.getRight()) && isBlack(node.getLeft())) {
            // 左旋转
            node = rotateLeft(node);
        }

        if (isRed(node.getLeft()) && isRed(node.getLeft().getLeft())) {
            // 右旋转
            node = rotateRight(node);
        }

        if (isRed(node.getLeft()) && isRed(node.getRight())) {
            // 变色
            flipColors(node);
        }

        return node;
    }

    public boolean contains(K key) {
        return this.get(key) != null;
    }

    public void delete(K key) {
        if (null == key) {
            throw new IllegalArgumentException("error");
        }

        if (!contains(key)) {
            return;
        }


    }

    private boolean isRed(RBTreeNode<K, V> node) {
        if (null == node) {
            return false;
        }
        return RBTreeNode.Color.RED.equals(node.getColor());
    }

    private boolean isBlack(RBTreeNode<K, V> node) {
        return !isRed(node);
    }


    private RBTreeNode<K, V> rotateLeft(RBTreeNode<K, V> node) {
        // 右子树
        RBTreeNode<K, V> parent = node.getRight();
        //
        node.setRight(parent.getLeft());
        parent.setLeft(node);
        parent.setColor(node.getColor());
        // 新插的是红的
        node.setColor(RBTreeNode.Color.RED);
        return parent;
    }

    private RBTreeNode<K, V> rotateRight(RBTreeNode<K, V> node) {
        RBTreeNode<K, V> left = node.getLeft();
        node.setLeft(left.getRight());
        left.setRight(node);
        left.setColor(node.getColor());
        node.setColor(RBTreeNode.Color.RED);
        return left;
    }

    private void flipColors(RBTreeNode<K, V> node) {
        node.setColor(RBTreeNode.Color.RED);
        node.getLeft().setColor(RBTreeNode.Color.BLACK);
        node.getRight().setColor(RBTreeNode.Color.BLACK);
    }

}
