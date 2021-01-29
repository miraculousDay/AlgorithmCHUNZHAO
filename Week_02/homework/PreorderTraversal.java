package homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * LeetCode链接：
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 *
 * 解题思路：
 * 1.递归
 * @see #preorderTraversal_1(InorderTraversal.TreeNode)
 *
 * 2.迭代，使用stack维护前序遍历顺序s
 * @see #preorderTraversal_2(InorderTraversal.TreeNode)
 *
 * 3.迭代2,使用双端队列维护，该方法后都可以使用，注意入栈顺序即可
 * @see #preorderTraversal_3(InorderTraversal.TreeNode)
 *
 * @author : tanyu
 * create at:  2021-01-27 16:14
 * @description: 二叉树的前序遍历
 */
public class PreorderTraversal {

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal_3(InorderTraversal.TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<InorderTraversal.TreeNode> stack = new Stack<>();
        // 预先往栈中放入根节点
        stack.push(root);
        while (!stack.empty()) {
            // 获取根节点
            root = stack.pop();
            if (root != null) {
                // 根据栈的先进后出的特性，将左节点后进站
                result.add(root.val);
                stack.push(root.right);
                stack.push(root.left);
            }
        }
        return result;
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * 与中序遍历类似，差别点是将值加入结果的位置。
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal_2(InorderTraversal.TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<InorderTraversal.TreeNode> stack = new Stack<>();
        while (root != null || !stack.empty()) {
            while (root != null) {
                result.add(root.val);
                stack.push(root.left);
                root = root.left;
            }
            root = stack.pop();
            root = root.right;
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
    public List<Integer> preorderTraversal_1(InorderTraversal.TreeNode root) {
        List<Integer> result = new ArrayList<>();
        helper(result, root);
        return result;
    }

    public void helper(List<Integer> result, InorderTraversal.TreeNode root) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        helper(result, root.left);
        helper(result, root.right);
    }
}
