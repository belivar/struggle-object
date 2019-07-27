package tree.src;

import tree.src.api.ITree;

/**
 * solution
 *
 * @author LDZ
 * @date 2019-07-25 23:31
 */
public class Solution {

    /**
     * 树
     * A
     * /        \
     * B           C
     * /    \     /     \
     * D     E    F      G
     */
    private static final Node<String> NODE_D = new Node<String>("D");
    private static final Node<String> NODE_E = new Node<String>("E");
    private static final Node<String> NODE_F = new Node<String>("F");
    private static final Node<String> NODE_G = new Node<String>("G");
    private static final Node<String> NODE_B = new Node<String>("B", NODE_D, NODE_E);
    private static final Node<String> NODE_C = new Node<String>("C", NODE_F, NODE_G);
    private static final Node<String> ROOT = new Node<String>("A", NODE_B, NODE_C);

    public static void main(String[] args) {

        ITree tree = new Tree();
//        tree.preOrderTraversal(ROOT);
//        tree.inorderTraversal(ROOT);
//        tree.postOrderTraversal(ROOT);
//        tree.nonRecPreOrderTraversal(ROOT);
//        tree.nonRecInOrderTraversal(ROOT);

//        tree.levelOrderTraversal(ROOT);
        tree.nonRecPostOrderTraversal(ROOT);
    }

}
