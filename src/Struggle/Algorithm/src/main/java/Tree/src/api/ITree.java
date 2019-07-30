package Tree.src.api;

import Tree.src.Node;

public interface ITree {

    /**
     * return the height of the tree
     *
     * @param node root of the tree
     * @return height
     */
    int height(Node node);

    /**
     * return the size of the tree
     *
     * @param node root of the tree
     * @return size
     */
    int size(Node node);

    /**
     * the mininum depth of tree
     *
     * @param node root of the tree
     * @return depth
     */
    int minDepth(Node node);

    /**
     * the maxinum depth of tree
     *
     * @param node root of the tree
     * @return depth
     */
    int maxDepth(Node node);

    /**
     * pre order
     *
     * @param node root of the tree
     */
    void preOrderTraversal(Node node);

    /**
     * in order
     *
     * @param node root of the tree
     */
    void inOrderTraversal(Node node);

    /**
     * post order
     *
     * @param node root of the tree
     */
    void postOrderTraversal(Node node);

    /**
     * level order
     *
     * @param node root of the tree
     */
    void levelOrderTraversal(Node node);

    /**
     * non rec pre order
     *
     * @param node root of the tree
     */
    void nonRecPreOrderTraversal(Node node);

    /**
     * non rec in order
     *
     * @param node root of the tree
     */
    void nonRecInOrderTraversal(Node node);

    /**
     * non rec post order
     *
     * @param node root of the tree
     */
    void nonRecPostOrderTraversal(Node node);

}
