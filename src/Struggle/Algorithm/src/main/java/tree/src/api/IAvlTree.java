package tree.src.api;

import tree.src.Node;

/**
 * avl 树
 *
 * @author LDZ
 * @date 2019-08-01 11:55
 */
public interface IAvlTree<T> {

    /**
     * insert avl tree
     *
     * @param avlNode root
     * @param t       t
     * @return root
     */
    Node<T> insert(Node<T> avlNode, T t);

}
