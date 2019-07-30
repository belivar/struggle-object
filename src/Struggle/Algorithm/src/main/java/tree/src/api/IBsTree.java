package tree.src.api;

import tree.src.Node;
import tree.src.Tree;

import java.util.List;

/**
 * 二叉排序树
 *
 * @author LDZ
 * @date 2019-07-27 15:05
 */
public interface IBsTree<T> {

    /**
     * create bs tree
     *
     * @param list
     * @return
     */
    Node<T> createBsTree(List<T> list);


    /**
     * insert node
     *
     * @param node
     */
    void insert(Node<T> node);

}
