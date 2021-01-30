package homework;

import sun.awt.image.ImageWatched;

import java.util.*;

/**
 * LeetCode链接：
 * https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/
 *
 * 解题思路：
 * 1.递归
 * @see #postorder_1(Node)
 *
 * 2.遍历，以栈的方式维护后序遍历顺序
 * @see #postorder_2(Node)
 * 
 * @author : tanyu
 * create at:  2021-01-28 10:50
 * @description: N叉树的后序遍历
 */
public class Postorder {

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param root
     * @return
     */
    public List<Integer> postorder_2(Node root) {
        LinkedList<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        // 使用双端队列模拟先进后出的顺序
        ArrayDeque<Node> stack = new ArrayDeque<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            // 尾部拿节点
            Node node = stack.pollLast();
            // 每次的节点值从头部加入，保证遍历顺序
            result.addFirst(node.val);
            // 遍历子节点
            for (Node item : node.children) {
                if (item != null) {
                    stack.add(item);
                }
            }
        }
        return result;
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param root
     * @return
     */
    List<Integer> result;
    public List<Integer> postorder_1(Node root) {
        result = new ArrayList<>();
        order(root);
        return result;
    }

    public void order(Node root) {
        if (root == null) {
            return;
        }
        for (Node node : root.children) {
            order(node);
        }
        result.add(root.val);
    }

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
