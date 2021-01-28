package homework;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode链接：
 * https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/
 *
 * 解题思路：
 * 1.递归
 * @see #preorder_1(Postorder.Node)
 *
 * 2.遍历，以栈的方式维护后序遍历顺序
 * @see #preorder_2(Postorder.Node)
 *
 * @author : tanyu
 * create at:  2021-01-28 10:50
 * @description: N叉树的前序遍历
 */
public class Preorder {

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param root
     * @return
     */
    public List<Integer> preorder_2(Postorder.Node root) {
        // 前序遍历是跟左右，使用ArrayList正向添加结即可
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        ArrayDeque<Postorder.Node> stack = new ArrayDeque<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            Postorder.Node node = stack.pollLast();
            result.add(node.val);
            // 左节点在list的下标在尾部
            if (node.children != null && node.children.size() > 0) {
                for (int i = node.children.size() - 1; i >=0; i--) {
                    stack.add(node.children.get(i));
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
    public List<Integer> preorder_1(Postorder.Node root) {
        List<Integer> result = new ArrayList<>();
        helper(root, result);
        return result;
    }

    public void helper(Postorder.Node root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        for (Postorder.Node item : root.children) {
            helper(item, result);
        }
    }

}
