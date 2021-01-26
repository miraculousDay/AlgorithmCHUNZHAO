package homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * LeetCode链接：
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/
 *
 * 解题思路：
 * 1.递归
 * @see #inorderTraversal_1(TreeNode)
 *
 * 2.遍历，手动维护一个栈，来处理中序遍历的顺序
 * @see #inorderTraversal_2(TreeNode)
 * 
 * @author : tanyu
 * create at:  2021-01-26 15:51
 * @description: 二叉树的中序遍历
 */
public class InorderTraversal {

    public List<Integer> inorderTraversal_2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.empty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            result.add(root.val);
            root  = root.right;
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
    public List<Integer> inorderTraversal_1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        helper(result, root);
        return result;
    }

    public void helper(List<Integer> result, TreeNode root) {
        if (root == null) {
            return;
        }
        // 中序遍历结构，左根右
        helper(result, root.left);
        result.add(root.val);
        helper(result, root.right);
    }

    static public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {
        }
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
