package tree.src;

import lombok.Data;

/**
 * @author LDZ
 * @date 2019-11-14 00:14
 */
@Data
public class RBTreeNode<K extends Comparable<K>, V> {


    enum Color {
        RED,
        BLACK
    }


    private K key;

    private V value;

    private Color color = Color.BLACK;

    private RBTreeNode<K, V> left, right;

    public RBTreeNode(K key, V value, Color color) {
        this.key = key;
        this.value = value;
        this.color = color;
    }
}
