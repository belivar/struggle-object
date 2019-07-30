package Tree.src;

import Tree.src.api.ITree;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * tree
 *
 * @author LDZ
 * @date 2019-07-25 23:47
 */
public class Tree implements ITree {
    @Override
    public int height(Node node) {
        if (null == node) {
            return 0;
        }
        int left = height(node.getLeftChild());
        int right = height(node.getRightChild());
        return 1 + Math.max(left, right);
    }

    @Override
    public int size(Node node) {
        return 0;
    }

    @Override
    public int minDepth(Node node) {
        if (null == node) {
            return 0;
        }
        if (null == node.getRightChild() && null == node.getLeftChild()) {
            return 1;
        }
        int left = minDepth(node.getLeftChild()) + 1;
        int right = minDepth(node.getRightChild()) + 1;
        if (1 == left) {
            left = Integer.MAX_VALUE;
        }
        if (1 == right) {
            right = Integer.MAX_VALUE;
        }
        return left > right ? right : left;
    }

    @Override
    public int maxDepth(Node node) {
        if (null == node) {
            return 0;
        }
        if (null == node.getRightChild() && null == node.getLeftChild()) {
            return 1;
        }
        int left = maxDepth(node.getLeftChild()) + 1;
        int right = maxDepth(node.getRightChild()) + 1;
        return left < right ? right : left;
    }

    @Override
    public void preOrderTraversal(Node node) {
        if (null != node) {
            System.out.println(node.toString());
            preOrderTraversal(node.getLeftChild());
            preOrderTraversal(node.getRightChild());
        }

    }

    @Override
    public void inOrderTraversal(Node node) {
        if (null != node) {
            inOrderTraversal(node.getLeftChild());
            System.out.println(node.toString());
            inOrderTraversal(node.getRightChild());
        }
    }

    @Override
    public void postOrderTraversal(Node node) {
        if (null != node) {
            postOrderTraversal(node.getLeftChild());
            postOrderTraversal(node.getRightChild());
            System.out.println(node.toString());
        }
    }

    @Override
    public void levelOrderTraversal(Node node) {
        Queue<Node> queue = new ArrayDeque<Node>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node p = queue.poll();
            System.out.println(p.toString());
            if (null != p.getLeftChild()) {
                queue.add(p.getLeftChild());
            }
            if (null != p.getRightChild()) {
                queue.add(p.getRightChild());
            }
        }
    }

    @Override
    public void nonRecPreOrderTraversal(Node node) {
        Stack<Node> stack = new Stack<Node>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node p = stack.pop();
            System.out.println(p.toString());
            if (null != p.getRightChild()) {
                stack.push(p.getRightChild());
            }
            if (null != p.getLeftChild()) {
                stack.push(p.getLeftChild());
            }
        }

    }

    @Override
    public void nonRecInOrderTraversal(Node node) {
        Stack<Node> stack = new Stack<Node>();
        Node p = node;
        while (null != p || stack.size() > 0) {
            while (null != p) {
                stack.push(p);
                p = p.getLeftChild();
            }

            if (stack.size() > 0) {
                p = stack.pop();
                System.out.println(p.toString());
                p = p.getRightChild();
            }
        }
    }

    @Override
    public void nonRecPostOrderTraversal(Node node) {
        Stack<Node> stack = new Stack<Node>();
        Node p = node;
        Node pre = null;
        while (null != p || !stack.isEmpty()) {
            while (null != p) {
                stack.push(p);
                p = p.getLeftChild();
            }
            p = stack.peek();
            if (null == p.getRightChild() || p.getRightChild() == pre) {
                p = stack.pop();
                System.out.println(p.toString());
                pre = p;
                p = null;
            } else {
                p = p.getRightChild();
            }
        }
    }
}
