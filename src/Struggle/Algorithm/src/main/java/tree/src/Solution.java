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

        BsTree bsTree = new BsTree();


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

        List<Integer> NUMS = new ArrayList();
        NUMS.add(1);
        NUMS.add(2);
        NUMS.add(4);
        NUMS.add(5);
        Node root = bsTree.createBsTree(NUMS);
        bsTree.inOrderTraversal(root);
        System.out.println("=========");
        bsTree.insert(new Node(3));
        bsTree.inOrderTraversal(root);
    }

}
