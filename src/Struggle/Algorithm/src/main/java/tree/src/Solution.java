package tree.src;

import java.util.ArrayList;
import java.util.List;

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
    private static final Node<String> NODE_D = new Node<>("D");
    private static final Node<String> NODE_E = new Node<>("E");
    private static final Node<String> NODE_F = new Node<>("F");
    private static final Node<String> NODE_G = new Node<>("G");
    private static final Node<String> NODE_B = new Node<>("B", NODE_D, NODE_E);
    private static final Node<String> NODE_C = new Node<>("C", NODE_F, NODE_G);
    private static final Node<String> ROOT = new Node<>("A", NODE_B, NODE_C);

    private static final Node<Integer> NODE_1 = new Node<>(1);
    private static final Node<Integer> NODE_2 = new Node<>(2);

    public static void main(String[] args) {

//        tree.preOrderTraversal(ROOT);
//        tree.inOrderTraversal(ROOT);
//        tree.postOrderTraversal(ROOT);
//        tree.nonRecPreOrderTraversal(ROOT);
//        tree.nonRecInOrderTraversal(ROOT);
//        tree.levelOrderTraversal(ROOT);
//        tree.nonRecPostOrderTraversal(ROOT);
//        System.out.println(tree.height(ROOT));
//        int res = NODE_1.compareTo(NODE_2);
//        System.out.println(res);

//        BsTree<Integer> bsTree = new BsTree<>();
//        Node<Integer> root;
//        root = bsTree.insert(null, 1);
//        root = bsTree.insert(root, 2);
//        root = bsTree.insert(root, 4);
//        root = bsTree.insert(root, 5);
//        bsTree.inOrderTraversal(root);
//        System.out.println("=========");
//        root = bsTree.insert(root, 3);
//        bsTree.inOrderTraversal(root);

        // AVL TREE
        AvlTree<Integer> avlTree = new AvlTree<>();
        Node<Integer> root;
        root = avlTree.insert(null, 1);
        root = avlTree.insert(root, 2);
        root = avlTree.insert(root, 4);
        root = avlTree.insert(root, 5);
        root = avlTree.insert(root, 3);
        avlTree.nonRecInOrderTraversal(root);
    }

}
