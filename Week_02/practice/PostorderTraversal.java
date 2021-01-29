package practice;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * LeetCode链接：
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 *
 * 解题思路：
 * 1.递归
 * @see #postorderTraversal_1(TreeNode)
 *
 * 2.遍历，使用双端队列维护，该方法前序都可以使用，注意入栈顺序即可
 * @see #postorderTraversal_2(TreeNode)
 *
 * @author : tanyu
 * create at:  2021-01-26 15:51
 * @description: 二叉树的后序遍历
 */
public class PostorderTraversal {

    LinkedList<Integer> result = new LinkedList<>();

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal_1(TreeNode root) {
        if (root != null) {
            helper(root);
        }
        return result;
    }

    public void helper(TreeNode node) {
        if (node == null) {
            return;
        }
        helper(node.left);
        helper(node.right);
        result.add(node.val);
    }

    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * 利用栈处理节点的访问顺序
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal_2(TreeNode root) {
        if (root != null) {
            Deque<TreeNode> stack = new LinkedList<>();
            // 倒序入栈
            stack.addFirst(root);
            while (!stack.isEmpty()) {
                root = stack.pop();
                // 倒序将结果加入结果集
                result.addFirst(root.val);
                // 正常的顺序是： 左-右-根，根的值上一步已经加入结果集
                // 下面则将 将右节点放在栈顶，左节点放在栈尾
                if (root.left != null) {
                    stack.addFirst(root.left);
                }
                if (root.right != null) {
                    stack.addFirst(root.right);
                }
            }
        }
        return result;
    }

    public class TreeNode {
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
